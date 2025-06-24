package com.sonodavide.gestorelibreria.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ReadStatus {
    READING("Leggendo"), READ("Letto"), NOT_READ("Non Letto");
    private final String val;
}
