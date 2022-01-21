package markup;
import java.util.List;

public class ListItem extends AbstractContainer {
    public ListItem(List<Listable> items) {
        super(items);
    }

    public void toBBCode(StringBuilder s) {
        super.toBBCode(s, "[*]", "");
    }

    public void toMarkdown(StringBuilder s) {
        throw new UnsupportedOperationException();
    }
}
