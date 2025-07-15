package com.murathnakts.controller.impl;

import com.murathnakts.entity.RootEntity;

public class BaseController {

    public <E> RootEntity<E> success(E data) {
        return RootEntity.success(data);
    }

    public <E> RootEntity<E> error(String errorMessage) {
        return RootEntity.error(errorMessage);
    }
}
