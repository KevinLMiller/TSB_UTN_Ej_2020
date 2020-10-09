package clases;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Principal {

    public static void main(String[] args) {

        int op, anio, temp, estreno;
        String nombre;
        File f;
        Scanner scan = new Scanner(System.in);
        ArrayList<Serie> lista = new ArrayList<>();

        do {
            System.out.println("Cargar Serie ?\n1)Si\n2)No");
            System.out.println("...");

            op = scan.nextInt();

            if (op == 1) {
                System.out.println("Ingrese nombre de la serie: ");
                nombre = scan.next();
                System.out.println("Ingrese Año de la serie: ");
                anio = scan.nextInt();
                System.out.println("Ingrese cantidad de temporadas de la serie: ");
                temp = scan.nextInt();

                Serie serie = new Serie(nombre, anio, temp);
                lista.add(serie);
            }
        } while (op == 1);


        do {
            System.out.println("1) Mostrar catálogo completo");
            System.out.println("2) Archivar catálogo completo en un archivo");
            System.out.println("3) Descargar por año: ingresar por teclado un año de estreno, y generar un archivo estrenoXXXX.dat con las series estrenadas ese año.");
            System.out.println("4) Consultar descargas: ingresar por teclado un año de estreno, determinar si existe el archivo asociado. Si no existe, informarlo. Si existe, mostrarlo.");
            System.out.println("5) Buscar Serie por Titulo ");
            System.out.println("6) Recuperar catalogo desde archivo ");
            System.out.println("7) Salir ");
            System.out.println("Ingrese la opcion deseada: ");

            op = scan.nextInt();

            switch (op) {
                case 1:
                    for (Serie item : lista) {
                        System.out.println(item);
                    }
                    break;
                case 2:
                    f = new File(".\\series.txt");
                    if (!f.exists()) {
                        try {
                            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage() + " che como que te tira una excepcion");
                        }
                    }
                    try {
                        grabar(lista, f);

                    } catch (IOException ex) {
                        System.out.println("Fijate flaco que le erraste en algo, pero tranka que ya casi lo tenes");
                    }
                    break;

                case 3:
                    System.out.println("Ingrese la fecha de estreno: ");
                    estreno = scan.nextInt();
                    ArrayList<Serie> listaEstrenos = new ArrayList<>();
                    for (Serie item : lista) {
                        if (item.getAnio() == estreno) {
                            listaEstrenos.add(item);
                        }
                    }

                    try {
                        f = new File(".\\estreno" + estreno + ".txt");
                        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
                        grabar(listaEstrenos, f);
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage() + "No se, vo fijate");

                    }
                    break;
                case 4:
                    System.out.println("Ingrese la fecha de estreno: ");
                    estreno = scan.nextInt();
                    f = new File(".\\estreno" + estreno + ".txt");
                    if (f.exists()) {
                        ArrayList<Serie> listaAnio = new ArrayList<>();
                        try {
                            listaAnio = leer(f);
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage() + "Fijate flaco que le erraste en algo, pero tranka que ya casi lo tenes");
                        }
                        for (Serie item : listaAnio) {
                            System.out.println(item);
                        }
                        break;

                    } else {
                        System.out.println("No existe un archivo para el año ingresado");
                    }

                    break;
                case 5:
                    System.out.println("Ingrese el titulo de la serie que está buscando ");
                    String titulo = scan.next();
                    if (!lista.isEmpty()) {
                        f = new File(".\\series.txt");
                        if (f.exists()) {
                            try {
                                lista = leer(f);
                            } catch (IOException ex) {
                                System.out.println(ex.getMessage() + "Fijate flaco que le erraste en algo, pero tranka que ya casi lo tenes");
                            } catch (ClassNotFoundException ex) {
                                System.out.println(ex.getMessage() + "Fijate flaco que le erraste en algo, pero tranka que ya casi lo tenes");
                            }
                        } else {
                            System.out.println("No existe un archivo de series");
                            break;
                        }

                    }
                    Serie.buscarSerie(titulo, lista);

                    break;
                case 6:
                    f = new File(".\\series.txt");
                    try {
                        lista = leer(f);
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage() + "Fijate flaco que le erraste en algo, pero tranka que ya casi lo tenes");
                    } catch (ClassNotFoundException ex) {
                        System.out.println(ex.getMessage() + "Fijate flaco que le erraste en algo, pero tranka que ya casi lo tenes");
                    }

                    break;
                default:
                    System.out.println("Andá a la cancha bobo");
            }
        } while (op != 7);

    }


    static public void grabar(ArrayList al, File f) throws FileNotFoundException, IOException {
        // creación de objetos para gestión de streams de salida

        FileOutputStream out = new FileOutputStream(f);
        ObjectOutputStream ofile = new ObjectOutputStream(out);

        // grabación de lista completa en el archivo serializado

        ofile.writeObject(al);

        // cierre de los flujos de salida

        ofile.flush();
        out.close();

    }

    public static ArrayList<Serie> leer(File f) throws FileNotFoundException, IOException, ClassNotFoundException {
        // creación de objetos para gestión de streams de entrada

        FileInputStream in = new FileInputStream(f);
        ObjectInputStream ifile = new ObjectInputStream(in);

        // lectura del objeto que estaba en el archivo

        ArrayList al = (ArrayList) ifile.readObject();

        //cierre de los flujos de entrada

        ifile.close();
        in.close();

        //retorno del objeto leído

        return al;
    }

}
