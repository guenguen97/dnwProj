//package com.doNotWorry;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.HttpClients;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.io.InputStream;
//
//@Service
//public class GeoLocationService {
//
//    private final ObjectMapper objectMapper;
//
//    public GeoLocationService(ObjectMapper objectMapper) {
//        this.objectMapper = objectMapper;
//    }
//
//    public GeoLocation getGeoLocation() throws IOException {
//        HttpClient httpClient = HttpClients.createDefault();
//        HttpGet request = new HttpGet("https://ipapi.co/json");
//
//        HttpResponse response = httpClient.execute(request);
//        InputStream responseStream = response.getEntity().getContent();
//
//        JsonNode jsonNode = objectMapper.readTree(responseStream);
//
//        double latitude = jsonNode.get("latitude").asDouble();
//        double longitude = jsonNode.get("longitude").asDouble();
//
//        return new GeoLocation(latitude, longitude);
//    }
//}
