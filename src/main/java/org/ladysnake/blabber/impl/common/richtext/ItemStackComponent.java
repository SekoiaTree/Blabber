package org.ladysnake.blabber.impl.common.richtext;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.item.ItemStack;
import org.joml.Matrix4f;

public class ItemStackComponent implements TextComponentRenderer {
    private final DrawContext context;
    private final ItemStack stack;

    public ItemStackComponent(DrawContext context, ItemStack stack) {
        this.context = context;
        this.stack = stack;
    }

    @Override
    public void draw(boolean italic, float x, float y, Matrix4f matrix, VertexConsumer vertexConsumer, float red, float green, float blue, float alpha, int light) {
        context.drawItem(stack, (int) x, (int) y);
    }
}
