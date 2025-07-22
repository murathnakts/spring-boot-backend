package com.murathnakts.controller;

import com.murathnakts.entity.RootEntity;
import com.murathnakts.utils.PageableRequest;
import com.murathnakts.utils.PageableResponse;
import com.murathnakts.utils.PagerUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class BaseController {

    public <E> RootEntity<E> success(E data) {
        return RootEntity.success(data);
    }

    public <E> RootEntity<E> error(String errorMessage) {
        return RootEntity.error(errorMessage);
    }

    public Pageable toPageable(PageableRequest pageableRequest) {
        return PagerUtil.toPageable(pageableRequest);
    }

    public <E> PageableResponse<E> toPageableResponse(Page<?> page, List<E> content) {
        return PagerUtil.toPageableResponse(page, content);
    }
}
