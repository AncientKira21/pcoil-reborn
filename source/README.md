# Power Coils — NeoForge, Fabric and Quilt

This local multi-module project builds Power Coils for Minecraft 26.1.2, 26.2
and 26.3. All source and build outputs remain under their module folders.

## Modules and JARs

| Module | Source | JAR output | Purpose |
| --- | --- | --- | --- |
| Common | `common/src/` | `common/build/libs/` | Compiled loader-independent runtime and resources |
| NeoForge | `neoforge/src/` | `neoforge/build/libs/` | NeoForge mod |
| Fabric | `fabric/src/` | `fabric/build/libs/` | Fabric mod with Common bundled |
| Quilt | `quilt/src/` | `quilt/build/libs/` | Quilt-targeted mod with Fabric hooks and Common bundled |

Common is a library, not a standalone Minecraft mod. Do not install it separately;
Fabric and Quilt JARs already contain its classes and resources. NeoForge includes
its native implementation and the same shared assets. The Quilt JAR uses Quilt
Loader's Fabric compatibility and includes separate Quilt entrypoint classes and
a `quilt_loader` dependency in its Fabric-format metadata. It is not an empty
scaffold or a QSL mod. Quilt retired QSL/QFAPI for 26.1 and newer:
https://quiltmc.org/en/blog/2026-02-03-non-obfuscated-updates/

## Build

Use JDK 25. The wrapper is included; first builds need internet access.
Run commands from this project root.

```sh
./gradlew build
./gradlew -PmcVersion=26.2 build
./gradlew -PmcVersion=26.3 build
```

The default target is 26.1.2. Use `./build-all.sh` (macOS/Linux) or
`build-all.bat` (Windows) to build all four modules for all three versions.
On Windows replace `./gradlew` with `gradlew.bat`.

Individual modules can be built with `:common:build`, `:neoforge:build`,
`:fabric:build`, or `:quilt:build`. Intermediate files are isolated in each
module's `build/targets/<version>/`; final JARs and `-sources.jar` files are
collected in that module's `build/libs/`.

Examples:

- `fabric/build/libs/pcoil-fabric-1.0.0+26.1.2.jar`
- `quilt/build/libs/pcoil-quilt-1.0.0+26.2.jar`
- `common/build/libs/pcoil-common-1.0.0+26.3.jar`

These build commands never open Minecraft or install mods into launcher instances.
The Common check task runs plain Java configuration checks without initializing
Minecraft. Gameplay, dedicated-server behavior and live loader compatibility
have not been verified.

## Dependencies

| Minecraft | NeoForge | Fabric API |
| --- | --- | --- |
| 26.1.2 | 26.1.2.114 | 0.155.3+26.1.2 |
| 26.2 | 26.2.0.88 | 0.161.0+26.2 |
| 26.3 | 26.3.0.69-beta | 0.162.0+26.3 |

Fabric builds use Fabric Loader 0.19.3. Quilt builds require a Quilt Loader
release compatible with the selected Minecraft version and its Fabric API.
Use regular Fabric API on Quilt for these versions, not retired QFAPI/QSL.
The 26.3 NeoForge dependency is a beta.

Install only the mod JAR matching the Minecraft version and loader. Fabric and
Quilt require Fabric API separately. Never install multiple loader variants of
Power Coils together or install a `-sources.jar` as the mod.

## Behavior and configuration

All six coils, crafting recipes, inventory models, wearable coil geometry,
deploy sounds, particles, alternate models, cooldowns and server-controlled
settings are implemented. Fabric/Quilt callbacks connect the shared runtime to
registry bootstrap, player ticks, creative tabs, networking and armor rendering.
The shared settings are synchronized from server to client; client model
preferences remain local. Internal marker effects are hidden in the inventory.

NeoForge retains its `PowerCoils-Client.toml` and `PowerCoils-Server.toml` files.
Fabric and Quilt use `config/PowerCoils-Client.json` and
`config/PowerCoils-Server.json`, created at normal mod initialization. They retain
the original setting names and defaults. Restart to apply JSON file changes;
there is no automatic TOML conversion or live JSON reload.

Shared assets/data are in `common/src/main/resources/`. Java runtime source is
in `common/src/main/java/com/ancientkira/pcoil/shared/`. Each loader's metadata
and entrypoints live in its own `src`. Version-specific armor/config adapters
are generated at build time. Source resources contain metadata templates; use
the built JARs rather than raw resource directories.

## Credits

Original Power Coils by Maxsteelbro, recovered from the supplied extracted mod.
The supplied metadata declares MIT License. The recovered and ported Java source
is included; the original MCreator workspace and author comments were unavailable.

## Naming

Java packages use `com.ancientkira.pcoil`; the mod/resource ID is `pcoil`.
JARs follow `<mod_id>-<module>-<version>+<mcVersion>.jar`. `mod_id` and
`version` come from `gradle.properties`; `mcVersion` selects the build target.
Source archives use the same stem followed by `-sources.jar`.
