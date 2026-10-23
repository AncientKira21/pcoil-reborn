package com.ancientkira.pcoil.shared.configuration;
import com.google.gson.*;
import java.io.IOException;
import java.nio.file.*;
public final class SharedConfig {
 private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
 public static final class Value<T> {
  private volatile T value;
  public Value(T value) { this.value=value; }
  public T get() { return value; }
  public void set(T value) { this.value=value; }
 }
 public static void load(Path directory) {
  try {
   Files.createDirectories(directory);
   loadFile(directory.resolve("PowerCoils-Client.json"), true);
   loadFile(directory.resolve("PowerCoils-Server.json"), false);
  } catch (IOException e) { throw new IllegalStateException("Could not load Power Coils configuration",e); }
 }
 private static void loadFile(Path file, boolean client) throws IOException {
  if (Files.exists(file)) apply(JsonParser.parseString(Files.readString(file)).getAsJsonObject(), client);
  else Files.writeString(file,GSON.toJson(snapshot(client)));
 }
 public static String serverJson() { return GSON.toJson(snapshot(false)); }
 public static void applyServerJson(String json) { apply(JsonParser.parseString(json).getAsJsonObject(),false); }
 private static JsonObject snapshot(boolean client) {
  JsonObject json=new JsonObject();
  if (client) {
   json.addProperty("Alt Coil Model",PowerCoilsConfigConfiguration.ALT_COIL_MODEL.get());
  } else {
   json.addProperty("Antispam",PowerCoilsServerConfigConfiguration.ANTISPAM.get());
   json.addProperty("Mute All Deploy Sounds",PowerCoilsServerConfigConfiguration.MUTE_DEPLOY_SOUNDS.get());
   json.addProperty("Gravity Coil Jump Boost Level",PowerCoilsServerConfigConfiguration.GRAVITY_COIL_JUMP.get());
   json.addProperty("Snowy Gravity Coil Jump Boost Level",PowerCoilsServerConfigConfiguration.SNOWY_GRAVITY_COIL_JUMP.get());
   json.addProperty("Snowy Gravity Coil Particles",PowerCoilsServerConfigConfiguration.SNOWY_GRAVITY_COIL_PARTICLES.get());
   json.addProperty("Dual Gravity Coil Jump Boost Level",PowerCoilsServerConfigConfiguration.DUAL_GRAVITY_COIL_JUMP.get());
   json.addProperty("Dual Gravity Coil Deactivates by Sneaking",PowerCoilsServerConfigConfiguration.DUAL_GRAVITY_COIL_SNEAKING.get());
   json.addProperty("Speed Coil Speed Level",PowerCoilsServerConfigConfiguration.SPEED_COIL_SPEED.get());
   json.addProperty("Speed Coil Particles",PowerCoilsServerConfigConfiguration.SPEED_COIL_PARTICLES.get());
   json.addProperty("Regen Coil Healing Rate Per Second",PowerCoilsServerConfigConfiguration.REGEN_COIL_HEAL.get());
   json.addProperty("Fusion Coil Jump Boost Level",PowerCoilsServerConfigConfiguration.FUSION_COIL_JUMP.get());
   json.addProperty("Fusion Coil Speed Level",PowerCoilsServerConfigConfiguration.FUSION_COIL_SPEED.get());
  }
  return json;
 }
 private static void apply(JsonObject json,boolean client) {
  if (client) {
   if (json.has("Alt Coil Model")) PowerCoilsConfigConfiguration.ALT_COIL_MODEL.set(json.get("Alt Coil Model").getAsBoolean());
  } else {
   if (json.has("Antispam")) PowerCoilsServerConfigConfiguration.ANTISPAM.set(json.get("Antispam").getAsBoolean());
   if (json.has("Mute All Deploy Sounds")) PowerCoilsServerConfigConfiguration.MUTE_DEPLOY_SOUNDS.set(json.get("Mute All Deploy Sounds").getAsBoolean());
   if (json.has("Gravity Coil Jump Boost Level")) PowerCoilsServerConfigConfiguration.GRAVITY_COIL_JUMP.set(checked(json.get("Gravity Coil Jump Boost Level").getAsDouble()));
   if (json.has("Snowy Gravity Coil Jump Boost Level")) PowerCoilsServerConfigConfiguration.SNOWY_GRAVITY_COIL_JUMP.set(checked(json.get("Snowy Gravity Coil Jump Boost Level").getAsDouble()));
   if (json.has("Snowy Gravity Coil Particles")) PowerCoilsServerConfigConfiguration.SNOWY_GRAVITY_COIL_PARTICLES.set(json.get("Snowy Gravity Coil Particles").getAsBoolean());
   if (json.has("Dual Gravity Coil Jump Boost Level")) PowerCoilsServerConfigConfiguration.DUAL_GRAVITY_COIL_JUMP.set(checked(json.get("Dual Gravity Coil Jump Boost Level").getAsDouble()));
   if (json.has("Dual Gravity Coil Deactivates by Sneaking")) PowerCoilsServerConfigConfiguration.DUAL_GRAVITY_COIL_SNEAKING.set(json.get("Dual Gravity Coil Deactivates by Sneaking").getAsBoolean());
   if (json.has("Speed Coil Speed Level")) PowerCoilsServerConfigConfiguration.SPEED_COIL_SPEED.set(checked(json.get("Speed Coil Speed Level").getAsDouble()));
   if (json.has("Speed Coil Particles")) PowerCoilsServerConfigConfiguration.SPEED_COIL_PARTICLES.set(json.get("Speed Coil Particles").getAsBoolean());
   if (json.has("Regen Coil Healing Rate Per Second")) PowerCoilsServerConfigConfiguration.REGEN_COIL_HEAL.set(checked(json.get("Regen Coil Healing Rate Per Second").getAsDouble()));
   if (json.has("Fusion Coil Jump Boost Level")) PowerCoilsServerConfigConfiguration.FUSION_COIL_JUMP.set(checked(json.get("Fusion Coil Jump Boost Level").getAsDouble()));
   if (json.has("Fusion Coil Speed Level")) PowerCoilsServerConfigConfiguration.FUSION_COIL_SPEED.set(checked(json.get("Fusion Coil Speed Level").getAsDouble()));
  }
 }
 private static double checked(double value) {
  if (!Double.isFinite(value) || value<0 || value>255) throw new IllegalArgumentException("Invalid Power Coils numeric setting: "+value);
  return value;
 }
}
