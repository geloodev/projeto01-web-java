package com.projeto01_web.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class MembershipDTO implements IDTO {
    private UUID id;
    private String name;
    private Double price;

    public MembershipDTO(UUID id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
