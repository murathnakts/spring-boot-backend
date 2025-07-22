package com.murathnakts.utils;

import lombok.Data;

import java.util.List;

@Data
public class PageableResponse<E> {
    private List<E> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
}
