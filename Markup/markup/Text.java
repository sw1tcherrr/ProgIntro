package markup;

public class Text implements Paragraphable {
    private final String text;

    public Text(String t) {
        text = t;
    }

    public void toMarkdown(StringBuilder s) {
        s.append(text);
    }

    public void toBBCode(StringBuilder s) {
        s.append(text);
    }
}
