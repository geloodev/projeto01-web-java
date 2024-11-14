package com.projeto01_web.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class DiscountDTO implements IDTO {
    private UUID id;
    private int duration;
    private int discountPercentage;

    public DiscountDTO(UUID id, int duration, int discountPercentage) {
        this.id = id;
        this.duration = duration;
        this.discountPercentage = discountPercentage;
    }
}
