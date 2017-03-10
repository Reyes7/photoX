package basic_changes;

import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter(AccessLevel.PUBLIC)
public class BasicChangesModel {
    private int contrast;
    private int brightness;
    private float gamma;
}
