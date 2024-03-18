package org.ladysnake.blabber.impl.common.richtext;

import net.minecraft.text.*;

import java.util.Collections;
import java.util.List;

public record RichText implements Text {
    @Override
    public Style getStyle() {
        return Style.EMPTY;
    }

    @Override
    public TextContent getContent() {
        return PlainTextContent.EMPTY;
    }

    @Override
    public List<Text> getSiblings() {
        return Collections.emptyList();
    }

    @Override
    public OrderedText asOrderedText() {
        // TODO: 2/20/24
    }
}
