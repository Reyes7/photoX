package config;

public interface Algorithms{

    int change(int pixel);

    default int getPixel(int r, int g, int b) {
        return (r << 16) + (g << 8) + b;
    }

    default int getRed(int pixel) {
        return ((pixel >> 16) & 0xff);
    }

    default int getGreen(int pixel) {
        return ((pixel >> 8) & 0xff);
    }

    default int getBlue(int pixel) {
        return (pixel & 0xff);
    }

    default int clampInt(float value, int min, int max) {
        if (value < min){
            return min;
        }else if(value > max){
            return max;
        }
        return (int) value;
    }

    default double fastPower(double a, double b) {
        long tmp = Double.doubleToLongBits(a);
        long tmp2 = (long) (b * (tmp - 4606921280493453312L)) + 4606921280493453312L;
        return Double.longBitsToDouble(tmp2);
    }
}
