package org.ladysnake.blabber.impl.common.richtext;

import org.ladysnake.blabber.impl.mixin.ITextRendererDrawer;

public interface TextComponentRenderer {
    void render(ITextRendererDrawer textRendererDrawer);

    TextComponentType<?> getType();
}
