package com.alisio.genesis.reader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLReader {
	
	public String path;
	public static List<XMLTree> trees = new ArrayList<XMLTree>();
	
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
			Document document = documentBuilder.parse(XMLReader.class.getResource(path).getPath());	
			getTrees(document);		
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			System.out.println("Could not get info from " + path);
		} catch (SAXException e) {
			e.printStackTrace();
			System.out.println("Could not get info from " + path);
		} catch (IOException e) {
			e.printStackTrace();
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
					Node locationX = e.getElementsByTagName("locationx").item(0);
					Node locationY = e.getElementsByTagName("locationy").item(0);
					XMLTree tree = new XMLTree(Integer.parseInt(locationX.getTextContent()),Integer.parseInt(locationY.getTextContent()));
					trees.add(tree);
				}
			}
		}		
	}
}
