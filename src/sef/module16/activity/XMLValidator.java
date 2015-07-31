package sef.module16.activity;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLValidator {

	public XMLValidator() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Source schemaFile = null;
		schemaFile = new StreamSource(new File("bin/sef/module16/activity/person.xsd"));
		Source xmlFile = new StreamSource(new File("bin/sef/module16/activity/person_XSD.xml"));
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = null;
		
		try {
			schema = schemaFactory.newSchema(schemaFile);
		} catch (SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Validator validator = schema.newValidator();
		try {
			try {
				validator.validate(xmlFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(xmlFile.getSystemId() + " is valid");
		} catch (SAXException e) {
			System.out.println(xmlFile.getSystemId() + " is NOT valid");
			System.out.println("Reason: " + e.getLocalizedMessage());
		}

	

	// Get the DOM Builder Factory
	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

	// Get the DOM Builder
	DocumentBuilder builder=null;;
	try {
		builder = factory.newDocumentBuilder();
	} catch (ParserConfigurationException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}

	// Load and Parse the XML document
	// document contains the complete XML as a Tree.
	Document document=null;;
	try {
		File target= new File(".\\bin\\sef\\module16\\activity\\person_XSD.xml");
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(target));
		document = builder.parse(in);
	} catch (SAXException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	List<Person> empList = new ArrayList<>();

	// Iterating through the nodes and extracting the data.
	NodeList nodeList = document.getDocumentElement().getChildNodes();

	for(
	int i = 0;i<nodeList.getLength();i++)

	{

		// We have encountered an <employee> tag.
		Node node = nodeList.item(i);
		if (node instanceof Element) {
			Person emp = new Person();

			NodeList childNodes = node.getChildNodes();
			for (int j = 0; j < childNodes.getLength(); j++) {
				Node cNode = childNodes.item(j);

				// Identifying the child tag of employee encountered.
				if (cNode instanceof Element) {
					String content = cNode.getLastChild().getTextContent().trim();
					switch (cNode.getNodeName()) {
					case "first_name":
						emp.name = content;
						break;
					case "last_name":
						emp.surname = content;
						break;
					case "gender":
						emp.gender = content;
						break;
					case "age":
						emp.age = content;
						break;
					}
				}
			}
			empList.add(emp);
		}

	}

	// Printing the Employee list populated.
	for(

	Person emp:empList)

	{
		System.out.println(emp);
	}

}
}


class Person {
	String name, surname, gender, age;

	@Override
	public String toString() {
		return name + " " + surname + " " + gender + " " + age;
	}

}
