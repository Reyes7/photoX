package algorithms;

import config.Algorithms;
import model.Lab;

public class LabAlgorithms implements Algorithms {
    private float xyz[] = null;
    private Lab sliderValues;

    public LabAlgorithms(Lab sliderValues) {
        this.xyz = new float[3];
        this.sliderValues = sliderValues;
    }

    @Override
    public int change(int pixel) {
        float red = (float) getRed(pixel) / 255.0f;
        float green = (float) getGreen(pixel) / 255.0f;
        float blue = (float) getBlue(pixel) / 255.0f;

        computeLab(red, green, blue);

        float lightness = clampFloat(116.0f * xyz[1] - 16.0f + sliderValues.getLightness(), 0.0f, 100.0f);
        float colorA = clampFloat(500.0f * (xyz[0] - xyz[1]) + sliderValues.getColorA(),-127.0f,128.0f);
        float colorB = clampFloat(200.0f * (xyz[1] - xyz[2]) + sliderValues.getColorB(),-127.0f,128.0f);

        return computeXYZ(lightness, colorA, colorB);
    }

    private int computeXYZ(float lightness, float colorA, float colorB) {
        xyz[1] = (lightness + 16.0f) / 116.0f;
        xyz[0] = colorA / 500.0f + xyz[1];
        xyz[2] = xyz[1] - colorB / 200.0f;

        xyz[0] = compareXYZ(xyz[0], 0.008856f);
        xyz[1] = compareXYZ(xyz[1], 0.008856f);
        xyz[2] = compareXYZ(xyz[2], 0.008856f);

        xyz[0] = 95.047f * xyz[0];
        xyz[1] = 100.000f * xyz[1];
        xyz[2] = 108.883f * xyz[2];

        float pom[] = XyzAlgorithms.computeRgb(xyz);

        int red = clampInt((int) pom[0],0,255);
        int green = clampInt((int) pom[1],0,255);
        int blue = clampInt((int) pom[2],0,255);

        return getPixel(red, green, blue);
    }


    private void computeLab(float red, float green, float blue){
        xyz = XyzAlgorithms.computeXyz(red, green, blue);

        xyz[0] = xyz[0] / 95.047f;
        xyz[1] = xyz[1] / 100.000f;
        xyz[2] = xyz[2] / 108.883f;

        xyz[0] = compareLab(xyz[0], 0.008856f);
        xyz[1] = compareLab(xyz[1], 0.008856f);
        xyz[2] = compareLab(xyz[2], 0.008856f);
    }

    private float compareXYZ(float a, float b){
        if (a * a * a > b){
            return a;
        }
        return (a - 0.1379f) / 7.787f;
    }

    private float compareLab(float a, float b){
        if (a > b){
            return (float) fastPower(a, 1.0 / 3.0 );
        }
        return ( 7.787f * a ) + 0.1379f;
    }
}