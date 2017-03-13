package model;

import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class Hsl{
    private float hue;
    private float saturation;
    private float lightness;
}
