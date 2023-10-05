package util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dtos.LibroDto;

/**
 * Clase con los metodos para pasar a DTO
 * 021023 dpm
 */
public class ADto {

	/**
	 * Pasa el resultado de la query al objeto LibroDto y lo añade a una lista
	 * @param resultado
	 * @return lista con todos los libros
	 */
	public ArrayList<LibroDto> ResultSetALibroDto(ResultSet resultado) {
		
		ArrayList<LibroDto> lista =new ArrayList<>();	
		
		//lectura del resultado de la consulta
		try {
			
			while(resultado.next())//devuelve false cuando no quedan mas filas(row)
			{
				lista.add(new LibroDto(resultado.getLong("id_libro"),//accede a cada posicion por el string(se puede con int) y la guarda en el objeto
						resultado.getString("titulo"),
						resultado.getString("autor"),
						resultado.getString("isbn"),
						resultado.getInt("edicion")
						));				
			}
			System.out.println("[INFO] Nº libros:"+lista.size()+"[ADto-ResultSetALibroDto]");			
			
		}catch(SQLException e) {
			System.err.println("[ERROR] Fallo al pasar de Resulset a ArrayList [ADto-ResultSetALibroDto]");
		}
		return lista;
	}
	
public ArrayList<LibroDto> ResultSetALibroDtoParametros(ResultSet resultado) {
		
		ArrayList<LibroDto> lista =new ArrayList<>();
		LibroDto libro = new LibroDto();
		
		//lectura del resultado de la consulta
		try {
			
			while(resultado.next())//devuelve false cuando no quedan mas filas(row)
			{				
				libro.setIdLibro(resultado.getLong("id_libro"));
				libro.setTitulo(resultado.getString("titulo"));
				libro.setAutor(resultado.getString("autor"));
				libro.setIsbn(resultado.getString("isbn"));
				libro.setEdicion(resultado.getInt("edicion"));				
				lista.add(libro);
			}
			System.out.println("[INFO] Nº libros:"+lista.size()+"[ADto-ResultSetALibroDto]");			
			
		}catch(SQLException e) {
			System.err.println("[ERROR] Fallo al pasar de Resulset a ArrayList [ADto-ResultSetALibroDto]");
		}
		return lista;
	}
}
