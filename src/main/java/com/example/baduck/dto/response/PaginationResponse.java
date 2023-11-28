package com.example.baduck.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PaginationResponse {
    private long total_count;
    private Object list;

    public PaginationResponse(long total_count, Object list) {
        this.total_count = total_count;
        this.list = list;
    }
}
