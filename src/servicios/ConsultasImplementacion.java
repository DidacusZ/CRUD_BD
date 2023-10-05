package servicios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import dtos.LibroDto;
import util.ADto;

/**
 * Implementacion de la interfaz de consultas 021023 dpm
 */
public class ConsultasImplementacion implements ConsultasInterfaz {

	ConexionInterfaz conexionImpl = new ConexionImplementacion();

	@Override
	public ArrayList<LibroDto> seleccionaTodosLibros() {
		// TODO Auto-generated method stub

		Connection conexion = conexionImpl.crearConexion();

		// Declaración SQL estática que devuelve los resultados que produce
		Statement declaracionSQL = null;// Solo se puede abrir un objeto ResultSet por cada objeto Statement
		// El resultado de la consulta a la BD
		ResultSet resultadoConsulta = null;
		ArrayList<LibroDto> listaLibros = new ArrayList<>();
		ADto aDto = new ADto();

		try {
			// Se abre una declaración
			declaracionSQL = conexion.createStatement();// Crea un objeto Statement para enviar declaraciones SQL a la
														// base de datos
			// Se define la consulta de la declaración y se ejecuta la query
			resultadoConsulta = declaracionSQL.executeQuery("SELECT * FROM gbp_almacen.gbp_alm_cat_libros");

			listaLibros = aDto.ResultSetALibroDto(resultadoConsulta);

			// siempre cerrar la conexion y demas para no sobrecargar el server
			conexion.close();
			declaracionSQL.close();
			resultadoConsulta.close();
			System.out.println(
					"[INFO] Conexion, declaracion, resultado cerrados [ConsultasImplementacion-seleccionaTodosLibros]");

		} catch (SQLException e) {
			System.err.println("[ERROR] No se puede lanzar la Query [ConsultasImplementacion-seleccionaTodosLibros]");
		}
		return listaLibros;
	}

	public ArrayList<LibroDto> seleccionLibros() {
		// TODO Auto-generated method stub

		Connection conexion = conexionImpl.crearConexion();

		// Declaración SQL estática que devuelve los resultados que produce
		Statement declaracionSQL = null;// Solo se puede abrir un objeto ResultSet por cada objeto Statement
		// El resultado de la consulta a la BD
		ResultSet resultadoConsulta = null;
		ArrayList<LibroDto> listaLibros = new ArrayList<>();
		ADto aDto = new ADto();

		// parametrizacion
		String consulta = "";
		PreparedStatement parametro = null;// sirve para poder parametrizar consultas

		try {
			// Se abre una declaración
			declaracionSQL = conexion.createStatement();// Crea un objeto Statement para enviar declaraciones SQL a la
														// base de datos
			// Se define la consulta de la declaración
			consulta = "SELECT * FROM gbp_almacen.gbp_alm_cat_libros WHERE titulo=?";

			parametro = conexion.prepareStatement(consulta);// envia declaraciones sql parametrizadas

			parametro.setString(1, "narnia");// añade un valor al '?' nº1

			resultadoConsulta = parametro.executeQuery();// ejecuta la query

			listaLibros = aDto.ResultSetALibroDtoParametros(resultadoConsulta);

			// siempre cerrar la conexion y demas para no sobrecargar el server
			conexion.close();
			declaracionSQL.close();
			resultadoConsulta.close();
			System.out.println(
					"[INFO] Conexion, declaracion, resultado cerrados [ConsultasImplementacion-seleccionLibros]");

		} catch (SQLException e) {
			System.err.println("[ERROR] No se puede lanzar la Query [ConsultasImplementacion-seleccionLibros]");
		}

		return listaLibros;

	}

	@Override
	public void borrarLibros() {
		// TODO Auto-generated method stub

		Connection conexion = conexionImpl.crearConexion();

		// DELETE FROM gbp_almacen.gbp_alm_cat_libros WHERE id_libro=?

		Statement declaracionSQL = null;// Solo se puede abrir un objeto ResultSet por cada objeto Statement

		// parametrizacion
		String consulta = "";
		PreparedStatement parametro = null;// sirve para poder parametrizar consultas

		try {
			// Se abre una declaración
			declaracionSQL = conexion.createStatement();// Crea un objeto Statement para enviar declaraciones SQL a la
														// base de datos

			// Se define la consulta de la declaración
			consulta = "DELETE FROM gbp_almacen.gbp_alm_cat_libros WHERE id_libro=?";

			parametro = conexion.prepareStatement(consulta);// envia declaraciones sql parametrizadas

			parametro.setInt(1, 5);

			parametro.executeUpdate();// ejecuta la query
			System.out.println("[INFO] Borrado correcto [ConsultasImplementacion-borrarLibros]");

			// siempre cerrar la conexion y demas para no sobrecargar el server
			conexion.close();
			declaracionSQL.close();
			System.out
					.println("[INFO] Conexion, declaracion, resultado cerrados [ConsultasImplementacion-borrarLibros]");

		} catch (SQLException e) {
			System.err.println("[ERROR] No se puede lanzar la Query [ConsultasImplementacion-borrarLibros]");
		}
	}

	@Override
	public void modificarLibro() {// lo modifica pero da error
		// TODO Auto-generated method stub

		Connection conexion = conexionImpl.crearConexion();

		// UPDATE gbp_almacen.gbp_alm_cat_libros SET titulo = ?, autor = ?,isbn = ?,
		// edicion = ? WHERE titulo=?

		Statement declaracionSQL = null;// Solo se puede abrir un objeto ResultSet por cada objeto Statement
		// El resultado de la consulta a la BD

		// parametrizacion
		String consulta = "";
		PreparedStatement parametro = null;// sirve para poder parametrizar consultas

		try {
			// Se abre una declaración
			declaracionSQL = conexion.createStatement();

			// Se define la consulta de la declaración
			consulta = "UPDATE gbp_almacen.gbp_alm_cat_libros SET edicion=? WHERE titulo=?";

			parametro = conexion.prepareStatement(consulta);// envia declaraciones sql parametrizadas

			preguntasModificar(parametro);//hace las preguntas y las parametriza

			// ejecuta la query

			System.out.println("[INFO] Modificacion terminada [ConsultasImplementacion-modificarLibro]");

			// siempre cerrar la conexion y demas para no sobrecargar el server
			conexion.close();
			declaracionSQL.close();
			System.out.println(
					"[INFO] Conexion, declaracion, resultado cerrados [ConsultasImplementacion-modificarLibro]");

		} catch (SQLException e) {
			System.err.println("[ERROR] No se puede lanzar la Query [ConsultasImplementacion-modificarLibro]");
			e.printStackTrace();
		}

	}

	@Override
	public void crearLibro() {
		// TODO Auto-generated method stub
		Connection conexion = conexionImpl.crearConexion();

		// INSERT INTO gbp_almacen.gbp_alm_cat_libros (titulo, autor, isbn,edicion)
		// VALUES (?,?,?,?);

		Statement declaracionSQL = null;// Solo se puede abrir un objeto ResultSet por cada objeto Statement
		// El resultado de la consulta a la BD

		// parametrizacion
		String consulta = "";
		PreparedStatement parametro = null;// sirve para poder parametrizar consultas

		try {
			// Se abre una declaración
			declaracionSQL = conexion.createStatement();

			// Se define la consulta de la declaración
			consulta = "INSERT INTO gbp_almacen.gbp_alm_cat_libros (titulo, autor, isbn,edicion) VALUES (?,?,?,?)";

			parametro = conexion.prepareStatement(consulta);// envia declaraciones sql parametrizadas

			parametro.setString(1, "el campo");
			parametro.setString(2, "jose");
			parametro.setInt(3, 92387234);
			parametro.setInt(4, 9);

			parametro.executeUpdate();// ejecuta la query
			System.out.println("[INFO] Libro registrado correctamente [ConsultasImplementacion-crearLibro]");

			// siempre cerrar la conexion y demas para no sobrecargar el server
			conexion.close();
			declaracionSQL.close();
			System.out.println("[INFO] Conexion, declaracion, resultado cerrados [ConsultasImplementacion-crearLibro]");

		} catch (SQLException e) {
			System.err.println("[ERROR] No se puede lanzar la Query [ConsultasImplementacion-crearLibro]");
		}

	}

	private void preguntasModificar(PreparedStatement parametro) throws SQLException {

		String campo = "";
		int n = 0;

		Scanner sc = new Scanner(System.in);

		do {
			System.out.print("Que libro/s quieres modificar: ");
			parametro.setString(2, sc.next());

			do {
				System.out.print("Que campo/s quieres modificar[titulo,autor,isbn,edicion]: ");
				campo = sc.next();
			} while (!campo.equals("titulo") && !campo.equals("autor") && !campo.equals("isbn")	&& !campo.equals("edicion"));
			
			System.out.print("Introduce el nuevo valor: ");
			if (campo.equals("titulo") || campo.equals("autor")) {
				parametro.setString(1, sc.next());
			}				
			else {
				parametro.setInt(1, sc.nextInt());
			}
				
		} while (preguntaSiNo("Quieres modificar otro?[si=1/no=0]: "));
		parametro.executeUpdate();
		sc.close();

	}

	private void preguntasMostrar() {

	}

	private void preguntasBorrar() {

	}
	
	
	
	private boolean preguntaSiNo(String txt) 
	{
		
		Scanner sc=new Scanner(System.in);
		String sioNo;
		boolean cerrarmenu=true;
		do {
			System.out.println(txt);
			  sioNo=sc.next().toLowerCase();
			  
			  switch(sioNo)
			  {
				  case "si":
					  return true;
				  case "no":
					  return false;
					default:
						System.err.println("[ERROR] Entrada no válida");
						cerrarmenu=false;
			  }
			  
		}while(!cerrarmenu);
		return true;
		
	}

}
