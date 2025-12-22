package exa1acda2526;

import java.io.File;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
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

import exa1acda2526.utils.LogicaEjercicios;

public class AppMain {
	public static LogicaEjercicios lg = new LogicaEjercicios();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int opcion = 0;
		while (opcion != 5) {
			menuTexto();
			try {
				opcion = sc.nextInt();
			} catch (Exception e) {
				System.err.println("Error al ingresar numeros");
				System.out.println("Detalles del error: " + e.getMessage());
				
			}

			switch (opcion) {
			case 1:
				try {
					ejercicio1("V�a L�ctea");
				} catch (Exception e) {
					System.err.println("Error al datos de Where");
					System.out.println("detalles del error: " + e.getMessage());
				}
				break;
			case 2:
				try {
					lg.cuantasEstrellasPorTipo();
				} catch (Exception e) {
					System.out.println("Error innesperado: " + e.getMessage());
				}
				break;
			case 3:
				try {
					lg.crearGalaxias();
					lg.crearPlanetas();
				} catch (Exception e) {
					System.out.println("Error innesperado: " + e.getMessage());
				}
				break;
			case 4:
				System.out.println("Aqui no hay nada enrique");
				break;
			case 5:
				System.out.println("Adios enrique");
				break;
			default:
				break;
			}
		}
	}

	private static void menuTexto() {
		System.out.println("Pulsa 1. Crear archivo xml");
		System.out.println("Pulsa 2. Ver cuantas tipos");
		System.out.println("Pulsa 3. Para crear neodatis");
		System.out.println("Pulsa 4. para consulta de neodatis");
		System.out.println("Pulsa 5. Para salir.");
	}

	private static void ejercicio1(String str) {
		try {
			DocumentBuilderFactory miFactoria = DocumentBuilderFactory.newInstance();
			DocumentBuilder miConstructor = miFactoria.newDocumentBuilder();
			DOMImplementation implementacion = miConstructor.getDOMImplementation();
			Document miDocumento = implementacion.createDocument(null, "Galaxias", null);
			miDocumento.setXmlVersion("1.0");

			miDocumento = lg.crearFicheroXML(miDocumento, str);

			Source source = new DOMSource(miDocumento);
			Result resultado = new StreamResult(new File("D://exa1acda2526//estrellasGalaxia.XML"));
			Transformer miTransformer = TransformerFactory.newInstance().newTransformer();
			miTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
			miTransformer.transform(source, resultado);
			
			System.out.println("Exito, se genro archivo xml");
		} catch (ParserConfigurationException e) {
			System.err.println("Un Error inesperado detalle del error " + e.getMessage());
		} catch (TransformerConfigurationException e) {
			System.err.println("Un Error inesperado detalle del error " + e.getMessage());
		} catch (TransformerFactoryConfigurationError e) {
			System.err.println("Un Error inesperado detalle del error " + e.getMessage());
		} catch (TransformerException e) {
			System.err.println("Un Error inesperado detalle del error " + e.getMessage());
		}

	}

}
