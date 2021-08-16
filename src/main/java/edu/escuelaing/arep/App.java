package edu.escuelaing.arep;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        get("/", (req, res) -> "Working! ma nigga");
        get("/hello", (req, res) -> "Hello World!");
    }
    
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567; //returns default port if heroku-port isn't set(i.e. on localhost)
    }
}
