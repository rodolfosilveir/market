package com.digio.market.adapter.in.rest.response;

import lombok.Builder;
import lombok.Generated;
import lombok.Getter;

@Generated
@Builder
@Getter
public class ErrorResponse {

    private Integer httpStatus;
    private String erroMessage;
    
}
