package prueba;

public class JobSalary {
    private String tituloTrabajo;
    private int anosExperiencia;
    private String nivelEducacion;
    private int cantidadHabilidades;
    private String industria;
    private String tamanoEmpresa;
    private String ubicacion;
    private String trabajoRemoto;
    private int certificaciones;
    private double salario;

    public JobSalary(String tituloTrabajo, int anosExperiencia, String nivelEducacion, 
                     int cantidadHabilidades, String industria, String tamanoEmpresa, 
                     String ubicacion, String trabajoRemoto, int certificaciones, double salario) {
        this.tituloTrabajo = tituloTrabajo;
        this.anosExperiencia = anosExperiencia;
        this.nivelEducacion = nivelEducacion;
        this.cantidadHabilidades = cantidadHabilidades;
        this.industria = industria;
        this.tamanoEmpresa = tamanoEmpresa;
        this.ubicacion = ubicacion;
        this.trabajoRemoto = trabajoRemoto;
        this.certificaciones = certificaciones;
        this.salario = salario;
    }

    public String getTituloTrabajo() { return tituloTrabajo; }
    public int getAnosExperiencia() { return anosExperiencia; }
    public String getNivelEducacion() { return nivelEducacion; }
    public int getCantidadHabilidades() { return cantidadHabilidades; }
    public String getIndustria() { return industria; }
    public String getTamanoEmpresa() { return tamanoEmpresa; }
    public String getUbicacion() { return ubicacion; }
    public String getTrabajoRemoto() { return trabajoRemoto; }
    public int getCertificaciones() { return certificaciones; }
    public double getSalario() { return salario; }

    @Override
    public String toString() {
        return String.format("Trabajo: %-20s | Exp: %2d anos | Nivel: %-10s | Habilidades: %2d | " +
                             "Ind: %-10s | Tamano: %-6s | Ubicacion: %-10s | Remoto: %-3s | " +
                             "Certs: %d | Salario: $%,.2f",
                             tituloTrabajo, anosExperiencia, nivelEducacion, cantidadHabilidades, 
                             industria, tamanoEmpresa, ubicacion, trabajoRemoto, certificaciones, salario);
    }
}
