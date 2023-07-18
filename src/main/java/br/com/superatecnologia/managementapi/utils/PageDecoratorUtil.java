package br.com.superatecnologia.managementapi.utils;

import org.springframework.data.domain.Page;

import java.util.List;

public class PageDecoratorUtil<T> {

    private final Page<T> page;

    public PageDecoratorUtil(Page<T> page) {
        this.page = page;
    }

    public List<T> getContent() {
        return this.page.getContent();
    }

    public long getTotalElements() {
        return this.page.getTotalElements();
    }

    public int getTotalPages() {
        return this.page.getTotalPages();
    }

    public boolean getLast() {
        return this.page.isLast();
    }

    public boolean getFirst() {
        return this.page.isFirst();
    }

    public int getNumberOfElements() {
        return this.page.getNumberOfElements();
    }

    public int getSize() {
        return this.page.getSize();
    }

    public int getNumber() {
        return (this.page.getNumber() + 1);
    }
}
