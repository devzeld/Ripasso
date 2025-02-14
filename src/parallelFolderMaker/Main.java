package parallelFolderMaker;

public class Main {
    public static void main(String[] args) {
        System.out.println("Start!");
        long start = System.currentTimeMillis();

        int size = 1000;

        int nThread = 8;
        Thread[] threads = new Thread[nThread];
        FolderMaker[] fms = new FolderMaker[nThread];

        for (int i = 0; i < nThread; i++) {
            fms[i] = new FolderMaker(size,size / nThread * i, size/ nThread * (i + 1), true);
            threads[i] = new Thread(fms[i]);

            threads[i].start();
        }

        for (int i = 0; i < nThread; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println(System.currentTimeMillis() - start + "ms");
        System.out.println("End!");
    }
}
