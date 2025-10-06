package ejercicio11;

import java.io.File;

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
import org.w3c.dom.Text;

public class App {
	// Realiza un programa en Java que permita crear un fichero XML, utilizando
	// DOM, con la siguiente indicada, introduciendo los datos por teclado.
	public static void main(String[] args)
			throws ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {
		
		DocumentBuilderFactory miFactoria = DocumentBuilderFactory.newInstance();
		DocumentBuilder miConstructor = miFactoria.newDocumentBuilder();
		DOMImplementation implementacion = miConstructor.getDOMImplementation();
		Document miDocumento = implementacion.createDocument(null, "Clientes", null);
		miDocumento.setXmlVersion("1.0");
		
		Element cliente = miDocumento.createElement("cliente");
		cliente.setAttribute("numero", "XX");
		
		Element nombre = miDocumento.createElement("nombre");
		Text textoNombre = miDocumento.createTextNode("XXXXXXXXX");
		nombre.appendChild(textoNombre);
		cliente.appendChild(nombre);
		
		Element poblacion = miDocumento.createElement("poblacion");
		Text textoPoblacion = miDocumento.createTextNode("");
		poblacion.appendChild(textoPoblacion);
		cliente.appendChild(textoPoblacion);
		
		Element tlf = miDocumento.createElement("tlf");
		Text textoTlf = miDocumento.createTextNode("XXXXXXXXXX");
		tlf.appendChild(textoTlf);
		cliente.appendChild(tlf);
		
		Element direccion = miDocumento.createElement("direccion");
		Text textoDireccion = miDocumento.createTextNode("XXXXXXXXX");
		direccion.appendChild(textoDireccion);
		cliente.appendChild(direccion);
		
		miDocumento.getDocumentElement().appendChild(cliente);
		
		Source source = new DOMSource(miDocumento);
		Result resultado = new StreamResult(new File("D:\\Clientes.XML"));
		Transformer miTransformer = TransformerFactory.newInstance().newTransformer();
		miTransformer.transform(source, resultado);
	}

}
