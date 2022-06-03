package usefulstuff.editorstuff;

public class Name implements Editing {
    public Name() {
    }

    public String check(String o) {
        while (true) {
            if (o.contains("[0-9]+")) {
                System.out.println("Не имя.");
                o = console.nextLine();
            } else {
                return o;
            }
        }
    }
}
