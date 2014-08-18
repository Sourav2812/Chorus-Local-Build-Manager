package com.ca.chorus.utils;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class CustomJLabel extends JLabel{
	
	public CustomJLabel(String text){
							
							super(text);
							super.setForeground(Color.BLUE);
							//super.setFon
							
	}
	
	public CustomJLabel(String s, int center) {
		// TODO Auto-generated constructor stub
		@SuppressWarnings("unused")
		JLabel jl1 = new JLabel(s,center);
	}

	

}
