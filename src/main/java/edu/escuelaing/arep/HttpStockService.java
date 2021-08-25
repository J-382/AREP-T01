package edu.escuelaing.arep;

import java.net.HttpURLConnection;

import org.json.JSONObject;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.net.URL;

public abstract class HttpStockService {


    private static final String USER_AGENT = "Mozilla/5.0";

    /**
     * Retorna el objeto JSON obtenido de la consulta a la API
     * @param timeSeries Modalidad a consultar
     * @param symbol Llave de la empresa a consultar
     * @return objeto JSON
     * @throws IOException
     */
    public JSONObject getStockData(String timeSeries, String symbol) throws IOException {

        URL obj = new URL(getUrl(timeSeries, symbol));
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        JSONObject json;
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        
        //The following invocation perform the connection implicitly before getting the code
        int responseCode = con.getResponseCode();
        
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
    
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();
            json = new JSONObject(response.toString());

        } else throw new IOException();
        return json;
    }

    /**
     * Retorna el URL generado para la consulta a la API
     * @param timeSeries Modalidad a consultar
     * @param symbol Llave de la empresa a consultar
     * @return Direccion URL
     */
    public abstract String getUrl(String timeSeries, String symbol);
}
