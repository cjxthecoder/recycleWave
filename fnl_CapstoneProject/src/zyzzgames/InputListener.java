/*
 * Copyright (c) 2022, 2025, Team Zyzz and/or its affiliates, All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.
 * 
 * Please contact danielcheng2200@gmail.com if you need additional information
 * or have any questions.
 * 
 * Current Version: 1.5
 */

package zyzzgames;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * The InputListener class is a {@link KeyAdapter} that invokes the built-in
 * keyPressed and keyReleased methods to take keyboard input from the user.
 * When one of those functions is called, the keyPressed() and keyReleased()
 * functions (not related to KeyAdapter) from the Player class will be called,
 * resulting in movement of the Player.
 * 
 * @author Daniel Cheng
 *
 * @since 1.0
 */

public class InputListener extends KeyAdapter
{
	@Override
	public void keyPressed(KeyEvent e) {
		GameWindow.getP().keyPressed(e);
	}
	@Override
	public void keyReleased(KeyEvent e) {
		GameWindow.getP().keyReleased(e);
	}
}
