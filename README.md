<p align="center">
    <img src="src/main/resources/assets/simple-survival-tweaks/icon.png" width="72px" height="72px"/>
</p>

<h1 align="center">Simple Survival Tweaks</h1>

<p align="center">
    <img src="https://img.shields.io/badge/for%20MC-1.19.4,%201.20-green" alt="Minecraft version"/>
    <img src="https://img.shields.io/github/v/release/legoraft/simple-survival-tweaks?color=yellow" alt="Release"/>
    <img src="https://img.shields.io/modrinth/dt/nF5XkJko?label=modrinth"/>
    <img src="https://img.shields.io/github/downloads/legoraft/simple-survival-tweaks/total" alt="Downloads"
</p>

## Information

### Features
This mod adds some small tweaks and qol rules to your survival world. These rules are changeable through the `survivaltweaks.properties` file.

The current feature list contains:
- No 'too expensive' warning which prevents having a lot of enchants on a single item. _Note: This also makes enchanting in an anvil cheaper_
- No enderman griefing, except pumpkins and melons for farming purposes.
- Make the debug stick functional in survival.
- Cheap renaming, which allows renaming items to always cost 1 level.
- No xp penalty, which removes the 7 level cap for dropped xp on death, and instead drops 50% of your xp.

Check out a full explanation of the features and the config file on the [wiki](https://github.com/legoraft/simple-survival-tweaks/wiki)

### Bugs
If you have any bug reports or a suggestion for the mod leave them [here](https://github.com/LegoRaft/simple-survival-tweaks/issues)

### Dependencies
Simple survival tweaks requires the [Fabric API](https://modrinth.com/mod/fabric-api)

### Installation
Releases for the mod are found on [Modrinth](https://modrinth.com/mod/simple-survival-tweaks) and at [Releases](https://github.com/LegoRaft/simple-survival-tweaks/releases). <br>
For an installation tutorial, go [here](https://fabricmc.net/wiki/install). _Note: Simple armor hud **requires** the Fabric API_

### Build
You can also build the mod directly from the github repository. <br>
For this, download or clone the github repository. <br>
Next, go to the directory with `cd user/directory/mod` (where you use the actual directories where the mod is stored, this is just an example.) <br>
After this, run `gradlew build`. <br>
When the task is completed, go to the 'build' and the 'libs' folders, and drag the simple-survival-tweaks-1.x.x-x.x.x file to your mods folder. Ignore the -dev, -sources and -sources-dev files, you can delete these.
