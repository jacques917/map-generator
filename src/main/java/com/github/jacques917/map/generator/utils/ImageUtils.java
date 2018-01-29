package com.github.jacques917.map.generator.utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageUtils {

    public static BufferedImage prepareEmptyBufferedImage() {
        int width = 800;
        int height = 600;
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        setWhiteBackground(bufferedImage);
        return bufferedImage;
    }

    private static void setWhiteBackground(BufferedImage bufferedImage) {
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
        graphics.dispose();
    }

}
