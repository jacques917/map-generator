package com.github.jacques917.map.generator.algorithm.fortune;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class SweepingEvent {

    public enum SweepingEventType {
        SITE,
        CIRCLE
    }

    private int x;
    private int y;
    private SweepingEventType type;

}
