package edu.escuelaing.arep;

public class CurrentServiceInstance {
    private static CurrentServiceInstance _instance = new CurrentServiceInstance();
    private HttpStockService service;

    private CurrentServiceInstance(){
        service = new AlphaVantageStockService();
    }

    public static CurrentServiceInstance getInstance(){
        return _instance;
    }

    public HttpStockService getService(){
        return service;
    }

    public String getQueryURL(String timeSeries, String symbol){
        return service.getUrl(timeSeries, symbol);
    }

}
