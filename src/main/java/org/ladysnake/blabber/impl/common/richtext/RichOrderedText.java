package org.ladysnake.blabber.impl.common.richtext;

import net.minecraft.client.font.GlyphRenderer;
import net.minecraft.client.font.TextRenderLayerSet;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.text.CharacterVisitor;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Style;
import org.joml.Matrix4f;

public class RichOrderedText implements OrderedText {
    public static final ThreadLocal<GlyphRenderer> RENDERER = new ThreadLocal<>();
    private final ComponentGlyphRenderer renderer;


    public RichOrderedText(TextComponentRenderer renderer) {
        this.renderer = new ComponentGlyphRenderer(renderer);
    }

    @Override
    public boolean accept(CharacterVisitor visitor) {
        try {
            RENDERER.set(renderer);
            return visitor.accept(0, Style.EMPTY, 0);
        } finally {
            RENDERER.remove();
        }
    }

    private static final class ComponentGlyphRenderer extends GlyphRenderer {
        private final TextComponentRenderer renderer;

        public ComponentGlyphRenderer(TextComponentRenderer renderer) {
            super(TextRenderLayerSet.of(FakeFontStorage.FAKE_FONT_ID), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
            this.renderer = renderer;
        }

        @Override
        public void draw(boolean italic, float x, float y, Matrix4f matrix, VertexConsumer vertexConsumer, float red, float green, float blue, float alpha, int light) {
            renderer.draw(italic, x, y, matrix, vertexConsumer, red, green, blue, alpha, light);
        }
    }
}
