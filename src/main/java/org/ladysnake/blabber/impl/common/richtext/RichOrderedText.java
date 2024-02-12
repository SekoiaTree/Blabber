package org.ladysnake.blabber.impl.common.richtext;

import net.minecraft.text.CharacterVisitor;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Style;

public class RichOrderedText implements OrderedText {
    public static final ThreadLocal<TextComponentRenderer> current = new ThreadLocal<>();

    private final TextComponentRenderer renderer;
    private final int width;

    public RichOrderedText(TextComponentRenderer renderer, float width) {
        this.renderer = renderer;
        this.width = Float.floatToRawIntBits(width);
    }


    public TextComponentType<?> getType() {
        return renderer.getType();
    }

    public TextComponentRenderer getRenderer() {
        return renderer;
    }

    @Override
    public boolean accept(CharacterVisitor visitor) {
        try {
            current.set(renderer);
            return visitor.accept(0, Style.EMPTY.withFont(FakeFontStorage.FAKE_FONT_ID), width);
        } finally {
            current.remove();
        }
    }
}
