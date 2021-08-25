package edu.escuelaing.arem;

import edu.escuelaing.arep.*;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.json.JSONObject;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
	public void MustReturnAIntradayJSONURL(){
        String URL = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=fb&interval=5min&apikey=Q1QZFVJQ21K7C6XM";
        assertTrue( URL.equals(CurrentServiceInstance.getInstance().getQueryURL("INTRADAY", "fb")) );
    }

    @Test
    public void MustReturnADailyJSONURL(){
        String URL = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=msft&apikey=Q1QZFVJQ21K7C6XM";
        assertTrue( URL.equals(CurrentServiceInstance.getInstance().getQueryURL("DAILY", "msft")) );
    }

    @Test
    public void MustReturnAWeekJSONURL(){
        String URL = "https://www.alphavantage.co/query?function=TIME_SERIES_WEEKLY&symbol=ibm&apikey=Q1QZFVJQ21K7C6XM";
        assertTrue( URL.equals(CurrentServiceInstance.getInstance().getQueryURL("WEEKLY", "ibm")) );
    }

    @Test
    public void MustReturnAMonthJSONURL(){
        String URL = "https://www.alphavantage.co/query?function=TIME_SERIES_MONTHLY&symbol=aapl&apikey=Q1QZFVJQ21K7C6XM";
        assertTrue( URL.equals(CurrentServiceInstance.getInstance().getQueryURL("MONTHLY", "aapl")) );
    }

    @Test
	public void MustReturnAIntradayJSON(){
        try {
            JSONObject json = CurrentServiceInstance.getInstance().getService().getStockData("INTRADAY", "fb");
            Object series = json.get("Time Series (5min)");
            System.out.println(series);
        } catch (Exception e) {
            fail();
        }
        assertTrue(true);
    }

    @Test
	public void MustReturnADailyJSON(){
        try {
            JSONObject json = CurrentServiceInstance.getInstance().getService().getStockData("DAILY", "msft");
            Object series = json.get("Time Series (Daily)");
            System.out.println(series);
        } catch (Exception e) {
            fail();
        }
        assertTrue(true);
    }

    @Test
	public void MustReturnAWeeklyJSON(){
        try {
            JSONObject json = CurrentServiceInstance.getInstance().getService().getStockData("WEEKLY", "ibm");
            Object series = json.get("Weekly Time Series");
            System.out.println(series);
        } catch (Exception e) {
            fail();
        }
        assertTrue(true);
    }

    @Test
	public void MustReturnAMonthlyJSON(){
        try {
            JSONObject json = CurrentServiceInstance.getInstance().getService().getStockData("WEEKLY", "ibm");
            Object series = json.get("Monthly Time Series");
            System.out.println(series);
        } catch (Exception e) {
            fail();
        }
        assertTrue(true);
    }


}