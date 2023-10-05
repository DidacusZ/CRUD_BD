package servicios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Implementacion Interfaz de conexion
 * 290923-dpm
 */
public class ConexionImplementacion implements ConexionInterfaz {

	@Override
	public Connection crearConexion() {
		// TODO Auto-generated method stub

		//inicializamos la conexion como null
		Connection conexion = null;
		//como este metodo devuelve un objeto conexion cuando hay algun error en el codigo devolvemos conexion = null;		
		
		// array con la url, user, pass
		// String[] parametrosConexion = configurarConexion();
		String[] parametrosConexion = configurarConexion();
		
		//controlamos si falta algun parametro de entrada (no controlamos la url porque simpre va a estar llena)
		if (parametrosConexion[1].isEmpty()) {
			System.err.println("[ERROR] El usuario esta vacio [ConexionImplementacion-crearConexion]");
			return conexion = null;
		}else if (parametrosConexion[2].isEmpty()) {
			System.err.println("[ERROR] La contraseña esta vacia [ConexionImplementacion-crearConexion]");
			return conexion = null;
		}

			try {
				//Instancia un objeto de la clase interfaz que se le pasa
				Class.forName("org.postgresql.Driver");//Registra el driver de conexión para la base de datos (si no no abria conexion)
				
				//Establecemos la conexión
				//Si pgadmin no tiene abierta la bd, no será posible establecer conexion contra ella
				conexion = DriverManager.getConnection(parametrosConexion[0],parametrosConexion[1],parametrosConexion[2]);//se llama a un metodo estatico que devuelve un objeto Connection
				
				//Devuelve true si la conexion no se a cerrado./se envia una consulta a la BD para verficarlo
				boolean esValida = conexion.isValid(50000);//El tiempo en ms que se espera a que se complete la operacion utilizada para validarla
				
				if(esValida == false) {
					conexion = null;
				}
				
				//si esValida=true devuelve la 1º opcion y si es false la 2º
				//System.out.println(esValida ? "[INFO] Conexión a PostgreSQL es válida [ConexionImplementacion-crearConexion]" : "[ERROR] Conexión a PostgreSQL no válida [ConexionImplementacion-crearConexion]");
	            
				if(esValida)
					System.out.println("[INFO] Conexión a PostgreSQL es válida [ConexionImplementacion-crearConexion]");
				else
					System.err.println("[ERROR] Conexión a PostgreSQL no válida [ConexionImplementacion-crearConexion]");
				
				return conexion;
	            
				
			}catch(ClassNotFoundException eD) {
				System.err.println("[ERROR] Registro drivers de postgre [ConexionImplementacion-crearConexion]");
				return conexion = null;
			}catch(SQLException eBD) {
				System.err.println("[ERROR] Conexion a postgre [ConexionImplementacion-crearConexion]");
				return conexion = null;
			}
	}
	
	
/**
 * Configura la conexion a la BD con los parametros de un fichero .properties
 * 290923-dpm
 * @param user
 * @param pass
 * @param port
 * @param host
 * @param bd
 * @return Vector con los parametros necesarios para la conexion (url, user, pass)
 */
	private String[] configurarConexion() {

		String user = "", pass = "", port = "", host = "", db = "", url = "";// datos para formar la conexion

		Properties datosConexion = new Properties();// instanciamos Properties para poder usar el archivo .properties

		try {
			// FileInputStrea --> nos permite leer archivos procedentes del sistema de archivos
			datosConexion.load(new FileInputStream(new File("C:\\Users\\clase\\Desktop\\clase 2\\java\\PROYECTOS\\Conexion_BD_Java\\src\\util\\conexion.properties")));

			// accede al valor que tenga ese nombre(String)
			user = datosConexion.getProperty("user");
			pass = datosConexion.getProperty("pass");

			// url
			host = datosConexion.getProperty("host");
			port = datosConexion.getProperty("port");
			db = datosConexion.getProperty("db");

			url = "jdbc:postgresql://" + host + ":" + port + "/" + db;//concatenamos la url

			String[] vConexion = { url, user, pass };//vector con los parametros necesarios para hacer una conexion

			return vConexion;

		} catch (Exception e) {
			System.err.println("[ERROR] No se consigue acceder al archivo [ConexionImplementacion-configurarConexion]");
			return null;
		}

	}

}
