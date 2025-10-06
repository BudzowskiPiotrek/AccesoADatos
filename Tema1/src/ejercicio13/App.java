package ejercicio13;

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
	// Realiza un programa en Java que permita crear un fichero XML, utilizando DOM,
	// con la estructura indicada. Los datos puedes introducirlos a mano u
	// obtenerlos desde un fichero de texto creado al uso.
	public static void main(String[] args)
			throws ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {

		DocumentBuilderFactory miFactoria = DocumentBuilderFactory.newInstance();
		DocumentBuilder miConstructor = miFactoria.newDocumentBuilder();
		DOMImplementation implementacion = miConstructor.getDOMImplementation();
		Document miDocumento = implementacion.createDocument(null, "Universidad", null);
		miDocumento.setXmlVersion("1.0");

		Element departamento = miDocumento.createElement("departamento");
		departamento.setAttribute("telefono", "654321987");
		departamento.setAttribute("tipo", "Labolatorio");

		Element codigo = miDocumento.createElement("codigo");
		Text textoCodigo = miDocumento.createTextNode("29-200");
		codigo.appendChild(textoCodigo);
		departamento.appendChild(codigo);

		Element nombre = miDocumento.createElement("nombre");
		Text textoNombre = miDocumento.createTextNode("Labolatorio del cole");
		nombre.appendChild(textoNombre);
		departamento.appendChild(nombre);
		
		// Agregando primer Empleado
		Element empleado1 = miDocumento.createElement("empleado");
		empleado1.setAttribute("salario", "2100");

		Element puesto = miDocumento.createElement("puesto");
		Text textoePuesto = miDocumento.createTextNode("Director");
		puesto.appendChild(textoePuesto);
		empleado1.appendChild(puesto);
		
		Element nombreEmpleado = miDocumento.createElement("nombre");
		Text textoenombreEmpleado = miDocumento.createTextNode("Paco");
		nombreEmpleado.appendChild(textoenombreEmpleado);
		empleado1.appendChild(nombreEmpleado);		

		departamento.appendChild(empleado1);
		
		// Agregando segundo Empleado
		Element empleado2 = miDocumento.createElement("empleado");
		empleado2.setAttribute("salario", "1100");
		
		puesto = miDocumento.createElement("puesto");
		textoePuesto = miDocumento.createTextNode("Lacayo");
		puesto.appendChild(textoePuesto);
		empleado2.appendChild(puesto);
		
		nombreEmpleado = miDocumento.createElement("nombre");
		textoenombreEmpleado = miDocumento.createTextNode("Carlos");
		nombreEmpleado.appendChild(textoenombreEmpleado);
		empleado2.appendChild(nombreEmpleado);
		
		departamento.appendChild(empleado2);
		
		miDocumento.getDocumentElement().appendChild(departamento);
		
		Source source = new DOMSource(miDocumento);
		Result resultado = new StreamResult(new File("D:\\Departamentos.XML"));
		Transformer miTransformer = TransformerFactory.newInstance().newTransformer();
		miTransformer.transform(source, resultado);
	}

}
