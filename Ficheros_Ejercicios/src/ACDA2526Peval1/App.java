package ACDA2526Peval1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ejercicio10.Empleado;

public class App {
	public static List<Estrella> estrellas = new ArrayList<>();
	public final static String RUTA = "D:\\peval1acda2526fichero.obj";

	public static void main(String[] args) {
		try {
			leerDocumento();
		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}
		guardarDocumento();

		cargarDatos();

		leerDatosGuardados();
	}

	private static void leerDatosGuardados() {
		for (Estrella estrella : estrellas) {
			System.out.println(estrella.toString());
		}
	}

	private static void cargarDatos() {
		File archivo = new File(RUTA);
		if (!archivo.exists()) {
			System.out.println("No existe el fichero");
			return;
		}
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RUTA))) {
			estrellas = (List<Estrella>) ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void guardarDocumento() {
		try (FileOutputStream fos = new FileOutputStream(RUTA); ObjectOutputStream aux = new ObjectOutputStream(fos)) {
			aux.writeObject(estrellas);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void leerDocumento() throws SAXException, IOException, ParserConfigurationException {
		String nombre = null, edad = null, temperatura = null, comentario = null, hidrogeno = null;
		DocumentBuilderFactory miFactoria = DocumentBuilderFactory.newInstance();
		DocumentBuilder miConstructor = miFactoria.newDocumentBuilder();
		Document miDocumento = miConstructor.parse(new File("D:\\CuerposCelestes.XML"));

		NodeList listaCuerpos = miDocumento.getElementsByTagName("Cuerpo");

		for (int i = 0; i < listaCuerpos.getLength(); i++) {
			Node nodoCuerpos = listaCuerpos.item(i);

			if (nodoCuerpos.getNodeType() == Node.ELEMENT_NODE) {
				Element cuerpos = (Element) nodoCuerpos;

				NodeList infoDepartamento = cuerpos.getChildNodes();

				for (int j = 0; j < infoDepartamento.getLength(); j++) {
					Node nodoInfo = infoDepartamento.item(j);

					if (nodoInfo.getNodeType() == Node.ELEMENT_NODE) {
						Element elemento = (Element) nodoInfo;

						if (elemento.getNodeName().equals("Nombre")) {
							nombre = elemento.getTextContent();
							System.out.println("Nombre: " + nombre);
						} else if (elemento.getNodeName().equals("Edad")) {
							edad = elemento.getTextContent();
							System.out.println("Edad: " + edad);
						} else if (elemento.getNodeName().equals("TemperaturaSuperficial")) {
							temperatura = elemento.getTextContent();
							System.out.println("Temperatura: " + temperatura);
						} else if (elemento.getNodeName().equals("Comentario")) {
							comentario = elemento.getTextContent();
							System.out.println("Comentario: " + comentario);
						} else if (elemento.getNodeName().equals("Composicion")) {

							NodeList infoComposicion = elemento.getChildNodes();

							for (int k = 0; k < infoComposicion.getLength(); k++) {
								Node nodoInfoComposicion = infoComposicion.item(k);

								if (nodoInfoComposicion.getNodeType() == Node.ELEMENT_NODE) {
									Element e1 = (Element) nodoInfoComposicion;
									NodeList infoComponente = elemento.getChildNodes();

									for (int l = 0; l < infoComponente.getLength(); l++) {
										Node n1 = infoComponente.item(l);

										if (n1.getNodeType() == Node.ELEMENT_NODE) {
											Element e2 = (Element) n1;

											if (e2.getAttribute("nombre").equals("Hidr�geno")) {
												hidrogeno = e2.getTextContent();
												System.out.println("Hidrogeno: " + hidrogeno);
												String[] campos = hidrogeno.split("%");
												String numPro = campos[0];
												int numNumerico = Integer.parseInt(numPro);
												if(numNumerico>70) {
													Estrella nueva = new Estrella(nombre, edad, temperatura, hidrogeno,
															comentario);
													estrellas.add(nueva);
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
		}

	}
}