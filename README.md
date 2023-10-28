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
This mod adds some small tweaks and QOL rules to your survival world. These rules are changeable through the `survivaltweaks.properties` file and the in-game option screen which requires [mod menu](https://modrinth.com/mod/modmenu).

### Features
The current feature list contains:
- No 'too expensive' warning which prevents having a lot of enchants on a single item. _Note: This also makes enchanting in an anvil cheaper_
- No enderman griefing, except pumpkins and melons for farming purposes.
- Make the debug stick functional in survival. This changes the debug stick behavior in survival, with shift-right click cycling through properties and right click editing them.
- Cheap renaming, which allows renaming items to always cost 1 level.
- No xp penalty, which removes the 7 level cap for dropped xp on death, and instead drops 50% of your xp.

Check out a full explanation of the features and the config file on the [wiki](https://github.com/legoraft/simple-survival-tweaks/wiki)

### Bugs
If you have any bug reports or a suggestion for the mod leave them [here](https://github.com/legoraft/simple-survival-tweaks/issues). If you have any coding experience and want to help out with development, fork the repository and open a [pull request](https://github.com/legoraft/simple-survival-tweaks/pulls).

### Dependencies
Simple survival tweaks requires the [Fabric API](https://modrinth.com/mod/fabric-api) and has an option screen accessible through [Mod Menu](https://modrinth.com/mod/modmenu).

### Installation
Releases for the mod are found on [Modrinth](https://modrinth.com/mod/simple-survival-tweaks) and at [Releases](https://github.com/LegoRaft/simple-survival-tweaks/releases). After downloading, you can put the `.jar` file in your `mods` folder. If you don't have fabric installed, take a look at this [installation tutorial](https://fabricmc.net/wiki/install) _Note: Simple survival tweaks **requires** the Fabric API_

### Build
To build from source, follow these steps:
1. Open a terminal and clone the repository using `git clone https://github.com/legoraft/simple-survival-tweaks`.
2. Go into this directory using `cd <location of cloned repo>`.
3. Run `./gradlew build` on linux or macos or `gradlew build` on windows.
4. Get the mod file from the `/build/libs` folder.
