package reyes.app;

import lombok.*;


@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class Hsl {
    private double hue;
    private double saturation;
    private double lightness;
}
