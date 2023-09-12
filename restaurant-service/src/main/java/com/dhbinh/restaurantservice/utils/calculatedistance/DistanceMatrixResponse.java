package com.dhbinh.restaurantservice.onlineorder.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DistanceMatrixResponse {
    private String[] destination_addresses;
    private String[] origin_addresses;
    private Row[] rows;
    private String status;

    // Getters and setters for the above fields

    // Nested classes to represent sub-structures in the response

    @Getter
    @Setter
    public static class Row {
        private Element[] elements;

        // Getters and setters for the elements array

        // Nested class to represent sub-structures in the elements array
        @Getter
        @Setter
        public static class Element {
            private Distance distance;
            private Duration duration;
            private String status;

            // Getters and setters for the distance, duration, and status fields
        }
    }

    @Getter
    @Setter
    public static class Distance {
        private String text;
        private int value;

        // Getters and setters for the text and value fields
    }

    @Getter
    @Setter
    public static class Duration {
        private String text;
        private int value;

        // Getters and setters for the text and value fields
    }
}
