package model;

import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class Cmyk{
    private float cyan;
    private float magenta;
    private float yellow;
    private float black;
}
