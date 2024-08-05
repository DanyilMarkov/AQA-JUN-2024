package org.prog.dto;

import lombok.Data;

@Data
public class LocationDTO {
    private String country;
    private String state;
    private String city;
    private StreetDTO street;
    private String postcode;
    private CoordinatesDTO coordinates;
    private TimezoneDTO timezone;
}
