package dev.sky.utils.render;

import dev.sky.utils.misc.MathUtil;
import java.awt.Color;
import java.awt.image.BufferedImage;
import org.lwjgl.opengl.GL11;

public enum ColorUtil {
    ;

    public static Color tripleColor(int rgbValue) {
        return tripleColor(rgbValue, 1.0f);
    }

    public static Color tripleColor(int rgbValue, float alpha) {
        alpha = Math.min(1.0f, Math.max(0.0f, alpha));
        return new Color(rgbValue, rgbValue, rgbValue, (int)(255.0f * alpha));
    }

    public static Color[] getAnalogousColor(Color color) {
        Color[] colors = new Color[2];
        float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        float degree = 0.083333336f;
        float newHueAdded = hsb[0] + degree;
        colors[0] = new Color(Color.HSBtoRGB(newHueAdded, hsb[1], hsb[2]));
        float newHueSubtracted = hsb[0] - degree;
        colors[1] = new Color(Color.HSBtoRGB(newHueSubtracted, hsb[1], hsb[2]));
        return colors;
    }

    public static Color getRandomColor() {
        return new Color(Color.HSBtoRGB((float)Math.random(), (float)(0.5 + Math.random() / 2.0), (float)(0.5 + Math.random() / 2.0)));
    }

    public static Color hslToRGB(float[] hsl) {
        float red;
        float green;
        float blue;
        if (hsl[1] == 0.0f) {
            blue = 1.0f;
            green = 1.0f;
            red = 1.0f;
        } else {
            float q = hsl[2] < 0.5 ? hsl[2] * (1.0f + hsl[1]) : hsl[2] + hsl[1] - hsl[2] * hsl[1];
            float p = 2.0f * hsl[2] - q;
            red = hueToRGB(p, q, hsl[0] + 0.33333334f);
            green = hueToRGB(p, q, hsl[0]);
            blue = hueToRGB(p, q, hsl[0] - 0.33333334f);
        }
        return new Color((int)(red *= 255.0f), (int)(green *= 255.0f), (int)(blue *= 255.0f));
    }

    public static float hueToRGB(float p, float q, float t) {
        float newT = t;
        if (newT < 0.0f) {
            newT += 1.0f;
        }
        if (newT > 1.0f) {
            newT -= 1.0f;
        }
        if (newT < 0.16666667f) {
            return p + (q - p) * 6.0f * newT;
        }
        if (newT < 0.5f) {
            return q;
        }
        if (newT < 0.6666667f) {
            return p + (q - p) * (0.6666667f - newT) * 6.0f;
        }
        return p;
    }

    public static float[] rgbToHSL(Color rgb) {
        float red = rgb.getRed() / 255.0f;
        float green = rgb.getGreen() / 255.0f;
        float blue = rgb.getBlue() / 255.0f;
        float max = Math.max(Math.max(red, green), blue);
        float min = Math.min(Math.min(red, green), blue);
        float c = (max + min) / 2.0f;
        float[] hsl = {c, c, c};
        if (max == min) {
            hsl[1] = 0.0f;
            hsl[0] = 0.0f;
        } else {
            float d = max - min;
            float f = hsl[1] = hsl[2] > 0.5 ? d / (2.0f - max - min) : d / (max + min);
            if (max == red) {
                hsl[0] = (green - blue) / d + (green < blue ? 6 : 0);
            } else if (max == blue) {
                hsl[0] = (blue - red) / d + 2.0f;
            } else if (max == green) {
                hsl[0] = (red - green) / d + 4.0f;
            }
            hsl[0] = hsl[0] / 6.0f;
        }
        return hsl;
    }

    public static Color imitateTransparency(Color backgroundColor, Color accentColor, float percentage) {
        return new Color(interpolateColor(backgroundColor, accentColor, 255.0f * percentage / 255.0f));
    }

    public static int applyOpacity(int color, float opacity) {
        Color old = new Color(color);
        return applyOpacity(old, opacity).getRGB();
    }

    public static Color applyOpacity(Color color, float opacity) {
        opacity = Math.min(1.0f, Math.max(0.0f, opacity));
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), (int)(color.getAlpha() * opacity));
    }

    public static Color darker(Color color, float FACTOR) {
        return new Color(Math.max((int)(color.getRed() * FACTOR), 0), Math.max((int)(color.getGreen() * FACTOR), 0), Math.max((int)(color.getBlue() * FACTOR), 0), color.getAlpha());
    }

    public static Color brighter(Color color, float FACTOR) {
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        int alpha = color.getAlpha();
        int i = (int)(1.0 / (1.0 - FACTOR));
        if (r == 0 && g == 0 && b == 0) {
            return new Color(i, i, i, alpha);
        }
        if (r > 0 && r < i) {
            r = i;
        }
        if (g > 0 && g < i) {
            g = i;
        }
        if (b > 0 && b < i) {
            b = i;
        }
        return new Color(Math.min((int)(r / FACTOR), 255), Math.min((int)(g / FACTOR), 255), Math.min((int)(b / FACTOR), 255), alpha);
    }

    public static Color averageColor(BufferedImage bi, int width, int height, int pixelStep) {
        int[] color = new int[3];
        for (int x = 0; x < width; x += pixelStep) {
            for (int y = 0; y < height; y += pixelStep) {
                Color pixel = new Color(bi.getRGB(x, y));
                color[0] = color[0] + pixel.getRed();
                color[1] = color[1] + pixel.getGreen();
                color[2] = color[2] + pixel.getBlue();
            }
        }
        int num = width * height / (pixelStep * pixelStep);
        return new Color(color[0] / num, color[1] / num, color[2] / num);
    }

    public static Color rainbow(int speed, int index, float saturation, float brightness, float opacity) {
        int angle = (int)((System.currentTimeMillis() / speed + index) % 360L);
        float hue = angle / 360.0f;
        Color color = new Color(Color.HSBtoRGB(hue, saturation, brightness));
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), Math.max(0, Math.min(255, (int)(opacity * 255.0f))));
    }

    public static Color interpolateColorsBackAndForth(int speed, int index, Color start, Color end, boolean trueColor) {
        int angle = (int)((System.currentTimeMillis() / speed + index) % 360L);
        angle = (angle >= 180 ? 360 - angle : angle) * 2;
        return trueColor ? interpolateColorHue(start, end, angle / 360.0f) : interpolateColorC(start, end, angle / 360.0f);
    }

    public static int interpolateColor(Color color1, Color color2, float amount) {
        amount = Math.min(1.0f, Math.max(0.0f, amount));
        return interpolateColorC(color1, color2, amount).getRGB();
    }

    public static int interpolateColor(int color1, int color2, float amount) {
        amount = Math.min(1.0f, Math.max(0.0f, amount));
        Color cColor1 = new Color(color1);
        Color cColor2 = new Color(color2);
        return interpolateColorC(cColor1, cColor2, amount).getRGB();
    }

    public static Color interpolateColorC(Color color1, Color color2, float amount) {
        amount = Math.min(1.0f, Math.max(0.0f, amount));
        return new Color(MathUtil.interpolateInt(color1.getRed(), color2.getRed(), amount), MathUtil.interpolateInt(color1.getGreen(), color2.getGreen(), amount), MathUtil.interpolateInt(color1.getBlue(), color2.getBlue(), amount), MathUtil.interpolateInt(color1.getAlpha(), color2.getAlpha(), amount));
    }

    public static Color interpolateColorHue(Color color1, Color color2, float amount) {
        amount = Math.min(1.0f, Math.max(0.0f, amount));
        float[] color1HSB = Color.RGBtoHSB(color1.getRed(), color1.getGreen(), color1.getBlue(), null);
        float[] color2HSB = Color.RGBtoHSB(color2.getRed(), color2.getGreen(), color2.getBlue(), null);
        Color resultColor = Color.getHSBColor(MathUtil.interpolateFloat(color1HSB[0], color2HSB[0], amount), MathUtil.interpolateFloat(color1HSB[1], color2HSB[1], amount), MathUtil.interpolateFloat(color1HSB[2], color2HSB[2], amount));
        return applyOpacity(resultColor, MathUtil.interpolateInt(color1.getAlpha(), color2.getAlpha(), amount) / 255.0f);
    }

    public static Color fade(int speed, int index, Color color, float alpha) {
        float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        int angle = (int)((System.currentTimeMillis() / speed + index) % 360L);
        angle = (angle > 180 ? 360 - angle : angle) + 180;
        Color colorHSB = new Color(Color.HSBtoRGB(hsb[0], hsb[1], angle / 360.0f));
        return new Color(colorHSB.getRed(), colorHSB.getGreen(), colorHSB.getBlue(), Math.max(0, Math.min(255, (int)(alpha * 255.0f))));
    }

    private static float getAnimationEquation(int index, int speed) {
        int angle = (int)((System.currentTimeMillis() / speed + index) % 360L);
        return ((angle > 180 ? 360 - angle : angle) + 180) / 360.0f;
    }

    public static int[] createColorArray(int color) {
        return new int[]{bitChangeColor(color, 16), bitChangeColor(color, 8), bitChangeColor(color, 0), bitChangeColor(color, 24)};
    }

    public static int getOppositeColor(int color) {
        int R = bitChangeColor(color, 0);
        int G = bitChangeColor(color, 8);
        int B = bitChangeColor(color, 16);
        int A = bitChangeColor(color, 24);
        R = 255 - R;
        G = 255 - G;
        B = 255 - B;
        return R + (G << 8) + (B << 16) + (A << 24);
    }

    public static Color getOppositeColor(Color color) {
        return new Color(getOppositeColor(color.getRGB()));
    }

    private static int bitChangeColor(int color, int bitChange) {
        return color >> bitChange & 0xFF;
    }

    static void glColor(Color color) {
        GL11.glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
    }

    public static Color blendColors(float[] fractions, Color[] colors, float progress) {
        int startPoint;
        int[] indices = new int[2];
        for (startPoint = 0; startPoint < fractions.length && fractions[startPoint] <= progress; ++startPoint) {
        }
        if (startPoint >= fractions.length) {
            startPoint = fractions.length - 1;
        }
        indices[0] = startPoint - 1;
        indices[1] = startPoint;
        float[] range = {fractions[indices[0]], fractions[indices[1]]};
        Color[] colorRange = {colors[indices[0]], colors[indices[1]]};
        float max = range[1] - range[0];
        float value = progress - range[0];
        float weight = value / max;
        float r = 1.0f - weight;
        float ir = 1.0f - r;
        float[] rgb1 = new float[3];
        float[] rgb2 = new float[3];
        colorRange[0].getColorComponents(rgb1);
        colorRange[1].getColorComponents(rgb2);
        float red = rgb1[0] * r + rgb2[0] * ir;
        float green = rgb1[1] * r + rgb2[1] * ir;
        float blue = rgb1[2] * r + rgb2[2] * ir;
        if (red < 0.0f) {
            red = 0.0f;
        } else if (red > 255.0f) {
            red = 255.0f;
        }
        if (green < 0.0f) {
            green = 0.0f;
        } else if (green > 255.0f) {
            green = 255.0f;
        }
        if (blue < 0.0f) {
            blue = 0.0f;
        } else if (blue > 255.0f) {
            blue = 255.0f;
        }
        return new Color(red, green, blue);
    }

    public static String getColor(int n) {
        if (n != 1) {
            if (n == 2) {
                return "\u00a7a";
            }
            if (n == 3) {
                return "\u00a73";
            }
            if (n == 4) {
                return "§4";
            }
            if (n >= 5) {
                return "\u00a7e";
            }
        }
        return "\u00a7f";
    }

    public static int getColor(float hueoffset, float saturation, float brightness) {
        float speed = 4500.0f;
        float hue = (System.currentTimeMillis() % 4500L) / 4500.0f;
        return Color.HSBtoRGB(hue - hueoffset / 54.0f, saturation, brightness);
    }
    public static int getRGB(int rgb) {
        return 0xff000000 | rgb;
    }

    public static int getRGB(int r, int g, int b, int a) {
        return (a & 0xFF) << 24 | (r & 0xFF) << 16 | (g & 0xFF) << 8 | b & 0xFF;
    }
    public static int reAlpha(int rgb,int alpha) {
        return getRGB(getRed(rgb),getGreen(rgb),getBlue(rgb),alpha);
    }

    public static int getRed(int rgb) {
        return (rgb >> 16) & 0xFF;
    }

    public static int getGreen(int rgb) {
        return (rgb >> 8) & 0xFF;
    }

    public static int getBlue(int rgb) {
        return rgb & 0xFF;
    }

    public static int getAlpha(int rgb) {
        return (rgb >> 24) & 0xff;
    }
}

