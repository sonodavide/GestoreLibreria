package com.sonodavide.gestorelibreria.service;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BookFilterStrategyContext {

    private BookFilterStrategy filter;

    public void setFilter(BookFilterStrategy filter) {
        this.filter = filter;
    }
    public void execute(){
        filter.execute();
    }
}
