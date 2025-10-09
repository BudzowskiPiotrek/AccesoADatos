package ejercicio17;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class App {
	// Crear un programa que permita insertar una opinión en una pintura
	// determinada, introduciendo todos los datos por teclado.
	public static void main(String[] args)
			throws ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException, SAXException, IOException {
		DocumentBuilderFactory miFactoria = DocumentBuilderFactory.newInstance();
		DocumentBuilder miConstructor = miFactoria.newDocumentBuilder();
		Document miDocumento = miConstructor.parse(new File("D:\\GaleriaDeArte.XML"));
		
		NodeList listaPinturas = miDocumento.getElementsByTagName("Pintura");

		for (int j = 0; j < listaPinturas.getLength(); j++) {
			Node nodoPintura = listaPinturas.item(j);

			if (nodoPintura.getNodeType() == Node.ELEMENT_NODE) {
				Element pinturas = (Element) nodoPintura;
				System.out.println("\n---- PINTURAS ---------------------------------------\n");
				System.out.println("Titulo : " + pinturas.getAttribute("titulo"));
				System.out.println("Artista : " + pinturas.getAttribute("artista"));
				System.out.println("Año : " + pinturas.getAttribute("año"));
			}
		}
		
		Scanner st = new Scanner(System.in);
		System.out.println("¿dime titulo de obra completa?");
		String titulo = st.nextLine();
		for (int j = 0; j < listaPinturas.getLength(); j++) {
			Node nodoPintura = listaPinturas.item(j);

			if (nodoPintura.getNodeType() == Node.ELEMENT_NODE) {
				Element pinturas = (Element) nodoPintura;
				if(titulo.equalsIgnoreCase(pinturas.getAttribute("titulo"))) {
					System.out.println("¿cual es tu opinion?");
					Element opinion = miDocumento.createElement("Opinion");
					Text textoOpinion = miDocumento.createTextNode(st.nextLine());
					opinion.appendChild(textoOpinion);
					pinturas.appendChild(opinion);
				}
			}
		}
		
		Source source = new DOMSource(miDocumento);
		Result resultado = new StreamResult(new File("D:\\GaleriaDeArte.XML"));
		Transformer miTransformer = TransformerFactory.newInstance().newTransformer();
		miTransformer.transform(source, resultado);
	}
}
