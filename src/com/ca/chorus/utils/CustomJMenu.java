package com.ca.chorus.utils;

import java.awt.Color;
import javax.swing.JMenu;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class CustomJMenu extends JMenu{
	
	public CustomJMenu(String text){
							
							
							super(text);
							
							super.setForeground(Color.BLUE);
							super.setBackground(Color.WHITE);
							super.setOpaque(true);
							UIManager.put("Menu.selectionForeground", Color.WHITE);
							UIManager.put("Menu.selectionBackground", Color.BLUE);
	}
	
	
	
}
