package org.ladysnake.blabber.impl.common.richtext;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.text.*;
import net.minecraft.util.dynamic.Codecs;
import org.ladysnake.blabber.api.DialogueIllustration;
import org.ladysnake.blabber.api.DialogueIllustrationType;
import org.ladysnake.blabber.impl.common.BlabberRegistrar;

import java.util.List;

public class TextCodecExtensions {
    public static final Codec<Text> CODEC = Codecs.createRecursive("Component", TextCodecExtensions::createCodec);

    // Straight up copied from TextCodecs, but with a little bit added.
    private static Codec<Text> createCodec(Codec<Text> selfCodec) {
        TextContent.Type<?>[] types = new TextContent.Type[]{
                PlainTextContent.TYPE, TranslatableTextContent.TYPE, KeybindTextContent.TYPE, ScoreTextContent.TYPE, SelectorTextContent.TYPE, NbtTextContent.TYPE
        };
        MapCodec<TextContent> mapCodec = TextCodecs.dispatchingCodec(types, TextContent.Type::codec, TextContent::getType, "type");
        Codec<Text> codec = Codecs.alternatively(
                RecordCodecBuilder.create(
                        instance -> instance.group(
                                mapCodec.forGetter(Text::getContent),
                                Codecs.createStrictOptionalFieldCodec(Codecs.nonEmptyList(selfCodec.listOf()), "extra", List.of()).forGetter(Text::getSiblings),
                                Style.Codecs.MAP_CODEC.forGetter(Text::getStyle)
                                )
                                .apply(instance, (content, siblings, style) -> {
                                    // We do this manually because the constructor's package-private :/
                                    // A mixin would make this much easier buuuut
                                    // It's an extra mixin.
                                    // REVIEWERS: should I make a mixin for all of this?
                                    MutableText text = MutableText.of(content);
                                    siblings.forEach(text::append);
                                    text.setStyle(style);
                                    return text;
                                })
                ),
                TextComponentType.CODEC.xmap(RichText::new, RichText::interior)
        );
        return Codec.either(Codec.either(Codec.STRING, Codecs.nonEmptyList(selfCodec.listOf())), codec)
                .xmap(either -> either.map(either2 -> either2.map(Text::literal, TextCodecExtensions::combine), text -> text), text -> {
                    String string = text.getLiteralString();
                    return string != null ? Either.left(Either.left(string)) : Either.right(text);
                });
    }

    private static MutableText combine(List<Text> texts) {
        MutableText mutableText = ((Text)texts.get(0)).copy();

        for(int i = 1; i < texts.size(); ++i) {
            mutableText.append((Text)texts.get(i));
        }

        return mutableText;
    }
}
