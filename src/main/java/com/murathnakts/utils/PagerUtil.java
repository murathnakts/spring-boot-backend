package com.murathnakts.utils;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@UtilityClass
public class PagerUtil {

    public boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    public Pageable toPageable(PageableRequest pageableRequest) {
        if (!isNullOrEmpty(pageableRequest.getColumnName())) {
            Sort sort = pageableRequest.getAsc() == true ?
                    Sort.by(Sort.Direction.ASC, pageableRequest.getColumnName())
                    : Sort.by(Sort.Direction.DESC, pageableRequest.getColumnName());
            return PageRequest.of(pageableRequest.getPageNumber(), pageableRequest.getPageSize(), sort);
        }
        return PageRequest.of(pageableRequest.getPageNumber(), pageableRequest.getPageSize());
    }

    public <E> PageableResponse<E> toPageableResponse(Page<?> page, List<E> content) {
        PageableResponse<E> pageableResponse = new PageableResponse<>();
        pageableResponse.setContent(content);
        pageableResponse.setPageNumber(page.getPageable().getPageNumber());
        pageableResponse.setPageSize(page.getPageable().getPageSize());
        pageableResponse.setTotalElements(page.getTotalElements());
        return pageableResponse;
    }
}
