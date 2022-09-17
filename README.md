# Capstone Project: Recycle Wave

Team name: Zyzz

Team members: Brandon Tsao, Ali Haryanawalla, Daniel Cheng

Document rev date: 5/24/22

## Program purpose:
A game with a character that moves up and down with a wave pattern and try and not to hit the sides, or you lose the game. An environmental idea is to have a plastic bottle and a tree or coral, and you try and make the bottle not hit the coral or the fish. 

## Target user profile:
Children or adults any ages

## Instructions: 
1. Press the run button
2. It will load you into a window that may or may not have a background. On top of the title there are 2 rectangles that says to "drag your cursor here" or "over there> AS WELL!". Drag your mouse there.
3. Dragging to the left rectangle will create the "Play" button. Click this to play the game. Dragging to the area pointed by the text "over there>" creates a drop down menu, where you can choose the speed of the game.
4. Once the game is running, your player will land on a platform before entering a cool portal. You can press the spacebar, enter, or up key to move your characater. Holding the key makes your character move up, while releasing the key allows you chracter to fall down.
5. Reach 100% to complete the level, where your final score will be given exactly by 100/((1.0185508475756433)^(deaths)). After the level is completed you can rerun the program to try the game at a different speed.

## Class List:
- Audio
- Collision
- GameSound
- GameWindow
- InputListener
- LevelEditor
- Main
- Player
- RunningPlayer
## Interface List:
- GameConstants
## Team responsibility:
### Brandon:
- GameConstants.java
- LevelEditor.java
- Main.java
### Ali:
- Audio.java
- Collision.java
- GameSound.java
### Daniel:
- GameWindow.java
- InputListener.java
- Player.java
- RunningPlayer.java
## Bugs & Problems (with fixes)
- A way for the user to choose different speeds to play the game
  - FIX: Using a JComboBox, and a switch-case block to apply different speed depending on what the user had choosen (by using .getSelectedItem())
- After pressing a button, the window can no longer read key inputs from the user
  - FIX: Use .setFocusable(false) on the button component so that after an action is performed, the Key Listener can continue to be focused on the window.
- It is very hard to implement a camera that tracks the player while updating the graphics, as well as the player's position, on the screen!
  - FIX: Make the player stay at a fixed x-position while letting the background of the level move to the left. This creates the illusion of the player moving forward in a level.
- Too much objects in the level for the computer the render?
  - FIX: When there were too much objects, an if statement is used to not draw anything that will appear offscreen.
- The setOffset() method doesn't start the music at the same place
  - FIX: Use a different value as the parameter so that there are more times where the music does start at the same place
- Adding +dx to every 4-5 digit value in all of those 2D arrays...too much work and hard to understand
  - FIX: A function is used so that the position of objects created are fixed to a grid, and thus the position of these objects in grid units are much smaller.
- When the player becomes smaller, using hitbox /= 2 results in the hitbox rapidly reaching zero, and vice versa for becoming bigger
  - FIX: Since the player's hitbox can only be 40x40, 20x20, or 10x10, every transformation scenario can be written out, with what the player's hitboxes will be in each case.
- Button not showing in the title screen
  - FIX: Using the FlowLayout from lipogrammer worked out very well, as well as some instructions for the player written out in text on the window.
- A way to center text
  - Using the .getAscent() method from the FontMetrics class allowed us to get the dimension of any string of text on the screen, and the problem is reduced to centering a rectangle on a rectangle window.
## Key lessons learned
- How to initialize an audio from a file, and how to make the audio stop or start by converting it into a clip
- How to make the program read key input from the user
- How to detect collision between various shapes
- How to create a drop-down menu with a list of options (JComboBox)
- How thread and runnable works to run a game
## Further development
- If we have more time, we would make more levels, try representing objects in an ArrayList, as well as adding more graphics to the game.
- Other areas of RND:
  - Explore modeling the player's movement by including a vector for x and y velocities so that other gamemodes such as cube or ship can be accurately implemented as well.
  - Remake the project but using JPanel and paintComponent so that there will not issues like the button not showing.
  - Add a camera to track the player.
  - Add a Level Editor GUI so that a level can be edited directly by the user in a window.
## Credit List
### Websites
- Java API
  - Allowed us to learn about Fonts, Graphics2D, Stroke, RenderingHint, and much more
- Music: F-777 - Sonic Blaster (HJfod Remix) - https://www.newgrounds.com/audio/listen/976863 (Free for use)
- Most of the game graphics: https://geometry-dash.fandom.com/wiki/Level_Components
- Plastic bottle cube: https://www.google.com/search?q=plastic+bottle+clipart&rlz=1CAJIKU_enUS966&source=lnms&tbm=isch&sa=X&ved=2ahUKEwjDme66z_v3AhWym44IHVOLBGYQ_AUoAXoECAEQAw&cshid=1653515153297890&biw=1366&bih=617&dpr=1&safe=active&ssui=on#imgrc=1L2HNFPNM7oy_M
- Plastic bottle wave: https://www.nicepng.com/ourpic/u2q8e6y3i1w7r5u2_plastic-bottles-clipart-full-water-bottle-clipart-png
- Recycle Bin: https://www.hiclipart.com/free-transparent-background-png-clipart-iexyq
### Labs
- Diploma
  - How to use the extends and super keywords to achieve a IS-A relationship
- Lipogrammer
  - How to create a button on a JFrame with a container and a layout
- Foot
  - How to draw images on the window
- Stars
  - How to use for-loops
