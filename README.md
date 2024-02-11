# Hunt the Wumpus

## Overview

This project is a modernized version of the classic Hunt the Wumpus game, originally developed in the 1970s. The game has been revamped with graphical elements while retaining the nostalgic atmosphere of the original. Key features include the introduction of arrows and treasures, an in-game map, a randomized cave network, and a user-friendly interface.

## Specifiations

- Introduction of arrows and treasures.
- Limited in-game visibility to just cave directions.
- Integration of an in-game map.
- Randomized cave network using cellular automata.
- JSwing facade for UI elements.
- ViewManager for scene management and display.
- GameStateManager for managing state transitions.

## Run the Program

Run `src/Main.java`.

## Gameplay

- The objective is to navigate through a network of caves to find and eliminate the Wumpus while collecting treasures.
- Use arrows to attack the Wumpus.
- Beware of hazards like bottomless pits and bats.
- The game ends when the Wumpus or the player is defeated.

## Implementation Details

- MVC pattern
- The game utilizes Java and JSwing for the GUI.
- Cave networks are generated using cellular automata for randomness.
- ViewManager handles scene rendering, while GameStateManager manages state transitions.
- Various UI elements are displayed to enhance user experience.

## Acknowledgments

- This project was completed as part of CS1006 coursework.
