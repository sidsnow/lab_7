package src.serverstuff.usefulstuff;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class ScanConsole implements Scannable {
    /**
     * helper to make commands managing senseful
     */

    private Scanner console;
    public ScanConsole() {}

    public ScanConsole (Scanner console) {
        this.console = console;
    }

    public void close() {
        console.close();
    }

    public String scanLine() throws IOException {
        return console.nextLine();
    }
    public boolean hasInt() {
        return console.hasNextInt();
    }
    public boolean hasDouble() {
        return console.hasNextDouble();
    }

    public void writeLine(String s) { System.out.println(s);
    }
}
