package org.ladysnake.blabber.impl.mixin;

import net.minecraft.client.font.FontStorage;
import net.minecraft.client.font.GlyphRenderer;
import org.ladysnake.blabber.impl.common.richtext.FakeFontStorage;
import org.ladysnake.blabber.impl.common.richtext.RichOrderedText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = "net/minecraft/client/font/TextRenderer$Drawer")
public abstract class TextRendererDrawerMixin {
    @Redirect(method = "accept", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/FontStorage;getGlyphRenderer(I)Lnet/minecraft/client/font/GlyphRenderer;"))
    private GlyphRenderer replaceSelf(FontStorage instance, int codePoint) {
        if (instance instanceof FakeFontStorage) {
            return RichOrderedText.RENDERER.get();
        } else {
            return instance.getGlyphRenderer(codePoint);
        }
    }
}
