package reyes.app;

import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class Cmyk {
    private double cyan;
    private double magenta;
    private double yellow;
    private double black;
}
