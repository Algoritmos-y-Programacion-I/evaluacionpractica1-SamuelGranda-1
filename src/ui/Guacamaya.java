package ui;

import java.util.Scanner;

public class Guacamaya {

    public static Scanner reader;
    public static double[] precios;
    public static int[] unidades;
    public static int[] calculado;

    public static void main(String[] args) {

        inicializarGlobales();
        menu();
    }

    /**
     * Descripcion: Este metodo se encarga de iniciar las variables globales
     * pre: El Scanner reader debe estar declarado
     * pos: l Scanner reader queda inicializado
     */
    public static void inicializarGlobales() {

        reader = new Scanner(System.in);

    }

    /**
     * Descripcion: Este metodo se encarga de desplegar el menu al usuario y mostrar
     * los mensajes de resultado para cada funcionalidad
     * pre: El Scanner reader debe estar inicializado
     * pre: El arreglo precios debe estar inicializado
     */
    public static void menu() {

        System.out.println("Bienvenido a Guacamaya!");

        establecerCantidadVendida();

        boolean salir = false;

        do {

            System.out.println("\nMenu Principal:");
            System.out
                    .println("1. Solicitar precios ($) y cantidades de cada referencia de producto vendida en el dia");
            System.out.println("2. Calcular la cantidad total de unidades vendidas en el dia");
            System.out.println("3. Calcular el precio promedio de las referencias de producto vendidas en el dia");
            System.out.println("4. Calcular las ventas totales (dinero recaudado) durante el dia");
            System.out.println(
                    "5. Consultar el numero de referencias de productos que en el dia han superado un limite minimo de ventas");
            System.out.println("6. Salir");
            System.out.println("\nDigite la opcion a ejecutar");
            int opcion = reader.nextInt();

            switch (opcion) {
                case 1:
                    solicitarDatos();
                    break;
                case 2:
                    System.out.println("\nLa cantidad total de unidades vendidas en el dia fue de: " + calcularTotalUnidadesVendidas());
                    break;
                case 3:
                    System.out.println("\nEl precio promedio de las referencias de producto vendidas en el dia fue de: " + calcularPrecioPromedio());
                    break;
                case 4:
                    System.out.println("\nLas ventas totales durante el dia fueron: " + calcularVentasTotales());
                    break;
                case 5:

                    System.out.println("\nDigite el limite minimo de ventas a analizar");
                    double limite = reader.nextDouble();
                    consultarReferenciasSobreLimite(limite);
                    System.out.println("\nDe las " + precios.length + " referencias vendidas en el dia, " + consultarReferenciasSobreLimite(limite) + " superaron el limite minimo de ventas de " + limite);
                    break;
                case 6:
                    System.out.println("\nGracias por usar nuestros servicios!");
                    salir = true;
                    reader.close();
                    break;

                default:
                    break;
            }

        } while (!salir);

    }

    /**
     * Descripcion: Este metodo se encarga de preguntar al usuario el numero de
     * referencias de producto diferentes
     * vendidas en el dia e inicializa con ese valor los arreglos encargados de
     * almacenar precios y cantidades
     * pre: El Scanner reader debe estar inicializado
     * pre: Los arreglos precios y unidades deben estar declarados
     * pos: Los arreglos precios y unidades quedan inicializados
     */
    public static void establecerCantidadVendida() {

        System.out.println("\nDigite el numero de referencias de producto diferentes vendidas en el dia ");
        int referencias = reader.nextInt();

        precios = new double[referencias];
        unidades = new int[referencias];

    }

    /**
     * Descripcion: Este metodo se encarga de preguntar al usuario el precio del
     * producto
     * y la cantidad vendida de dicho producto, almacenandolos respectivamente en
     * arreglos.
     * pre: El Scanner reader debe estar inicializado.
     * pre: Los arreglos precios y unidades deben estar declarados.
     * pos: Los arreglos precios y unidades quedan actualizados con los datos
     * ingresados.
     */
    public static void solicitarDatos() {
        for (int i = 0; i < unidades.length; i++) {
            System.out.println("Digite el precio del producto " + (i + 1));
            double PrecioP = reader.nextDouble();
            System.out.println("Digita la cantidad  vendida  " + (i + 1));
            int UnidadesP = reader.nextInt();
            if (precios[i] == 0) {
                precios[i] = PrecioP;
            }
            if (unidades[i] == 0) {
                unidades[i] = UnidadesP;
            }

        }
    }

    /**
     * Descripcion: Este metodo calcula el total de unidades vendidas.
     * 
     * @param unidades Arreglo que contiene la cantidad de unidades vendidas por
     *                 referencia.
     *                 pre: El arreglo unidades debe estar inicializado.
     *                 pos: Retorna la suma total de unidades vendidas.
     * @return La cantidad total de unidades vendidas.
     */

    public static int calcularTotalUnidadesVendidas() {
        int totalUni = 0;
        for (int i = 0; i < unidades.length; i++) {
            totalUni += unidades[i];

        }
        return totalUni;
    }

    /**
     * Descripcion: Este metodo calcula el precio promedio de los productos
     * vendidos.
     * pre: El arreglo precios debe estar inicializado.
     * pos: Retorna el precio promedio de los productos vendidos.
     * 
     * @return El precio promedio de las referencias de producto vendidas.
     */

    public static double calcularPrecioPromedio() {
        double temp = 0;
        for (double precio : precios) {

            temp += precio;

        }
        double prom = temp / unidades.length;

        return prom;

    }

    /**
     * Descripcion: Este metodo calcula las ventas totales del dia.
     * 
     * @param precios  Arreglo con los precios de las referencias de productos.
     * @param unidades Arreglo con las cantidades vendidas de cada producto.
     *                 pre: Los arreglos precios y unidades deben estar
     *                 inicializados.
     *                 pos: Retorna la cantidad total de dinero recaudado.
     * @return El total de las ventas en dinero.
     */

    public static double calcularVentasTotales() {
        double ventasTotales = 0;
        for (int i = 0; i < unidades.length; i++) {
            ventasTotales += (precios[i] * unidades[i]);

        }

        return ventasTotales;

    }

    /**
     * Descripcion: Este metodo calcula cuantas referencias de productos superan un
     * limite minimo de ventas.
     * 
     * @param limite El valor minimo de ventas para ser considerado.
     *               pre: Los arreglos precios y unidades deben estar inicializados
     *               y deben tener el mismo tamaño.
     *               pos: Retorna el numero de referencias de productos que superan
     *               el limite minimo de ventas.
     * @return El numero de referencias que superan el limite de ventas.
     */

    public static int consultarReferenciasSobreLimite(double limite) {
        int contador = 0;
        for (int unidad : unidades) {
            if (unidad > limite) {
                contador++;
            }
        }
        return contador;

    }

}