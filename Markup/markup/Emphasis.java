package markup;

import java.util.List;

public class Emphasis extends AbstractContainer implements Paragraphable {
    public Emphasis(List<Paragraphable> items) {
        super(items);
    }
    public void toMarkdown(StringBuilder s) {
        super.toMarkdown(s, "*");
    }

    public void toBBCode(StringBuilder s) {
        super.toBBCode(s, "[i]", "[/i]");
    }
}
