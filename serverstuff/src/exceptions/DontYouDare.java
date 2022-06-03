package src.serverstuff.exceptions;

public class DontYouDare extends RuntimeException {
    /**the name speaks itself
     *
     * @param s
     */
    public DontYouDare (String s) {
        super(s);
    }
}
