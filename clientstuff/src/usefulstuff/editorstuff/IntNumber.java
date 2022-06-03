package usefulstuff.editorstuff;

public class IntNumber implements Editing {
    public IntNumber() {
    }

    public Integer check(String o) {
        while (true) {
            try {
                return Integer.parseInt(o);
            } catch (IllegalArgumentException e) {
                System.out.println("Не число.");
                o = console.nextLine();
            }
        }
    }
}
