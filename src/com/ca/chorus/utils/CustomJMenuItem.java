package com.ca.chorus.utils;

import java.awt.Color;

import javax.swing.JMenuItem;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class CustomJMenuItem extends JMenuItem{
	
	public CustomJMenuItem(String text){
							
							super(text);
							super.setForeground(Color.BLUE);
							super.setOpaque(true);
							super.setBackground(Color.WHITE);
							UIManager.put("MenuItem.selectionForeground", Color.WHITE);
							UIManager.put("MenuItem.selectionBackground", Color.BLUE);
	}
}
