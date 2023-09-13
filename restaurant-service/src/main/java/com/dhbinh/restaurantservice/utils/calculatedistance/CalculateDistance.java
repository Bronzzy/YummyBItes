package com.dhbinh.restaurantservice.utils.calculatedistance;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CalculateDistance {
    @Value("${google.maps.api.key}")
    private static String googleMapsApiKey;

    private static RestTemplate restTemplate = null;

    public CalculateDistance(RestTemplate restTemplate) {
        CalculateDistance.restTemplate = restTemplate;
    }

    public static double calculateDistance(String origin, String destination) {
        String url = "https://maps.googleapis.com/maps/api/distancematrix/json"
                + "?origins=" + origin
                + "&destinations=" + destination
                + "&key=" + googleMapsApiKey;

        DistanceMatrixResponse response = restTemplate.getForObject(url, DistanceMatrixResponse.class);

        if (response != null && response.getRows().length > 0) {
            return response.getRows()[0].getElements()[0].getDistance().getValue();
        }
        return -1.0;
    }
}
