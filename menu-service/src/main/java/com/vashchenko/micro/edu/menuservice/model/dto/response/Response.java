package com.vashchenko.micro.edu.menuservice.model.dto.response;

import lombok.Data;

@Data
public class Response<T> {
    private T data;

    public Response(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
