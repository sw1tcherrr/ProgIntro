package markup;

import java.util.List;

public class Strong extends AbstractContainer implements Paragraphable {
    public Strong(List<Paragraphable> items) {
        super(items);
    }

    public void toMarkdown(StringBuilder s) {
        super.toMarkdown(s, "__");
    }

    public void toBBCode(StringBuilder s) {
        super.toBBCode(s, "[b]", "[/b]");
    }
}
