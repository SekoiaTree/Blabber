package org.ladysnake.blabber.impl.mixin;

import net.minecraft.client.font.GlyphRenderer;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(targets = "net/minecraft/client/font/TextRenderer$Drawer")
public interface ITextRendererDrawer {

    @Accessor
    VertexConsumerProvider getVertexConsumers();
    @Accessor
    boolean getShadow();
    @Accessor
    float getBrightnessMultiplier();
    @Accessor
    float getRed();
    @Accessor
    float getGreen();
    @Accessor
    float getBlue();
    @Accessor
    float getAlpha();
    @Accessor
    Matrix4f getMatrix();
    @Accessor
    TextRenderer.TextLayerType getLayerType();
    @Accessor
    int getLight();
    @Accessor(value="x")
    float getX();
    @Accessor(value="y")
    float getY();
    @Accessor
    List<GlyphRenderer.Rectangle> getRectangles();
    @Accessor(value="field_24240")
    TextRenderer getTextRenderer();
}
