# Markup

Classes for text markup

Support converting to markdown and BBcode

```java
Paragraph paragraph = new Paragraph(List.of(
    new Strong(List.of(
        new Text("1"),
        new Strikeout(List.of(
            new Text("2"),
            new Emphasis(List.of(
                new Text("3"),
                new Text("4")
            )),
            new Text("5")
        )),
        new Text("6")
    ))
));

paragraph.toMarkdown(new StringBuilder()); // __1~2*34*5~6__
```

### Class diargam

This task is about minimizing duplicated code and creating the most abstract inheritance scheme

![](/home/switcher/Documents/repos/ProgIntro/Markup/class_diagram.png)

