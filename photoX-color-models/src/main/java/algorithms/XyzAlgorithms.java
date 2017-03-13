package algorithms;

class XyzAlgorithms {

    static float[] computeXyz(float red, float green, float blue) {
        float xyz[] = new float[3];

        float pomRed, pomGreen, pomBlue;
        pomRed = (red + 0.055f) / 1.055f;
        pomGreen = (green + 0.055f) / 1.055f;
        pomBlue = (blue + 0.055f) / 1.055f;

        red = compareChannels(red, 0.04045f, pomRed) * 100f;
        green = compareChannels(green, 0.04045f, pomGreen) * 100f;
        blue = compareChannels(blue, 0.04045f, pomBlue) * 100f;

        xyz[0] = red * 0.4124f + green * 0.3576f + blue * 0.1805f;
        xyz[1] = red * 0.2126f + green * 0.7152f + blue * 0.0722f;
        xyz[2] = red * 0.0193f + green * 0.1192f + blue * 0.9505f;

        return xyz;
    }

    private static float compareChannels(float channelA, float channelB, float value){
        if (channelA > channelB){
            return (float) Math.pow(value, 2.4);
        }else{
            return channelA / 12.92f;
        }
    }

    static float[] computeRgb(float xyz[]){
        float red, green, blue;
        float x, y, z;

        x = xyz[0] / 100.0f;
        y = xyz[1] / 100.0f;
        z = xyz[2] / 100.0f;

        red = compareChannel(x *  3.2406f + y * -1.5372f + z * -0.4986f);
        green = compareChannel(x * -0.9689f + y *  1.8758f + z *  0.0415f);
        blue = compareChannel(x *  0.0557f + y * -0.2040f + z *  1.0570f);

        float[] rgb = new float [3];
        rgb[0] = red * 255.0f;
        rgb[1] = green * 255.0f;
        rgb[2] = blue * 255.0f;

        return rgb;
    }

    private static float compareChannel(float value) {
        if (value > 0.0031308f ) {
            return 1.055f * (float) Math.pow(value,  1.0 / 2.4) - 0.055f;
        }
        return 12.92f * value;
    }
}
