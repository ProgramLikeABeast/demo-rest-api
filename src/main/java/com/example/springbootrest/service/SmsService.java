package com.example.springbootrest.service;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

@Service
public class SmsService {

    private static final String SINCH_URL = "https://sms.api.sinch.com/xms/v1/8bd12a9c4bff42faa2f8e11772306ceb/batches";
    private static final String API_KEY = "e714dda8-c033-4c62-b84c-79636cc3b71f";
    private static final String API_SECRET = "5sCGPxsRMS53sL9eaOSH4g-K4B";
    // Sinch Bearer token (replace with your actual token)
    private static final String BEARER_TOKEN = "065e3d30d9b840d4930f02546c3fa6e2";
    private static final String fromNumber = "12066578297";

    private final ObjectMapper objectMapper = new ObjectMapper();

    public String sendSms(String toNumber, String message) {
        StringBuilder response = new StringBuilder();
        int responseCode = 0;
        try {
            // Set up the request URL and connection
            URL url = new URL(SINCH_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + BEARER_TOKEN);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // Create a Map for the JSON payload
            Map<String, Object> payload = new HashMap<>();
            payload.put("from", fromNumber);
            payload.put("to", Collections.singletonList(toNumber));
            payload.put("body", message);

            // Convert Map to JSON string
            String jsonInputString = objectMapper.writeValueAsString(payload);

            // Write the JSON payload to the output stream
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Get the response code
            responseCode = conn.getResponseCode();
            
            // Read the response body
            InputStream inputStream;
            if (responseCode >= 200 && responseCode < 400) {
                inputStream = conn.getInputStream();
            } else {
                inputStream = conn.getErrorStream();
            }

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(inputStream, "utf-8"))) {
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
            }

            // Display the response information
            System.out.println("Response Code: " + responseCode);
            System.out.println("Response Body: " + response.toString());

        } catch (Exception e) {
            System.out.println("Error sending SMS: " + e.getMessage());
            e.printStackTrace();
        }

        if (responseCode == 200 || responseCode == 201) {
            return "SMS sent successfully! Response Code: " + responseCode + ", Response Body: " + response.toString();
        } else {
            return "Failed to send SMS. Response Code: " + responseCode + ", Response Body: " + response.toString();
        }
    }

    // Helper method to base64 encode API credentials
    private String encodeBase64(String input) {
        return java.util.Base64.getEncoder().encodeToString(input.getBytes());
    }
}
