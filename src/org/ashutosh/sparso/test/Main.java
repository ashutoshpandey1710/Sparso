package org.ashutosh.sparso.test;

import org.ashutosh.sparso.core.SparseVector;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SparseVector s1 = new SparseVector(10000);
        SparseVector s2 = new SparseVector(10000);

        s1.put(123, 1.4);
        s1.put(854, 1.8);
        s1.put(4933, 2.0);

        s1.serialize("/home/ashu/dev/vector.bin");

        s2.deserialize("/home/ashu/dev/vector.bin");

        System.out.println("Vector: " + s2);
    }
}
