package XML;

import java.io.File;
import java.io.IOException;

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
import org.xml.sax.SAXException;

import XML.utils.Ejercicio1;
import XML.utils.Ejercicio2;
import XML.utils.Ejercicio3;
import XML.utils.Ejercicio4;
import XML.utils.Ejercicio5;
import XML.utils.Ejercicio6;
import XML.utils.Ejercicio7;
import XML.utils.Ejercicio8;
import XML.utils.Ejercicio9;

public class PruebasEjercicios {
	public static void main(String[] args) {
		// SQL --> XML
//		ejercicio1();
//		ejercicio2();
//		ejercicio3();
//		ejercicio7();
//		ejercicio9();

		// Neodatis --> XML
//		ejercicio4();
//      ejercicio5();

		// XML --> SQL
//		ejercicio6();

		// XML --> Neodatis
//		ejercicio8();
		ejercicio10();
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

	private static void ejercicio4() {
		Ejercicio4 e4 = new Ejercicio4();

		try {
			DocumentBuilderFactory dF = DocumentBuilderFactory.newInstance();
			DocumentBuilder dB = dF.newDocumentBuilder();
			DOMImplementation im = dB.getDOMImplementation();
			Document doc = im.createDocument(null, "universo", null);
			doc.setXmlVersion("1.0");

			doc = e4.crearDocumentoXML(doc, "Andrómeda");

			Source s = new DOMSource(doc);
			Result r = new StreamResult(new File("D:\\xml\\universo_neodatis.xml"));
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

	private static void ejercicio5() {
		Ejercicio5 e5 = new Ejercicio5();

		try {
			DocumentBuilderFactory dF = DocumentBuilderFactory.newInstance();
			DocumentBuilder dB = dF.newDocumentBuilder();
			DOMImplementation im = dB.getDOMImplementation();
			Document doc = im.createDocument(null, "universo", null);
			doc.setXmlVersion("1.0");

			doc = e5.crearDocumentoXML(doc, "Vía Láctea");

			Source s = new DOMSource(doc);
			Result r = new StreamResult(new File("D:\\xml\\universo_neodatis2.xml"));
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

	private static void ejercicio6() {
		Ejercicio6 e6 = new Ejercicio6();

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new File("D:\\xml\\evento_esports.xml"));

			boolean exito = e6.enviarLeidoSQL(doc);

			if (exito) {
				System.out.println("Volcado de datos exitoso");
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			System.out.println(
					"Un error inesperado al intentar acceder al archivo, detalle del error: " + e.getMessage());
		}
	}

	private static void ejercicio7() {
		Ejercicio7 e7 = new Ejercicio7();

		try {
			DocumentBuilderFactory dF = DocumentBuilderFactory.newInstance();
			DocumentBuilder dB = dF.newDocumentBuilder();
			DOMImplementation im = dB.getDOMImplementation();
			Document doc = im.createDocument(null, "clinica", null);
			doc.setXmlVersion("1.0");

			doc = e7.convertirXML(doc, "Juan Pérez");

			Source s = new DOMSource(doc);
			Result r = new StreamResult(new File("D:\\xml\\clinica.xml"));
			Transformer t = TransformerFactory.newInstance().newTransformer();
			t.transform(s, r);

		} catch (ParserConfigurationException | TransformerException | TransformerFactoryConfigurationError e) {
			System.err.println("Error al intentar crear documento de xml, Dertalle del error: " + e.getMessage());
		}
	}

	private static void ejercicio8() {
		Ejercicio8 e8 = new Ejercicio8();

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new File("D:\\xml\\clinica.xml"));

			boolean exito = e8.CrearNeodatis(doc);

			if (exito) {
				System.out.println("Exito al pasar datos a neodatis");
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			System.err.println("Error al intentar abrir archivo, Detalle del error : " + e.getMessage());
		}
	}

	private static void ejercicio9() {
		Ejercicio9 e9 = new Ejercicio9();
		try {
			DocumentBuilderFactory dF = DocumentBuilderFactory.newInstance();
			DocumentBuilder dB = dF.newDocumentBuilder();
			DOMImplementation im = dB.getDOMImplementation();
			Document doc = im.createDocument(null, "universo_dnd", null);
			doc.setXmlVersion("1.0");

			doc = e9.convertirXML(doc);

			Source s = new DOMSource(doc);
			Result r = new StreamResult(new File("D:\\xml\\universo_dnd.xml"));
			Transformer t = TransformerFactory.newInstance().newTransformer();
			t.transform(s, r);
			System.out.println("Proceso terminado");

		} catch (ParserConfigurationException | TransformerException | TransformerFactoryConfigurationError e) {
			System.err.println("Error al intentar abrir archivo, Detalle del error : " + e.getMessage());
		}
	}

	private static void ejercicio10() {
		// TODO Auto-generated method stub
	}
}
