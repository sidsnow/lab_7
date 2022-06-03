package src.serverstuff.usefulstuff;


import com.MovieDTO;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import src.serverstuff.commands.ArgsCommand;
import src.serverstuff.commands.ArgsWConsoleCommand;
import src.serverstuff.exceptions.DontYouDare;
import com.movienco.Movie;
import com.movienco.MovieCollection;
import java.io.*;

import java.util.Scanner;

public class FileParser {
    /**
     *
     */
    private static FileParser instance;
    MovieCollection m;
    String filePath;
    Scanner console;

    private FileParser() {}

    public static FileParser getInstance() {
        if (instance == null) {
            instance = new FileParser();
        }
        return instance;
    }


    public void setFileParser(MovieCollection m, String FilePath, Scanner sc) {
        this.m = m;
        this.filePath = FilePath;
        this.console = sc;
    }



    public MovieCollection getMovieCollection() {
        return m;
    }

    public String parseAFile (String filePath) throws IOException {
        ScanFile inFile = new ScanFile(filePath);
        String result = "";
       /* try {
            String f;
            while((f = inFile.scanLine()) != null) {

                String[] comArgs = f.split(" ");
                int num = comArgs.length;
                if (CommandParser.getInstance().getCommandCollection().containsCommand(comArgs[0])) {
                    if ((CommandParser.getInstance().getCommandCollection().getCommand(comArgs[0]) instanceof ArgsCommand)) {
                        try {
                            result += ((ArgsCommand)CommandParser.getInstance().getCommandCollection().getCommand(comArgs[0])).execute(comArgs[1]) + '\n';
                        } catch (ArrayIndexOutOfBoundsException var7) {
                            result += CommandParser.getInstance().getCommandCollection().getCommand(comArgs[0]).execute()+ '\n';
                        }
                    } else if (CommandParser.getInstance().getCommandCollection().getCommand(comArgs[0]) instanceof ArgsWConsoleCommand) {
                        result += ((ArgsWConsoleCommand)CommandParser.getInstance().getCommandCollection().getCommand(comArgs[0])).execute(new MovieDTO(comArgs[1]))+ '\n';
                    } else {
                        result += CommandParser.getInstance().getCommandCollection().getCommand(comArgs[0]).execute()+ '\n';
                    }
                } else {
                    result = "Такой команды нет. Измените скрипт. \n";
                }
            }

            inFile.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Скрипт завершен"); inFile.close(); */
        return result;


} }

