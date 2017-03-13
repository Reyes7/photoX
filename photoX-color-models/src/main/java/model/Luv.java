package model;

import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter(AccessLevel.PUBLIC)
public class Luv {
    private float lightness;
    private float colorU;
    private float colorV;
}
