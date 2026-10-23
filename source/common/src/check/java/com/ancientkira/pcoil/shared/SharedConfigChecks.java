package com.ancientkira.pcoil.shared;
import java.nio.file.*;
import java.util.Comparator;
import com.ancientkira.pcoil.shared.configuration.*;
/** Plain Java build check: loads no Minecraft client or server. */
public final class SharedConfigChecks {
 public static void main(String[] args) throws Exception {
  Path config=Files.createTempDirectory("pcoil-config-check-");
  try {
   SharedConfig.load(config);
   check(Files.isRegularFile(config.resolve("PowerCoils-Client.json")),"Client defaults not written");
   check(Files.isRegularFile(config.resolve("PowerCoils-Server.json")),"Server defaults not written");
   String snapshot=SharedConfig.serverJson();
   check(!snapshot.contains("Alt Coil Model"),"Client-only setting leaked into server sync");
   PowerCoilsConfigConfiguration.ALT_COIL_MODEL.set(true);
   PowerCoilsServerConfigConfiguration.GRAVITY_COIL_JUMP.set(9.0);
   SharedConfig.applyServerJson(snapshot);
   check(PowerCoilsServerConfigConfiguration.GRAVITY_COIL_JUMP.get()==3.0,"Server sync failed to restore defaults");
   check(PowerCoilsConfigConfiguration.ALT_COIL_MODEL.get(),"Server sync overwrote client visuals");
   Files.writeString(config.resolve("PowerCoils-Server.json"),"{\"Speed Coil Speed Level\":7.0}");
   SharedConfig.load(config);
   check(PowerCoilsServerConfigConfiguration.SPEED_COIL_SPEED.get()==7.0,"Config file override was ignored");
   boolean rejected=false;
   try { SharedConfig.applyServerJson("{\"Speed Coil Speed Level\":-1}"); }
   catch (IllegalArgumentException expected) { rejected=true; }
   check(rejected,"Negative effect level was accepted");
   check(PowerCoilsServerConfigConfiguration.SPEED_COIL_SPEED.get()==7.0,"Rejected level changed the active setting");
   System.out.println("Passed config creation, file overrides, server/client isolation, synchronization and invalid-level checks.");
  } finally {
   try (var paths=Files.walk(config)) { for (Path file:paths.sorted(Comparator.reverseOrder()).toList()) Files.delete(file); }
  }
 }
 private static void check(boolean condition,String message) { if (!condition) throw new AssertionError(message); }
}
