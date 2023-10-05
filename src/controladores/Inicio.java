package controladores;

import java.util.ArrayList;
import java.util.Scanner;

import dtos.LibroDto;
import servicios.ConsultasImplementacion;
import servicios.ConsultasInterfaz;
import servicios.MenuImplementacion;
import servicios.MenuInterfaz;

public class Inicio {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConsultasInterfaz consultas = new ConsultasImplementacion();
		ArrayList<LibroDto> listaLibros = new ArrayList<>();

		MenuInterfaz menu = new MenuImplementacion();
		Scanner sc = new Scanner(System.in);
		int n = 0;

		try {

			do {
				menu.Menu();
				n = sc.nextInt();

				switch (n) {

				case 1://todos los libros
					listaLibros = consultas.seleccionaTodosLibros();
					System.out.println("---todos los libros---");
					for (int i = 0; i < listaLibros.size(); i++)
						System.out.println(listaLibros.get(i).toString());
					break;
					
				case 2://libro particular
					listaLibros = consultas.seleccionLibros();
					break;

				case 3://modificar
					consultas.modificarLibro();					
					break;

				case 4://borrar
					consultas.borrarLibros();
					break;
				}

			} while (n != 0);
			
		} catch (Exception e) {
			System.out.println("[ERROR-Main] Se ha producido un error al ejecutar la aplicación: " + e);
		}
	}
}