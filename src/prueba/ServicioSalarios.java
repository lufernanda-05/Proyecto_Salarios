
package prueba;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ServicioSalarios {

    private final List<JobSalary> salarios;

    public ServicioSalarios(String archivo) throws IOException {
        Path ruta = Paths.get(archivo);

        try (var lineas = Files.lines(ruta)) {
            salarios = lineas
                    .skip(1)
                    .map(String::trim)
                    .filter(linea -> !linea.isEmpty())
                    .map(linea -> linea.split(",", -1))
                    .filter(datos -> datos.length >= 10)
                    .map(datos -> new JobSalary(
                            datos[0].trim(),
                            Integer.parseInt(datos[1].trim()),
                            datos[2].trim(),
                            Integer.parseInt(datos[3].trim()),
                            datos[4].trim(),
                            datos[5].trim(),
                            datos[6].trim(),
                            datos[7].trim(),
                            Integer.parseInt(datos[8].trim()),
                            Double.parseDouble(datos[9].trim())
                    ))
                    .collect(Collectors.toList());
        }
    }

    public List<JobSalary> getSalarios() {
        return List.copyOf(salarios);
    }

    private void imprimirLista(List<JobSalary> lista) {
        if (lista.isEmpty()) {
            System.out.println("No se encontraron registros.");
        } else {
            lista.forEach(System.out::println);
        }
    }

    private String dinero(double valor) {
        return String.format("$%,.2f", valor);
    }

    // =========================================================
    // MÓDULO 1: Predicate y filter
    // =========================================================

    public void empleadosSalarioSuperior150000() {
        Predicate<JobSalary> criterio =
                empleado -> empleado.getSalario() > 150000;

        System.out.println("\n=== EMPLEADOS CON SALARIO SUPERIOR A 150000 ===");

        salarios.stream()
                .filter(criterio)
                .forEach(System.out::println);
    }

    public void empleadosConMas10AnosExperiencia() {
        Predicate<JobSalary> criterio =
                empleado -> empleado.getAnosExperiencia() > 10;

        System.out.println("\n=== EMPLEADOS CON MÁS DE 10 AÑOS DE EXPERIENCIA ===");

        salarios.stream()
                .filter(criterio)
                .forEach(System.out::println);
    }

    public void empleadosTrabajoRemoto() {
        Predicate<JobSalary> criterio =
                empleado -> empleado.getTrabajoRemoto()
                        .equalsIgnoreCase("Yes");

        System.out.println("\n=== EMPLEADOS CON TRABAJO REMOTO ===");

        salarios.stream()
                .filter(criterio)
                .forEach(System.out::println);
    }

    public void empleadosNivelPhD() {
        Predicate<JobSalary> criterio =
                empleado -> empleado.getNivelEducacion()
                        .equalsIgnoreCase("PhD");

        System.out.println("\n=== EMPLEADOS CON NIVEL EDUCATIVO PhD ===");

        salarios.stream()
                .filter(criterio)
                .forEach(System.out::println);
    }

    public void empleadosConMas10Certificaciones() {
        Predicate<JobSalary> criterio =
                empleado -> empleado.getCertificaciones() > 10;

        System.out.println("\n=== EMPLEADOS CON MÁS DE 10 CERTIFICACIONES ===");

        List<JobSalary> resultado = salarios.stream()
                .filter(criterio)
                .collect(Collectors.toList());

        imprimirLista(resultado);

        System.out.println(
                "Nota: el dataset tiene normalmente entre 0 y 5 certificaciones."
        );
    }

    public void empleadosIndustriaHealthcare() {
        Predicate<JobSalary> criterio =
                empleado -> empleado.getIndustria()
                        .equalsIgnoreCase("Healthcare");

        System.out.println("\n=== EMPLEADOS DE LA INDUSTRIA HEALTHCARE ===");

        salarios.stream()
                .filter(criterio)
                .forEach(System.out::println);
    }

    public void empleadosIndustriaTechnology() {
        Predicate<JobSalary> criterio =
                empleado -> empleado.getIndustria()
                        .equalsIgnoreCase("Technology");

        System.out.println("\n=== EMPLEADOS DE LA INDUSTRIA TECHNOLOGY ===");

        salarios.stream()
                .filter(criterio)
                .forEach(System.out::println);
    }

    public void empleadosEmpresasEnterprise() {
        Predicate<JobSalary> criterio =
                empleado -> empleado.getTamanoEmpresa()
                        .equalsIgnoreCase("Enterprise");

        System.out.println("\n=== EMPLEADOS DE EMPRESAS ENTERPRISE ===");

        salarios.stream()
                .filter(criterio)
                .forEach(System.out::println);
    }

    public void empleadosUbicadosUSA() {
        Predicate<JobSalary> criterio =
                empleado -> empleado.getUbicacion()
                        .equalsIgnoreCase("USA");

        System.out.println("\n=== EMPLEADOS UBICADOS EN USA ===");

        salarios.stream()
                .filter(criterio)
                .forEach(System.out::println);
    }

    public void empleadosAIEngineer() {
        Predicate<JobSalary> criterio =
                empleado -> empleado.getTituloTrabajo()
                        .equalsIgnoreCase("AI Engineer");

        System.out.println("\n=== EMPLEADOS CON CARGO AI ENGINEER ===");

        salarios.stream()
                .filter(criterio)
                .forEach(System.out::println);
    }

    // =========================================================
    // MÓDULO 2: Function y map
    // =========================================================

    public List<String> convertirCargosAMayusculas() {
        Function<JobSalary, String> transformar =
                empleado -> empleado.getTituloTrabajo().toUpperCase();

        List<String> resultado = salarios.stream()
                .map(transformar)
                .collect(Collectors.toList());

        System.out.println("\n=== CARGOS EN MAYÚSCULAS ===");
        resultado.forEach(System.out::println);

        return resultado;
    }

    public List<Double> obtenerListaSalarios() {
        Function<JobSalary, Double> transformar =
                JobSalary::getSalario;

        List<Double> resultado = salarios.stream()
                .map(transformar)
                .collect(Collectors.toList());

        System.out.println("\n=== LISTA DE SALARIOS ===");
        resultado.forEach(salario -> System.out.println(dinero(salario)));

        return resultado;
    }

    public List<String> obtenerNombresCargos() {
        Function<JobSalary, String> transformar =
                JobSalary::getTituloTrabajo;

        List<String> resultado = salarios.stream()
                .map(transformar)
                .collect(Collectors.toList());

        System.out.println("\n=== NOMBRES DE CARGOS ===");
        resultado.forEach(System.out::println);

        return resultado;
    }

    public List<String> crearDescripcionEmpleados() {
        Function<JobSalary, String> transformar =
                empleado -> String.format(
                        "%s tiene %d años de experiencia, nivel %s y salario %s.",
                        empleado.getTituloTrabajo(),
                        empleado.getAnosExperiencia(),
                        empleado.getNivelEducacion(),
                        dinero(empleado.getSalario())
                );

        List<String> resultado = salarios.stream()
                .map(transformar)
                .collect(Collectors.toList());

        System.out.println("\n=== DESCRIPCIONES DE EMPLEADOS ===");
        resultado.forEach(System.out::println);

        return resultado;
    }

    public List<Integer> obtenerAnosExperiencia() {
        Function<JobSalary, Integer> transformar =
                JobSalary::getAnosExperiencia;

        List<Integer> resultado = salarios.stream()
                .map(transformar)
                .collect(Collectors.toList());

        System.out.println("\n=== AÑOS DE EXPERIENCIA ===");
        resultado.forEach(System.out::println);

        return resultado;
    }

    public List<String> combinarCargoYSalario() {
        Function<JobSalary, String> transformar =
                empleado -> empleado.getTituloTrabajo()
                        + " - " + dinero(empleado.getSalario());

        List<String> resultado = salarios.stream()
                .map(transformar)
                .collect(Collectors.toList());

        System.out.println("\n=== CARGO Y SALARIO ===");
        resultado.forEach(System.out::println);

        return resultado;
    }

    // =========================================================
    // MÓDULO 3: Consumer
    // =========================================================

    public void imprimirTodosRegistros() {
        Consumer<JobSalary> consumidor =
                empleado -> System.out.println(empleado);

        System.out.println("\n=== TODOS LOS REGISTROS ===");

        salarios.stream()
                .forEach(consumidor);
    }

    public void imprimirEmpleadosRemotos() {
        Consumer<JobSalary> consumidor =
                empleado -> System.out.println(empleado);

        System.out.println("\n=== EMPLEADOS REMOTOS ===");

        salarios.stream()
                .filter(empleado -> empleado.getTrabajoRemoto()
                        .equalsIgnoreCase("Yes"))
                .forEach(consumidor);
    }

    public void imprimirEmpleadosSalarioSuperiorPromedio() {
        double promedio = calcularSalarioPromedio();

        Consumer<JobSalary> consumidor =
                empleado -> System.out.println(empleado);

        System.out.println(
                "\n=== EMPLEADOS CON SALARIO SUPERIOR AL PROMEDIO ==="
        );
        System.out.println("Salario promedio: " + dinero(promedio));

        salarios.stream()
                .filter(empleado -> empleado.getSalario() > promedio)
                .forEach(consumidor);
    }

    public void mostrarReporteDetallado() {
        Consumer<JobSalary> reporte =
                empleado -> System.out.printf(
                        "Cargo: %-25s | Experiencia: %2d años | Salario: %s%n",
                        empleado.getTituloTrabajo(),
                        empleado.getAnosExperiencia(),
                        dinero(empleado.getSalario())
                );

        System.out.println("\n=== REPORTE DETALLADO ===");

        salarios.stream()
                .forEach(reporte);
    }

    // =========================================================
    // MÓDULO 4: BiFunction
    // =========================================================

    public double sumarDosSalarios(double salario1, double salario2) {
        BiFunction<Double, Double, Double> operacion =
                (valor1, valor2) -> valor1 + valor2;

        double resultado = operacion.apply(salario1, salario2);

        System.out.println("\n=== SUMA DE DOS SALARIOS ===");
        System.out.println(dinero(salario1) + " + "
                + dinero(salario2) + " = " + dinero(resultado));

        return resultado;
    }

    public String combinarCargoYEducacion(
            String cargo,
            String educacion
    ) {
        BiFunction<String, String, String> operacion =
                (nombreCargo, nivel) ->
                        nombreCargo + " - " + nivel;

        String resultado = operacion.apply(cargo, educacion);

        System.out.println("\n=== CARGO Y EDUCACIÓN ===");
        System.out.println(resultado);

        return resultado;
    }

    public double compararSalarios(double salario1, double salario2) {
        BiFunction<Double, Double, Double> operacion =
                Math::max;

        double resultado = operacion.apply(salario1, salario2);

        System.out.println("\n=== COMPARACIÓN DE SALARIOS ===");
        System.out.println("El salario mayor es: " + dinero(resultado));

        return resultado;
    }

    public double promedioDosSalarios(
            double salario1,
            double salario2
    ) {
        BiFunction<Double, Double, Double> operacion =
                (valor1, valor2) -> (valor1 + valor2) / 2;

        double resultado = operacion.apply(salario1, salario2);

        System.out.println("\n=== PROMEDIO DE DOS SALARIOS ===");
        System.out.println("Promedio: " + dinero(resultado));

        return resultado;
    }

    // =========================================================
    // MÓDULO 5: Stream y Collect
    // =========================================================

    public List<String> obtenerCargosUnicos() {
        List<String> resultado = salarios.stream()
                .map(JobSalary::getTituloTrabajo)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        System.out.println("\n=== CARGOS ÚNICOS ===");
        resultado.forEach(System.out::println);

        return resultado;
    }

    public Map<String, List<JobSalary>> agruparEmpleadosPorCargo() {
        Map<String, List<JobSalary>> resultado = salarios.stream()
                .collect(Collectors.groupingBy(
                        JobSalary::getTituloTrabajo,
                        LinkedHashMap::new,
                        Collectors.toList()
                ));

        System.out.println("\n=== EMPLEADOS AGRUPADOS POR CARGO ===");

        resultado.forEach((cargo, empleados) ->
                System.out.println(cargo + ": " + empleados.size()
                        + " empleados"));

        return resultado;
    }

    public Map<String, List<JobSalary>> agruparEmpleadosPorEducacion() {
        Map<String, List<JobSalary>> resultado = salarios.stream()
                .collect(Collectors.groupingBy(
                        JobSalary::getNivelEducacion,
                        LinkedHashMap::new,
                        Collectors.toList()
                ));

        System.out.println("\n=== EMPLEADOS AGRUPADOS POR EDUCACIÓN ===");

        resultado.forEach((educacion, empleados) ->
                System.out.println(educacion + ": " + empleados.size()
                        + " empleados"));

        return resultado;
    }

    public Map<String, Long> contarEmpleadosPorIndustria() {
        Map<String, Long> resultado = salarios.stream()
                .collect(Collectors.groupingBy(
                        JobSalary::getIndustria,
                        LinkedHashMap::new,
                        Collectors.counting()
                ));

        System.out.println("\n=== CANTIDAD DE EMPLEADOS POR INDUSTRIA ===");

        resultado.forEach((industria, cantidad) ->
                System.out.println(industria + ": " + cantidad));

        return resultado;
    }

    public Map<String, Double> calcularSalarioTotalPorCargo() {
        Map<String, Double> resultado = salarios.stream()
                .collect(Collectors.groupingBy(
                        JobSalary::getTituloTrabajo,
                        LinkedHashMap::new,
                        Collectors.summingDouble(
                                JobSalary::getSalario
                        )
                ));

        System.out.println("\n=== SALARIO TOTAL POR CARGO ===");

        resultado.forEach((cargo, total) ->
                System.out.println(cargo + ": " + dinero(total)));

        return resultado;
    }

    public List<JobSalary> obtenerEmpleadosRemotos() {
        List<JobSalary> resultado = salarios.stream()
                .filter(empleado -> empleado.getTrabajoRemoto()
                        .equalsIgnoreCase("Yes"))
                .collect(Collectors.toList());

        System.out.println("\n=== COLECCIÓN DE EMPLEADOS REMOTOS ===");
        resultado.forEach(System.out::println);

        return resultado;
    }

    // =========================================================
    // ANÁLISIS ESTADÍSTICO
    // =========================================================

    public double calcularSalarioTotal() {
        double resultado = salarios.stream()
                .map(JobSalary::getSalario)
                .reduce(0.0, Double::sum);

        System.out.println("\nSalario total: " + dinero(resultado));

        return resultado;
    }

    public double calcularSalarioPromedio() {
        double resultado = salarios.stream()
                .mapToDouble(JobSalary::getSalario)
                .average()
                .orElse(0.0);

        System.out.println("\nSalario promedio: " + dinero(resultado));

        return resultado;
    }

    public double obtenerSalarioMaximo() {
        double resultado = salarios.stream()
                .mapToDouble(JobSalary::getSalario)
                .max()
                .orElse(0.0);

        System.out.println("\nSalario máximo: " + dinero(resultado));

        return resultado;
    }

    public double obtenerSalarioMinimo() {
        double resultado = salarios.stream()
                .mapToDouble(JobSalary::getSalario)
                .min()
                .orElse(0.0);

        System.out.println("\nSalario mínimo: " + dinero(resultado));

        return resultado;
    }

    public double promedioAnosExperiencia() {
        double resultado = salarios.stream()
                .mapToInt(JobSalary::getAnosExperiencia)
                .average()
                .orElse(0.0);

        System.out.printf("%nPromedio de años de experiencia: %.2f%n",
                resultado);

        return resultado;
    }

    public double promedioCertificaciones() {
        double resultado = salarios.stream()
                .mapToInt(JobSalary::getCertificaciones)
                .average()
                .orElse(0.0);

        System.out.printf("%nPromedio de certificaciones: %.2f%n",
                resultado);

        return resultado;
    }

    public double promedioHabilidades() {
        double resultado = salarios.stream()
                .mapToInt(JobSalary::getCantidadHabilidades)
                .average()
                .orElse(0.0);

        System.out.printf("%nPromedio de habilidades: %.2f%n",
                resultado);

        return resultado;
    }

    public String cargoConSalarioPromedioMasAlto() {
        Map<String, Double> promedios = salarios.stream()
                .collect(Collectors.groupingBy(
                        JobSalary::getTituloTrabajo,
                        Collectors.averagingDouble(
                                JobSalary::getSalario
                        )
                ));

        Map.Entry<String, Double> resultado = promedios.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null);

        if (resultado == null) {
            System.out.println("No existen datos.");
            return "Sin datos";
        }

        System.out.println("\nCargo con salario promedio más alto: "
                + resultado.getKey());
        System.out.println("Promedio: "
                + dinero(resultado.getValue()));

        return resultado.getKey();
    }

    public String industriaConSalarioPromedioMasAlto() {
        Map<String, Double> promedios = salarios.stream()
                .collect(Collectors.groupingBy(
                        JobSalary::getIndustria,
                        Collectors.averagingDouble(
                                JobSalary::getSalario
                        )
                ));

        Map.Entry<String, Double> resultado = promedios.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null);

        if (resultado == null) {
            System.out.println("No existen datos.");
            return "Sin datos";
        }

        System.out.println("\nIndustria con salario promedio más alto: "
                + resultado.getKey());
        System.out.println("Promedio: "
                + dinero(resultado.getValue()));

        return resultado.getKey();
    }

    public String nivelEducativoConSalarioPromedioMasAlto() {
        Map<String, Double> promedios = salarios.stream()
                .collect(Collectors.groupingBy(
                        JobSalary::getNivelEducacion,
                        Collectors.averagingDouble(
                                JobSalary::getSalario
                        )
                ));

        Map.Entry<String, Double> resultado = promedios.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null);

        if (resultado == null) {
            System.out.println("No existen datos.");
            return "Sin datos";
        }

        System.out.println("\nNivel educativo con salario promedio más alto: "
                + resultado.getKey());
        System.out.println("Promedio: "
                + dinero(resultado.getValue()));

        return resultado.getKey();
    }

    public List<String> top10CargosMejorRemunerados() {
        Map<String, Double> promedios = salarios.stream()
                .collect(Collectors.groupingBy(
                        JobSalary::getTituloTrabajo,
                        Collectors.averagingDouble(
                                JobSalary::getSalario
                        )
                ));

        List<String> resultado = promedios.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Double>comparingByValue()
                        .reversed())
                .limit(10)
                .map(entrada -> entrada.getKey()
                        + " - " + dinero(entrada.getValue()))
                .collect(Collectors.toList());

        System.out.println("\n=== TOP 10 CARGOS MEJOR REMUNERADOS ===");
        resultado.forEach(System.out::println);

        return resultado;
    }

    public Map<String, Long> totalEmpleadosPorModalidad() {
        Map<String, Long> resultado = salarios.stream()
                .collect(Collectors.groupingBy(
                        JobSalary::getTrabajoRemoto,
                        LinkedHashMap::new,
                        Collectors.counting()
                ));

        System.out.println("\n=== EMPLEADOS POR MODALIDAD ===");

        resultado.forEach((modalidad, cantidad) ->
                System.out.println(modalidad + ": " + cantidad));

        return resultado;
    }

    public Map<String, Long> totalEmpleadosPorIndustria() {
        Map<String, Long> resultado = salarios.stream()
                .collect(Collectors.groupingBy(
                        JobSalary::getIndustria,
                        LinkedHashMap::new,
                        Collectors.counting()
                ));

        System.out.println("\n=== EMPLEADOS POR INDUSTRIA ===");

        resultado.forEach((industria, cantidad) ->
                System.out.println(industria + ": " + cantidad));

        return resultado;
    }

    public Map<String, Long> totalEmpleadosPorEducacion() {
        Map<String, Long> resultado = salarios.stream()
                .collect(Collectors.groupingBy(
                        JobSalary::getNivelEducacion,
                        LinkedHashMap::new,
                        Collectors.counting()
                ));

        System.out.println("\n=== EMPLEADOS POR NIVEL EDUCATIVO ===");

        resultado.forEach((educacion, cantidad) ->
                System.out.println(educacion + ": " + cantidad));

        return resultado;
    }

    public List<String> rankingIndustriasMayoresIngresos() {
        Map<String, Double> totales = salarios.stream()
                .collect(Collectors.groupingBy(
                        JobSalary::getIndustria,
                        Collectors.summingDouble(
                                JobSalary::getSalario
                        )
                ));

        List<String> resultado = totales.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Double>comparingByValue()
                        .reversed())
                .map(entrada -> entrada.getKey()
                        + " - " + dinero(entrada.getValue()))
                .collect(Collectors.toList());

        System.out.println("\n=== RANKING DE INDUSTRIAS POR INGRESOS ===");
        resultado.forEach(System.out::println);

        return resultado;
    }

    // =========================================================
    // Métodos auxiliares para el menú
    // =========================================================

    public void mostrarResumenEstadistico() {
        System.out.println("\n=== RESUMEN ESTADÍSTICO ===");
        System.out.println("Total de registros: " + salarios.size());

        calcularSalarioTotal();
        calcularSalarioPromedio();
        obtenerSalarioMaximo();
        obtenerSalarioMinimo();
        promedioAnosExperiencia();
        promedioCertificaciones();
        promedioHabilidades();
        cargoConSalarioPromedioMasAlto();
        industriaConSalarioPromedioMasAlto();
        nivelEducativoConSalarioPromedioMasAlto();
        top10CargosMejorRemunerados();
        totalEmpleadosPorModalidad();
        totalEmpleadosPorIndustria();
        totalEmpleadosPorEducacion();
        rankingIndustriasMayoresIngresos();
    }
}
