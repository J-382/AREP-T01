package edu.escuelaing.arem;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        get("/", (req, res) -> "Working! ma nigga");
        get("/hello", (req, res) -> ""+System.getenv("PORT"));
    }
    public static int getPort() {
        if (System.getenv("PORT") != null) {
            System.out.println(System.getenv("PORT"));
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567; //returns default port if heroku-port isn't set(i.e. on localhost)
    }
}
