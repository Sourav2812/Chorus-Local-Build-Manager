package com.ca.chorus.builder;

import java.io.File;  
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;  
import javax.xml.parsers.DocumentBuilderFactory;  

import org.w3c.dom.Document;  
import org.w3c.dom.Element;  
import org.w3c.dom.Node;  
import org.w3c.dom.NodeList;  

public class ReadConfig {
	
	public static String readConfig(String tagname)
	{
		String value = "";
		try {  
			  
			   File xmlFile = new File("Configuration.xml");  
			   DocumentBuilderFactory documentFactory = DocumentBuilderFactory  
			     .newInstance();  
			   DocumentBuilder documentBuilder = documentFactory  
			     .newDocumentBuilder();  
			   Document doc = documentBuilder.parse(xmlFile);  
			  
			   doc.getDocumentElement().normalize();
			   NodeList nodeList = doc.getElementsByTagName("chorusbuilderinfo");    
			  
			   for (int temp = 0; temp < nodeList.getLength(); temp++) {  
			    Node node = nodeList.item(temp); 
			    if (node.getNodeType() == Node.ELEMENT_NODE) {  
			  
			     Element currnode = (Element) node;  
			     value = currnode.getElementsByTagName(tagname).item(0).getTextContent();
			     
			  
			    }  
			   }  
			  } catch (Exception e) {  
			   e.printStackTrace();  
			  }
		return value;  
	}
	public static List<String> readWorkspace(String workspacepath)
	{
		List<String> value = new ArrayList<String>();
		try {  
			  
			   File xmlFile = new File("Configuration.xml");  
			   DocumentBuilderFactory documentFactory = DocumentBuilderFactory  
			     .newInstance();  
			   DocumentBuilder documentBuilder = documentFactory  
			     .newDocumentBuilder();  
			   Document doc = documentBuilder.parse(xmlFile);  
			  
			   doc.getDocumentElement().normalize();
			   NodeList nodeList = doc.getElementsByTagName("chorusbuilderinfo");    
			  
			   for (int temp = 0; temp < nodeList.getLength(); temp++) {  
			    Node node = nodeList.item(temp); 
			    if (node.getNodeType() == Node.ELEMENT_NODE) {  
			  
			    NodeList workspaces = doc.getElementsByTagName("Workspaces");
			    for (int temp1 = 0; temp1 < workspaces.getLength(); temp1++) {  
				    Node workspacenode = workspaces.item(temp1); 
				    if (workspacenode.getNodeType() == workspacenode.ELEMENT_NODE) { 
			     Element currworkspace = (Element) workspacenode;  
			     value.add(currworkspace.getElementsByTagName(workspacepath).item(0).getTextContent());
		
				    }
				    }
				    
			   }  }
			  } catch (Exception e) {  
			   e.printStackTrace();  
			  }
		return value;  

}}
