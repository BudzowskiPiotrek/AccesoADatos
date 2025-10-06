package practica09;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;

import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.Source;
import javax.xml.transform.Result;
import org.w3c.dom.DOMImplementation;

import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class App {

	public static void main(String[] args)
			throws ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {
		DocumentBuilderFactory miFactoria = DocumentBuilderFactory.newInstance(); // Definición de la factoría DOM
		DocumentBuilder miConstructor = miFactoria.newDocumentBuilder(); // Definición del constructor DOM
		DOMImplementation implementacion = miConstructor.getDOMImplementation(); // Interfaz DOM
		Document miDocumento = implementacion.createDocument(null, "concesionario", null); // Creación del documento
		miDocumento.setXmlVersion("1.0"); // versión que se le va a dar de XML
		// COCHE 1
		Element coches = miDocumento.createElement("coches"); // Definición de la raíz del documento
		Element coche = miDocumento.createElement("coche"); // Definición del primer elemento "global"
		coche.setAttribute("matricula", "1111AAA"); // Asignación de un atributo al elemento "global"

		Element marca = miDocumento.createElement("marca"); // Definición del primer elemento dentro del elemento coche
		Text textoMarca = miDocumento.createTextNode("TOYOTA"); // Definición del valor del elemento creado
		marca.appendChild(textoMarca); // Asignación del valor al elemento
		coche.appendChild(marca); // Asignación del elemento al elemento "global"
		Element precio = miDocumento.createElement("precio"); // Definición del segundo elemento dentro del elemento
																// coche
		Text textoPrecio = miDocumento.createTextNode("25000"); // Definición del valor del elemento creado
		precio.appendChild(textoPrecio); // Asignación del valor al elemento
		coche.appendChild(precio); // Asignación del elemento al elemento "global"
		// Vamos a crear un elemento que a su vez se compone de dos elementos más
		Element propietario = miDocumento.createElement("propietario"); // Definición del tercer elemento dentro del
																		// elemento coche
		propietario.setAttribute("DNI", "11111111A"); // Asignación de un atributo al elemento

		Element nombre = miDocumento.createElement("nombre"); // Definición de un subelemento dentro del elemento creado
		Text nombrePropietario = miDocumento.createTextNode("Jacinto"); // Definición del valor del subelemento
		nombre.appendChild(nombrePropietario); // Asignación del valor al subelemento

		Element apellido = miDocumento.createElement("apellido"); // Definición de un subelemento dentro del elemento
																	// creado
		Text apellidoPropietario = miDocumento.createTextNode("Benavente"); // Definición del valor del subelemento
		apellido.appendChild(apellidoPropietario); // Asignación del valor al subelemento

		propietario.appendChild(nombre); // Asignación del subelemento al elemento creado
		propietario.appendChild(apellido); // Asignación del subelemento al elemento creado
		coche.appendChild(propietario); // Asignación del elemento al elemento "global"

		coches.appendChild(coche); // Asignación del elemento "global" a la raíz
		miDocumento.getDocumentElement().appendChild(coches); // Asignación de la raíz al documento

		// Creación y grabación del fichero
		Source source = new DOMSource(miDocumento);
		Result resultado = new StreamResult(new File("D:\\concesionario.XML"));
		Transformer miTransformer = TransformerFactory.newInstance().newTransformer();
		miTransformer.transform(source, resultado);
	}
}