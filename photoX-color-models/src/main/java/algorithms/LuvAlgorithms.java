package algorithms;

import config.Algorithms;
import model.Luv;

public class LuvAlgorithms implements Algorithms {
    private final float COLOR_U_D65;
    private final float COLOR_V_D65;

    private float xyz[] = null;
    private float uPrim, vPrim;
    private Luv sliderValues;

    public LuvAlgorithms(Luv sliderValues) {
        this.sliderValues = sliderValues;

        xyz = new float[3];

        COLOR_U_D65 = ( 4.0f * 95.047f ) / ( 95.047f + 15.0f * 100.000f + 3.0f * 108.883f);
        COLOR_V_D65 = ( 9.0f * 100.000f ) / (95.047f + 15.0f * 100.000f + 3.0f * 108.883f);
    }

    @Override
    public int change(int pixel){
        float red = (float) getRed(pixel) / 255.0f;
        float green = (float) getGreen(pixel) / 255.0f;
        float blue = (float) getBlue(pixel) / 255.0f;

        computeLuv(red, green, blue);

        float lightness = ( 116.0f * xyz[1] ) - 16.0f;
        lightness = clampFloat(lightness + sliderValues.getLightness(), 0.0f, 100.0f);
        float colorU = 13.0f * lightness * ( uPrim - COLOR_U_D65);
        colorU = clampFloat(colorU + sliderValues.getColorU(), -128.0f, 127.0f);
        float colorV = 13.0f * lightness * ( vPrim - COLOR_V_D65);
        colorV = clampFloat(colorV + sliderValues.getColorV(), -128.0f, 127.0f);

        return computeXyz(lightness, colorU, colorV);
    }

    private void computeLuv(float red, float green, float blue){
        xyz = XyzAlgorithms.computeXyz(red, green, blue);

        uPrim = ( 4.0f * xyz[0] ) / ( xyz[0] + 15.0f * xyz[1] + 3.0f * xyz[2] );
        vPrim = ( 9.0f * xyz[1] ) / ( xyz[0] + 15.0f * xyz[1] + 3.0f * xyz[2] );

        xyz[1] = xyz[1] / 100.0f;

        if (xyz[1] > 0.008856f ) {
            xyz[1] = (float) fastPower((double)xyz[1], 1.0 / 3.0);
        } else {
            xyz[1] = ( 7.787f * xyz[1] ) + 0.1379f;
        }
    }

    private int computeXyz(float lightness, float colorU, float colorV){
        float yn, pomYn;

        yn = ( lightness + 16.0f ) / 116.0f;
        pomYn = yn * yn * yn;

        if (pomYn > 0.008856f){
            yn = pomYn;
        } else{
            yn = (yn - 0.1379f) / 7.787f;
        }

        uPrim = colorU / (13.0f * lightness) + COLOR_U_D65;
        vPrim = colorV / (13.0f * lightness) + COLOR_V_D65;

        xyz[1] = yn * 100.0f;
        xyz[0] = -(9.0f * xyz[1] * uPrim) / ((uPrim - 4.0f) * vPrim - uPrim * vPrim);
        xyz[2] = (9.0f * xyz[1] - (15.0f * vPrim * xyz[1]) - (vPrim * xyz[0])) / (3.0f * vPrim);

        float rgb[] = XyzAlgorithms.computeRgb(xyz);

        int red = clampInt(rgb[0], 0, 255);
        int green = clampInt(rgb[1], 0, 255);
        int blue = clampInt(rgb[2], 0, 255);

        return getPixel(red, green, blue);
    }
}