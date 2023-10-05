package servicios;

public class MenuImplementacion implements MenuInterfaz {

	@Override
	public void Menu() {
		// TODO Auto-generated method stub
		System.out.println("");
		System.out.println("1-Mostrar todos los libros");
		System.out.println("2-Mostrar libro concreto");
		System.out.println("3-Modificar libro/s");
		System.out.println("4-Borrar libros");
		System.out.println("0-Salir");
		System.out.print("\nElige una opcion: ");
	}

}
