package org.ladysnake.blabber.impl.common.richtext;

import com.mojang.serialization.Codec;
import org.ladysnake.blabber.impl.common.BlabberRegistrar;

public final class TextComponentType<T extends TextComponentRenderer> {
    public static final Codec<RichOrderedText> CODEC =
            BlabberRegistrar.TEXT_COMPONENT_REGISTRY.getCodec()
                    .dispatch("type", RichOrderedText::getType, TextComponentType::getCodec);

    private final Codec<RichOrderedText> codec;


    public TextComponentType(Codec<T> codec, float width) {
        // This case is okay because our codec only returns RichOrderedText returned by this codec.
        // Maybe.
        // I'm not sure.
        //noinspection unchecked
        this.codec = codec.xmap(r -> new RichOrderedText(r, width), t -> (T) t.getRenderer());
    }

    public Codec<RichOrderedText> getCodec() {
        return codec;
    }
}
