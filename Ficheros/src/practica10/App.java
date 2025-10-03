package practica10;

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

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory miFactoria = DocumentBuilderFactory.newInstance(); // Definición de la factoría DOM
		DocumentBuilder miConstructor = miFactoria.newDocumentBuilder(); // Definición del constructor DOM
		Document miDocumento = miConstructor.parse(new File("D:\\concesionario.XML")); // Acceso al archivo XML
																						// existente
		NodeList listaCoches = miDocumento.getElementsByTagName("coche"); // creación de una lista con todos los
	
		
		for (int i = 0; i < listaCoches.getLength(); i++) {
			Node nodo = listaCoches.item(i);
			if (nodo.getNodeType() == Node.ELEMENT_NODE) { // detectamos si es un elementos para obtener sus nodos
				Element e = (Element) nodo; // convierto el nodo en un elemento casteándolo
				System.out.println(e.getNodeName() + " Matrícula " + ": " + e.getAttribute("matricula")); // visualizo
																											
				NodeList listaHijos = e.getChildNodes(); // obtengo la lista de nodos que tengan los hijos
				
				
				for (int j = 0; j < listaHijos.getLength(); j++) { // repito la acción de recorrido de todos los nodos
																	// hijos
					Node hijo = listaHijos.item(j);
					if (hijo.getNodeType() == Node.ELEMENT_NODE) { // detectamos si es un elemento para obtener sus
																	// hijos
						Element eHijo = (Element) hijo;
						if (eHijo.getNodeName() == "propietario")
							System.out.println("---DNI Propietario:" + eHijo.getAttribute("DNI"));
						else
							System.out.println(eHijo.getNodeName() + ":" + eHijo.getTextContent());
						NodeList listaNietos = eHijo.getChildNodes();
						
						
						for (int k = 0; k < listaNietos.getLength(); k++) { // repito la acción de recorrido de todos
																			// los nodos nietos
							Node nieto = listaNietos.item(k);
							if (nieto.getNodeType() == Node.ELEMENT_NODE) { // detectamos si es un elemento para obtener
																			// sus hijos (nietos)
								Element eNieto = (Element) nieto;
								System.out.println(eNieto.getNodeName() + ":" + eNieto.getTextContent());
							}
						}
					}
				}
			}
		}
	}

}
