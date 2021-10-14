/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author JoaoGDLima
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(urlGet());
        System.out.println(urlPost());   
        System.out.println(urlGet());
    }

    public static String urlGet() {
        try {
            URL url = new URL("http://localhost:21262/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() != 200) {
                return "HTTP error code : " + conn.getResponseCode();
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output, json = "";
            while ((output = br.readLine()) != null) {
                json += output;
            }
            conn.disconnect();
            return json;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public static String urlPost() {
        try {

            URL url = new URL("http://localhost:21262/add/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            String input = "{\"5\": {\"Nome\":\"Cliente 5\", \"Idade\":\"31\"}"
                         + "}";
            
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() == 400) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output, result = "";
            
            while ((output = br.readLine()) != null) {
                result = result + output;
            }

            conn.disconnect();
            return result;
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
