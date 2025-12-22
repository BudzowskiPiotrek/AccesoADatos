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

public class PruebasEjercicios {
	public static void main(String[] args) {
		ejerciocio1();
	}

	private static void ejerciocio1() {
		Ejercicio1 e1 = new Ejercicio1();

		try {
			DocumentBuilderFactory dF = DocumentBuilderFactory.newInstance();
			DocumentBuilder dB = dF.newDocumentBuilder();
			DOMImplementation im = dB.getDOMImplementation();
			Document doc = im.createDocument(null, "Navieras", null);
			doc.setXmlVersion("1.0");

			doc = e1.convertirXML(doc, "Maersk Line");

			Source s = new DOMSource(doc);
			Result r = new StreamResult(new File("D:\\naviera.xml"));
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
}
