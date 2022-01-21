package markup;

import java.util.List;

public class OrderedList extends AbstractContainer implements Listable {
    public OrderedList(List<ListItem> items) {
        super(items);
    }

    public void toBBCode(StringBuilder s) {
        super.toBBCode(s, "[list=1]", "[/list]");
    }

    public void toMarkdown(StringBuilder s) {
        throw new UnsupportedOperationException();
    }
}