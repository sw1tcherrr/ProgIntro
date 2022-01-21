package markup;

import java.util.List;

public class Strikeout extends AbstractContainer implements Paragraphable {
    public Strikeout(List<Paragraphable> items) {
        super(items);
    }

    public void toMarkdown(StringBuilder s) {
        super.toMarkdown(s, "~");
    }

    public void toBBCode(StringBuilder s) {
        super.toBBCode(s, "[s]", "[/s]");
    }
}
