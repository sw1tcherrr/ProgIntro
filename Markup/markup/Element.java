package markup;

public interface Element {
    void toMarkdown(StringBuilder s);
    void toBBCode(StringBuilder s);
}
