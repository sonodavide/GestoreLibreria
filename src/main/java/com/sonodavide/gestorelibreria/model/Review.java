package com.sonodavide.gestorelibreria.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Review {

    private int stars;
    private String comment;

    public Review(int stars) {
        if (stars < 0 || stars > 5) throw new IllegalArgumentException();
        this.stars = stars;
        this.comment = "";
    }
    public Review(int stars, String comment) {
        this(stars);
        this.comment = comment;
    }
}
