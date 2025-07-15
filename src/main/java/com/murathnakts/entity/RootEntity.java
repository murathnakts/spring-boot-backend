package com.murathnakts.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RootEntity<E> {

    private boolean result;
    private String errorMessage;
    private E data;

    public static <E> RootEntity<E> success(E data) {
        RootEntity<E> rootEntity = new RootEntity<>();
        rootEntity.setResult(true);
        rootEntity.setData(data);
        rootEntity.setErrorMessage(null);
        return rootEntity;
    }

    public static <E> RootEntity<E> error(String errorMessage) {
        RootEntity<E> rootEntity = new RootEntity<>();
        rootEntity.setResult(false);
        rootEntity.setData(null);
        rootEntity.setErrorMessage(errorMessage);
        return rootEntity;
    }
}
