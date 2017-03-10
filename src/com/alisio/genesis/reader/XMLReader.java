package com.alisio.genesis.reader;

import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLReader {
	
	public String path;
	public static List<XMLObject> trees = new ArrayList<XMLObject>();
	public static String error  = "";
	
	public XMLReader(String path) {
		this.path = path;
		readFile();
	}
	
	private void readFile() {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
		        .newInstance();
		
		DocumentBuilder documentBuilder = null;
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(XMLReader.class.getResourceAsStream(path));	
			getTrees(document);		
		} catch (Exception e) {
			error = e.getMessage();
			System.out.println("Could not get info from " + path);
		}
	}
	
	private void getTrees(Document document){
		NodeList nodelist = document.getElementsByTagName("tree");
		if(nodelist != null){
			for(int i = 0; i < nodelist.getLength();i++) {
				Node node = nodelist.item(i);
				if(node != null) {
					Element e = (Element)node;
					Node name = e.getElementsByTagName("name").item(0);
					Node locationX = e.getElementsByTagName("locationx").item(0);
					Node locationY = e.getElementsByTagName("locationy").item(0);
					XMLObject tree = new XMLObject(name.getTextContent(),Integer.parseInt(locationX.getTextContent()),Integer.parseInt(locationY.getTextContent()));
					trees.add(tree);
				}
			}
		}		
	}
}
