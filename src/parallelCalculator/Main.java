package parallelCalculator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Start!");
        long start = System.currentTimeMillis();

        ArrayList<Float> dataset = new ArrayList<>();
        int size;
        double tot = 0;

        try {
            String line;
            BufferedReader br = new BufferedReader(new FileReader("input10^5.txt"));

            while((line = br.readLine()) != null) {
               dataset.add(Float.parseFloat(line));
            }
            size = dataset.size();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int nThread = 8;
        Thread[] threads = new Thread[nThread];
        ParallelCalculator[] pcs = new ParallelCalculator[nThread];

        for (int i = 0; i < nThread; i++) {
            pcs[i] = new ParallelCalculator(dataset, size / nThread * i, (size / nThread * (i + 1)));
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
