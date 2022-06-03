package src.serverstuff.usefulstuff;

import java.io.*;
import java.util.Scanner;

public class ConsoleClient extends  ScanConsole implements Scannable {
    /**
     * helper to make commands managing senseful
     */

    private BufferedReader reader;
    private PrintWriter writer;
    private ObjectInputStream objReciever;
    private ObjectOutputStream objSender;

    public ConsoleClient (BufferedReader reader, PrintWriter writer,
                          ObjectInputStream objrec, ObjectOutputStream objsen) {
        this.reader = reader;
        this.writer = writer;
        this.objReciever = objrec;
        this.objSender = objsen;
    }


    public String scanLine() throws IOException {return reader.readLine();}
    public void writeLine(String s) {writer.write(s); }
    public void sendObject (Object o) throws IOException {
        objSender.writeObject(o); objSender.flush();
        }
    public Object recieveObject() throws IOException, ClassNotFoundException {
         return  objReciever.readObject();
    }
    }


