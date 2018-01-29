package com.github.jacques917.map.generator.algorithm.seed;

import com.github.jacques917.map.generator.events.GenerateSeed;
import com.github.jacques917.map.generator.events.RenderAlgorithmEvent;
import com.github.jacques917.map.generator.events.SeedGenerated;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import io.vavr.collection.Stream;
import javafx.scene.image.Image;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;

import static java.util.stream.Collectors.toList;

@Slf4j
@Component
public class SeedGenerator {

    @Autowired
    private EventBus eventBus;

    @Autowired
    private SeedRenderer seedRenderer;

    @PostConstruct
    public void init() {
        eventBus.register(this);
    }

    @Subscribe
    public void handleGenerateSeed(GenerateSeed generateSeed) {
        List<Seed> seedList = generateSeed();
        updateView(seedList);
        log.info("Seed list: {}", seedList);
        eventBus.post(new SeedGenerated(seedList));
    }

    private List<Seed> generateSeed() {
        List<Integer> xValues = new Random()
                .ints(8, 10, 790)
                .boxed()
                .collect(toList());
        List<Integer> yValues = new Random()
                .ints(8, 10, 590)
                .boxed()
                .collect(toList());
        return Stream.ofAll(xValues)
                .zip(yValues)
                .map(coordinates -> new Seed(coordinates._1, coordinates._2))
                .asJava();
    }

    private void updateView(List<Seed> seedList) {
        Image image = seedRenderer.render(seedList);
        eventBus.post(new RenderAlgorithmEvent(image));
    }

}
