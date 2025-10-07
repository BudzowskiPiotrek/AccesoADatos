package ejercicio15;

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
		DocumentBuilderFactory miFactoria = DocumentBuilderFactory.newInstance();
		DocumentBuilder miConstructor = miFactoria.newDocumentBuilder();
		Document miDocumento = miConstructor.parse(new File("D:\\Departamentos.XML"));

		NodeList listaDepartamentos = miDocumento.getElementsByTagName("departamento");

		// 3. Iterar sobre cada <departamento>
		for (int i = 0; i < listaDepartamentos.getLength(); i++) {
			Node nodoDepartamento = listaDepartamentos.item(i);

			if (nodoDepartamento.getNodeType() == Node.ELEMENT_NODE) {
				Element departamento = (Element) nodoDepartamento;
				String telefono = departamento.getAttribute("telefono");
				String tipo = departamento.getAttribute("tipo");
				System.out.println("Teléfono: " + telefono);
				System.out.println("Tipo: " + tipo);

				NodeList infoDepartamento = departamento.getChildNodes();
				
				for (int j = 0; j < infoDepartamento.getLength(); j++) {
					Node nodoInfo = infoDepartamento.item(j);
					
					if (nodoInfo.getNodeType() == Node.ELEMENT_NODE) {
						Element elemento = (Element) nodoInfo;
						
						if (elemento.getNodeName().equals("codigo")) {
							System.out.println("Código: " + elemento.getTextContent());
						} else if (elemento.getNodeName().equals("nombre")) {
							System.out.println("Nombre: " + elemento.getTextContent());
						} else if (elemento.getNodeName().equals("empleado")) {
							System.out.println("  Empleado:");
							String salario = elemento.getAttribute("salario");
							System.out.println("  Salario: " + salario);

							NodeList infoEmpleado = elemento.getChildNodes();
							
							for (int k = 0; k < infoEmpleado.getLength(); k++) {
								Node nodoEmpleadoInfo = infoEmpleado.item(k);
								
								if (nodoEmpleadoInfo.getNodeType() == Node.ELEMENT_NODE) {
									Element infoE = (Element) nodoEmpleadoInfo;
									
									if (infoE.getNodeName().equals("puesto")) {
										System.out.println("  Puesto: " + infoE.getTextContent());
									} else if (infoE.getNodeName().equals("nombre")) {
										System.out.println("  Nombre: " + infoE.getTextContent());
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
