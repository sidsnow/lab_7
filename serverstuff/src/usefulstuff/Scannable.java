package src.serverstuff.usefulstuff;

import java.io.IOException;

public interface Scannable {
    void close() throws IOException;
    String scanLine() throws IOException;

}
