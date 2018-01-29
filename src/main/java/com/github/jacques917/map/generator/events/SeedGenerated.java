package com.github.jacques917.map.generator.events;

import com.github.jacques917.map.generator.algorithm.seed.Seed;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SeedGenerated {

    private List<Seed> seedList;

}
