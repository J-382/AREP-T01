package edu.escuelaing.arep;

import static spark.Spark.port;
import static spark.Spark.get;
import static spark.Spark.after;
import static spark.Spark.staticFiles;

import spark.Filter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import spark.Request;
import spark.Response;
import org.json.JSONObject;

public class App {

    public static void main(String[] args) throws IOException {
        port(getPort());
        staticFiles.location("/");
        get("/", "text/html", ((req, res) -> {res.redirect("page.html"); return null;}));
        get("/consult", "application/json", ((req, res) -> facade(req, res)));
    }
    
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

    private static JSONObject facade(Request req, Response res){
        String symbol = req.queryParams("symbol"), timeSeries = req.queryParams("timeSeries");
        JSONObject response = null;
        try {
            response = CurrentServiceInstance.getInstance().getService().getStockData(timeSeries, symbol);
        } catch (Exception e) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, e);
        }
        return response;
    }
    
}
