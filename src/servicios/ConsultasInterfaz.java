package servicios;

import java.sql.Connection;
import java.util.ArrayList;

import dtos.LibroDto;

/**
 * Interfaz con los metodos necesarios para hacer las consultas al la BD
 * 021023 dpm
 */
public interface ConsultasInterfaz {

	/**
	 * hace un select de toda la tabla
	 * @param conexionGenerada
	 * @return
	 */
	ArrayList<LibroDto> seleccionaTodosLibros();
	
	ArrayList<LibroDto> seleccionLibros();
	
	
	
	void modificarLibro();
	
	void crearLibro();
	
	void borrarLibros();
}
