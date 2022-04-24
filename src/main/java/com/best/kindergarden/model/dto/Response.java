package com.best.kindergarden.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class Response<T> {
    private String message;
    private  T data;
    private  int code;



    public Response(String message, T data, int code) {
        this.message = message;
        this.data = data;
        this.code = code;
    }

    public Response(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public Response(T data, int code) {
        this.data = data;
        this.code = code;
    }

    public Response(String message, int code) {
        this.message = message;
        this.code = code;
    }
}
