package com.ancientkira.pcoil.shared.network;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
public record CoilConfigPayload(String json) implements CustomPacketPayload {
 public static final Type<CoilConfigPayload> TYPE=new Type<>(Identifier.parse("pcoil:shared_config"));
 public static final StreamCodec<RegistryFriendlyByteBuf,CoilConfigPayload> CODEC=StreamCodec.of((buf,data)->buf.writeUtf(data.json,32767),buf->new CoilConfigPayload(buf.readUtf(32767)));
 @Override public Type<CoilConfigPayload> type() { return TYPE; }
}
