package com.alisio.genesis.util.reader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.alisio.genesis.level.Level;

public class XMLReader {
	
	public String path;
	private Level level;
	public static String error  = "";
	
	public XMLReader(String path, Level level) {
		this.path = path;
		this.level = level;
		readFile();
	}
	
	private void readFile() {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
		        .newInstance();
		
		DocumentBuilder documentBuilder = null;
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(XMLReader.class.getResourceAsStream(path));	
			getObjects(document);		
		} catch (Exception e) {
			error = e.getMessage();
			System.out.println("Could not get info from " + path);
		}
	}
	
	private void getObjects(Document document){
		NodeList nodelist = document.getElementsByTagName("object");
		if(nodelist != null){
			for(int i = 0; i < nodelist.getLength();i++) {
				Node node = nodelist.item(i);
				if(node != null) {
					Element e = (Element)node;
					Node name = e.getElementsByTagName("name").item(0);
					Node locationX = e.getElementsByTagName("locationx").item(0);
					Node locationY = e.getElementsByTagName("locationy").item(0);
					XMLObject object = new XMLObject(name.getTextContent(),Integer.parseInt(locationX.getTextContent()),Integer.parseInt(locationY.getTextContent()));
					level.add(object);
				}
			}
		}		
	}
}
