package org.ashutosh.sparso.core;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ashu on 11/23/15.
 */
public class SparseVector {
    public Map<Integer, Double> vector;
    private int size;


    public SparseVector(int initialSize) {
        this.size = initialSize;
        this.vector = new HashMap<>(this.size);
    }

    public SparseVector(double[] vector, int initialSize) {
        this.size = initialSize;
        this.vector = new HashMap<>();

        for(int i = 0; i < vector.length; i++) {
            this.vector.put(i, vector[i]);
        }
    }

    public void serialize(String filename) throws IOException {
        FileOutputStream fos = new FileOutputStream(filename);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this.vector);

        oos.close();
        fos.close();;
    }

    public void deserialize(String filename) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream ois = new ObjectInputStream(fis);

        this.vector = (HashMap<Integer, Double>) ois.readObject();

    }


    public int getSize() {
        return this.size;
    }
    public Double innerProduct(SparseVector other) {
        Double total = 0.0;
        for(Map.Entry kvPair : this.vector.entrySet()) {
            if(other.vector.containsKey(kvPair.getKey())) {
                total += (this.get((Integer) kvPair.getKey()) * other.get((Integer) kvPair.getKey()));
            }
        }
        return total;
    }


    public Double get(Integer index) {
        if(validateIndex(index)) {
            if(this.vector.containsKey(index)) {
                return this.vector.get(index);
            }
            else {
                return 0.0;
            }
        }

        else {
            throw new IndexOutOfBoundsException("Invalid index " + index + ".");
        }
    }

    public void put(Integer index, Double value) {
        if (validateIndex(index)) {
            this.vector.put(index, value);
        }
        else {
            throw new IndexOutOfBoundsException("Invalid index " + index + ".");
        }
    }
    @Override
    public String toString() {
        String result = "";
        for(Map.Entry kvp : this.vector.entrySet()) {
            result += String.format("%4d -> %2.2f|", kvp.getKey(), kvp.getValue());
        }
        return result;
    }
    private boolean validateIndex(int index) {
        return ((index >= 0) && (index < this.size));
    }
}
