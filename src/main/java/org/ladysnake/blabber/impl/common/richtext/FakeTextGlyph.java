package org.ladysnake.blabber.impl.common.richtext;

import net.minecraft.client.font.EmptyGlyphRenderer;
import net.minecraft.client.font.Glyph;
import net.minecraft.client.font.GlyphRenderer;
import net.minecraft.client.font.RenderableGlyph;

import java.util.function.Function;

public record FakeTextGlyph(float width) implements Glyph {
    @Override
    public float getAdvance() {
        return width;
    }

    @Override
    public float getAdvance(boolean bold) {
        return width;
    }

    @Override
    public GlyphRenderer bake(Function<RenderableGlyph, GlyphRenderer> glyphRendererGetter) {
        return EmptyGlyphRenderer.INSTANCE;
    }

    @Override
    public float getBoldOffset() {
        return 0.0F;
    }

    @Override
    public float getShadowOffset() {
        return 0.0F;
    }
}
