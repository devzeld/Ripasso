package parallelCalculator;

import java.util.ArrayList;

public class ParallelCalculator implements Runnable{
    //Abbiamo un file input.txt contenente una certa quantità di numeri interi di cui vogliamo fare la media.

    //Fase 1
    //Si scriva un programma che legga tutti i dati in input e poi esegua il calcolo della media utilizzando da 1 a 8 thread. Prima di procedere realizzare l'activity diagram del programma.

    //Si testi il programma fissando l'input (10000, 100000, 1000000 di numeri) e variando il numero di thread.

    //Fase 2
    //Si testi il programma su un input di 1000000000 e oltre valori. Il programma potrebbe andare in errore terminando la memoria a sua disposizione nello stack. procedere alla lettura del file in modo parziale in blocchi di 1000000 di valori massimo su cui fare le operazioni di calcolo intermedie.
    //Tracciare le prestazioni.

    //Risultati
    //10^4 = 502607.2832651398
    //10^5 = 499664.29139839316
    //10^6 = 499730.321972445
    //10^9 = 499995.16687235486


    private int start;
    private int end;

    private ArrayList<Float> dataset;

    private double tot = 0;

    public ParallelCalculator(ArrayList<Float> dataset, int start, int end) {
        this.start = start;
        this.end = end;
        this.dataset = dataset;
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++) {
           tot += dataset.get(i);
        }
    }

    public int getStart() {
        return start;
    }

    public ArrayList<Float> getDataset() {
        return dataset;
    }

    public int getEnd() {
        return end;
    }

    public double getTot() {
        return tot;
    }

}
