package basic_changes;

import config.Algorithms;

public class BasicsAlgorithms implements Algorithms {
    private BasicChangesModel sliderValues;
    private int[] lut;

    public BasicsAlgorithms(BasicChangesModel sliderValues) {
        this.sliderValues = sliderValues;
        lut = new int[256];
    }

    @Override
    public int change(int pixel) {
        int red = lut[getRed(pixel)];
        int green = lut[getGreen(pixel)];
        int blue = lut[getBlue(pixel)];

        return getPixel(red, green, blue);
    }

    public void computeLUT() {
        int contrast = sliderValues.getContrast();
        int brightness = sliderValues.getBrightness();
        float gamma = sliderValues.getGamma();

        for (int i = 0; i < 256; i++) {
            lut[i] = clampInt(i + brightness, 0, 255);

            if (contrast >= 0) {
                lut[i] = clampInt(127.0f / (127.0f - contrast) * (lut[i] - contrast), 0, 255);
            } else {
                lut[i] = clampInt(((127.0f + contrast) / 127.0f) * lut[i] - contrast, 0, 255);
            }

            float pom = (float) fastPower((lut[i] / 255.0), 1 / clampGamma(gamma));
            lut[i] = clampInt(pom * 255.0f, 0, 255);
        }
    }

    private float clampGamma(float value) {
        if (value == 0.0f) {
            return 1.0f;
        } else if (value < 0.0f) {
            return (10.0f + value) / 10.0f;
        }
        return value;
    }
}
