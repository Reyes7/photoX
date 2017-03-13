package algorithms;

import config.Algorithms;
import model.Hsl;

public class HslAlgorithms implements Algorithms {

    private Hsl sliderValues;

    public HslAlgorithms(Hsl sliderValues) {
        this.sliderValues = sliderValues;
    }

    @Override
    public int change(int pixel) {
        float red = getRed(pixel) / 255.0f;
        float green = getGreen(pixel) / 255.0f;
        float blue = getBlue(pixel) / 255.0f;

        float max = Math.max(red, Math.max(green, blue));
        float min = Math.min(red, Math.min(green, blue));
        float average = max - min;

        float lightness = clampFloat((max + min) / 2.0f + sliderValues.getLightness(), 0.0f, 1.0f);
        float hue = sliderValues.getHue() / 360.0f;
        float saturation = sliderValues.getSaturation();

        if (average != 0.0f) {
            if (lightness < 0.5f) {
                saturation = average / (max + min);
            } else {
                saturation = average / (2f - max - min);
            }

            float pomRed, pomGreen, pomBlue;

            pomRed = (((max - red) / 6.0f) + (average / 2.0f)) / average;
            pomGreen = (((max - green) / 6.0f) + (average / 2.0f)) / average;
            pomBlue = (((max - blue) / 6.0f) + (average / 2.0f)) / average;

            if (red == max) {
                hue = pomBlue - pomGreen;
            } else if (green == max) {
                hue = (1.0f / 3.0f) + pomRed - pomBlue;
            } else {
                hue = (2.0f / 3.0f) + pomGreen - pomRed;
            }

            hue = clampV(hue);
            hue = clampHue(hue + sliderValues.getHue()) / 360.0f;
            saturation = clampFloat(saturation + sliderValues.getSaturation(), 0.0f, 1.0f);
        }

        return computeRgb(hue, saturation, lightness);
    }

    private float clampHue(float hue) {
        if (hue > 360.0f) {
            return hue - 360.0f;
        } else if (hue < 0.0f) {
            return hue + 360.0f;
        }
        return hue;
    }

    private int computeRgb(float hue, float saturation, float lightnress) {
        float p, q;
        if (saturation == 0f) {
            int value = (int) (lightnress * 255.0f);
            return getPixel(value, value, value);
        } else {
            if (lightnress < 0.5f) {
                q = lightnress * (1.0f + saturation);
            } else {
                q = (lightnress + saturation) - (saturation * lightnress);
            }

            p = 2.0f * lightnress - q;

            int red = (int) (255.0f * scaleValues(p, q, hue + 1.0f / 3.0f));
            int green = (int) (255.0f * scaleValues(p, q, hue));
            int blue = (int) (255.0f * scaleValues(p, q, hue - 1.0f / 3.0f));

            return getPixel(red, green, blue);
        }
    }

    private float scaleValues(float p, float q, float v) {
        v = clampV(v);

        if ((6.0f * v) < 1.0f) {
            return (p + (q - p) * 6f * v);
        } else if ((2.0f * v) < 1.0f) {
            return q;
        } else if ((3.0f * v) < 2f) {
            return (p + (q - p) * ((2.0f / 3.0f) - v) * 6.0f);
        }
        return p;
    }

    private float clampV(float v) {
        if (v < 0.0f) {
            v += 1.0f;
        } else if (v > 1.0f) {
            v -= 1.0f;
        }
        return v;
    }
}