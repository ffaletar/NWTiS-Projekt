/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.ffaletar.rest.klijenti;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.foi.nwtis.ffaletar.baza.UredajiBaza;
import org.foi.nwtis.ffaletar.helpers.KonfiguracijaHelper;
import org.foi.nwtis.ffaletar.konfiguracije.Konfiguracija;
import org.foi.nwtis.ffaletar.podaci.Lokacija;
import org.foi.nwtis.ffaletar.podaci.MeteoPodaci;
//import org.foi.nwtis.ffaletar.web.podaci.MeteoPrognoza;

/**
 *
 * @author nwtis_1
 */
public class OWMKlijent {

    String apiKey;
    OWMRESTHelper helper;
    Client client;

    public OWMKlijent() {
        this.apiKey = KonfiguracijaHelper.getApiKeyOWM();
        helper = new OWMRESTHelper(KonfiguracijaHelper.getApiKeyOWM());
        client = ClientBuilder.newClient();
    }

    /**
     * Metoda za dohvaćanje trenutnih vremenskih prilika za odabrani latitude i longitude
     * @param latitude
     * @param longitude
     * @return Vraća objekt tipa MeteoPodaci
     */
    public synchronized MeteoPodaci getRealTimeWeather(String latitude, String longitude) {
        WebTarget webResource = client.target(OWMRESTHelper.getOWM_BASE_URI())
                .path(OWMRESTHelper.getOWM_Current_Path());
        webResource = webResource.queryParam("lat", latitude);
        webResource = webResource.queryParam("lon", longitude);
        webResource = webResource.queryParam("lang", "hr");
        webResource = webResource.queryParam("units", "metric");
        webResource = webResource.queryParam("APIKEY", apiKey);

        String odgovor = webResource.request(MediaType.APPLICATION_JSON).get(String.class);
        try {
            JsonReader reader = Json.createReader(new StringReader(odgovor));

            JsonObject jo = reader.readObject();

            MeteoPodaci mp = new MeteoPodaci();
            mp.setSunRise(new Date(jo.getJsonObject("sys").getJsonNumber("sunrise").bigDecimalValue().longValue() * 1000));
            mp.setSunSet(new Date(jo.getJsonObject("sys").getJsonNumber("sunset").bigDecimalValue().longValue() * 1000));

            mp.setTemperatureValue(new Double(jo.getJsonObject("main").getJsonNumber("temp").doubleValue()).floatValue());
            mp.setTemperatureMin(new Double(jo.getJsonObject("main").getJsonNumber("temp_min").doubleValue()).floatValue());
            mp.setTemperatureMax(new Double(jo.getJsonObject("main").getJsonNumber("temp_max").doubleValue()).floatValue());
            mp.setTemperatureUnit("celsius");

            mp.setHumidityValue(new Double(jo.getJsonObject("main").getJsonNumber("humidity").doubleValue()).floatValue());
            mp.setHumidityUnit("%");

            mp.setPressureValue(new Double(jo.getJsonObject("main").getJsonNumber("pressure").doubleValue()).floatValue());
            mp.setPressureUnit("hPa");

            mp.setWindSpeedValue(new Double(jo.getJsonObject("wind").getJsonNumber("speed").doubleValue()).floatValue());
            mp.setWindSpeedName("");

            mp.setWindDirectionValue(new Double(jo.getJsonObject("wind").getJsonNumber("deg").doubleValue()).floatValue());
            mp.setWindDirectionCode("");
            mp.setWindDirectionName("");

            mp.setCloudsValue(jo.getJsonObject("clouds").getInt("all"));
            mp.setCloudsName(jo.getJsonArray("weather").getJsonObject(0).getString("description"));
            mp.setPrecipitationMode("");

            mp.setWeatherNumber(jo.getJsonArray("weather").getJsonObject(0).getInt("id"));
            mp.setWeatherValue(jo.getJsonArray("weather").getJsonObject(0).getString("description"));
            mp.setWeatherIcon(jo.getJsonArray("weather").getJsonObject(0).getString("icon"));

            mp.setLastUpdate(new Date(jo.getJsonNumber("dt").bigDecimalValue().longValue() * 1000));
            return mp;

        } catch (Exception ex) {
            Logger.getLogger(OWMKlijent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public synchronized MeteoPodaci getRealTimeWeatherById(int idUredaja){
        
        UredajiBaza uredajiBaza = new UredajiBaza();
        Lokacija lokacija = uredajiBaza.dohvatiLatitudeLongitude(idUredaja);
        
        MeteoPodaci meteoPodaci = getRealTimeWeather(lokacija.getLatitude(), lokacija.getLongitude());
      
        return meteoPodaci;
    } 
    
    /**
     * Metoda za dohvaćanje vremenskih prilika za zadnjih  dana za odabrani latitude i longitude
     * @param id
     * @param latitude
     * @param longitude
     * @return 
     */
//    public MeteoPrognoza[] getWeatherForecast(int id, String latitude, String longitude) {
//        int brojPrognoza = 40;
//
//        ArrayList<MeteoPrognoza> meteoPrognoze = new ArrayList<>();
//
//        Target webResource = client.target(OWMRESTHelper.getOWM_BASE_URI())
//                .path(OWMRESTHelper.getOWM_Forecast_Path());
//        webResource = webResource.queryParam("lat", latitude);
//        webResource = webResource.queryParam("lon", longitude);
//        webResource = webResource.queryParam("lang", "hr");
//        webResource = webResource.queryParam("units", "metric");
//        webResource = webResource.queryParam("APIKEY", apiKey);
//
//        String odgovor = webResource.request(MediaType.APPLICATION_JSON).get(String.class);
//
//        JsonReader reader = Json.createReader(new StringReader(odgovor));
//        JsonObject jo = reader.readObject();
//        JsonArray ja = (JsonArray) jo.get("list");
//
//        MeteoPrognoza[] mp = new MeteoPrognoza[ja.size()];
//
//        for (int i = 0; i < ja.size(); i++) {
//
//            MeteoPodaci meteoPodaci = new MeteoPodaci();
//            meteoPodaci.setTemperatureValue(new Double(ja.getJsonObject(0).getJsonObject("main").getJsonNumber("temp").doubleValue()).floatValue());
//            meteoPodaci.setTemperatureMax(new Double(ja.getJsonObject(0).getJsonObject("main").getJsonNumber("temp_max").doubleValue()).floatValue());
//            meteoPodaci.setTemperatureMin(new Double(ja.getJsonObject(0).getJsonObject("main").getJsonNumber("temp_min").doubleValue()).floatValue());
//            meteoPodaci.setTemperatureUnit("celsius");
//            
//            meteoPodaci.setPressureValue(new Double(ja.getJsonObject(0).getJsonObject("main").getJsonNumber("pressure").doubleValue()).floatValue());
//            meteoPodaci.setPressureUnit("hPa");
//            
//            meteoPodaci.setHumidityValue(new Double(ja.getJsonObject(0).getJsonObject("main").getJsonNumber("humidity").doubleValue()).floatValue());
//            meteoPodaci.setHumidityUnit("%");
//            
//            meteoPodaci.setWindSpeedValue(new Double(ja.getJsonObject(i).getJsonObject("wind").getJsonNumber("speed").doubleValue()).floatValue());
//            meteoPodaci.setWindSpeedName("");
//            meteoPodaci.setWindDirectionValue(new Double(ja.getJsonObject(i).getJsonObject("wind").getJsonNumber("deg").doubleValue()).floatValue());
//            
//            meteoPodaci.setWeatherValue(ja.getJsonObject(i).getJsonArray("weather").getJsonObject(0).getString("description"));
//            meteoPodaci.setWeatherNumber(ja.getJsonObject(i).getJsonArray("weather").getJsonObject(0).getInt("id"));
//            meteoPodaci.setWeatherIcon(ja.getJsonObject(i).getJsonArray("weather").getJsonObject(0).getString("icon"));
//
//            
//            meteoPodaci.setLastUpdate(new Date(ja.getJsonObject(i).getJsonNumber("dt").bigDecimalValue().longValue() * 1000));
//
//            mp[i] = new MeteoPrognoza(id,((int)i/8), meteoPodaci);
//        }
//
//
//        return mp;
//    }
    
    
}
