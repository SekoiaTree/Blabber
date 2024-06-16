package org.ladysnake.blabber.impl.common.richtext;

import net.minecraft.client.render.VertexConsumer;
import org.joml.Matrix4f;

@FunctionalInterface
public interface TextComponentRenderer {
    void draw(boolean italic, float x, float y, Matrix4f matrix, VertexConsumer vertexConsumer, float red, float green, float blue, float alpha, int light);
}
