package markup;

import java.util.List;

public class UnorderedList extends AbstractContainer implements Listable{
    public UnorderedList(List<ListItem> items) {
        super(items);
    }

    public void toBBCode(StringBuilder s) {
        super.toBBCode(s, "[list]", "[/list]");
    }

    public void toMarkdown(StringBuilder s) {
        throw new UnsupportedOperationException();
    }
}