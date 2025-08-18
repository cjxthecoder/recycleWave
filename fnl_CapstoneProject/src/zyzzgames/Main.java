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
 * Current Version: 1.3
 */

package zyzzgames;

import java.awt.Color;
import javax.swing.JFrame;

/**
 * The main method for our game.
 * 
 * @author Brandon Tsao
 *
 * @since 1.0
 */

public class Main
{	
	public static void main(String[] args)
	{
		GameWindow gw = new GameWindow(0, 0, 1536, 840);
		gw.setBackground(new Color(64, 144, 176));
		gw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gw.setResizable(false);
		gw.setVisible(true);
	}
}
