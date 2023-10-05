package dtos;

/**
 * Entidad para guardar cada libro; 290923 dpm
 */
public class LibroDto {

	// Atributos
	private long idLibro;
	private String titulo;
	private String autor;
	private String isbn;
	private int edicion;

	// constructor con todoss los campos
	public LibroDto(long idLibro, String titulo, String autor, String isbn, int edicion) {
		super();
		this.idLibro = idLibro;
		this.titulo = titulo;
		this.autor = autor;
		this.isbn = isbn;
		this.edicion = edicion;
	}

	// constructor vacio (como existe un constructor con parametros este se pierde
	// si no lo declaras)
	public LibroDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	// getters y setters
	public long getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(long idLibro) {
		this.idLibro = idLibro;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getEdicion() {
		return edicion;
	}

	public void setEdicion(int edicion) {
		this.edicion = edicion;
	}

	@Override
	public String toString() {
		return "LibroDto [idLibro=" + idLibro + ", titulo=" + titulo + ", autor=" + autor + ", isbn=" + isbn
				+ ", edicion=" + edicion + "]";
	}

}