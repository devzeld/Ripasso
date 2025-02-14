package parallelFolderMaker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FolderMaker implements Runnable {

    private int size;
    private int start;
    private int end;

    private boolean isDeleting;

    private static boolean created = false;

    public FolderMaker(int size, int start, int end, boolean isDeleting) {
        this.size = size;
        this.start = start;
        this.end = end;
        this.isDeleting = isDeleting;
    }

    private void deleteDir(File dir) {
        File[] contents = dir.listFiles();
        if (contents != null) {
            for (File f : contents) {
                deleteDir(f);
            }
        }
        dir.delete();
    }

    @Override
    public void run() {
        File dir = new File("./src/parallelFolderMaker/test");
        if (this.isDeleting) {
            deleteDir(dir);
        } else {
            created = dir.mkdir();
            for (int i = start; i <= end; i++) {
                dir = new File("./src/parallelFolderMaker/test/" + i);
                if (dir.mkdir()) {
                    File file = new File(String.format("./src/parallelFolderMaker/test/%s/%s", i, i));
                    try {
                        FileWriter writer = new FileWriter(file);
                        for (int j = 0; j <= size; j++) {
                            writer.write(j + "\n");
                        }
                        writer.close();
                    } catch (IOException ignored) {
                    }
                }
            }

        }
    }
}
