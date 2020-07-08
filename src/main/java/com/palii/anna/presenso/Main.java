package com.palii.anna.presenso;

import com.palii.anna.presenso.matrix.calculator.ParallelMultiplier;
import com.palii.anna.presenso.matrix.calculator.SequentialMultiplier;
import com.palii.anna.presenso.solution.SolutionRunner;

import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        if (args == null || args.length != 1) {
            throw new IllegalArgumentException("Wrong arguments supplied, should be an unsigned integer number");
        }

        int size = Integer.parseInt(args[0]);
        if (size < 1 || size > 10000) {
            throw new IllegalArgumentException("Provided matrix size should be in the range [1...10000]");
        }

        int[][] firstMatrix = new int[size][size];
        int[][] secondMatrix = new int[size][size];

        fillMatrix(firstMatrix);
        fillMatrix(secondMatrix);

        new Thread(new SolutionRunner(firstMatrix, secondMatrix, new SequentialMultiplier())).start();
        new Thread(new SolutionRunner(firstMatrix, secondMatrix, new ParallelMultiplier())).start();
    }

    private static void fillMatrix(int[][] matrix) {
        for (int[] r : matrix)
            Arrays.fill(r, new Random().nextInt(2));

    }
}
