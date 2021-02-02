package com.main;

import com.google.gson.Gson;
import com.main.dto.PointResponse;
import com.main.dto.WeatherForecastResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.TreeMap;


public class GetWetherData {

    private static String API = "https://api.weather.gov/points/%s";

    public static void main(String[] args) throws IOException {
        try {


            URL obj = new URL(String.format(API, args[0]));

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(obj.openStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            PointResponse pointResponse = new Gson().fromJson(String.valueOf(new JSONObject(response.toString()).getJSONObject("properties")), PointResponse.class);
            Map<LocalDateTime, WeatherForecastResponse> wetherForcastDataHourly = getWetherForcastDataHourly(pointResponse.getForecast());
            System.out.println("============Weather Forecast============");
            System.out.println(pointResponse);
            System.out.println("DateTime\t\tName\t\t\t\t\t Detailed Forecast");
            for (Map.Entry<LocalDateTime, WeatherForecastResponse> map : wetherForcastDataHourly.entrySet()) {
                System.out.println(map.getKey().format(DateTimeFormatter.ofPattern("hh:mm a")) + "\t\t" + map.getValue().toString());
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("Invalid Argument. Re-run and Please Provide Latitude and Langitude Value in proper format. Format (Latitude,Longitude)");
            System.exit(0);
        }
    }


    public static Map getWetherForcastDataHourly(String url) throws IOException {
        if (!url.isEmpty()) {
            Map<LocalDateTime, WeatherForecastResponse> resp = new TreeMap();
            URL obj = new URL(url);
            BufferedReader in = new BufferedReader(new InputStreamReader(obj.openStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            JSONArray myResponse = new JSONObject(response.toString()).getJSONObject("properties").getJSONArray("periods");
            LocalDateTime timeToCheck = LocalDateTime.now().plusDays(5);
            for (Object obj1 : myResponse) {
                JSONObject jsonLineItems = (JSONObject) obj1;
                WeatherForecastResponse weatherForecastResponse = new Gson().fromJson(obj1.toString(), WeatherForecastResponse.class);
                String endTime = jsonLineItems.getString("startTime");

                LocalDateTime current = timeToCheck.parse(endTime.substring(0, endTime.lastIndexOf('-')));
                if (timeToCheck.isAfter(current)) {
                    resp.put(current, weatherForecastResponse);
                }
            }
            return resp;
        }
        return null;
    }

}

