package zyzzgames;
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
import java.awt.Color;
import javax.swing.JFrame;

/**
 * The main method for our game.
 * 
 * @author Brandon Tsao
 *
 * @since 1.0
 */

public class Main extends JFrame
{	
	public static void main(String[] args)
	{
		GameWindow gw = new GameWindow();
		gw.setBounds(0, 0, 1536, 840);
		gw.setBackground(new Color(20, 90, 230));
		gw.setDefaultCloseOperation(EXIT_ON_CLOSE);
		gw.setResizable(false);
		gw.setVisible(true);
	}
}
