package com.palii.anna.presenso.matrix.calculator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Log4j
public class ParallelMultiplier implements Multiplier {

    public int[][] multiply(int[][] firstMatrix, int[][] secondMatrix) {
        int matrixSize = firstMatrix.length;
        int[][] result = new int[matrixSize][matrixSize];

        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<RowResult>> resultList = new ArrayList<>();

        for (int i = 0; i < matrixSize; i++) {

            resultList.add(executorService.submit(new CellCounter(firstMatrix, secondMatrix, i)));

        }
        executorService.shutdown();

        resultList.forEach(future -> {

            RowResult rowResult = getLineResult(future);
            result[rowResult.getRow()] = rowResult.getResult();
        });


        return result;
    }

    private static RowResult getLineResult(Future<RowResult> future) {
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("Calculation failed", e);
        }
        return null;
    }

    @AllArgsConstructor
    private static class CellCounter implements Callable<RowResult> {

        private final int[][] firstMatrix;
        private final int[][] secondMatrix;
        private final int row;

        @Override
        public RowResult call() {
            int matrixSize = firstMatrix.length;

            int[] result = new int[matrixSize];

            for (int j = 0; j < matrixSize; j++) {
                for (int k = 0; k < matrixSize; k++) {
                    result[j] = (result[j] + (firstMatrix[row][k] * secondMatrix[k][j]) % 2) % 2;
                }
            }
            return new RowResult(result, row);
        }
    }

    @Getter
    @AllArgsConstructor
    private static class RowResult {

        private final int[] result;
        private final int row;
    }
}
