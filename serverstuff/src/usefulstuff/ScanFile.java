package src.serverstuff.usefulstuff;

import java.io.*;
import java.nio.Buffer;

public class ScanFile implements Scannable {
    /**
     * helper to make command management meaningful
     */

    private BufferedReader file;

    public ScanFile (String filePath) {
        try {
            this.file = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
        } catch (FileNotFoundException e ) {
            System.out.print("Нет файла.");
        }
    }

    public void close() throws IOException {
        file.close();
    }
    public void writeLine(String s) {System.out.println("В файлы не пишут");}

    public String scanLine() throws IOException {
        return file.readLine();
    }
    public boolean hasInt() throws IOException {
        return file.readLine().matches("[0-9]+");
    }
    public boolean hasDouble() throws IOException {
        return file.readLine().matches("[0-9]+");
    }

}
