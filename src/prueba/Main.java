package prueba;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String rutaArchivo = "data/job_salary_prediction_dataset.csv";

        try {
            ServicioSalarios servicio =
                    new ServicioSalarios(rutaArchivo);

            System.out.println("======================================");
            System.out.println("   SISTEMA DE ANÁLISIS SALARIAL");
            System.out.println("======================================");
            System.out.println("Registros cargados: "
                    + servicio.getSalarios().size());

            ejecutarMenuPrincipal(servicio);

        } catch (Exception excepcion) {
            System.err.println(
                    "\nNo fue posible iniciar la aplicación."
            );
            System.err.println("Detalle: " + excepcion.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static void ejecutarMenuPrincipal(
            ServicioSalarios servicio
    ) {
        int opcion;

        do {
            mostrarMenuPrincipal();
            opcion = leerEntero("Seleccione una opción: ");

            try {
                switch (opcion) {
                    case 1 -> menuModulo1(servicio);
                    case 2 -> menuModulo2(servicio);
                    case 3 -> menuModulo3(servicio);
                    case 4 -> menuModulo4(servicio);
                    case 5 -> menuModulo5(servicio);
                    case 6 -> menuEstadistico(servicio);
                    case 0 -> System.out.println(
                            "\nAplicación finalizada correctamente."
                    );
                    default -> System.out.println(
                            "\nOpción no válida."
                    );
                }
            } catch (Exception excepcion) {
                System.out.println(
                        "\nSe produjo un error: "
                                + excepcion.getMessage()
                );
            }

        } while (opcion != 0);
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("\n======================================");
        System.out.println("           MENÚ PRINCIPAL");
        System.out.println("======================================");
        System.out.println("1. Módulo 1 - Consultas con Predicate");
        System.out.println("2. Módulo 2 - Transformaciones con Function");
        System.out.println("3. Módulo 3 - Operaciones con Consumer");
        System.out.println("4. Módulo 4 - Operaciones con BiFunction");
        System.out.println("5. Módulo 5 - Stream y Collect");
        System.out.println("6. Análisis estadístico");
        System.out.println("0. Salir");
        System.out.println("======================================");
    }

    private static void menuModulo1(ServicioSalarios servicio) {
        int opcion;

        do {
            System.out.println("\n=== MÓDULO 1: PREDICATE Y FILTER ===");
            System.out.println("1. Salario superior a 150000");
            System.out.println("2. Más de 10 años de experiencia");
            System.out.println("3. Trabajo remoto");
            System.out.println("4. Nivel educativo PhD");
            System.out.println("5. Más de 10 certificaciones");
            System.out.println("6. Industria Healthcare");
            System.out.println("7. Industria Technology");
            System.out.println("8. Empresas Enterprise");
            System.out.println("9. Ubicados en USA");
            System.out.println("10. Cargo AI Engineer");
            System.out.println("0. Regresar");

            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1 -> servicio.empleadosSalarioSuperior150000();
                case 2 -> servicio.empleadosConMas10AnosExperiencia();
                case 3 -> servicio.empleadosTrabajoRemoto();
                case 4 -> servicio.empleadosNivelPhD();
                case 5 -> servicio.empleadosConMas10Certificaciones();
                case 6 -> servicio.empleadosIndustriaHealthcare();
                case 7 -> servicio.empleadosIndustriaTechnology();
                case 8 -> servicio.empleadosEmpresasEnterprise();
                case 9 -> servicio.empleadosUbicadosUSA();
                case 10 -> servicio.empleadosAIEngineer();
                case 0 -> System.out.println("Regresando...");
                default -> System.out.println("Opción no válida.");
            }

            pausar();

        } while (opcion != 0);
    }

    private static void menuModulo2(ServicioSalarios servicio) {
        int opcion;

        do {
            System.out.println("\n=== MÓDULO 2: FUNCTION Y MAP ===");
            System.out.println("1. Convertir cargos a mayúsculas");
            System.out.println("2. Obtener lista de salarios");
            System.out.println("3. Obtener nombres de cargos");
            System.out.println("4. Crear descripciones");
            System.out.println("5. Obtener años de experiencia");
            System.out.println("6. Combinar cargo y salario");
            System.out.println("0. Regresar");

            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1 -> servicio.convertirCargosAMayusculas();
                case 2 -> servicio.obtenerListaSalarios();
                case 3 -> servicio.obtenerNombresCargos();
                case 4 -> servicio.crearDescripcionEmpleados();
                case 5 -> servicio.obtenerAnosExperiencia();
                case 6 -> servicio.combinarCargoYSalario();
                case 0 -> System.out.println("Regresando...");
                default -> System.out.println("Opción no válida.");
            }

            pausar();

        } while (opcion != 0);
    }

    private static void menuModulo3(ServicioSalarios servicio) {
        int opcion;

        do {
            System.out.println("\n=== MÓDULO 3: CONSUMER ===");
            System.out.println("1. Imprimir todos los registros");
            System.out.println("2. Imprimir empleados remotos");
            System.out.println("3. Salario superior al promedio");
            System.out.println("4. Mostrar reporte detallado");
            System.out.println("0. Regresar");

            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1 -> servicio.imprimirTodosRegistros();
                case 2 -> servicio.imprimirEmpleadosRemotos();
                case 3 -> servicio.imprimirEmpleadosSalarioSuperiorPromedio();
                case 4 -> servicio.mostrarReporteDetallado();
                case 0 -> System.out.println("Regresando...");
                default -> System.out.println("Opción no válida.");
            }

            pausar();

        } while (opcion != 0);
    }

    private static void menuModulo4(ServicioSalarios servicio) {
        int opcion;

        do {
            System.out.println("\n=== MÓDULO 4: BI FUNCTION ===");
            System.out.println("1. Sumar dos salarios");
            System.out.println("2. Combinar cargo y educación");
            System.out.println("3. Comparar dos salarios");
            System.out.println("4. Promedio de dos salarios");
            System.out.println("0. Regresar");

            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1 -> {
                    double salario1 =
                            leerDouble("Ingrese el primer salario: ");
                    double salario2 =
                            leerDouble("Ingrese el segundo salario: ");

                    servicio.sumarDosSalarios(salario1, salario2);
                }
                case 2 -> {
                    String cargo =
                            leerTexto("Ingrese el cargo: ");
                    String educacion =
                            leerTexto("Ingrese el nivel educativo: ");

                    servicio.combinarCargoYEducacion(
                            cargo,
                            educacion
                    );
                }
                case 3 -> {
                    double salario1 =
                            leerDouble("Ingrese el primer salario: ");
                    double salario2 =
                            leerDouble("Ingrese el segundo salario: ");

                    servicio.compararSalarios(salario1, salario2);
                }
                case 4 -> {
                    double salario1 =
                            leerDouble("Ingrese el primer salario: ");
                    double salario2 =
                            leerDouble("Ingrese el segundo salario: ");

                    servicio.promedioDosSalarios(salario1, salario2);
                }
                case 0 -> System.out.println("Regresando...");
                default -> System.out.println("Opción no válida.");
            }

            pausar();

        } while (opcion != 0);
    }

    private static void menuModulo5(ServicioSalarios servicio) {
        int opcion;

        do {
            System.out.println("\n=== MÓDULO 5: STREAM Y COLLECT ===");
            System.out.println("1. Obtener cargos únicos");
            System.out.println("2. Agrupar por cargo");
            System.out.println("3. Agrupar por educación");
            System.out.println("4. Contar por industria");
            System.out.println("5. Salario total por cargo");
            System.out.println("6. Obtener empleados remotos");
            System.out.println("0. Regresar");

            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1 -> servicio.obtenerCargosUnicos();
                case 2 -> servicio.agruparEmpleadosPorCargo();
                case 3 -> servicio.agruparEmpleadosPorEducacion();
                case 4 -> servicio.contarEmpleadosPorIndustria();
                case 5 -> servicio.calcularSalarioTotalPorCargo();
                case 6 -> servicio.obtenerEmpleadosRemotos();
                case 0 -> System.out.println("Regresando...");
                default -> System.out.println("Opción no válida.");
            }

            pausar();

        } while (opcion != 0);
    }

    private static void menuEstadistico(ServicioSalarios servicio) {
        int opcion;

        do {
            System.out.println("\n=== ANÁLISIS ESTADÍSTICO ===");
            System.out.println("1. Salario total");
            System.out.println("2. Salario promedio");
            System.out.println("3. Salario máximo");
            System.out.println("4. Salario mínimo");
            System.out.println("5. Promedio de años de experiencia");
            System.out.println("6. Promedio de certificaciones");
            System.out.println("7. Promedio de habilidades");
            System.out.println("8. Cargo con mayor salario promedio");
            System.out.println("9. Industria con mayor salario promedio");
            System.out.println("10. Educación con mayor salario promedio");
            System.out.println("11. Top 10 cargos mejor remunerados");
            System.out.println("12. Empleados por modalidad");
            System.out.println("13. Empleados por industria");
            System.out.println("14. Empleados por educación");
            System.out.println("15. Ranking de industrias por ingresos");
            System.out.println("16. Mostrar resumen completo");
            System.out.println("0. Regresar");

            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1 -> servicio.calcularSalarioTotal();
                case 2 -> servicio.calcularSalarioPromedio();
                case 3 -> servicio.obtenerSalarioMaximo();
                case 4 -> servicio.obtenerSalarioMinimo();
                case 5 -> servicio.promedioAnosExperiencia();
                case 6 -> servicio.promedioCertificaciones();
                case 7 -> servicio.promedioHabilidades();
                case 8 ->
                        servicio.cargoConSalarioPromedioMasAlto();
                case 9 ->
                        servicio.industriaConSalarioPromedioMasAlto();
                case 10 ->
                        servicio.nivelEducativoConSalarioPromedioMasAlto();
                case 11 -> servicio.top10CargosMejorRemunerados();
                case 12 -> servicio.totalEmpleadosPorModalidad();
                case 13 -> servicio.totalEmpleadosPorIndustria();
                case 14 -> servicio.totalEmpleadosPorEducacion();
                case 15 ->
                        servicio.rankingIndustriasMayoresIngresos();
                case 16 -> servicio.mostrarResumenEstadistico();
                case 0 -> System.out.println("Regresando...");
                default -> System.out.println("Opción no válida.");
            }

            pausar();

        } while (opcion != 0);
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException excepcion) {
                System.out.println(
                        "Debe ingresar un número entero válido."
                );
            }
        }
    }

    private static double leerDouble(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException excepcion) {
                System.out.println(
                        "Debe ingresar un número válido."
                );
            }
        }
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    private static void pausar() {
        System.out.println(
                "\nPresione ENTER para continuar..."
        );
        scanner.nextLine();
    }
}