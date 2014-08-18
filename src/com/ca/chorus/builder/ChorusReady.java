package com.ca.chorus.builder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.ca.chorus.utils.CustomJButton;
import com.ca.chorus.utils.CustomJLabel;

public class ChorusReady{
							public static void chorusReady(String workspacepath, String role,boolean[] operations, String buildcommand, String cargocommand,CustomJButton clean_status_button,CustomJButton build_status_button,CustomJButton cargo_status_button, CustomJButton start_Button)
							
							{
								int cleanstatus = 0;
								int buildstatus = 0;
								String resumefrom = "";
								String cmd = "";
								
								
								if(operations[0] && operations[1]  && operations[2])
								{
									
									cleanstatus = ChorusMavenCleaner.mavenCleaner(workspacepath, role, clean_status_button);
									if(cleanstatus == 0)
									{
										clean_status_button.setBackground(Color.GREEN);
										clean_status_button.setText("CLEAN SUCCESS!");
										buildstatus = ChorusMavenBuilder.mavenBuilder(workspacepath, buildcommand, build_status_button,ReadConfig.readConfig("UserId"));
										
										if(buildstatus == 0)
										{
											ChorusCargoRunner.runCargo(workspacepath, cargocommand,cargo_status_button);
											cargo_status_button.setBackground(Color.YELLOW);
											cargo_status_button.setText("CARGO RUNNING!");
										}
										else if(buildstatus == 1)
										{
											build_status_button.setBackground(Color.RED);
											start_Button.setText("RESUME");
											cmd = ReadConfig.readConfig("build")+" -rf :"+resumefrom;
					          		    	resumebuild(workspacepath,resumefrom,cmd);
										}
									}
									else if(cleanstatus == 1){
										System.out.println("Clean up Failure");
										clean_status_button.setBackground(Color.RED);
									}
									
								}
								else if(operations[0] && operations[1])
								{
									cleanstatus = ChorusMavenCleaner.mavenCleaner(workspacepath, role, clean_status_button);
									if(cleanstatus == 0)
									{
										clean_status_button.setBackground(Color.GREEN);
										clean_status_button.setText("CLEAN SUCCESS!");
										buildstatus = ChorusMavenBuilder.mavenBuilder(workspacepath, buildcommand, build_status_button,ReadConfig.readConfig("UserId"));
									
									if(buildstatus == 0)
									{
										build_status_button.setBackground(Color.GREEN);	
										build_status_button.setText("BUILD SUCCESS!");
									}
									else if(buildstatus == 1)
									{
										build_status_button.setBackground(Color.RED);
										build_status_button.setText("BUILD FAILURE!");
										start_Button.setText("RESUME");
										cmd = ReadConfig.readConfig("build")+" -rf :"+resumefrom;
				          		    	resumebuild(workspacepath,resumefrom,cmd);
									}
									}
									else if(cleanstatus == 1){
										System.out.println("Clean up Failure");
										clean_status_button.setBackground(Color.RED);
									}
								}
								else if(operations[0] && operations[2])
								{
									cleanstatus = ChorusMavenCleaner.mavenCleaner(workspacepath, role, clean_status_button);
									if(cleanstatus == 0)
									{
										clean_status_button.setBackground(Color.GREEN);
										clean_status_button.setText("CLEAN SUCCESS!");
										ChorusCargoRunner.runCargo(workspacepath, cargocommand,cargo_status_button);
										cargo_status_button.setBackground(Color.YELLOW);
										cargo_status_button.setText("CARGO RUNNING!");
									}
									else if(cleanstatus == 1){
										System.out.println("Clean up Failure");
										clean_status_button.setBackground(Color.RED);
									}
								}
								else if(operations[1] && operations[2])
								{
									buildstatus = ChorusMavenBuilder.mavenBuilder(workspacepath, buildcommand, build_status_button,ReadConfig.readConfig("UserId"));
								
									if(buildstatus == 0)
									{
									build_status_button.setBackground(Color.GREEN);	
									build_status_button.setText("BUILD SUCCESS!");
									ChorusCargoRunner.runCargo(workspacepath, cargocommand,cargo_status_button);
									cargo_status_button.setBackground(Color.YELLOW);
									cargo_status_button.setText("CARGO RUNNING!");
									}
									else if(buildstatus == 1)
									{
									build_status_button.setBackground(Color.RED);
									build_status_button.setText("BUILD FAILURE!");
									start_Button.setText("RESUME");
									cmd = ReadConfig.readConfig("build")+" -rf :"+resumefrom;
			          		    	resumebuild(workspacepath,resumefrom,cmd);
									}
								}
								else if(operations[0])
								{
									cleanstatus = ChorusMavenCleaner.mavenCleaner(workspacepath, role, clean_status_button);
									if(cleanstatus == 0)
									{
										clean_status_button.setBackground(Color.GREEN);
										clean_status_button.setText("CLEAN SUCCESS!");
									}
									else if(cleanstatus == 1){
										System.out.println("Clean up Failure");
										clean_status_button.setBackground(Color.RED);
									}
								}
								else if(operations[1])
								{
									buildstatus = ChorusMavenBuilder.mavenBuilder(workspacepath, buildcommand, build_status_button,ReadConfig.readConfig("UserId"));
									
									if(buildstatus == 0)
									{
									build_status_button.setBackground(Color.GREEN);	
									build_status_button.setText("BUILD SUCCESS!");
									}
									else if(buildstatus == 1)
									{
									build_status_button.setBackground(Color.RED);
									build_status_button.setText("BUILD FAILURE!");
									start_Button.setText("RESUME");
									cmd = ReadConfig.readConfig("build")+" -rf :"+resumefrom;
			          		    	resumebuild(workspacepath,resumefrom,cmd);
									}
								}
								else if(operations[2])
								{
									
									ChorusCargoRunner.runCargo(workspacepath, cargocommand,cargo_status_button);
									cargo_status_button.setBackground(Color.YELLOW);
									cargo_status_button.setText("CARGO RUNNING!");
								}
							}
							
							public static void resumebuild(final String workspacePath, final String resumefrom, final String command)
							{
								final JFrame frame = new JFrame ("Resume Build");
				        		frame.setSize(400, 120);
				        		frame.setLocationRelativeTo(null);
				        		frame.setResizable(false);
				        		frame.setUndecorated(true);
				        		frame.getRootPane().setWindowDecorationStyle(1);
				        		JPanel panel1 = new JPanel(new GridBagLayout());
				        		panel1.setBackground(Color.WHITE);
				        		JPanel panel2 = new JPanel(new GridBagLayout());
				        		panel2.setBackground(Color.WHITE);
				        	    GridBagConstraints gc = new GridBagConstraints();
				        		final CustomJLabel resume_from = new CustomJLabel("Resume From:");
				        	    gc.fill = GridBagConstraints.NONE;
				        	    gc.weightx = 0.5;
				        	    gc.gridx = 2;
				        	    gc.gridy = 1;
				        	    panel1.add(resume_from,gc);
				        	    final JTextField resumetext = new JTextField(20);
				        	    gc.fill = GridBagConstraints.NONE;
				        	    gc.weightx = 0.5;
				        	    gc.weighty = 2.0;
				        	    gc.gridx = 3;
				        	    gc.gridy = 1;
				        	    panel1.add(resumetext,gc);
				        	    
				        	    CustomJButton resume_Button = new CustomJButton("Resume Build");
				        	    gc.fill = GridBagConstraints.NONE;
				        	    gc.weightx = 0.5;
				        	    gc.gridwidth = 1;
				        	    gc.gridx = 0;
				        	    gc.gridy = 1;
				        	    panel2.add(resume_Button,gc);
				        	    resumetext.setText(resumefrom);
				        	    System.out.println(resumefrom);
				        	    resumetext.setEditable(true);
				        	    frame.add(panel1, BorderLayout.NORTH);
				        	    frame.add(panel2,BorderLayout.CENTER);
				                frame.setVisible (true);
				                resume_Button.addActionListener(new ActionListener() {
				        	    	 
				          		    public void actionPerformed(ActionEvent e) {
				          		    				  
				          		    	//ChorusMavenBuilder.mavenBuilder(workspacePath,command);
				          		    	System.out.println(command);
				          		    	frame.dispose();
				          		    }
				            });
				           
							}
}
																		
							