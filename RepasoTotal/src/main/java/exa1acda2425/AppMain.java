package exa1acda2425;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import exa1acda2425.utils.*;

public class AppMain {
	public static void main(String[] args) {
		ejercicio1();
		ejercicio2();
		ejercicio3();
		ejercicio3_2();
		ejercicio4();
	}

	private static void ejercicio1() {
		Ejercicio1 ejercicio1 = new Ejercicio1();
		ejercicio1.obtenerDatosPersonal(2);
	}

	private static void ejercicio2() {
		Ejercicio2 ejercicio2 = new Ejercicio2();
		ejercicio2.obtenerDatosRecaudacion();
	}

	private static void ejercicio3() {
		Ejercicio3 ejercicio3 = new Ejercicio3();
		try {
			DocumentBuilderFactory dF = DocumentBuilderFactory.newInstance();
			DocumentBuilder dB = dF.newDocumentBuilder();
			DOMImplementation im = dB.getDOMImplementation();
			Document doc = im.createDocument(null, "Vuelos", null);
			doc.setXmlVersion("1.0");
			
			doc = ejercicio3.crearDocumentoXML(2, doc);
			
			Source s = new DOMSource(doc);
			Result r = new StreamResult(new File("D:\\vuelos.xml"));
			Transformer t = TransformerFactory.newInstance().newTransformer();
			t.transform(s, r);

		} catch (Exception e) {
			System.err.println("Ha ocurrido un error durante la generación del XML:");
			System.err.println("Detalle de Error: " + e.getMessage());
		}
	}

	private static void ejercicio3_2() {
		Ejercicio3_2 ejercicio3_2 = new Ejercicio3_2();

		try {

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new File("D:\\vuelos.xml"));
			doc.getDocumentElement().normalize();


			ejercicio3_2.insertarDesdeXML(doc);

		} catch (Exception e) {
			System.err.println("Ha ocurrido un error durante el parseo o la inserción:");
			System.err.println("Detalle de Error: " + e.getMessage());
		}

	}

	private static void ejercicio4() {
		Ejercicio4 ejercicio4 = new Ejercicio4();
		ejercicio4.insertar();

	}

}
