package com.projeto01_web.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class ClientDTO implements IDTO {
    private UUID id;
    private String name;
    private String email;
    private String phone;
    private MembershipDTO membership;
    private DiscountDTO discount;

    public ClientDTO(UUID id, String name, String email, String phone,
            MembershipDTO membership, DiscountDTO discount) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.membership = membership;
        this.discount = discount;
    }

    public ClientDTO(String name, String email, String phone,
            MembershipDTO membership, DiscountDTO discount) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.membership = membership;
        this.discount = discount;
    }
}
