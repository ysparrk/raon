package com.arch.raon.domain.member.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class CheckActiveResDTO implements Serializable {
    private Boolean active;

    public CheckActiveResDTO() { super(); }

    @Builder
    public CheckActiveResDTO(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "CheckActiveResDTO{" +
                "isActive=" + active +
                '}';
    }
}
