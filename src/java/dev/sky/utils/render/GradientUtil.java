package dev.sky.utils.render;

import dev.sky.utils.IMinecraft;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

import java.awt.*;

public class GradientUtil extends IMinecraft {

    private static final ShaderUtil gradientMaskShader = new ShaderUtil("gradientMask");
    private static final ShaderUtil gradientShader = new ShaderUtil("gradient");


    public static void drawGradient(float x, float y, float width, float height, float alpha, Color bottomLeft, Color topLeft, Color bottomRight, Color topRight) {
        ScaledResolution sr = new ScaledResolution(mc);

        RenderUtil.setAlphaLimit(0);
        RenderUtil.resetColor();
        GLUtil.startBlend();
        gradientShader.init();
        gradientShader.setUniformf("location", x * sr.getScaleFactor(), (Minecraft.getMinecraft().displayHeight - (height * sr.getScaleFactor())) - (y * sr.getScaleFactor()));
        gradientShader.setUniformf("rectSize", width * sr.getScaleFactor(), height * sr.getScaleFactor());
        // Bottom Left
        gradientShader.setUniformf("color1", bottomLeft.getRed() / 255.0f, bottomLeft.getGreen() / 255.0f, bottomLeft.getBlue() / 255.0f, alpha);
        //Top left
        gradientShader.setUniformf("color2", topLeft.getRed() / 255.0f, topLeft.getGreen() / 255.0f, topLeft.getBlue() / 255.0f, alpha);
        //Bottom Right
        gradientShader.setUniformf("color3", bottomRight.getRed() / 255.0f, bottomRight.getGreen() / 255.0f, bottomRight.getBlue() / 255.0f, alpha);
        //Top Right
        gradientShader.setUniformf("color4", topRight.getRed() / 255.0f, topRight.getGreen() / 255.0f, topRight.getBlue() / 255.0f, alpha);

        //Apply the gradient to whatever is put here
        ShaderUtil.drawQuads(x, y, width, height);

        gradientShader.unload();
        GLUtil.endBlend();
    }

    public static void drawGradient(float x, float y, float width, float height, Color bottomLeft, Color topLeft, Color bottomRight, Color topRight) {
        ScaledResolution sr = new ScaledResolution(mc);

        RenderUtil.resetColor();
        GLUtil.startBlend();
        gradientShader.init();
        gradientShader.setUniformf("location", x * sr.getScaleFactor(), (Minecraft.getMinecraft().displayHeight - (height * sr.getScaleFactor())) - (y * sr.getScaleFactor()));
        gradientShader.setUniformf("rectSize", width * sr.getScaleFactor(), height * sr.getScaleFactor());
        // Bottom Left
        gradientShader.setUniformf("color1", bottomLeft.getRed() / 255.0f, bottomLeft.getGreen() / 255.0f, bottomLeft.getBlue() / 255.0f, bottomLeft.getAlpha() / 255.0f);
        //Top left
        gradientShader.setUniformf("color2", topLeft.getRed() / 255.0f, topLeft.getGreen() / 255.0f, topLeft.getBlue() / 255.0f, topLeft.getAlpha() / 255.0f);
        //Bottom Right
        gradientShader.setUniformf("color3", bottomRight.getRed() / 255.0f, bottomRight.getGreen() / 255.0f, bottomRight.getBlue() / 255.0f, bottomRight.getAlpha() / 255.0f);
        //Top Right
        gradientShader.setUniformf("color4", topRight.getRed() / 255.0f, topRight.getGreen() / 255.0f, topRight.getBlue() / 255.0f, topRight.getAlpha() / 255.0f);

        //Apply the gradient to whatever is put here
        ShaderUtil.drawQuads(x, y, width, height);

        gradientShader.unload();
        GLUtil.endBlend();
    }

    public static void drawGradientLR(float x, float y, float width, float height, float alpha, Color left, Color right) {
        drawGradient(x, y, width, height, alpha, left, left, right, right);
    }

    public static void drawGradientTB(float x, float y, float width, float height, float alpha, Color top, Color bottom) {
        drawGradient(x, y, width, height, alpha, bottom, top, bottom, top);
    }


    public static void applyGradientHorizontal(float x, float y, float width, float height, float alpha, Color left, Color right, Runnable content) {
        applyGradient(x, y, width, height, alpha, left, left, right, right, content);
    }

    public static void applyGradientVertical(float x, float y, float width, float height, float alpha, Color top, Color bottom, Runnable content) {
        applyGradient(x, y, width, height, alpha, bottom, top, bottom, top, content);
    }


    public static void applyGradientCornerRL(float x, float y, float width, float height, float alpha, Color bottomLeft, Color topRight, Runnable content) {
        Color mixedColor = ColorUtil.interpolateColorC(topRight, bottomLeft, 0.5f);
        applyGradient(x, y, width, height, alpha, bottomLeft, mixedColor, mixedColor, topRight, content);
    }

    public static void applyGradientCornerLR(float x, float y, float width, float height, float alpha, Color bottomRight, Color topLeft, Runnable content) {
        Color mixedColor = ColorUtil.interpolateColorC(bottomRight, topLeft, 0.5f);
        applyGradient(x, y, width, height, alpha, mixedColor, topLeft, bottomRight, mixedColor, content);
    }

    public static void applyGradient(float x, float y, float width, float height, float alpha, Color bottomLeft, Color topLeft, Color bottomRight, Color topRight, Runnable content) {
        RenderUtil.resetColor();
        GLUtil.startBlend();
        gradientMaskShader.init();

        ScaledResolution sr = new ScaledResolution(mc);

        gradientMaskShader.setUniformf("location", x * sr.getScaleFactor(), (Minecraft.getMinecraft().displayHeight - (height * sr.getScaleFactor())) - (y * sr.getScaleFactor()));
        gradientMaskShader.setUniformf("rectSize", width * sr.getScaleFactor(), height * sr.getScaleFactor());
        gradientMaskShader.setUniformf("alpha", alpha);
        gradientMaskShader.setUniformi("tex", 0);
        // Bottom Left
        gradientMaskShader.setUniformf("color1", bottomLeft.getRed() / 255.0f, bottomLeft.getGreen() / 255.0f, bottomLeft.getBlue() / 255.0f);
        //Top left
        gradientMaskShader.setUniformf("color2", topLeft.getRed() / 255.0f, topLeft.getGreen() / 255.0f, topLeft.getBlue() / 255.0f);
        //Bottom Right
        gradientMaskShader.setUniformf("color3", bottomRight.getRed() / 255.0f, bottomRight.getGreen() / 255.0f, bottomRight.getBlue() / 255.0f);
        //Top Right
        gradientMaskShader.setUniformf("color4", topRight.getRed() / 255.0f, topRight.getGreen() / 255.0f, topRight.getBlue() / 255.0f);

        //Apply the gradient to whatever is put here
        content.run();

        gradientMaskShader.unload();
        GLUtil.endBlend();
    }


}