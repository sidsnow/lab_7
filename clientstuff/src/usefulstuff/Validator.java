package usefulstuff;

import com.CommandDelegate;
import com.MovieDTO;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Stream;

public class Validator {
    private static Validator instance;
    private Scanner console = new Scanner(System.in);
    private static String[] comList = {"help", "info", "show", "clear", "group_counting_by_director", "print_descending",
            "insert","update", "remove_key","remove_greater_key", "remove_lower_key",
            "execute_script", "remove_greater","count_by_genre"};
    private static HashMap<String, Function<String, Object>> validateData;

    private Validator () {
        validateData = new HashMap<>();
        validateData.put("insert", (String o) ->  {
            try {
            Integer check = Integer.valueOf(o);
            return MovieEditor.getInstance().createMovieDTO(o, console);
            }
        catch (IllegalArgumentException e) {return "Id - число."; } });
        validateData.put("update", (String o) ->  { try {
            Integer check = Integer.valueOf(o);
            return MovieEditor.getInstance().createMovieDTO(o, console);
        }
        catch (IllegalArgumentException e) {return "Id - число."; }});
        validateData.put("remove_greater", (String o) -> o );
        validateData.put("remove_greater_key", (String o) ->{
            Object data;
            try {
                data = Integer.valueOf(o);
            } catch (IllegalArgumentException e ) {
                data = "invalid_arg";
            }
            return data;
        } );
        validateData.put("remove_key", (String o) ->{
            Object data;
            try {
                data = Integer.valueOf(o);
            } catch (IllegalArgumentException e ) {
                data = "invalid_arg";
            }
            return data;
        } );
        validateData.put("count_by_genre", (String o) -> {
            String data;
            if ((o.equals("ADVENTURE")|
                    o.equals("THRILLER")|
                    o.equals("COMEDY"))) {
                data = o;
            } else {data = "invalid_arg";}
            return data;
        });
        validateData.put("execute_script", (String o) -> o);


    }
    public void setConsole(Scanner console) {this.console = console;}
    public static Validator getInstance() {
        if (instance == null) {
            instance = new Validator();
        }
        return instance;
    }

    public String[] getComList() {
        return comList;
    }

    public Object validateData(String[] data) {

        Object o = null;
         if (Stream.of(comList).anyMatch(x -> data[0].equals(x))) {
            if (validateData.containsKey(data[0]))
                 {  //если с аргументами команда, возвращается
                     // преобразованный аргумент команды
                     try {
                     o = validateData.get(data[0]).apply(data[1]); }
                     catch (ArrayIndexOutOfBoundsException e) {o = null; }
                 } else {
                o = data[0]; }
         } return  o;
    }

    }
