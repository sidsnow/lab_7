package usefulstuff.editorstuff;

public class LongNumber implements Editing{
    public LongNumber() {}
    public Long check (String o) {
        while (true) {
            try {
                return Long.parseLong(o);
            } catch (IllegalArgumentException e) {
                System.out.println("Не число.\n");
                o = console.nextLine();
            }
        }
    }
}
