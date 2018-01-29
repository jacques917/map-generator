package com.github.jacques917.map.generator.algorithm;

import com.github.jacques917.map.generator.algorithm.seed.Seed;
import com.github.jacques917.map.generator.events.SeedGenerated;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

import static java.util.Collections.emptyList;

@Component
public class AlgorithmDataHolder {

    @Getter
    private List<Seed> seedList = emptyList();

    @Autowired
    private EventBus eventBus;

    @PostConstruct
    public void init() {
        eventBus.register(this);
    }

    @Subscribe
    public void handleSeedGenerated(SeedGenerated seedGenerated) {
        this.seedList = seedGenerated.getSeedList();
    }

}
