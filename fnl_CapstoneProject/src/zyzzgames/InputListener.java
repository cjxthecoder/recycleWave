/*
 * Copyright (c) 2022, Team Zyzz LLC, All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation. Team Zyzz designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Team Zyzz in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * Please contact Sunnyvale, Rockefeller Dr, Apt 3B, CA 94097 USA if you
 * need additional information or have any questions.
 */

package zyzzgames;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * The InputListener class is a {@link KeyAdapter} that invokes the built-in
 * keyPressed and keyReleased functions to take keyboard input from the user.
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
		GameWindow.p.keyPressed(e);
	}	
	@Override
	public void keyReleased(KeyEvent e) {
		GameWindow.p.keyReleased(e);
	}
}
