package com.github.jacques917.map.generator.ui.controller;

import com.github.jacques917.map.generator.events.GenerateSeed;
import com.github.jacques917.map.generator.events.RenderAlgorithmEvent;
import com.github.jacques917.map.generator.events.SolveFortune;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static java.util.Optional.ofNullable;

@Slf4j
@Component
public class UiController {

    @Autowired
    private EventBus eventBus;
    @FXML
    private ImageView algorithmView;

    @PostConstruct
    public void init() {
        eventBus.register(this);
    }

    @Subscribe
    public void handleRenderAlgorithmEvent(RenderAlgorithmEvent event) {
        Platform.runLater(() -> ofNullable(event)
                .map(RenderAlgorithmEvent::getImage)
                .ifPresent(algorithmView::setImage));
    }

    @FXML
    public void handleGenerateSeedButton() {
        eventBus.post(new GenerateSeed());
    }

    @FXML
    public void handleStartFortune() {
        eventBus.post(new SolveFortune());
    }

}
