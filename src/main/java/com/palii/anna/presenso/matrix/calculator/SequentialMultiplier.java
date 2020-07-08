package com.palii.anna.presenso.matrix.calculator;

public class SequentialMultiplier implements Multiplier {

    public int[][] multiply(int[][] firstMatrix, int[][] secondMatrix) {

        int matrixSize = firstMatrix.length;
        int[][] result = new int[matrixSize][matrixSize];

        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                for (int k = 0; k < matrixSize; k++) {
                    result[i][j] = (result[i][j] + (firstMatrix[i][k] * secondMatrix[k][j]) % 2) % 2;
                }
            }
        }
        return result;
    }
}
