package com.palii.anna.presenso.solution;

import com.palii.anna.presenso.matrix.calculator.SequentialMultiplier;
import com.palii.anna.presenso.solution.asserts.MatrixAssert;
import org.junit.Test;

public class SequentialMultiplierTest {

    SequentialMultiplier multiplier = new SequentialMultiplier();

    @Test
    public void test2x2() {

        int[][] firstMatrix = {{1, 1}, {0, 1}};
        int[][] secondMatrix = {{1, 1}, {1, 1}};

        int[][] expectedResult = {{0, 0}, {1, 1}};
        MatrixAssert.assertMatrixEquals(expectedResult, multiplier.multiply(firstMatrix, secondMatrix));
    }

}
