package com.sonodavide.gestorelibreria.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Pair<T> {
    private T x;
    private T y;
}
