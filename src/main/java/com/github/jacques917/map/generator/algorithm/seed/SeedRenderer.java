package com.github.jacques917.map.generator.algorithm.seed;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

import static com.github.jacques917.map.generator.utils.ImageUtils.prepareEmptyBufferedImage;

@Component
public class SeedRenderer {

    private static final int CIRCLE_RADIUS = 3;

    public Image render(List<Seed> seedList) {
        BufferedImage bufferedImage = prepareEmptyBufferedImage();
        Graphics2D graphics2D = bufferedImage.createGraphics();
        seedList.forEach(seed -> drawSeed(seed, graphics2D));
        graphics2D.dispose();
        return SwingFXUtils.toFXImage(bufferedImage, null);
    }

    private void drawSeed(Seed seed, Graphics2D graphics2D) {
        graphics2D.setPaint(Color.RED);
        graphics2D.fillOval(
                seed.getX() - CIRCLE_RADIUS,
                seed.getY() - CIRCLE_RADIUS,
                2 * CIRCLE_RADIUS,
                2 * CIRCLE_RADIUS);
    }

}
