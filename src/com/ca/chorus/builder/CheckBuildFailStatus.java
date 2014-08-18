package com.ca.chorus.builder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CheckBuildFailStatus {
	
	public static String checkBuildStatus(String file)
	
	{
		String resumefrom = "";
		BufferedReader br = null;
		 
		try {
 
			String sCurrentLine;
			String serrorline;
			String errorlines = "";
			br = new BufferedReader(new FileReader(file));
 
			while ((sCurrentLine = br.readLine()) != null) {
				
				if(sCurrentLine.contains("[ERROR]"))
				{				
					
					errorlines += sCurrentLine+"<br>";
				
				}
				if(sCurrentLine.contains("FAILURE ["))
				{				
					
					resumefrom = "<br><br>"+" <b>Failed Project :</b>"+sCurrentLine+" </br>";
				
				}
				if(sCurrentLine.contains("BUILD FAILURE"))
				{
									
					while((serrorline = br.readLine()) != null)
					{
						if(serrorline.contains("mvn <goals> -rf :"))
						{
							resumefrom +=  "<br>"+"Resume From : "+serrorline.substring(serrorline.indexOf(":")+1);
						}
					}
					resumefrom = errorlines+resumefrom;
					//System.out.println(resumefrom);
				}
				else if(sCurrentLine.equals("BUILD SUCCESS"))
					
				{
					break;
				}
				
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return resumefrom ;
		
	}

}
