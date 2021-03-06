package com.dmtaiwan.alexander.rxandroidtest.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander on 4/12/2016.
 */
public class AqStationParser {
    private static final String SITE_MAME = "SiteName";
    private static final String COUNTY = "County";
    private static final String PM25 = "PM2.5";
    private static final String PUBLISH_TIME = "PublishTime";
    private static final String WIND_SPEED = "WindSpeed";
    private static final String WIND_DIRECTION = "WindDirec";

    private static final DecimalFormat mDecimalFormat = new DecimalFormat("0.#");

    public static List<AQStation> parse(String json) throws JSONException {
        List<AQStation> aqStations = new ArrayList<>();

        JSONArray jsonArray = new JSONArray(json);

        if (jsonArray.length() > 0) {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonStation = jsonArray.getJSONObject(i);
                AQStation aqStation = new AQStation();

                aqStation.setSiteNumber(i);

                if (!jsonStation.isNull(SITE_MAME)) {
                    aqStation.setSiteName(jsonStation.getString(SITE_MAME));
                }

                if (!jsonStation.isNull(COUNTY)) {
                    aqStation.setCounty(jsonStation.getString(COUNTY));
                }

                if (!jsonStation.isNull(PM25)) {
                    aqStation.setPM25(jsonStation.getString(PM25));
                    aqStation.setAQI(aqiCalc(jsonStation.getString(PM25)));
                }

                if (!jsonStation.isNull(PUBLISH_TIME)) {
                    aqStation.setPublishTime(jsonStation.getString(PUBLISH_TIME));
                    aqStation.setFormattedTime(formatTime(jsonStation.getString(PUBLISH_TIME)));
                }

                if (!jsonStation.isNull(WIND_SPEED)) {
                    aqStation.setWindSpeed(jsonStation.getString(WIND_SPEED));
                    aqStation.setFormattedWindSpeed(formatWindSpeed(jsonStation.getString(WIND_SPEED)));
                }

                if (!jsonStation.isNull(WIND_DIRECTION)) {
                    aqStation.setWindDirec(jsonStation.getString(WIND_DIRECTION));
                }

                aqStations.add(aqStation);
            }
        }
        return aqStations;
    }

    private final static String aqiCalc(String pm25String) {
        Double pm25;
        if (pm25String.equals("")) {
            return "?";
        } else {
            pm25 = Double.valueOf(pm25String);
        }
        Double c = Math.floor((10 * pm25) / 10);
        Double AQI = null;
        if (c >= 0 && c < 12.1) {
            AQI = linear(50.0, 0.0, 12.0, 0.0, c);
        } else if (c >= 12.1 && c < 35.5) {
            AQI = linear(100.0, 51.0, 35.4, 12.1, c);
        } else if (c >= 35.5 && c < 55.5) {
            AQI = linear(150.0, 101.0, 55.4, 35.5, c);
        } else if (c >= 55.5 && c < 150.5) {
            AQI = linear(200.0, 151.0, 150.4, 55.5, c);
        } else if (c >= 150.5 && c < 250.5) {
            AQI = linear(300.0, 201.0, 250.4, 150.5, c);
        } else if (c >= 250.5 && c < 350.5) {
            AQI = linear(400.0, 301.0, 350.4, 250.5, c);
        } else if (c >= 350.5 && c < 500.5) {
            AQI = linear(500.0, 401.0, 500.4, 350.5, c);
        } else {
            AQI = -1.0;
        }
        return mDecimalFormat.format(AQI);
    }

    private final static Double linear(Double AQIHigh, Double AQILow, Double concHigh, Double concLow, Double Concentration) {
        Double linear;
        Double a = ((Concentration - concLow) / (concHigh - concLow)) * (AQIHigh - AQILow) + AQILow;
        linear = Double.valueOf(Math.round(a));
        return linear;
    }

    private final static String formatTime(String time) {
        String parsedTime = "";

        for (int i = (time.length() - 5); i < time.length(); i++) {
            parsedTime = parsedTime + time.charAt(i);
        }
        return parsedTime;
    }

    private final static String formatWindSpeed(String windSpeed) {
        if (windSpeed.equals("")) {
            return "0 km/h";
        } else {
            float metersPerSec = Float.valueOf(windSpeed);
            metersPerSec = metersPerSec * (18 / 5);
            String formattedWindSpeed = mDecimalFormat.format(metersPerSec);
            return formattedWindSpeed + " km/h";
        }
    }
}
