package com.ca.chorus.builder;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.ca.chorus.utils.CustomJButton;

public class ChorusCargoRunner {
	
	
public static void runCargo(String workspacepath, String command,CustomJButton cargo_status_button){
	
	ProcessBuilder builder1 = new ProcessBuilder(	         
        	"cmd.exe", "/c", "cd \""+workspacepath+"\\parent\\server\\chorus-server-run\" && START "+command);
				
	            builder1.redirectErrorStream(true);
	            Process p1;
				try {
					p1 = builder1.start();
					BufferedReader r1 = new BufferedReader(new InputStreamReader(p1.getInputStream()));
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					cargo_status_button.setBackground(Color.RED);
				}
	            

}
}
