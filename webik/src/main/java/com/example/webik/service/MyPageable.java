package com.example.webik.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;

public class MyPageable implements Pageable {
    private int offset = 0;
    private int limit = 0;
    private final Sort sort;
    private Specification spec;

    public MyPageable(Specification spec, int offset, int limit, Sort sort) {
        this.spec = spec;
        this.offset = offset;
        this.limit = limit;
        this.sort = sort;
    }

    public MyPageable(int offset, int limit, Sort sort) {
        if (offset < 0) {
            throw new IllegalArgumentException("Offset index can't be less than zero!");
        }

        if (limit < 1) {
            throw new IllegalArgumentException("Limit must not be less than one!");
        }
        this.limit = limit;
        this.offset = offset;
        this.sort = sort;
    }

    public MyPageable(int offset, int limit) {
        this(offset, limit, Sort.unsorted());
    }

    public MyPageable(int offset, int limit, Sort.Direction direction, String... properties) {
        this(offset, limit, Sort.by(direction, properties));
    }


    @Override
    public int getPageNumber() {
        return 0;
    }

    @Override
    public int getPageSize() {
        return limit;
    }

    @Override
    public long getOffset() {
        return offset;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public Pageable next() {
        return new MyPageable((int) (getOffset() + getPageSize()), getPageSize(), getSort());
    }

    @Override
    public Pageable previousOrFirst() {
        return hasPrevious() ? previous() : first();
    }

    @Override
    public Pageable first() {
        return new MyPageable(0, getPageSize(), getSort());
    }

    @Override
    public Pageable withPage(int pageNumber) {
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return offset > limit;
    }

    public MyPageable previous() {
        return hasPrevious() ? new MyPageable((int) (getOffset() - getPageSize()), getPageSize(), getSort()) : this;
    }

}
