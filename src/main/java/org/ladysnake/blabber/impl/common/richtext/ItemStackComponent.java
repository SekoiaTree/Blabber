package org.ladysnake.blabber.impl.common.richtext;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import org.ladysnake.blabber.impl.mixin.ITextRendererDrawer;

public class ItemStackComponent implements TextComponentRenderer {
    private final DrawContext context;
    private final ItemStack stack;

    public ItemStackComponent(DrawContext context, ItemStack stack) {
        this.context = context;
        this.stack = stack;
    }

    @Override
    public void render(ITextRendererDrawer textRendererDrawer) {
        context.drawItem(stack, (int) textRendererDrawer.getX(), (int) textRendererDrawer.getY());
    }
}
