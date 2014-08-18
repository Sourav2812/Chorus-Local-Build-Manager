package com.ca.chorus.builder;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ProcessBuilder.Redirect;

import javax.swing.JTextArea;

import com.ca.chorus.utils.CustomJButton;

public class ChorusMavenCleaner{
	
	public static int mavenCleaner(final String workspacepath, String role, CustomJButton clean_status_button){
		
		//clean_status_button.setBackground(Color.YELLOW);
		String cmd = "";
//		clean_status_button.setBackground(Color.YELLOW);
		
		int returncode = 0;
		if(role.equalsIgnoreCase("None"))
		{
			
			cmd = "mvn clean";
			
		}
		else{
			cmd = "mvn clean package -P"+role;
		}
		//System.out.println("Sourav");
		clean_status_button.setOpaque(true);
    	clean_status_button.setBackground(Color.YELLOW);
    	clean_status_button.setText("CLEAN RUNNING!");
    	

	ProcessBuilder builder = new ProcessBuilder(	         
			
			"cmd.exe", "/c", "cd \""+workspacepath+"\\parent\\server\\chorus-server-run\" && "+cmd+" > cleanLogDemo.txt");
	  
	   
       builder.redirectErrorStream(true);
       builder.redirectOutput();
        Process p;
		try {
			
			p = builder.start();
			BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
			returncode = p.waitFor();
			
			if(returncode == 0){
			clean_status_button.setBackground(Color.GREEN);
			clean_status_button.setText("CLEAN SUCCESS!");
			}
			else{
				clean_status_button.setBackground(Color.RED);
				clean_status_button.setText("CLEAN FAILURE!");
			}
			System.out.println(returncode);
			System.out.println(cmd);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return returncode;
	}
	

	
	
}
