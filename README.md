# Cybersecurity Capstone final project

A game for the Cybersecurity Capstone class made with Java Swing.

## Authors

* Sohun Tambe (@soru4) - Level2, Level 3, Level 4, Level 5, Level 6, Scenario, ComputerComponent recursive checkers, Scene.
* Leonid Lednev (@leonidlednev2) - Level0, Level1, ComputerComponent
* Brandon Wright (@EnderTheWigg) - Level 2, Game Framework, Logic, Level 4, Level 5.

Licensed under the MIT.

## Running

```bash
java source/Runner.java
```

## How to play

0. The game will generate a scenario, including the budget, workforce, and network type.
1. Buy routers, PCs, servers, and switches from the store based on the scenario given
2. Connect the components together given the following rules:
   * Internet (1 "port") can only be connected to the router (5 ports)
   * Routers can be connected to switches (20 ports) and servers (2 ports)
   * Switches can be connected to PCs (1 port) and servers
   * All components must be connected to each other
3. Configure network IPs
4. Answer questions based on the OSI model
5. Same as 4
6. Answer some cryptographic questions to set up security features

## TODO

* Create Level 7. (Type in a url and the game plays an animation going back through everything that you did)
* Make this game have a proper build system (Maven, Gradle, ...)
* Add a release with the jar
* Make a Windows wrapper for the jar
