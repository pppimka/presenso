package com.palii.anna.presenso.solution;

import com.palii.anna.presenso.matrix.calculator.Multiplier;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@AllArgsConstructor
public class SolutionRunner implements Runnable {

    private final int[][] firstMatrix;
    private final int[][] secondMatrix;
    private final Multiplier multiplier;

    @Override
    public void run() {
        long start = System.nanoTime();

        multiplier.multiply(firstMatrix, secondMatrix);

        long end = System.nanoTime();

        log.info(multiplier.getClass() + " solution execution time: " + (end - start) + " nano sec.");
    }
}
