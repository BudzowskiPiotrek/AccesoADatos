package ejercicio01;

import com.db4o.*;
import java.util.*;

public class Main {

    private static ObjectContainer db;
    private static final String DB_NAME = "EMPLEDEP.YAP";

    public static void main(String[] args) {
        db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), DB_NAME);

        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- MENÚ DB4O ---");
            System.out.println("1. Insertar empleado");
            System.out.println("2. Mostrar empleados");
            System.out.println("3. Actualizar empleado");
            System.out.println("4. Eliminar empleado");
            System.out.println("5. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> insertarEmpleado(sc);
                case 2 -> mostrarEmpleados();
                case 3 -> actualizarEmpleado(sc);
                case 4 -> eliminarEmpleado(sc);
                case 5 -> System.out.println("Cerrando...");
                default -> System.out.println("Opción no válida");
            }

        } while (opcion != 5);

        db.close();
        sc.close();
    }

    private static void insertarEmpleado(Scanner sc) {
        System.out.print("ID: ");
        int id = sc.nextInt(); sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Salario: ");
        double salario = sc.nextDouble(); sc.nextLine();
        System.out.print("Departamento: ");
        String depNombre = sc.nextLine();

        Departamento d = new Departamento(id, depNombre);
        Empleado e = new Empleado(id, nombre, salario, d);
        db.store(e);
        System.out.println("Empleado insertado correctamente.");
    }

    private static void mostrarEmpleados() {
        ObjectSet<Empleado> empleados = db.query(Empleado.class);
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados.");
        } else {
            for (Empleado e : empleados) {
                System.out.println(e);
            }
        }
    }

    private static void actualizarEmpleado(Scanner sc) {
        System.out.print("Ingrese ID del empleado a actualizar: ");
        int id = sc.nextInt(); sc.nextLine();
        ObjectSet<Empleado> result = db.queryByExample(new Empleado(id, null, 0, null));
        if (result.isEmpty()) {
            System.out.println("Empleado no encontrado.");
            return;
        }

        Empleado e = result.next();
        System.out.print("Nuevo nombre: ");
        e.setNombre(sc.nextLine());
        System.out.print("Nuevo salario: ");
        e.setSalario(sc.nextDouble());
        db.store(e);
        System.out.println("Empleado actualizado.");
    }

    private static void eliminarEmpleado(Scanner sc) {
        System.out.print("Ingrese ID del empleado a eliminar: ");
        int id = sc.nextInt();
        ObjectSet<Empleado> result = db.queryByExample(new Empleado(id, null, 0, null));
        if (result.isEmpty()) {
            System.out.println("Empleado no encontrado.");
            return;
        }

        db.delete(result.next());
        System.out.println("Empleado eliminado.");
    }
}
