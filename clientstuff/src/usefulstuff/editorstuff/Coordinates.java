package usefulstuff.editorstuff;

import java.util.ArrayList;

public class Coordinates implements Editing {
    public Coordinates () {}
    public ArrayList<Double> check (String o) {
        while (true) {
            try {
                String[] oinfo = new String[2]; oinfo =  o.split(" ");
                ArrayList<Double> corresult = new ArrayList<>(); corresult.add(0, Double.parseDouble(oinfo[0]));
                corresult.add(1,Double.parseDouble(oinfo[1]));
                return corresult;
            } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Неверные координаты.");
                o = console.nextLine();
            }

        }

    }
}
