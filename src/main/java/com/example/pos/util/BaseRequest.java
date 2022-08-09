package com.example.pos.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(
        ignoreUnknown = true
)
public class BaseRequest implements Serializable {
    public BaseRequest() {
    }
}
