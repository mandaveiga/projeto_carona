package com.carona.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TravelDTO {

    private Long value;

    private Integer maxPassangers;

    private Long driverId;

    private List<Long> passangers;
}
