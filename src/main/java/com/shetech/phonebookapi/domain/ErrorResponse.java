package com.shetech.phonebookapi.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@Builder
@ToString
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    /* - fields - */
    private String resource;
    private String type;
    private Date timestamp;
    private String message;
    private String stacktrace;
}
