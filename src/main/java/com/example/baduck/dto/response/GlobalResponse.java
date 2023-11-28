package com.example.baduck.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
public class GlobalResponse {
    private int status;
    private Object data;

    public GlobalResponse(int status, Object data) {
        this.status = status;
        this.data = data;
    }
}
