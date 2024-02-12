package org.ladysnake.blabber.impl.mixin;

import net.minecraft.text.Style;
import org.ladysnake.blabber.impl.common.richtext.FakeFontStorage;
import org.ladysnake.blabber.impl.common.richtext.RichOrderedText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net/minecraft/client/font/TextRenderer$Drawer")
public abstract class TextRendererDrawerMixin {
    @Inject(method = "accept", at = @At("HEAD"), cancellable = true)
    private void replaceSelf(int i, Style style, int j, CallbackInfoReturnable<Boolean> cir) {
        if (style.getFont().equals(FakeFontStorage.FAKE_FONT_ID)) {
            RichOrderedText.current.get().render((ITextRendererDrawer) this);
            cir.setReturnValue(true);
        }
    }
}
