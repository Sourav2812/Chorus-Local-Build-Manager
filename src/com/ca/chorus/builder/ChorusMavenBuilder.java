package com.ca.chorus.builder;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.ca.chorus.utils.CustomJButton;

public class ChorusMavenBuilder {
	public static int mavenBuilder(final String workspacepath, final String command, CustomJButton build_status_button, String mailid){
		int returncode = 0;
		
  		    	ProcessBuilder builder = new ProcessBuilder(	         
            			"cmd.exe", "/c", "cd \""+workspacepath+"\\parent\" && buildLogDemo.txt");
    	            builder.redirectErrorStream(true);
    	            Process p;
    	            
					try {
						p = builder.start();
						BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
  		    	
  				ProcessBuilder builder1 = new ProcessBuilder(	         
  						"cmd.exe", "/c", "cd \""+workspacepath+"\\parent\" && "+command+" > buildLogDemo.txt");
  				builder1.redirectErrorStream(true);
  				try{
  		        Process p1;
  		        		
  		        	p1 = builder1.start();
  					BufferedReader r = new BufferedReader(new InputStreamReader(p1.getInputStream()));
  					returncode = p1.waitFor();
  					if(returncode == 0){
  						build_status_button.setBackground(Color.GREEN);
  						build_status_button.setText("BUILD SUCCESS!");
  						}
  						else{
  							build_status_button.setBackground(Color.RED);
  							build_status_button.setText("BUILD FAILURE!");
  							
								String contents = "Hi, <br>  Build Failure Occured at "+new SimpleDateFormat("dd/MM/YYYY HH:mm:ss").format(Calendar.getInstance().getTime())+" HRS.";
								String resumefrom = CheckBuildFailStatus.checkBuildStatus("C:\\Users\\"+mailid+"\\WorkspaceLatest\\parent\\buildLogDemo.txt");
								contents += "<br><br>"+resumefrom+"<br><br>This is an Auto Generated Email. Please do not reply to this one.";
								new SendMail().test(contents,mailid+"@ca.com");
  							
  						}
  				}catch(Exception e)
  				{
  					e.printStackTrace();
  				}
  					System.out.println(returncode);
  					return returncode;
					
  		     
  		       
  		    	
  		        	
  		 
		
		
		
	}
	
}
