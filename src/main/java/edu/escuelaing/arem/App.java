package edu.escuelaing.arem;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        get("/", (req, res) -> "Working! ma nigga");
        get("/hello", (req, res) -> "Hello World!");
    }
    
}
