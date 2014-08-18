 package com.ca.chorus.builder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicArrowButton;

import com.ca.chorus.utils.CustomJButton;
import com.ca.chorus.utils.CustomJCheckBox;
import com.ca.chorus.utils.CustomJLabel;
import com.ca.chorus.utils.CustomJMenu;
import com.ca.chorus.utils.CustomJMenuItem;
import com.ca.chorus.utils.JOutlookBar;


public class ChorusBuilderModule {
	
	public static String  workspacePath= "";
	public static String cmd = "";
	public static String timeoutLimit = "60000000";
	public static String role = "";
	public static boolean mailnotify;
	
	public static CustomJButton clean_status_button = new CustomJButton("Maven Clean Status");
	public static CustomJButton build_status_button = new CustomJButton("Maven Build Status");
	public static CustomJButton cargo_status_button = new CustomJButton("Cargo Startup Status");
	public static CustomJButton Clean_Button = new CustomJButton("Clean");
	
	public static void main(String[] args) throws Exception{
		
		
		SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
            	CreateConfig.createConfig("", "");
                initUI();
            }
        });

	}	
	public static void initUI(){
		JOutlookBar outlookbar = new JOutlookBar();
		JFrame frame = new JFrame("Chorus Local Build Manager");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(820,420);
		outlookbar.addBar("Save Workspace", saveWorkspacePanel(frame));
		outlookbar.addBar("Configuration", configurationPanel(frame));
		outlookbar.addBar("Option Panel", optionPanel());
		outlookbar.setVisibleBar(2);
		frame.add(dataPanel(frame), BorderLayout.BEFORE_FIRST_LINE);
		frame.add(outlookbar, BorderLayout.CENTER);
		frame.add(infoPanel(frame),BorderLayout.AFTER_LAST_LINE);

        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setResizable(false);
        frame.setUndecorated(true);
        frame.getRootPane().setWindowDecorationStyle(1);
        frame.setVisible(true);
        
	}
	@SuppressWarnings("unchecked")
	private static JPanel dtspanel() {
		// TODO Auto-generated method stub
		
		JPanel dtsPanel = new JPanel(new GridBagLayout());
		 GridBagConstraints gc = new GridBagConstraints();
		 
		 final DefaultComboBoxModel sysNames = new DefaultComboBoxModel();
		 sysNames.addElement("Nyx");
		 sysNames.addElement("Styx");
		  
		final JComboBox sysList = new JComboBox(sysNames);
		sysList.setBackground(Color.WHITE);
		sysList.setForeground(Color.BLUE);
		    gc.fill = GridBagConstraints.NONE;
		    gc.weightx = 0.3;
		    gc.gridwidth = 1;
		    gc.gridx = 0;
		    gc.gridy = 0;
		   dtsPanel.add(sysList,gc);
		   
		CustomJButton flowCopy = new CustomJButton("FlowCopy");
 	    gc.fill = GridBagConstraints.NONE;
 	    gc.weightx = 0.5;
 	    gc.gridwidth = 1;
 	    gc.gridx = 1;
 	    gc.gridy = 0;
 	    dtsPanel.add(flowCopy,gc);
 	    
 	   CustomJButton startJboss = new CustomJButton("Start JBoss");
	    gc.fill = GridBagConstraints.NONE;
	    gc.weightx = 0.5;
	    gc.gridwidth = 1;
	    gc.gridx = 2;
	    gc.gridy = 0;
	    dtsPanel.add(startJboss,gc);
	    
	    CustomJButton stopJboss = new CustomJButton("Stop JBoss");
	    gc.fill = GridBagConstraints.NONE;
	    gc.weightx = 0.5;
	    gc.gridwidth = 1;
	    gc.gridx = 3;
	    gc.gridy = 0;
	    dtsPanel.add(stopJboss,gc);
		
		return dtsPanel;
	}
	public static JMenuBar myMenuBar(final JFrame frame1) {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.WHITE);
        CustomJMenu demoMenu = new CustomJMenu("test");
        CustomJMenu fileMenu = new CustomJMenu("File");
        CustomJMenu optionMenu = new CustomJMenu("Option");
        CustomJMenu helpMenu = new CustomJMenu("Help");
        CustomJMenuItem demomenuitem = new CustomJMenuItem("demomenuitem");
        CustomJMenuItem saveWorkspace = new CustomJMenuItem("Save a new Workspace");
        CustomJMenu switchWorkspace = new CustomJMenu("Switch Workspace");
        CustomJMenu viewLogs = new CustomJMenu("View Logs");
        CustomJMenuItem openCargo = new CustomJMenuItem("Open Cargo log");
        CustomJMenuItem openBuildlog = new CustomJMenuItem("Open Build Log");
        CustomJMenuItem manageCmd = new CustomJMenuItem("Manage Commands");
        CustomJMenuItem increaseTimeOut = new CustomJMenuItem("Increase Timeout Limit");
        CustomJMenuItem resumeBuild = new CustomJMenuItem("Resume Build");
        CustomJMenuItem deleteCargo = new CustomJMenuItem("Delete Cargo");
        CustomJMenuItem help = new CustomJMenuItem("User Guide");
        CustomJMenuItem troubleshootguide = new CustomJMenuItem("Troubleshoot Guide");
        final List<CustomJMenuItem> subMenuItem = setSubmenu(switchWorkspace);
        fileMenu.add(saveWorkspace);
        fileMenu.add(switchWorkspace);
        fileMenu.add(viewLogs);
        viewLogs.add(openCargo);
        viewLogs.add(openBuildlog);
        
        optionMenu.add(manageCmd);
        optionMenu.add(resumeBuild);
        optionMenu.add(deleteCargo);
        
        helpMenu.add(help);
        helpMenu.add(troubleshootguide);

        menuBar.add(fileMenu);
        menuBar.add(optionMenu);
        menuBar.add(helpMenu);
        for( int i = 0 ; i < subMenuItem.size(); i++)
        {
        	switchWorkspace.add(subMenuItem.get(i));
        	final CustomJMenuItem temp = subMenuItem.get(i);
        	temp.addActionListener(new ActionListener() {
   	    	 
                public void actionPerformed(ActionEvent e)
                {
                	CreateConfig.setcurrentWorkspace(temp.getText());
                	restart(frame1);
                	
						
                }
                private void restart(JFrame frame1) {
					SwingUtilities.invokeLater(new Runnable() {

			            @Override
			            public void run() {
			                initUI();
			            }});
						frame1.dispose();
						}
            });
        }
        
        workspacePath = ReadConfig.readConfig("currentworkspace");
        
        openBuildlog.addActionListener(new ActionListener() {
	    	 
            public void actionPerformed(ActionEvent e)
            {
            	ProcessBuilder builder = new ProcessBuilder(	         
            			"cmd.exe", "/c", "cd \""+workspacePath+"\\parent\" && BuildLogMay23321pm.txt");
    	            builder.redirectErrorStream(true);
    	            Process p;
					try {
						p = builder.start();
						BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            }
        });
        openCargo.addActionListener(new ActionListener() {
	    	 
            public void actionPerformed(ActionEvent e)
            {
            	ProcessBuilder builder = new ProcessBuilder(	         
            			"cmd.exe", "/c", "cd \""+workspacePath+"\\parent\\server\\chorus-server-run\\target\" && cargo.log");
    	            builder.redirectErrorStream(true);
    	            Process p;
					try {
						p = builder.start();
						BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
            }
        });
        troubleshootguide.addActionListener(new ActionListener() {
	    	 
            public void actionPerformed(ActionEvent e)
            {
           String url = "http://cawiki.ca.com/display/DatacenterModernization/How+to+Debug+Chorus+4.0+Issues"; 	
	    Desktop desktop = Desktop.getDesktop();
        try {
            desktop.browse(new URI(url));
        } catch (IOException | URISyntaxException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
            }});
		
        help.addActionListener(new ActionListener() {
	    	 
            public void actionPerformed(ActionEvent e)
            {
           String url = "http://cawiki.ca.com/display/ActivityManagement/How+To+Use+Chorus+Build+Manager+to+do+daily+local+desktop+build"; 	
	    Desktop desktop = Desktop.getDesktop();
        try {
            desktop.browse(new URI(url));
        } catch (IOException | URISyntaxException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
            }});
        
        manageCmd.addActionListener(new ActionListener() {
	    	 
            public void actionPerformed(ActionEvent e)
            {
            	JFrame managecmdframe = new JFrame ("Command Manager");
            	managecmdframe.setSize(820, 180);
            	managecmdframe.setLocationRelativeTo(null);
        		managecmdframe.setResizable(false);
            	managecmdframe.getContentPane().setBackground(Color.WHITE);
            	managecmdframe.setUndecorated(true);
            	managecmdframe.getRootPane().setWindowDecorationStyle(1);
        		JPanel panel = new JPanel(new GridBagLayout());
        		panel.setBackground(Color.WHITE);
        		
        	    GridBagConstraints gc = new GridBagConstraints();   	    
        		final CustomJLabel buildLabel = new CustomJLabel("Build Command:");
        	    gc.fill = GridBagConstraints.NONE;
        	    gc.weightx = 0.5;
        	    gc.gridx = 2;
        	    gc.gridy = 2;
        	    panel.add(buildLabel,gc);
        	    final JTextField buildText = new JTextField(60);
        	    gc.fill = GridBagConstraints.NONE;
        	    gc.weightx = 0.5;
        	    gc.weighty = 2.0;
        	    gc.gridx = 4;
        	    gc.gridy = 2;
        	    panel.add(buildText,gc);
        	    
        	    final CustomJCheckBox chkbox1 = new CustomJCheckBox("");
        	    gc.fill = GridBagConstraints.NONE;
        	    gc.weightx = 0.5;
        	    gc.weighty = 2.0;
        	    gc.gridx = 5;
        	    gc.gridy = 2;
        	    panel.add(chkbox1,gc);
        	    
        	    final CustomJLabel cargoLabel = new CustomJLabel("Cargo Command:");
        	    gc.fill = GridBagConstraints.NONE;
        	    gc.weightx = 0.5;
        	    gc.gridx = 2;
        	    gc.gridy = 3;
        	    panel.add(cargoLabel,gc);
        	    final JTextField cargoText = new JTextField(60);
        	    gc.fill = GridBagConstraints.NONE;
        	    gc.weightx = 0.5;
        	    gc.weighty = 2.0;
        	    gc.gridx = 4;
        	    gc.gridy = 3;
        	    panel.add(cargoText,gc);
        	    
        	    final CustomJCheckBox chkbox2 = new CustomJCheckBox("");
        	    gc.fill = GridBagConstraints.NONE;
        	    gc.weightx = 0.5;
        	    gc.weighty = 2.0;
        	    gc.gridx = 5;
        	    gc.gridy = 3;
        	    panel.add(chkbox2,gc);
        	    CustomJButton savebuildCmd = new CustomJButton("Save");
        	    gc.fill = GridBagConstraints.NONE;
        	    gc.weightx = 0.5;
        	    gc.gridwidth = 1;
        	    gc.gridx = 4;
        	    gc.gridy = 4;
        	    panel.add(savebuildCmd,gc);
        	    managecmdframe.add(panel,BorderLayout.CENTER);
        	    managecmdframe.setVisible (true);
                
                buildText.setText(ReadConfig.readConfig("build"));
                cargoText.setText(ReadConfig.readConfig("cargo"));
                
                	buildText.setEnabled(false);
                	cargoText.setEnabled(false);
                	chkbox1.addActionListener(new ActionListener() {
              	    	 
                        public void actionPerformed(ActionEvent e)
                        {
                        	if(chkbox1.isSelected())
                        	{
                        		buildText.setEnabled(true);
                        	}
                        	else{
                        		buildText.setEnabled(false);
                        	}
                        }
                    });
                	
                	chkbox2.addActionListener(new ActionListener() {
             	    	 
                        public void actionPerformed(ActionEvent e)
                        {
                        	if(chkbox2.isSelected())
                        	{
                        		cargoText.setEnabled(true);
                        	}
                        	else{
                        		cargoText.setEnabled(false);
                        	}
                        }
                    });
                   
                	savebuildCmd.addActionListener(new ActionListener() {
              	    	 
                        public void actionPerformed(ActionEvent e)
                        {
                        	if(cargoText.isEnabled() && buildText.isEnabled())
                        	{
                        		CreateConfig.editConfig("build", buildText.getText());
                        		CreateConfig.editConfig("cargo", cargoText.getText());
                        	}
                        else if(cargoText.isEnabled())
                        	{
                        		CreateConfig.editConfig("cargo", cargoText.getText());
                        	}
                        	else if(buildText.isEnabled())
                        	{
                        		CreateConfig.editConfig("build", buildText.getText());
                        	}
                        }
                    });
                    
            }
        });
 	   
        
        resumeBuild.addActionListener(new ActionListener() {
	    	 
            public void actionPerformed(ActionEvent e)
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
        		final CustomJLabel resumefrom = new CustomJLabel("Resume From:");
        	    gc.fill = GridBagConstraints.NONE;
        	    gc.weightx = 0.5;
        	    gc.gridx = 2;
        	    gc.gridy = 1;
        	    panel1.add(resumefrom,gc);
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
        	    frame.add(panel1, BorderLayout.NORTH);
        	    frame.add(panel2,BorderLayout.CENTER);
                frame.setVisible (true);
                resume_Button.addActionListener(new ActionListener() {
        	    	 
          		    public void actionPerformed(ActionEvent e) {
          		    	cmd = ReadConfig.readConfig("build")+" -rf :"+resumetext.getText();
          		    	ChorusMavenBuilder.mavenBuilder(workspacePath,cmd,build_status_button,ReadConfig.readConfig("UserId"));
          		    	System.out.println(cmd);
          		    	frame.dispose();
          		    }
            });
           }
        });
        
        saveWorkspace.addActionListener(new ActionListener() {
	    	 
            public void actionPerformed(ActionEvent e)
            {
                final JFrame frame = new JFrame ("Save Workspace");
        		frame.setSize(550, 100);
        		frame.setLocationRelativeTo(null);
        		frame.setUndecorated(true);
        		frame.getRootPane().setWindowDecorationStyle(1);
        		JPanel panel = new JPanel(new GridBagLayout());
        		panel.setBackground(Color.WHITE);
        	    GridBagConstraints gc = new GridBagConstraints();
        		final CustomJLabel loadLabel = new CustomJLabel("Load Workspace:");
        	    gc.fill = GridBagConstraints.NONE;
        	    gc.weightx = 0.3;
        	    gc.gridx = 2;
        	    gc.gridy = 1;
        	    panel.add(loadLabel,gc);
        	    final JTextField pathText = new JTextField(30);
        	    gc.fill = GridBagConstraints.NONE;
        	    gc.weightx = 0.3;
        	    gc.weighty = 2.0;
        	    gc.gridx = 3;
        	    gc.gridy = 1;
        	    panel.add(pathText,gc);
        	    CustomJButton browsePath = new CustomJButton("Browse");
        	    gc.fill = GridBagConstraints.NONE;
        	    gc.weightx = 0.3;
        	    gc.gridwidth = 1;
        	    gc.gridx = 4;
        	    gc.gridy = 1;
        	    panel.add(browsePath,gc);
        	    CustomJButton save = new CustomJButton("Save");
        	    gc.fill = GridBagConstraints.NONE;
        	    gc.weightx = 0.3;
        	    gc.gridwidth = 1;
        	    gc.gridx = 3;
        	    gc.gridy = 2;
        	    panel.add(save,gc);
        	    browsePath.addActionListener(new ActionListener() {
       	    	 
        		    public void actionPerformed(ActionEvent e) {
        		    	JFileChooser chooser = new JFileChooser();
        	            chooser.setCurrentDirectory(new java.io.File("."));
        	            chooser.setDialogTitle("Browse the folder to process");
        	            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        	            chooser.setAcceptAllFileFilterUsed(false);

        	            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        	                
        	                pathText.setText(chooser.getSelectedFile().toString());
        	            } else {
        	                System.out.println("No Selection ");
        	            }
        		    	}
        		    });
        	   save.addActionListener(new ActionListener() {
         	    	 
          		    public void actionPerformed(ActionEvent e) {
          		    	
          		    	CreateConfig.addWorkspace(pathText.getText());
          		    	frame.dispose();
          		    	restart(frame1);
          					
          		    	 
          		    }

          		  private void restart(JFrame frame1) {
						SwingUtilities.invokeLater(new Runnable() {

				            @Override
				            public void run() {
				                initUI();
				            }});
							frame1.dispose();
							}
						
					

					
        	   });
        	    frame.add(panel,BorderLayout.CENTER);
        	    frame.setResizable(false);
                frame.setVisible (true);
            }
        });
        
        
        deleteCargo.addActionListener(new ActionListener() {
	    	 
            public void actionPerformed(ActionEvent e)
            {
            	ProcessBuilder builder = new ProcessBuilder(	         
            			"cmd.exe", "/c", "cd \""+workspacePath+"\\parent\\server\\chorus-server-run\\target\" && del cargo.log");
            	            builder.redirectErrorStream(true);
            	            Process p;
        					try {
        						p = builder.start();
        						BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        					} catch (IOException e1) {
        						// TODO Auto-generated catch block
        						e1.printStackTrace();
        					}
        					            }
        });
        return menuBar;
	}
	public static List<CustomJMenuItem> setSubmenu(CustomJMenu menuItem)
	{
		CustomJMenuItem submenuItem = null;
		List<CustomJMenuItem> submenuItems = new ArrayList<CustomJMenuItem>();
		List<String> Workspaces = new ArrayList<String>();
		
		Workspaces = ReadConfig.readWorkspace("Workspacepath");
		for(int i = 0 ; i < Workspaces.size(); i++)
		{
			if(!Workspaces.get(i).equals(ReadConfig.readConfig("currentworkspace")))
			{
			submenuItem = new CustomJMenuItem(Workspaces.get(i));
			submenuItems.add(submenuItem);
			}
		}
		return submenuItems;
	}
	
	public static JPanel versiononePanel()
	{
		JPanel v1panel = new JPanel(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		v1panel.setBackground(Color.WHITE);
		final CustomJLabel workitemlabel = new CustomJLabel("Work item Id:");
	    gc.fill = GridBagConstraints.NONE;
	    gc.weightx = 0.5;
	    gc.gridx = 0;
	    gc.gridy = 0;
	    v1panel.add(workitemlabel,gc);
	    
	    final JTextField workitemText = new JTextField(10);
	    gc.fill = GridBagConstraints.NONE;
	    gc.weightx = 0.3;
	    gc.weighty = 2.0;
	    gc.gridx = 1;
	    gc.gridy = 0;
	    v1panel.add(workitemText,gc);
	    
	    final CustomJCheckBox none = new CustomJCheckBox("None");
	    
	    gc.fill = GridBagConstraints.NONE;
	    gc.weightx = 0.5;
	    gc.gridx = 2;
	    gc.gridy = 0;
	    v1panel.add(none,gc);
	   
	    final CustomJCheckBox inProgress = new CustomJCheckBox("In Progress");
	    
	    gc.fill = GridBagConstraints.NONE;
	    gc.weightx = 0.5;
	    gc.weighty = 1.0;
	    gc.gridx = 3;
	    gc.gridy = 0;
	    v1panel.add(inProgress,gc);
	   
	    final CustomJCheckBox rft = new CustomJCheckBox("Ready For Test");
	    
	    gc.fill = GridBagConstraints.NONE;
	    gc.weightx = 0.5;
	    gc.weighty = 1.0;
	    gc.gridx = 4;
	    gc.gridy = 0;
	    v1panel.add(rft,gc);
	    
	    final CustomJButton fetch = new CustomJButton("Fetch");
	    
	    gc.fill = GridBagConstraints.NONE;
	    gc.weightx = 0.5;
	    gc.weighty = 1.0;
	    gc.gridx = 5;
	    gc.gridy = 0;
	    v1panel.add(fetch,gc);
	    
		return v1panel;
	}
	
	public static JPanel configurationPanel(final JFrame frame1)
	{
		JPanel main = new JPanel( new FlowLayout(FlowLayout.CENTER, 10, 10) );
		main.setBackground(Color.WHITE);
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBackground(Color.WHITE);
		
	    GridBagConstraints gc = new GridBagConstraints();
	    final DefaultComboBoxModel buildcommands = new DefaultComboBoxModel();
		final CustomJLabel buildLabel = new CustomJLabel("Build Command:");
	    gc.fill = GridBagConstraints.NONE;
	    gc.weightx = 0.5;
	    gc.gridx = 2;
	    gc.gridy = 2;
	    panel.add(buildLabel,gc);
	    final JTextField buildText = new JTextField(60);
	    gc.fill = GridBagConstraints.NONE;
	    gc.weightx = 0.3;
	    gc.weighty = 2.0;
	    gc.gridx = 4;
	    gc.gridy = 2;
	    panel.add(buildText,gc);
	    
	    final CustomJCheckBox chkbox1 = new CustomJCheckBox("");
	    gc.fill = GridBagConstraints.NONE;
	    gc.weightx = 0.5;
	    gc.weighty = 2.0;
	    gc.gridx = 5;
	    gc.gridy = 2;
	    panel.add(chkbox1,gc);
	    
	    final CustomJLabel cargoLabel = new CustomJLabel("Cargo Command:");
	    gc.fill = GridBagConstraints.NONE;
	    gc.weightx = 0.5;
	    gc.gridx = 2;
	    gc.gridy = 3;
	    panel.add(cargoLabel,gc);
	    final JTextField cargoText = new JTextField(60);
	    gc.fill = GridBagConstraints.NONE;
	    gc.weightx = 0.5;
	    gc.weighty = 2.0;
	    gc.gridx = 4;
	    gc.gridy = 3;
	    panel.add(cargoText,gc);
	    
	    final CustomJCheckBox chkbox2 = new CustomJCheckBox("");
	    gc.fill = GridBagConstraints.NONE;
	    gc.weightx = 0.5;
	    gc.weighty = 2.0;
	    gc.gridx = 5;
	    gc.gridy = 3;
	    panel.add(chkbox2,gc);
	    
	    final CustomJLabel mailid = new CustomJLabel("Mail ID : ");
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.weightx = 0/5;
	    gc.weighty = 2.0;
	    gc.gridx = 2;
	    gc.gridy = 4;
	    panel.add(mailid,gc);
	    
	    final JTextField mailtext = new JTextField(8);
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.weightx = 1;
	    gc.weighty = 2.0;
	    gc.gridx = 4;
	    gc.gridy = 4;
	    panel.add(mailtext,gc);
	    
	    final CustomJCheckBox mailcheck = new CustomJCheckBox("");
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.weightx = 1;
	    gc.weighty = 2.0;
	    gc.gridx = 5;
	    gc.gridy = 4;
	    panel.add(mailcheck,gc);
	    
	    CustomJButton addCommand = new CustomJButton("Add");
	    gc.fill = GridBagConstraints.NONE;
	    gc.weightx = 0.5;
	    gc.gridwidth = 1;
	    gc.gridx = 4;
	    gc.gridy = 5;
	    panel.add(addCommand,gc);
	   
      
	    
	    mailcheck.setSelected(true);

        main.add(panel);
       
        buildText.setText(ReadConfig.readConfig("build"));
        cargoText.setText(ReadConfig.readConfig("cargo"));
        mailtext.setText(ReadConfig.readConfig("UserId"));
        
        	buildText.setEnabled(false);
        	cargoText.setEnabled(false);
        	chkbox1.addActionListener(new ActionListener() {
      	    	 
                public void actionPerformed(ActionEvent e)
                {
                	if(chkbox1.isSelected())
                	{
                		buildText.setEnabled(true);
                	}
                	else{
                		buildText.setEnabled(false);
                	}
                }
            });
        	
        	chkbox2.addActionListener(new ActionListener() {
     	    	 
                public void actionPerformed(ActionEvent e)
                {
                	if(chkbox2.isSelected())
                	{
                		cargoText.setEnabled(true);
                	}
                	else{
                		cargoText.setEnabled(false);
                	}
                }
            });
           
        	mailcheck.addActionListener(new ActionListener() {
     	    	 
                public void actionPerformed(ActionEvent e)
                {
                	
					if(mailcheck.isSelected())
                	{
                		mailtext.setEnabled(true);
                		mailnotify = true;
                	}
                	else{
                		mailtext.setEnabled(false);
                		mailnotify = false;
                	}
                }
            });
        	addCommand.addActionListener(new ActionListener() {
      	    	 
                public void actionPerformed(ActionEvent e)
                {
                	if(cargoText.isEnabled() && buildText.isEnabled() && mailtext.isEnabled())
                	{
                		CreateConfig.editConfig("build", buildText.getText());
                		CreateConfig.editConfig("cargo", cargoText.getText());
                		CreateConfig.editConfig("UserId", mailtext.getText());
                	}
                else if(cargoText.isEnabled())
                	{
                		CreateConfig.editConfig("cargo", cargoText.getText());
                	}
                	else if(buildText.isEnabled())
                	{
                		CreateConfig.editConfig("build", buildText.getText());
                	}
                	else if(mailtext.isEnabled())
                	{
                		CreateConfig.editConfig("UserId", mailtext.getText());
                	}
                	
                	restart(frame1);
                }

				
					private void restart(JFrame frame1) {
    					SwingUtilities.invokeLater(new Runnable() {

    			            @Override
    			            public void run() {
    			                initUI();
    			            }});
    						frame1.dispose();
    						}
            });
            
    return main;
	}
	public static JPanel dataPanel(final JFrame frame1)
	{
		String[] items = { "None","All", "Demo", "Security", "DB2tools", "Storage", "Performance", "SDK"};
		final CustomJCheckBox[] checkboxes = new CustomJCheckBox[8];
	    JPanel panel = new JPanel(new GridBagLayout());
	    panel.setBorder(new EmptyBorder(20, 0, 10, 0));
	    panel.setBackground(Color.WHITE);
	    JPanel panel1 = new JPanel(new GridBagLayout());
	    GridBagConstraints gc = new GridBagConstraints();
	    
	   
	    
	    CustomJLabel roles = new CustomJLabel("Roles:");
	    gc.fill = GridBagConstraints.NONE;
	    gc.weightx = 0.5;
	    gc.gridx = 0;
	    gc.gridy = 1;
	    panel.add(roles,gc);
	    
	    final CustomJCheckBox noRole = new CustomJCheckBox(items[0]);
	    
	    gc.fill = GridBagConstraints.NONE;
	    gc.weightx = 0.5;
	    gc.gridx = 0;
	    gc.gridy = 2;
	    panel.add(noRole,gc);
	    checkboxes[0] = noRole;
	    final CustomJCheckBox allRole = new CustomJCheckBox(items[1]);
	    
	    gc.fill = GridBagConstraints.NONE;
	    gc.weightx = 0.5;
	    gc.weighty = 1.0;
	    gc.gridx = 1;
	    gc.gridy = 2;
	    panel.add(allRole,gc);
	    checkboxes[1] = allRole;
	    final CustomJCheckBox demoRole = new CustomJCheckBox(items[2]);
	    
	    gc.fill = GridBagConstraints.NONE;
	    gc.weightx = 0.5;
	    gc.weighty = 1.0;
	    gc.gridx = 2;
	    gc.gridy = 2;
	    panel.add(demoRole,gc);
	    checkboxes[2] = demoRole;
	    final CustomJCheckBox securityRole = new CustomJCheckBox(items[3]);
	    
	    gc.fill = GridBagConstraints.NONE;
	    gc.weightx = 0.5;
	    gc.weighty = 1.0;
	    gc.gridx = 3;
	    gc.gridy = 2;
	    panel.add(securityRole,gc);
	    checkboxes[3] = securityRole;
	    final CustomJCheckBox dbaRole = new CustomJCheckBox(items[4]);
	    
	    gc.fill = GridBagConstraints.NONE;
	    gc.weightx = 0.5;
	    gc.weighty = 1.0;
	    gc.gridx = 4;
	    gc.gridy = 2;
	    panel.add(dbaRole,gc);
	    checkboxes[4] = dbaRole;
	    final CustomJCheckBox storageRole = new CustomJCheckBox(items[5]);
	    
	    gc.fill = GridBagConstraints.NONE;
	    gc.weightx = 0.5;
	    gc.weighty = 1.0;
	    gc.gridx = 5;
	    gc.gridy = 2;
	    panel.add(storageRole,gc);
	    checkboxes[5] = storageRole;
	    final CustomJCheckBox performanceRole = new CustomJCheckBox(items[6]);
	    
	    gc.fill = GridBagConstraints.NONE;
	    gc.weightx = 0.5;
	    gc.weighty = 1.0;
	    gc.gridx = 6;
	    gc.gridy = 2;
	    panel.add(performanceRole,gc);
	    checkboxes[6] = performanceRole;
	    final CustomJCheckBox sdkRole = new CustomJCheckBox(items[7]);
	    
	    gc.fill = GridBagConstraints.NONE;
	    gc.weightx = 0.5;
	    gc.weighty = 1.0;
	    gc.gridx = 7;
	    gc.gridy = 2;
	    panel.add(sdkRole,gc);
	    checkboxes[7] = sdkRole;
	    
	    final CustomJCheckBox clean = new CustomJCheckBox("Clean");
	    
	    gc.fill = GridBagConstraints.NONE;
	    gc.weightx = 0.5;
	    gc.weighty = 1.0;
	    gc.gridx = 1;
	    gc.gridy = 4;
	    panel.add(clean,gc);
	    
        final CustomJCheckBox build = new CustomJCheckBox("Build");
	    
	    gc.fill = GridBagConstraints.NONE;
	    gc.weightx = 0.5;
	    gc.weighty = 1.0;
	    gc.gridx = 2;
	    gc.gridy = 4;
	    panel.add(build,gc);
	    
        final CustomJCheckBox cargo = new CustomJCheckBox("Cargo");
	    
	    gc.fill = GridBagConstraints.NONE;
	    gc.weightx = 0.5;
	    gc.weighty = 1.0;
	    gc.gridx = 3;
	    gc.gridy = 4;
	    panel.add(cargo,gc);
	    
	    final CustomJButton Start_Button = new CustomJButton("START");
	    gc.fill = GridBagConstraints.NONE;
	    gc.weightx = 0.5;
	    gc.gridwidth = 2;
	    gc.gridx = 4;
	    gc.gridy = 4;
	    panel.add(Start_Button,gc);
	    
	    final CustomJCheckBox set_default = new CustomJCheckBox("Set as default");
	    
	    gc.fill = GridBagConstraints.NONE;
	    gc.weightx = 0.5;
	    gc.weighty = 1.0;
	    gc.gridx = 6;
	    gc.gridy = 4;
	    panel.add(set_default,gc);
	    clean.setSelected(true);
	    build.setSelected(true);
	    securityRole.setSelected(true);
	    
	   final boolean operations[] = {false,false,false};
	    if(clean.isSelected())
	    	operations[0] = true;
	    else
	    	operations[0] = false;
	    if(build.isSelected())
	    	operations[1] = true;
	    else
	    	operations[1] = true;
	    if(cargo.isSelected())
	    	operations[2] = true;
	    else
	    	operations[2] = false;
	    
	    clean.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(clean.isSelected())
				{
				
					operations[0] = true;
				}
				else
					operations[0] = false;
				
			}
		});
	    
	    build.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(build.isSelected())
				{
				
					operations[1] = true;
				}
				else
					operations[1] = false;
				
			}
		});
 
	    cargo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(cargo.isSelected())
				{
				
					operations[2] = true;
				}
				else
					operations[2] = false;
				
			}
		});
	    final Thread t = new Thread() {
            @Override
            public void run() {  // override the run() for the running behaviors
         	   ChorusReady.chorusReady(workspacePath, role,operations, ReadConfig.readConfig("build"), ReadConfig.readConfig("cargo"),clean_status_button, build_status_button,cargo_status_button,Start_Button);
                  try {
                     sleep(10);  // milliseconds
                  } catch (InterruptedException ex) {
                	  interrupt();
                  }
               }
            } ;
	    Start_Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ArrayList<String> roles = new ArrayList<String>();
	        	
	        	for(int i = 0 ; i < checkboxes.length ; i++)
	        	{
	        		if(checkboxes[i].isSelected())
	        		{
	           			roles.add(checkboxes[i].getText().toLowerCase());
	        			
	        		}	
	        	
	        	}
	        	if(roles.size() > 1)
	        	{
	        		for(int i = 0 ; i < roles.size()-1 ; i++)
	        		{
	        			role += roles.get(i)+",";
	        		}
	        		role += roles.get(roles.size()-1);
	        	}
	        	else
	        	{
	        		role = roles.get(0);
	        	}  
		            t.start();	
				
			}
		});
	    
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.weightx = 0.5;
	    gc.weighty = 5.0;
	    gc.anchor = GridBagConstraints.PAGE_END;
	    gc.insets = new Insets(10,0,10,0);
	    gc.gridwidth = 3;
	    gc.gridx = 0;
	    gc.gridy = 6;
	    panel.add(clean_status_button,gc);
	    
	    
	    
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.weightx = 0.5;
	    gc.weighty = 5.0;
	    gc.anchor = GridBagConstraints.PAGE_END;
	    gc.insets = new Insets(10,0,10,0);
	    gc.gridwidth = 3;
	    gc.gridx = 3;
	    gc.gridy = 6;
	    panel.add(build_status_button,gc);
    
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.weightx = 0.5;
	    gc.weighty = 5.0;
	    gc.anchor = GridBagConstraints.PAGE_END;
	    gc.insets = new Insets(10,0,10,0);
	    gc.gridwidth = 2;
	    gc.gridx = 6;
	    gc.gridy = 6;
	    panel.add(cargo_status_button,gc);
	    
	    workspacePath = getWorkspacePath();

	    Clean_Button.addActionListener(new ActionListener() {
	    	 
            public void actionPerformed(ActionEvent e)
            {
            	
    			
            	ArrayList<String> roles = new ArrayList<String>();
            	
            	for(int i = 0 ; i < checkboxes.length ; i++)
            	{
            		if(checkboxes[i].isSelected())
            		{
               			roles.add(checkboxes[i].getText().toLowerCase());
            		}	
            	
            	}
            	if(roles.size() > 1)
            	{
            		for(int i = 0 ; i < roles.size()-1 ; i++)
            		{
            			role += roles.get(i)+",";
            		}
            		role += roles.get(roles.size()-1);
            	}
            	else
            	{
            		role = roles.get(0);
            	}
            	
            	ChorusMavenCleaner.mavenCleaner(workspacePath,role,clean_status_button);
            	
            	try {
            		clean_status_button.setOpaque(true);
                	clean_status_button.setBackground(Color.YELLOW);
					new Thread();
					Thread.sleep(3000);
					clean_status_button.setBackground(Color.GREEN);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	
    	           
            }
        });
	    
	    clean_status_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String path = workspacePath+"//parent//server//chorus-server-run";
				try {
					Desktop.getDesktop().open(new File(path+"//cleanLogDemo.txt"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}); 
	 
	    build_status_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String path = workspacePath+"//parent";
				try {
					Desktop.getDesktop().open(new File(path+"//buildLogDemo.txt"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	
	   return panel;
	}
	
	public static JPanel infoPanel(final JFrame frame1)
	{
		
		
		JPanel infopanel = new JPanel(new GridBagLayout());
		
		infopanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		GridBagConstraints gc = new GridBagConstraints();
		
		JLabel workspaces = new JLabel("Workspaces");
		workspaces.setForeground(Color.RED);
	    gc.fill = GridBagConstraints.NONE;
	    gc.weightx = 0.5;
	    gc.gridx = 3;
	    gc.gridy = 0;
	    infopanel.add(workspaces,gc);
		
	    
	    final JRadioButton radiobtn1 = new JRadioButton();
	    final JRadioButton radiobtn2 = new JRadioButton();
	    final JRadioButton radiobtn3 = new JRadioButton();
	    final List<JRadioButton> radiobuttonList = new ArrayList<JRadioButton>();
	    final List<String> workspaceList = new ArrayList<String>();
	    if(ReadConfig.readWorkspace("Workspacepath").size() > 0)
	    {
	    	
	    	
		if(ReadConfig.readWorkspace("Workspacepath").size()>= 3)
		{
			BasicArrowButton leftarrow = new BasicArrowButton(BasicArrowButton.WEST);
			gc.weightx = 0.3;
		    gc.gridx = 0;
		    gc.gridy = 1;
		    infopanel.add(leftarrow,gc);
	    for(int i = 0 ; i < 3 ; i++)
	    {	
	    	workspaceList.add(ReadConfig.readWorkspace("Workspacepath").get(i));
	    	
	    	
	    }

		radiobtn1.setText(ReadConfig.readConfig("OtherWorkspace1"));
		radiobtn1.setForeground(Color.BLUE);
		gc.weightx = 0;
	    gc.gridx = 1;
	    gc.gridy = 1;
	    infopanel.add(radiobtn1,gc);

	    radiobtn2.setText(ReadConfig.readConfig("OtherWorkspace2"));
		radiobtn2.setForeground(Color.BLUE);
		gc.weightx = 0;
	    gc.gridx = 3;
	    gc.gridy = 1;
	    infopanel.add(radiobtn2,gc);
	    
	    
	    
	    radiobtn3.setText(ReadConfig.readConfig("OtherWorkspace3"));
		radiobtn3.setForeground(Color.BLUE);
		gc.weightx = 0;
	    gc.gridx = 5;
	    gc.gridy = 1;
	    infopanel.add(radiobtn3,gc);
	    
	    radiobuttonList.add(radiobtn1);
	    radiobuttonList.add(radiobtn2);
	    radiobuttonList.add(radiobtn3);
	    BasicArrowButton rightarrow = new BasicArrowButton(BasicArrowButton.EAST);
	    gc.weightx = 0.3;
	    gc.gridx = 7;
	    gc.gridy = 1;
	    
	    
	    for(int i = 0 ; i < radiobuttonList.size() ; i++)
	    {
	    	if(radiobuttonList.get(i).getText().equals(getWorkspacePath()))
	    	{
	    		radiobuttonList.get(i).setSelected(true);
	    	}
	    }
	    CreateConfig.editConfig("OtherWorkspace1", ReadConfig.readWorkspace("Workspacepath").get(0));
		  CreateConfig.editConfig("OtherWorkspace2", ReadConfig.readWorkspace("Workspacepath").get(1));
		  CreateConfig.editConfig("OtherWorkspace3", ReadConfig.readWorkspace("Workspacepath").get(2));
	    rightarrow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int x = 1;
				if(ReadConfig.readWorkspace("Workspacepath").size() > 3)
				{
				
					  CreateConfig.editConfig("OtherWorkspace1", ReadConfig.readWorkspace("Workspacepath").get(x));
					  CreateConfig.editConfig("OtherWorkspace2", ReadConfig.readWorkspace("Workspacepath").get(x+1));
					  CreateConfig.editConfig("OtherWorkspace3", ReadConfig.readWorkspace("Workspacepath").get(x+2));
					x++;
				}
				
				
				restart();
				frame1.dispose();
			}
            private void restart() {
				SwingUtilities.invokeLater(new Runnable() {

		            @Override
		            public void run() {
		                initUI();
		            }});
					}

			
		});
	    
 leftarrow.addActionListener(new ActionListener() {
	 
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(ReadConfig.readWorkspace("Workspacepath").size() > 3)
				{
					int x = 1;
					  CreateConfig.editConfig("OtherWorkspace1", ReadConfig.readWorkspace("Workspacepath").get(x-1));
					  CreateConfig.editConfig("OtherWorkspace2", ReadConfig.readWorkspace("Workspacepath").get(x));
					  CreateConfig.editConfig("OtherWorkspace3", ReadConfig.readWorkspace("Workspacepath").get(x+1));
					x++;
				}
				
				
				restart();
				frame1.dispose();
			}
            private void restart() {
				SwingUtilities.invokeLater(new Runnable() {

		            @Override
		            public void run() {
		                initUI();
		            }});
					}

			
		});
	    
	    	
	 
	   
 		infopanel.add(rightarrow,gc);	
		}
		else{
			for(int i = 0 ; i < ReadConfig.readWorkspace("Workspacepath").size() ; i++)
		    {	
		    	workspaceList.add(ReadConfig.readWorkspace("Workspacepath").get(i));
		    	
		    	
		    }
			if(ReadConfig.readWorkspace("Workspacepath").size()== 1)
			{
				radiobtn1.setText(workspaceList.get(0));
				radiobtn1.setForeground(Color.BLUE);
				gc.weightx = 0;
			    gc.gridx = 3;
			    gc.gridy = 1;
			    infopanel.add(radiobtn1,gc);
			    radiobuttonList.add(radiobtn1);
			   
			}
			else{
				radiobtn1.setText(workspaceList.get(0));
				radiobtn1.setForeground(Color.BLUE);
				gc.weightx = 0;
			    gc.gridx = 1;
			    gc.gridy = 1;
			    infopanel.add(radiobtn1,gc);
			    
			    radiobtn2.setText(workspaceList.get(1));
				radiobtn2.setForeground(Color.BLUE);
				gc.weightx = 0;
			    gc.gridx = 3;
			    gc.gridy = 1;
			    infopanel.add(radiobtn2,gc);
			    
			    radiobuttonList.add(radiobtn1);
			    radiobuttonList.add(radiobtn2);
			}
		}
		
		 for(int i = 0 ; i < radiobuttonList.size() ; i++)
		    {
		    	if(radiobuttonList.get(i).getText().equals(getWorkspacePath()))
		    	{
		    		radiobuttonList.get(i).setSelected(true);
		    	}
		    }
	   
		 radiobtn1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					CreateConfig.setcurrentWorkspace(radiobtn1.getText());
					restart();
					frame1.dispose();
				}
				  private void restart() {
						SwingUtilities.invokeLater(new Runnable() {

				            @Override
				            public void run() {
				                initUI();
				            }});
							//frame1.dispose();
							}
				
			});
		    
		    radiobtn2.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					CreateConfig.setcurrentWorkspace(radiobtn2.getText());
					
					restart();
					frame1.dispose();
				}
				  private void restart() {
						SwingUtilities.invokeLater(new Runnable() {

				            @Override
				            public void run() {
				                initUI();
				            }});
							//frame1.dispose();
							}
			});
	 		radiobtn3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CreateConfig.setcurrentWorkspace(radiobtn3.getText());
				restart();
				frame1.dispose();
			}
			  private void restart() {
					SwingUtilities.invokeLater(new Runnable() {

			            @Override
			            public void run() {
			                initUI();
			            }});
						//frame1.dispose();
						}
	 		});
	    
	    }
	    else{
	    	JLabel saveworkspace = new JLabel("Save at least one workpsace");
	    	saveworkspace.setForeground(Color.RED);
		    gc.fill = GridBagConstraints.NONE;
		    gc.weightx = 0.5;
		    gc.gridx = 3;
		    gc.gridy = 1;
		    infopanel.add(saveworkspace,gc);
	    }
	    
        
		return infopanel;
	}
	
	@SuppressWarnings("unchecked")
	public static JPanel optionPanel()
	{
		workspacePath = ReadConfig.readConfig("currentworkspace");
		ArrayList<String> logfiles = new ArrayList<String>();
		File base = new File(workspacePath+"\\parent\\server\\chorus-server-run\\target\\chorus\\logs");
		File[] listofFiles = base.listFiles();
		
		JPanel optionpanel = new JPanel(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		optionpanel.setBackground(Color.WHITE);
		 CustomJButton Launch_Investigator = new CustomJButton("Launch Investigator");
		    gc.fill = GridBagConstraints.NONE;
		    gc.weightx = 0.3;
		    gc.gridwidth = 1;
		    gc.gridx = 0;
		    gc.gridy = 0;
		   optionpanel.add(Launch_Investigator,gc);
		   
		   CustomJButton Launch_KC = new CustomJButton("Launch Knowledge Center");
		   gc.fill = GridBagConstraints.NONE;
		    gc.weightx = 0.3;
		    gc.gridwidth = 1;
		    gc.gridx = 1;
		    gc.gridy = 0;
		   optionpanel.add(Launch_KC,gc);
		   
		   CustomJButton Launch_local_Button = new CustomJButton("Launch Local");
		    gc.fill = GridBagConstraints.NONE;
		    gc.weightx = 0.3;
		    gc.gridwidth = 1;
		    gc.gridx = 2;
		    gc.gridy = 0;
		   optionpanel.add(Launch_local_Button,gc);
		   
		   CustomJButton increase_time = new  CustomJButton("Increase Timeout");
		   
		    gc.fill = GridBagConstraints.NONE;
		    gc.weightx = 0.3;
		    gc.gridwidth = 1;
		    gc.gridx = 3;
		    gc.gridy = 0;
		   optionpanel.add(increase_time,gc);
		   
		   CustomJButton redeploy_War = new CustomJButton("Redeploy WAR");
		   
		    gc.fill = GridBagConstraints.NONE;
		    gc.weightx = 0.3;
		    gc.insets = new Insets(10,0,10,0);
		    gc.gridwidth = 1;
		    gc.gridx = 0;
		    gc.gridy = 1;
		   optionpanel.add(redeploy_War,gc);
		   
		   
		   CustomJButton open_log = new CustomJButton("Open");
		   
		    gc.fill = GridBagConstraints.NONE;
		    gc.weightx = 0.3;
		    gc.insets = new Insets(10,0,10,0);
		    gc.gridwidth = 1;
		    gc.gridx = 2;
		    gc.gridy = 1;
		   optionpanel.add(open_log,gc);
		   
		   
		   try{
				for(int i = 0 ; i < listofFiles.length ; i++)
				{
					if(listofFiles[i].isFile()){
						logfiles.add(listofFiles[i].getName());
					}
				}
				
				final DefaultComboBoxModel logNames = new DefaultComboBoxModel();
				   for(int i = 0 ; i < logfiles.size();i++)
				   {
					   logNames.addElement(logfiles.get(i));
				   }
				   
				final JComboBox logList = new JComboBox(logNames);
				   logList.setBackground(Color.WHITE);
				   logList.setForeground(Color.BLUE);
				   logList.setRenderer(new DefaultListCellRenderer() {
					    @Override
					    public void paint(Graphics g) {
					        setBackground(Color.WHITE);
					        super.paint(g);
					    }
					});
				    gc.fill = GridBagConstraints.NONE;
				    gc.weightx = 0.3;
				    gc.gridwidth = 1;
				    gc.gridx = 1;
				    gc.gridy = 1;
				   optionpanel.add(logList,gc);
				   
				   open_log.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							String path = workspacePath+"//parent//server//chorus-server-run//target//chorus//logs";
							try {
								Process p = Runtime.getRuntime().exec("notepad "+path+"//"+logList.getSelectedItem() );
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					});
				}
				catch(Exception e)
				{
					open_log.setEnabled(false);
					CustomJLabel info = new CustomJLabel("No logs are available Currently");
					info.setForeground(Color.RED);
					gc.fill = GridBagConstraints.NONE;
				    gc.weightx = 0.3;
				    gc.gridwidth = 1;
				    gc.gridx = 1;
				    gc.gridy = 1;
				   optionpanel.add(info,gc);
				
				}
		  
		   Launch_local_Button.addActionListener(new ActionListener() {
		    	 
	            public void actionPerformed(ActionEvent e)
	            {
	           String url = "http://localhost:8080/Chorus/"; 	
		    Desktop desktop = Desktop.getDesktop();
	        try {
	            desktop.browse(new URI(url));
	        	
	        } catch (IOException | URISyntaxException e1) {
	            // TODO Auto-generated catch block
	            e1.printStackTrace();
	        }
	         
	            	
	            	
	            }});
		   
		   Launch_Investigator.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				 String url = "http://localhost:8080/Chorus/chorusR2.jsp"; 	
				    Desktop desktop = Desktop.getDesktop();
			        try {
			            desktop.browse(new URI(url));
			        	
			        } catch (IOException | URISyntaxException e1) {
			            // TODO Auto-generated catch block
			            e1.printStackTrace();
			        }
				
				
			}
		});
		   
		   increase_time.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				final JFrame frame = new JFrame ("Increase Timeout");
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
        		final CustomJLabel newtime = new CustomJLabel("New Timeout:");
        	    gc.fill = GridBagConstraints.NONE;
        	    gc.weightx = 0.5;
        	    gc.gridx = 2;
        	    gc.gridy = 1;
        	    panel1.add(newtime,gc);
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
        	    frame.add(panel1, BorderLayout.NORTH);
        	    frame.add(panel2,BorderLayout.CENTER);
                frame.setVisible (true);
                resume_Button.addActionListener(new ActionListener() {
        	    	 
          		    public void actionPerformed(ActionEvent e) {
          		    	cmd = ReadConfig.readConfig("build");
          		    	String regex = "=\\d+";
          		    	if(cmd.contains(regex))
          		    	{
          		    	cmd = cmd.replaceAll(regex, "="+resumetext.getText());
          		    	}
          		    	else{
          		    		cmd += "-Dcom.ca.chorus.bootstrap.teiidVdbReadyTimeoutSecs="+resumetext.getText();
          		    	}
          		    	ChorusMavenBuilder.mavenBuilder(workspacePath,cmd,build_status_button,ReadConfig.readConfig("UserId"));
          		    	frame.dispose();
          		    }
            });
				
			}
		});
		   
		  redeploy_War.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					 String path = workspacePath+"//parent//server//chorus-server-run//target//chorus//jboss//bin";
					 try {
						Runtime.getRuntime().exec("cmd /c START "+path+"//jboss-cli.bat");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				}
			});
		   
		   Launch_KC.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					 String url = "http://localhost:8080/Help/HelpNavigator.jsp"; 	
					    Desktop desktop = Desktop.getDesktop();
				        try {
				            desktop.browse(new URI(url));
				        	
				        } catch (IOException | URISyntaxException e1) {
				            // TODO Auto-generated catch block
				            e1.printStackTrace();
				        }
					
					
				}
			});
		
		return optionpanel;
	}
	public static JPanel saveWorkspacePanel(final JFrame frame1)
	{
		JPanel workspacepanel = new JPanel(new GridBagLayout());
		workspacepanel.setBackground(Color.WHITE);
	    GridBagConstraints gc = new GridBagConstraints();
		final CustomJLabel loadLabel = new CustomJLabel("Load Workspace:");
	    gc.fill = GridBagConstraints.NONE;
	    gc.weightx = 0.2;
	    gc.gridx = 1;
	    gc.gridy = 0;
	    workspacepanel.add(loadLabel,gc);
	    final JTextField pathText = new JTextField(40);
	    gc.fill = GridBagConstraints.NONE;
	    gc.weightx = 0.3;
	    gc.weighty = 2.0;
	    gc.gridx = 3;
	    gc.gridy = 0;
	    workspacepanel.add(pathText,gc);
	    CustomJButton browsePath = new CustomJButton("Browse");
	    gc.fill = GridBagConstraints.NONE;
	    gc.weightx = 0.3;
	    gc.gridwidth = 1;
	    gc.gridx = 4;
	    gc.gridy = 0;
	    workspacepanel.add(browsePath,gc);
	    CustomJButton save = new CustomJButton("Save");
	    gc.fill = GridBagConstraints.NONE;
	    gc.weightx = 0.3;
	    gc.gridwidth = 1;
	    gc.gridx = 3;
	    gc.gridy = 1;
	    workspacepanel.add(save,gc);
	    browsePath.addActionListener(new ActionListener() {
	    	 
		    public void actionPerformed(ActionEvent e) {
		    	JFileChooser chooser = new JFileChooser();
	            chooser.setCurrentDirectory(new java.io.File("."));
	            chooser.setDialogTitle("Browse the folder to process");
	            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	            chooser.setAcceptAllFileFilterUsed(false);

	            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
	                
	                pathText.setText(chooser.getSelectedFile().toString());
	            } else {
	                System.out.println("No Selection ");
	            }
		    	}
		    });
	   save.addActionListener(new ActionListener() {
 	    	 
  		    public void actionPerformed(ActionEvent e) {
  		    	
  		    	CreateConfig.addWorkspace(pathText.getText());
  		    	
  		    	restart(frame1);
  		    	 
  		    }
  		  private void restart(JFrame frame1) {
				SwingUtilities.invokeLater(new Runnable() {

		            @Override
		            public void run() {
		                initUI();
		            }});
					frame1.dispose();
					}
	   });
				
		
		return workspacepanel ;
	}
	public static void setWorkspacePath(String pathName, String file, JFrame frame) throws Exception
	{
		try {
				
			File filex = new File(file);
	           if(filex.exists())
	           {
	        	   filex.delete();
	           }
	           		File filey = new File(file);
	        	   FileOutputStream fos = new FileOutputStream(filey,false);
	        	   fos.write(pathName.getBytes());
	        	   fos.close();
	           
	         } catch (IOException e1) {
	            e1.printStackTrace();
	         }
		
		setjobossHome(pathName);
		
		SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                initUI();
            }});
			frame.dispose();
			}
		
	
	
	
	private static void setjobossHome(String pathName) {
		// TODO Auto-generated method stub
		ProcessBuilder builder1 = new ProcessBuilder(	         
	        	"cmd.exe", "/c", "cd \""+pathName+"\\parent\\server\\chorus-server-run\" && START setx JBOSS_HOME "+pathName+"\\parent\\server\\chorus-server-run\\target\\chorus\\jboss");
		            builder1.redirectErrorStream(true);
		            Process p1;
					try {
						p1 = builder1.start();
						BufferedReader r1 = new BufferedReader(new InputStreamReader(p1.getInputStream()));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	}
	public static String getWorkspacePath()
	{
		String path = "";
		
		path = ReadConfig.readConfig("currentworkspace");
		return path;
	}
	
	
}    
class Chooser extends JFrame {

JFileChooser chooser;
String fileName;

public Chooser() {
chooser = new JFileChooser();
int r = chooser.showOpenDialog(new JFrame());
if (r == JFileChooser.APPROVE_OPTION) {
fileName = chooser.getSelectedFile().getPath();
}
}
}

