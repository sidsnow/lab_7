package usefulstuff;

import com.MovieDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MovieEditor {
    private static MovieEditor instance;
    private static HashMap<InputCriteria, Function<String, Object>> checkCriteria;
    private Scanner console;

    private MovieEditor() {
        checkCriteria = new HashMap<>();
        checkCriteria.put(InputCriteria.NAME, (String name) -> name);
        checkCriteria.put(InputCriteria.COORDINATES, (String x) -> {
            try {
                String[] xinfo = new String[2]; xinfo =  x.split(" ");
                ArrayList<Double> corresult = new ArrayList<>(); corresult.add(0, Double.parseDouble(xinfo[0]));
                corresult.add(1,Double.parseDouble(xinfo[1]));
                return corresult;
            } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                throw new IllegalArgumentException("Неправильные координаты.\n");
                    }
        });
        checkCriteria.put(InputCriteria.OSCARSCOUNT, (String o) -> {
        try {
            return Integer.parseInt(o);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Не число.\n");
        }
        });
        checkCriteria.put(InputCriteria.GOLDENPALMCOUNT, (String o) -> {
            try {
                return Long.parseLong(o);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Не число.\n");
            }
        });
        checkCriteria.put(InputCriteria.TOTALBOXOFFICE, (String o) -> {
            try {
                return Integer.parseInt(o);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Не число.\n");
            }
        });
        checkCriteria.put(InputCriteria.GENRE, (String o) -> {
            try {
                return com.movienco.MovieGenre.valueOf(o);
            } catch (Exception e) {
                throw new IllegalArgumentException("Нет такого жанра.\n");
            }
        });
        checkCriteria.put(InputCriteria.DIRECTOR, (String o) -> {
            if (o == null) {
                throw new IllegalArgumentException("Режиссера без имени не бывает.");
            } else if (o.contains("[0-9]+")) {
                throw new IllegalArgumentException("В именах цифр не бывает;). ");
            } else {
                return o;
            }
        });

    }

    public static MovieEditor getInstance() {
        if (instance == null) {
            instance = new MovieEditor();
        }
        return instance;
    }

    public enum InputCriteria {

        NAME("название"),COORDINATES("координаты (х, у)"), OSCARSCOUNT("количество Оскаров"),
        GOLDENPALMCOUNT("количество Золотых веточек(?)"), TOTALBOXOFFICE("кассовый сбор"),
        GENRE("жанр (THRILLER, COMEDY, ADVENTURE)"), DIRECTOR("режиссер");
        public String name;
        InputCriteria(String s) {
            this.name = s;
        }

    }
    public MovieDTO createMovieDTO(String s, Scanner console) {
        this.console = console;
        ArrayList<Object> info = new ArrayList<>(); int flag = 0;
        InputCriteria[] inputCriteria = { InputCriteria.NAME, InputCriteria.COORDINATES, InputCriteria.OSCARSCOUNT,
        InputCriteria.GOLDENPALMCOUNT, InputCriteria.TOTALBOXOFFICE, InputCriteria.GENRE, InputCriteria.DIRECTOR};
        while (flag!=1) { try {
            info.addAll( Stream.of(inputCriteria).map(x ->
                    {
                System.out.println("Введите " + x.name);
                 return checkCriteria.get(x).apply(console.nextLine().trim());
            })
                    .collect(Collectors.toList()));
            info.add(Integer.valueOf(s));
            info.add(0,User.getInstance().getUserId());
            flag+=1;
        } catch (Exception e ) {
            System.out.println(e.getMessage());
        }}
        return new MovieDTO(info);

    }


}


