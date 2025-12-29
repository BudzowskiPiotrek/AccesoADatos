package XML;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import XML.utils.Ejercicio1;
import XML.utils.Ejercicio2;
import XML.utils.Ejercicio3;

public class PruebasEjercicios {
	public static void main(String[] args) {
		//ejercicio1();
		//ejercicio2();
		ejercicio3();
	}

	private static void ejercicio1() {
		Ejercicio1 e1 = new Ejercicio1();
		try {
			DocumentBuilderFactory dF = DocumentBuilderFactory.newInstance();
			DocumentBuilder dB = dF.newDocumentBuilder();
			DOMImplementation im = dB.getDOMImplementation();
			Document doc = im.createDocument(null, "Navieras", null);
			doc.setXmlVersion("1.0");

			doc = e1.convertirXML(doc, "Maersk Line");

			Source s = new DOMSource(doc);
			Result r = new StreamResult(new File("D:\\xml\\naviera.xml"));
			Transformer t = TransformerFactory.newInstance().newTransformer();
			t.transform(s, r);

		} catch (ParserConfigurationException e) {

		} catch (TransformerConfigurationException e) {
			System.err.println("Detalle del error: " + e.getMessage());
		} catch (TransformerFactoryConfigurationError e) {
			System.err.println("Detalle del error: " + e.getMessage());
		} catch (TransformerException e) {
			System.err.println("Detalle del error: " + e.getMessage());
		}
	}

	private static void ejercicio2() {

		Ejercicio2 e1 = new Ejercicio2();
		try {
			DocumentBuilderFactory dF = DocumentBuilderFactory.newInstance();
			DocumentBuilder dB = dF.newDocumentBuilder();
			DOMImplementation im = dB.getDOMImplementation();
			Document doc = im.createDocument(null, "evento_esports", null);
			doc.setXmlVersion("1.0");

			doc = e1.convertirXML(doc, "VAL-2025");

			Source s = new DOMSource(doc);
			Result r = new StreamResult(new File("D:\\xml\\evento_esports.xml"));
			Transformer t = TransformerFactory.newInstance().newTransformer();
			t.transform(s, r);

		} catch (ParserConfigurationException e) {

		} catch (TransformerConfigurationException e) {
			System.err.println("Detalle del error: " + e.getMessage());
		} catch (TransformerFactoryConfigurationError e) {
			System.err.println("Detalle del error: " + e.getMessage());
		} catch (TransformerException e) {
			System.err.println("Detalle del error: " + e.getMessage());
		}
	}

	private static void ejercicio3() {
		Ejercicio3 e3 = new Ejercicio3();

		try {
			DocumentBuilderFactory dF = DocumentBuilderFactory.newInstance();
			DocumentBuilder dB = dF.newDocumentBuilder();
			DOMImplementation im = dB.getDOMImplementation();
			Document doc = im.createDocument(null, "universo", null);
			doc.setXmlVersion("1.0");

			doc = e3.crearDocumentoXML(doc, "MW-01");

			Source s = new DOMSource(doc);
			Result r = new StreamResult(new File("D:\\xml\\universo.xml"));
			Transformer t = TransformerFactory.newInstance().newTransformer();
			t.transform(s, r);

		} catch (ParserConfigurationException e) {
			System.err.println("Error al intentar crear documento : , detalle del error : " + e.getMessage());
		} catch (TransformerConfigurationException e) {
			System.err.println("Detalle del error: " + e.getMessage());
		} catch (TransformerFactoryConfigurationError e) {
			System.err.println("Detalle del error: " + e.getMessage());
		} catch (TransformerException e) {
			System.err.println("Detalle del error: " + e.getMessage());
		}

	}
}
