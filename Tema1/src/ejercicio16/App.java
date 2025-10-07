package ejercicio16;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class App {
	// 1. Crear un programa que lea el siguiente fichero xml.
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory miFactoria = DocumentBuilderFactory.newInstance();
		DocumentBuilder miConstructor = miFactoria.newDocumentBuilder();
		Document miDocumento = miConstructor.parse(new File("D:\\GaleriaDeArte.XML"));

		NodeList listaColecciones = miDocumento.getElementsByTagName("Coleccion");

		for (int i = 0; i < listaColecciones.getLength(); i++) {
			Node nodoColecciones = listaColecciones.item(i);

			if (nodoColecciones.getNodeType() == Node.ELEMENT_NODE) {
				Element colecciones = (Element) nodoColecciones;
				System.out.println("-------------------- COLECCIONES --------------------");
				System.out.println("Nombre : " + colecciones.getAttribute("nombre"));
				System.out.println("Año inicio : " + colecciones.getAttribute("año_inicio"));
				System.out.println("Año fin : " + colecciones.getAttribute("año_fin"));

				NodeList listaPinturas = colecciones.getChildNodes();

				for (int j = 0; j < listaPinturas.getLength(); j++) {
					Node nodoPintura = listaPinturas.item(j);

					if (nodoPintura.getNodeType() == Node.ELEMENT_NODE) {
						Element pinturas = (Element) nodoPintura;
						System.out.println("\n---- PINTURAS ---------------------------------------\n");
						System.out.println("Titulo : " + pinturas.getAttribute("titulo"));
						System.out.println("Artista : " + pinturas.getAttribute("artista"));
						System.out.println("Año : " + pinturas.getAttribute("año"));

						NodeList infoPinturas = pinturas.getChildNodes();

						for (int k = 0; k < infoPinturas.getLength(); k++) {
							Node nodoInfoPintura = infoPinturas.item(k);

							if (nodoInfoPintura.getNodeType() == Node.ELEMENT_NODE) {
								Element elementosPinturas = (Element) nodoInfoPintura;

								System.out.println(
										elementosPinturas.getTagName() + " " + elementosPinturas.getTextContent());
								if (elementosPinturas.getTagName().equalsIgnoreCase("Tecnica")) {
									System.out.println(" - tipo : " + elementosPinturas.getAttribute("tipo"));
									System.out.println(
											" - dimensiones : " + elementosPinturas.getAttribute("dimensiones"));
								} else if (elementosPinturas.getTagName().equalsIgnoreCase("Ubicacion")) {
									System.out.println(" - sala : " + elementosPinturas.getAttribute("sala"));
									System.out.println(" - pared : " + elementosPinturas.getAttribute("pared"));
								}

								NodeList listaOpiniones = elementosPinturas.getChildNodes();

								for (int l = 0; l < listaOpiniones.getLength(); l++) {
									Node nodoOpiniones = listaOpiniones.item(l);

									if (nodoOpiniones.getNodeType() == Node.ELEMENT_NODE) {
										Element elementosOpiniones = (Element) nodoOpiniones;

										System.out.println("-------------------------------------- OPINIONES ----");
										System.out.println("Autor : " + elementosOpiniones.getAttribute("autor"));
										System.out.println(
												"Puntuacion : " + elementosOpiniones.getAttribute("puntuacion"));
										System.out.println("opinion : " + elementosOpiniones.getTextContent());
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
