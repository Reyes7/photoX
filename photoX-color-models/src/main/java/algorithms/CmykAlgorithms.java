package algorithms;

import config.Algorithms;
import model.Cmyk;

public class CmykAlgorithms implements Algorithms {

    private Cmyk sliderValues;

    public CmykAlgorithms(Cmyk sliderValues) {
        this.sliderValues = sliderValues;
    }

    @Override
    public int change(int pixel) {
        float cyan = 1.0f - getRed(pixel) / 255.0f;
        float magenta = 1.0f - getGreen(pixel) / 255.0f;
        float yellow = 1.0f - getBlue(pixel) / 255.0f;
        float black = Math.min(cyan,Math.min(magenta,yellow));

        return computeCMYK(cyan, magenta, yellow, black);
    }

    private int computeCMYK(float cyan, float magenta, float yellow, float black) {
        float cyanPom = (cyan - black) / (1.0f - black);
        float magentaPom = (magenta - black) / (1.0f - black);
        float yellowPom = (yellow - black) / (1.0f - black);

        cyanPom = clampFloat(cyanPom + sliderValues.getCyan(), 0.0f, 1.0f);
        magentaPom = clampFloat(magentaPom + sliderValues.getMagenta(), 0.0f, 1.0f);
        yellowPom = clampFloat(yellowPom + sliderValues.getYellow(), 0.0f, 1.0f);

        black = clampFloat(black + sliderValues.getBlack(), 0.0f, 1.0f);

        cyan = cyanPom * (1.0f - black) + black;
        magenta = magentaPom * (1.0f - black) + black;
        yellow = yellowPom * (1.0f - black) + black;

        int red  = (int) ((1.0f - cyan) * 255.0f);
        int green = (int) ((1.0f - magenta) * 255.0f);
        int blue = (int) ((1.0f - yellow) * 255.0f);

        return getPixel(red,green,blue);
    }
}