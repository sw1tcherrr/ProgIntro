package markup;

import java.util.List;

public class Paragraph extends AbstractContainer implements Listable {
    public Paragraph(List<Paragraphable> items) {
        super(items);
    }

    public void toBBCode(StringBuilder s) {
        super.toBBCode(s, "", "");
    }

    public void toMarkdown(StringBuilder s) {
        super.toMarkdown(s, "");
    }
}