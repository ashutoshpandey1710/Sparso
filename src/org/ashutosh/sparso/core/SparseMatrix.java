package org.ashutosh.sparso.core;

import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ashu on 11/23/15.
 */
public class SparseMatrix {
    private int rows;
    private int cols;

    public class Key {
        private final int i;
        private final int j;
        private final int rowCount = rows;
        private final int colCount = cols;

        Key(int x, int y) {
            this.i = x;
            this.j = y;
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Key)) {
                return false;
            }

            Key key = (Key) other;
            return this.i == key.i && this.j == key.j;
        }

        @Override
        public int hashCode() {
            return this.i * this.colCount + this.j;
        }

    }
    private Map<Key, Double> matrix;


    public SparseMatrix(int rowCount, int colCount) {
        this.rows = rowCount;
        this.cols = colCount;
        this.matrix = new HashMap<>();
    }

    public SparseVector multiplyWithRowVector(SparseVector vector) {
        if(vector.getSize() == this.cols) {
            SparseVector result = new SparseVector(this.rows);
        }
        return null;
    }

    public Double get(Key key) {
        if(withinBounds(key)) {
            if(this.matrix.containsKey(key)) {
                return this.matrix.get(key);
            }
            else {
                return 0.0;
            }
        }
        else {
            throw new IndexOutOfBoundsException(String.format("Invalid index (%d, %d).", key.i, key.j));
        }
    }

    public void put(Key key, Double value) {
        if(withinBounds(key)) {
            this.matrix.put(key, value);
        }
        else {
            throw new IndexOutOfBoundsException(String.format("Invalid index (%d, %d).", key.i, key.j));
        }
    }

    private boolean withinBounds(Key key) {
        return ((key.i >= 0) && (key.i < this.rows) && (key.j >= 0) && (key.j < this.cols));
    }
}
