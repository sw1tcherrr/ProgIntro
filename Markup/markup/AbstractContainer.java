package markup;

import java.util.List;

public abstract class AbstractContainer implements Element {
    protected List<? extends Element> items;

    public AbstractContainer(List<? extends Element> items) {
        this.items = items;
    }

    protected void toMarkdown(StringBuilder s, String tag) {
        s.append(tag);
        for (Element item : items) {
            item.toMarkdown(s);
        }
        s.append(tag);
    }

    protected void toBBCode(StringBuilder s, String openTag, String closeTag) {
        s.append(openTag);
        for (Element item : items) {
            item.toBBCode(s);
        }
        s.append(closeTag);
    }
}
