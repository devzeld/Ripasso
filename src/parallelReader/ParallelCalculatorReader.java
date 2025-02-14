package parallelReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ParallelCalculatorReader implements Runnable{

    private String filename;
    private int start;
    private int end;

    private double tot = 0;

    public ParallelCalculatorReader(String filename, int start, int end) {
        this.filename = filename;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        ArrayList<Float> dataset = new ArrayList<>();

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));

            ois.skipBytes(4);
            if (start != 0) {
                ois.skipBytes(start * 4);
            }

            for (int i = start; i < end; i++) {
                dataset.add(ois.readFloat());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (float val : dataset) {
            tot += val;
        }
    }

    public double getTot() {
        return tot;
    }
}
