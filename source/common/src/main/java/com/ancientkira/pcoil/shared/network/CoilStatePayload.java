package com.ancientkira.pcoil.shared.network;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
public record CoilStatePayload(int flags) implements CustomPacketPayload {
 public static final Type<CoilStatePayload> TYPE=new Type<>(Identifier.parse("pcoil:shared_state"));
 public static final StreamCodec<RegistryFriendlyByteBuf,CoilStatePayload> CODEC=StreamCodec.of((buf,data)->buf.writeVarInt(data.flags),buf->new CoilStatePayload(buf.readVarInt()));
 @Override public Type<CoilStatePayload> type() { return TYPE; }
}
