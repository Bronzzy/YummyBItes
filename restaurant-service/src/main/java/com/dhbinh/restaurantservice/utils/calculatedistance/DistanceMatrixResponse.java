package com.dhbinh.restaurantservice.utils.calculatedistance;

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

    @Getter
    @Setter
    public static class Row {
        private Element[] elements;
        @Getter
        @Setter
        public static class Element {
            private Distance distance;
            private Duration duration;
            private String status;
        }
    }

    @Getter
    @Setter
    public static class Distance {
        private String text;
        private int value;
    }

    @Getter
    @Setter
    public static class Duration {
        private String text;
        private int value;
    }
}
