package ejercicio2;


import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class App {

	private static final String FILE_PATH = "D:/exa1acda2425/vuelos.xml";
    private static final int MES_FILTRO = 11;
    private static final int ANIO_FILTRO = 2025;
    
    // Consulta 1: Obtener todos los vuelos del mes
    private static final String SQL_VUELOS = 
        "SELECT IDENTIFICADOR, AEROPUERTO_ORIGEN, AEROPUERTO_DESTINO, FECHA_VUELO " +
        "FROM vuelo " +
        "WHERE MONTH(FECHA_VUELO) = ? AND YEAR(FECHA_VUELO) = ?";

    // Consulta 2: Obtener la tripulación para un Vuelo ID
    private static final String SQL_TRIPULACION =
        "SELECT p.NOMBRE, p.CATEGORIA, t.PUESTO " +
        "FROM personal p " +
        "INNER JOIN tripulacion t ON p.CODIGO = t.PERSONAL_COD " +
        "WHERE t.VUELO_IDENTIFICADOR = ?";

    // Consulta 3: Obtener los pasajeros para un Vuelo ID
    private static final String SQL_PASAJEROS =
        "SELECT p.NOMERE, pas.NUMASIENTO, pas.CLASE " +
        "FROM pasajero p " +
        "INNER JOIN pasaje pas ON p.COD = pas.PASAJERO_COD " +
        "WHERE pas.IDENTIFICADOR = ?";

    public void generarXMLMultiConsulta() {
        System.out.println("\n--- GENERACIÓN DE XML CON CONSULTAS ANIDADAS (EJERCICIO 3) ---");
        
        Document doc = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try (Connection conn = DatabaseUtil.getConnection()) {
            
            // =============================================
            // 1. INICIO: CREACIÓN DEL DOCUMENTO XML
            // =============================================
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.newDocument();
            Element rootElement = doc.createElement("vuelos");
            doc.appendChild(rootElement);
            
            // 2. Ejecutar CONSULTA 1 (VUELOS)
            try (PreparedStatement stmtVuelos = conn.prepareStatement(SQL_VUELOS)) {
                
                stmtVuelos.setInt(1, MES_FILTRO);
                stmtVuelos.setInt(2, ANIO_FILTRO);
                
                try (ResultSet rsVuelos = stmtVuelos.executeQuery()) {
                    
                    // Bucle principal: 1 elemento <vuelo> por cada fila del ResultSet de vuelos
                    while (rsVuelos.next()) {
                        String idVuelo = rsVuelos.getString("IDENTIFICADOR");

                        // 2.1. Crear el elemento <vuelo>
                        Element vueloElement = doc.createElement("vuelo");
                        vueloElement.setAttribute("identificador", idVuelo);
                        rootElement.appendChild(vueloElement);

                        // 2.2. Agregar campos directos
                        createElementAndAppend(doc, vueloElement, "origen", rsVuelos.getString("AEROPUERTO_ORIGEN"));
                        createElementAndAppend(doc, vueloElement, "destino", rsVuelos.getString("AEROPUERTO_DESTINO"));
                        createElementAndAppend(doc, vueloElement, "fecha", dateFormat.format(rsVuelos.getDate("FECHA_VUELO")));

                        // 2.3. Crear el contenedor <tripulacion>
                        Element tripulacionElement = doc.createElement("tripulacion");
                        vueloElement.appendChild(tripulacionElement);
                        
                        // 3. Ejecutar CONSULTA 2 (TRIPULACIÓN)
                        try (PreparedStatement stmtTripulacion = conn.prepareStatement(SQL_TRIPULACION)) {
                            stmtTripulacion.setString(1, idVuelo);
                            try (ResultSet rsTripulacion = stmtTripulacion.executeQuery()) {
                                
                                // Bucle anidado: 1 elemento <tripulante> por cada fila
                                while (rsTripulacion.next()) {
                                    Element tripulanteElement = doc.createElement("tripulante");
                                    tripulanteElement.setAttribute("nombre", rsTripulacion.getString("NOMBRE"));
                                    tripulacionElement.appendChild(tripulanteElement);
                                    
                                    createElementAndAppend(doc, tripulanteElement, "categoria", rsTripulacion.getString("CATEGORIA"));
                                    createElementAndAppend(doc, tripulanteElement, "puesto", rsTripulacion.getString("PUESTO"));
                                }
                            }
                        }

                        // 2.4. Crear el contenedor <pasajeros>
                        Element pasajerosElement = doc.createElement("pasajeros");
                        vueloElement.appendChild(pasajerosElement);

                        // 4. Ejecutar CONSULTA 3 (PASAJEROS)
                        try (PreparedStatement stmtPasajeros = conn.prepareStatement(SQL_PASAJEROS)) {
                            stmtPasajeros.setString(1, idVuelo);
                            try (ResultSet rsPasajeros = stmtPasajeros.executeQuery()) {
                                
                                // Bucle anidado: 1 elemento <pasajero> por cada fila
                                while (rsPasajeros.next()) {
                                    Element pasajeroElement = doc.createElement("pasajero");
                                    pasajeroElement.setAttribute("nombre", rsPasajeros.getString("NOMERE"));
                                    pasajerosElement.appendChild(pasajeroElement);

                                    createElementAndAppend(doc, pasajeroElement, "asiento", rsPasajeros.getString("NUMASIENTO"));
                                    createElementAndAppend(doc, pasajeroElement, "clase", rsPasajeros.getString("CLASE"));
                                }
                            }
                        }
                    } // Fin Bucle principal de Vuelos
                }
            } // Fin try-with-resources de StmtVuelos

            // =============================================
            // 5. FINAL: GUARDAR EL ARCHIVO XML
            // =============================================
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            
            // Configuración de salida
            transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4"); 
            transformer.setOutputProperty(javax.xml.transform.OutputKeys.ENCODING, "ISO-8859-1"); 
            
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(FILE_PATH));
            
            transformer.transform(source, result);
            
            System.out.println("\n[INFO] Fichero 'vuelos.xml' generado con éxito en: " + FILE_PATH);

        } catch (SQLException e) {
            System.err.println("\n[ERROR SQL] Fallo al ejecutar la consulta: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("\n[ERROR XML] Fallo al crear o guardar el XML: " + e.getMessage());
        }
    }
    
    // Método auxiliar para simplificar la creación de elementos simples
    private void createElementAndAppend(Document doc, Element parent, String tagName, String textContent) {
        Element element = doc.createElement(tagName);
        element.appendChild(doc.createTextNode(textContent));
        parent.appendChild(element);
    }
}