package app.markdown;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;

public class MarkdownRenderer {
    private final Parser parser;
    private final HtmlRenderer renderer;

    public MarkdownRenderer() {
        parser = Parser.builder().build();
        renderer = HtmlRenderer.builder().build();
    }

    public String render(String markdown) {
        if (markdown == null) markdown = "";
        return renderer.render(parser.parse(markdown));
    }
}
