# Parkour Plugin PaperMc
This is a plugin to add configurable parkours to minecraft servers.

### Features
- Create and edit parkours and checkpoints
- Restrict the plugin to only work within certain worlds
- Permissions for creating parkours
- Items in hotbar for resetting or quitting

### Planned Features
- Checkpoints to add potion effects to the player (until the next checkpoint is reached)
- Personal and server-wide highscores

## Building from source
1. Clone the repository using
    ```bash
    git clone https://github.com/HiWiSciFi/io.github.hiwiscifi.mc.plugins.parkour.git
    ```
2. Execute the following in the root directory of the repository
    ```bash
    ./gradlew build
    ```
3. Find the compiled plugin in the `build/libs` directory

## Development
If you want to contribute to the plugin then I recommend the following procedure:

1. Download a java IDE that preferably supports Gradle as a build system. The two most popular IDEs would be
   [IntelliJ IDEA Community](https://www.jetbrains.com/idea/download/?section=windows) (scroll down on the download page)
   and [Eclipse IDE for Java Developers](https://www.eclipse.org/downloads/).
2. Also download and install [git](https://git-scm.com/downloads)
3. Download and install [Minecraft Java Edition](https://www.minecraft.net/de-de/store/minecraft-java-bedrock-edition-pc)
   (for testing plugin features)
4. Clone the repository using
    ```bash
   git clone https://github.com/HiWiSciFi/io.github.hiwiscifi.mc.plugins.parkour.git
    ```
5. Open the downloaded directory as a gradle project in your IDE
6. <b>Start Coding!</b> The following steps are *optional* but *recommended*
7. Set the `run` action of your IDE to execute the `runServer` gradle task (`./gradlew runServer`)
8. Open up Minecraft, go to the Multiplayer Tab and add a server. Use `localhost` as the IP/Hostname
9. To test the current state of the plugin just run the `runServer` task and connect to the newly added server
