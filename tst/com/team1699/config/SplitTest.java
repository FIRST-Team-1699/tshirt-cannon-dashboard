package com.team1699.config;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class SplitTest {

    @Test
    void testLineSplitting(){
        System.out.println(Arrays.toString("x:y".split(":")));
    }
}
