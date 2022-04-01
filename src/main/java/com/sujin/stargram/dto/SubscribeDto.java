package com.sujin.stargram.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@NoArgsConstructor

@Data
public class SubscribeDto {
    private int id;
    private String username;
    private String profileImageUrl;
    private BigInteger subscribeState;
    private BigInteger equalUserState;

    public SubscribeDto(int id, String username,String profileImageUrl,
                        BigInteger subscribeState, BigInteger equalUserState) {
        this.id = id;
        this.username = username;
        this.profileImageUrl = profileImageUrl;
        this.subscribeState = subscribeState;
        this.equalUserState = equalUserState;
    }

}
