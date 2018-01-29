package com.github.jacques917.map.generator.algorithm.fortune;

import com.github.jacques917.map.generator.algorithm.AlgorithmDataHolder;
import com.github.jacques917.map.generator.algorithm.seed.Seed;
import com.github.jacques917.map.generator.events.RenderAlgorithmEvent;
import com.github.jacques917.map.generator.events.SolveFortune;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import javafx.scene.image.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.github.jacques917.map.generator.algorithm.fortune.SweepingEvent.SweepingEventType.SITE;

@Component
public class FortuneSolver {

    @Autowired
    private EventBus eventBus;

    @Autowired
    private FortuneRenderer fortuneRenderer;

    @Autowired
    private AlgorithmDataHolder algorithmDataHolder;

    @PostConstruct
    public void init() {
        eventBus.register(this);
    }

    @Subscribe
    public void handleSolveFortune(SolveFortune solveFortune) {
        fortune(algorithmDataHolder.getSeedList());
    }

    private void fortune(List<Seed> seedList) {
        PriorityQueue<SweepingEvent> eventQueue = new PriorityQueue<>(Comparator.comparing(SweepingEvent::getX));
        seedList.stream()
                .map(seed -> new SweepingEvent(seed.getX(), seed.getY(), SITE))
                .forEach(eventQueue::add);

        Executors.newSingleThreadScheduledExecutor()
                .scheduleAtFixedRate(() -> {
                    updateView(seedList, eventQueue.poll().getX());
                }, 0, 1, TimeUnit.SECONDS);

    }

    private void updateView(List<Seed> seedList, int sweepingLineX) {
        Image image = fortuneRenderer.render(seedList, sweepingLineX);
        eventBus.post(new RenderAlgorithmEvent(image));
    }

}
