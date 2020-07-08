package com.palii.anna.presenso.solution.asserts;

import org.junit.Assert;

public class MatrixAssert {

    public static void assertMatrixEquals(int[][] expected, int[][] actual) {
        int matrixSize = expected.length;
        for (int i = 0; i < matrixSize; i++) {
            Assert.assertArrayEquals(String.format("Difference in line[%s]", i), expected[i], actual[i]);
        }
    }
}
