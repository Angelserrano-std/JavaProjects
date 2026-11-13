import java.util.Scanner;
import java.util.InputMismatchException;

public class TestMVT{

    private static int leerEntero(Scanner lector) {
        while (true) {
            try {
                return lector.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("se necesita un entero: ");
                lector.next();
            }
        }
    }

    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);

        int principal;

        do {
            System.out.print("Tamaño de memoria principal en KB: ");
            principal = leerEntero(lector);

            if (principal <= 0) {
                System.out.println("El tamaño debe ser mayor que cero.");
            }
        } while (principal <= 0);


        int secundaria;

        do {
            System.out.print("Tamaño de memoria secundaria en KB: ");
            secundaria = leerEntero(lector);

            if (secundaria <= 0) {
                System.out.println("El tamaño debe ser mayor que cero.");
            }
        } while (secundaria <= 0);


        MVT mvt= new MVT(principal,secundaria);

        int opc;

        do{
            mvt.mostrarMemorias();

            System.out.println("MENU");
            System.out.println("1-registrar proceso");
            System.out.println("2-eliminar proceso");
            System.out.println("3-intercambio");
            System.out.println("4-compactación");
            System.out.println("0-salir");

            System.out.print("Selecciona una opción: ");
            opc = leerEntero(lector);

            switch (opc){
                case 1: {
                    System.out.println("id del proceso: ");
                    int id = leerEntero(lector);

                    System.out.println("tamaño del proceso? (KB)");
                    int tamaño = leerEntero(lector);
                    mvt.registroProceso(id,tamaño);
                    break;
                }

                case 2: {
                    System.out.println("id del proceso a eliminar? ");
                    int id = leerEntero(lector);

                    mvt.eliminarProceso(id);
                }

                case 3: {
                    System.out.println("id para intercambiar?");
                    int id = leerEntero(lector);

                    mvt.intercambio(id);
                    break;
                }

                case 4: {
                    mvt.compactarMemoria();
                    break;
                }

                case 0: {
                    break;
                }
                default: {
                    System.out.println("eso no se puede");
                    break;
                }
            }

        }while (opc !=0);




    }
}