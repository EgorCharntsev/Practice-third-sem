package ru.kpfu.itis.charntsev.controlwork.bot.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class WeatherUtil {

    private String name;
    private double temperature;

    public WeatherUtil(String name) {
        this.name = name;
    }

    public String getWeather(String name) throws IOException {
        return "Current temperature in " + name + " is " + getTemperature(getCoordinates(name)) + "°C";
    }

    private double[] getCoordinates(String name) throws IOException {
        Matcher matcher = Pattern.compile(" ").matcher(name);
        if (matcher.find()) name = matcher.replaceFirst("+"); // "St Petersburg" convert in "St+Petersburg" for URL

        matcher = Pattern.compile("'").matcher(name);
        if (matcher.find()) name = matcher.replaceFirst("%27"); // for example "Ольгинка" on English will be "Ol'ginka"

        double[] coordinates = new double[2];

        JSONArray jsonArray = getJSON("https://geocoding-api.open-meteo.com/v1/search?name="
                + name + "&count=1&language=en&format=json")
                .getJSONArray("results");
        for (int i = 0; i < jsonArray.length(); i++) {
            coordinates[0]= jsonArray.getJSONObject(i).getDouble("latitude");
            coordinates[1] = jsonArray.getJSONObject(i).getDouble("longitude");
        }

        return coordinates;
    }

    private double getTemperature(double[] coordinates) throws IOException {
        return getJSON("https://api.open-meteo.com/v1/forecast?latitude=" + coordinates[0] +
                "&longitude=" + coordinates[1] + "&hourly=temperature_2m&current_weather=true")
                .getJSONObject("current_weather")
                .getDouble("temperature");
    }

    private JSONObject getJSON(String string) throws IOException, JSONException {
        URL url = new URL(string);
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));

        StringBuilder sb = new StringBuilder();
        String str;
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }

        br.close();
        return new JSONObject(sb.toString());
    }
}
