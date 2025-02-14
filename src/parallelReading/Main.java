package parallelReading;

import parallelCalculator.ParallelCalculator;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Start!");
        long start = System.currentTimeMillis();

        int size;
        double tot = 0;

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./src/parallelReading/input10^5.bin"));
            size = ois.readInt();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int nThread = 8;
        Thread[] threads = new Thread[nThread];
        ParallelCalculatorReader[] pcs = new ParallelCalculatorReader[nThread];

        for (int i = 0; i < nThread; i++) {
            pcs[i] = new ParallelCalculatorReader("./src/parallelReading/input10^5.bin", size / nThread * i, (size / nThread * (i + 1)));
            threads[i] = new Thread(pcs[i]);

            threads[i].start();
        }

        for (int i = 0; i < nThread; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        for (int i = 0; i < nThread; i++) {
            tot += pcs[i].getTot();
        }

        double avg = tot / size;

        System.out.println("Media: " + avg);
        System.out.println(System.currentTimeMillis() - start + "ms");
        System.out.println("End!");
    }
}
