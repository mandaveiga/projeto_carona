package com.carona.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TravelDTO {

    private Long value;

    private Integer maxPassangers;

    private Long driverId;

    private List<Long> passangers;
}
