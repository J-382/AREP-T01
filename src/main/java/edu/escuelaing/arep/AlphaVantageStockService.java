package edu.escuelaing.arep;

public class AlphaVantageStockService extends HttpStockService{
    public AlphaVantageStockService(){}

    @Override
    public String getUrl(String timeSeries, String symbol) {
        String URL = "https://www.alphavantage.co/query?function=TIME_SERIES_"+timeSeries+"&symbol="+symbol;
        if(timeSeries.equals("INTRADAY")) URL += "&interval=5min";
        return URL + "&apikey=Q1QZFVJQ21K7C6XM";
    }
    
}
