package com.ca.chorus.builder;

import java.io.File;  
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;  
import javax.xml.parsers.DocumentBuilderFactory;  
import javax.xml.parsers.ParserConfigurationException;  
import javax.xml.transform.Transformer;  
import javax.xml.transform.TransformerException;  
import javax.xml.transform.TransformerFactory;  
import javax.xml.transform.dom.DOMSource;  
import javax.xml.transform.stream.StreamResult;  
  






import org.w3c.dom.Attr;  
import org.w3c.dom.Document;  
import org.w3c.dom.Element; 
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class CreateConfig {
	
	public static void createConfig(String build, String cargo)
	{
		File f = new File("Configuration.xml");
		if(!f.exists()){
		try {  
			
			  
			   DocumentBuilderFactory documentFactory = DocumentBuilderFactory  
			     .newInstance();  
			   DocumentBuilder documentBuilder = documentFactory  
			     .newDocumentBuilder();  
			  
			   // define root elements  
			   Document document = documentBuilder.newDocument();  
			   Element rootElement = document.createElement("chorusbuilderinfo");  
			   document.appendChild(rootElement); 
			   
			  
			   
			   Element credential = document.createElement("CurrentworkspacePath");
			   rootElement.appendChild(credential);  
			   
			   Element currWorkspace = document.createElement("currentworkspace");  
			   currWorkspace.appendChild(document.createTextNode(""));  
			   credential.appendChild(currWorkspace);
 
			   Element info = document.createElement("info");  
			   rootElement.appendChild(info);  
			  
			   Element otherworkspace1 = document.createElement("OtherWorkspace1");
			   otherworkspace1.appendChild(document.createTextNode("")); 
			   credential.appendChild(otherworkspace1);
			   
			   Element otherworkspace2 = document.createElement("OtherWorkspace2");
			   otherworkspace2.appendChild(document.createTextNode("")); 
			   credential.appendChild(otherworkspace2);
			   
			   Element otherworkspace3 = document.createElement("OtherWorkspace3");
			   otherworkspace1.appendChild(document.createTextNode("")); 
			   credential.appendChild(otherworkspace3);
			   
			   Element userid = document.createElement("UserId");
			   userid.appendChild(document.createTextNode(""));
			   info.appendChild(userid);
			   
			   Element buildcmd = document.createElement("build");  
			   buildcmd.appendChild(document.createTextNode(build));  
			   info.appendChild(buildcmd); 
			   
			   Element cargocmd = document.createElement("cargo");  
			   cargocmd.appendChild(document.createTextNode(cargo));  
			   info.appendChild(cargocmd); 
			  
			   // creating and writing to xml file  
			   TransformerFactory transformerFactory = TransformerFactory  
			     .newInstance();  
			   Transformer transformer = transformerFactory.newTransformer();  
			   DOMSource domSource = new DOMSource(document);  
			   StreamResult streamResult = new StreamResult(new File(  
			     "Configuration.xml"));  
			  
			   transformer.transform(domSource, streamResult);  
			  
			   //System.out.println("File saved to specified path!");  
			  
			  } catch (ParserConfigurationException pce) {  
			   pce.printStackTrace();  
			  } catch (TransformerException tfe) {  
			   tfe.printStackTrace();  
			  }  }
	}
	
	public static void addWorkspace(String workspacepath)
	{
		try{
			
			String filepath = "Configuration.xml";

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);
			Node root = doc.getFirstChild();
			
			Element workspace = doc.createElement("Workspaces");
			root.appendChild(workspace);
			
			Element workspacePath = doc.createElement("Workspacepath"); 
			workspacePath.appendChild(doc.createTextNode(workspacepath));
			workspace.appendChild(workspacePath);
			
			  
			
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath));
			transformer.transform(source, result);
		}
		catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		   } catch (IOException ioe) {
			ioe.printStackTrace();
		   } catch (SAXException sae) {
			sae.printStackTrace();
		   }catch (TransformerException tfe) {
				tfe.printStackTrace();
		   }
	}
	public static void currentthreeWorkspace(String workspacepath,String element)
	{
		try{
			
			String filepath = "Configuration.xml";

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);
			Node root = doc.getFirstChild();
			
			Element workspace = doc.createElement("CurrentthreeWorkspaces");
			root.appendChild(workspace);
			
			Element workspacePath = doc.createElement(element); 
			workspacePath.appendChild(doc.createTextNode(workspacepath));
			workspace.appendChild(workspacePath);
			
			  
			
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath));
			transformer.transform(source, result);
		}
		catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		   } catch (IOException ioe) {
			ioe.printStackTrace();
		   } catch (SAXException sae) {
			sae.printStackTrace();
		   }catch (TransformerException tfe) {
				tfe.printStackTrace();
		   }
	}
	public static void setcurrentWorkspace(String workspacepath)
	{
		try{
			
			 
				  
					String filepath = "Configuration.xml"; 
				   DocumentBuilderFactory documentFactory = DocumentBuilderFactory  
				     .newInstance();  
				   DocumentBuilder documentBuilder = documentFactory  
				     .newDocumentBuilder();  
				   Document doc = documentBuilder.parse(filepath);  
				  
				   doc.getDocumentElement().normalize();
				   NodeList nodeList = doc.getElementsByTagName("chorusbuilderinfo");    
				  
				   for (int temp = 0; temp < nodeList.getLength(); temp++) {  
				    Node node = nodeList.item(temp); 
				    if (node.getNodeType() == Node.ELEMENT_NODE) {  
				  
				     Element currnode = (Element) node;  
				     currnode.getElementsByTagName("currentworkspace").item(0).setTextContent(workspacepath);
				     
				  
				   
			
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath));
			transformer.transform(source, result);
		}}}
		catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		   } catch (IOException ioe) {
			ioe.printStackTrace();
		   } catch (SAXException sae) {
			sae.printStackTrace();
		   }catch (TransformerException tfe) {
				tfe.printStackTrace();
		   }
	}
	
	public static void editConfig(String tagName,String Value)
	{
		try{
			
			 
				  
					String filepath = "Configuration.xml"; 
				   DocumentBuilderFactory documentFactory = DocumentBuilderFactory  
				     .newInstance();  
				   DocumentBuilder documentBuilder = documentFactory  
				     .newDocumentBuilder();  
				   Document doc = documentBuilder.parse(filepath);  
				  
				   doc.getDocumentElement().normalize();
				   NodeList nodeList = doc.getElementsByTagName("chorusbuilderinfo");    
				  
				   for (int temp = 0; temp < nodeList.getLength(); temp++) {  
				    Node node = nodeList.item(temp); 
				    if (node.getNodeType() == Node.ELEMENT_NODE) {  
				  
				     Element currnode = (Element) node;  
				     currnode.getElementsByTagName(tagName).item(0).setTextContent(Value);
				     
				  
				   
			
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath));
			transformer.transform(source, result);
		}}}
		catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		   } catch (IOException ioe) {
			ioe.printStackTrace();
		   } catch (SAXException sae) {
			sae.printStackTrace();
		   }catch (TransformerException tfe) {
				tfe.printStackTrace();
		   }
	}

}
