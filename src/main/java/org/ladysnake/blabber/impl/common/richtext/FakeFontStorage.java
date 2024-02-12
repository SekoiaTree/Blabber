package org.ladysnake.blabber.impl.common.richtext;

import net.minecraft.client.font.EmptyGlyphRenderer;
import net.minecraft.client.font.FontStorage;
import net.minecraft.client.font.Glyph;
import net.minecraft.client.font.GlyphRenderer;
import net.minecraft.util.Identifier;
import org.ladysnake.blabber.Blabber;

public class FakeFontStorage extends FontStorage {

    public static final Identifier FAKE_FONT_ID = Blabber.id("font_override");

    public FakeFontStorage() {
        super(null, FAKE_FONT_ID);
    }

    @Override
    public GlyphRenderer getGlyphRenderer(int codePoint) {
        return EmptyGlyphRenderer.INSTANCE;
    }

    @Override
    public Glyph getGlyph(int codePoint, boolean validateAdvance) {
        return new FakeTextGlyph(Float.intBitsToFloat(codePoint));
    }
}
