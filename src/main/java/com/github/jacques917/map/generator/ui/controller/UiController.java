package com.github.jacques917.map.generator.ui.controller;

import com.github.jacques917.map.generator.test.PingEvent;
import com.github.jacques917.map.generator.test.PongEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import javafx.fxml.FXML;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class UiController {

    @Autowired
    private EventBus eventBus;

    @PostConstruct
    public void init() {
        eventBus.register(this);
    }

    @FXML
    public void testButton() {
        log.info("test button");
        eventBus.post(new PingEvent());
    }

    @Subscribe
    public void pongEventSub(PongEvent pongEvent) {
        log.info("PONG");
    }

}
