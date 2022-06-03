package usefulstuff.editorstuff;

import com.movienco.MovieGenre;

public class Genre implements Editing {
    public Genre () {}
    public MovieGenre check (String o) {
        while ( true) {
        try {
            return com.movienco.MovieGenre.valueOf(o);
        } catch (Exception e) {
            System.out.println("Нет такого жанра.\n");
        o = console.nextLine();
        }
    } }
}
