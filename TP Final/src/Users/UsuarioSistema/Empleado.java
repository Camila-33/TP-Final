package Users.UsuarioSistema;

import java.util.Objects;

public class Empleado extends UsuarioSistema{

    private double sueldo;
    private int aniosAntiguedad;

    public Empleado(String userName, String contrasena, String nombre, String apellido, String email, String telefono, String dni, String direccion, double sueldo, int aniosAntiguedad) {
        super(userName, contrasena, nombre, apellido, email, telefono, dni, direccion);
        this.sueldo = sueldo;
        this.aniosAntiguedad = aniosAntiguedad;
    }

    public Empleado() {
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public int getAniosAntiguedad() {
        return aniosAntiguedad;
    }

    public void setAniosAntiguedad(int aniosAntiguedad) {
        this.aniosAntiguedad = aniosAntiguedad;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Empleado empleado)) return false;
        return Double.compare(sueldo, empleado.sueldo) == 0 && aniosAntiguedad == empleado.aniosAntiguedad;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sueldo, aniosAntiguedad);
    }

    @Override
    public String toString() {
        return "Empleado {" + super.toString()+
                "sueldo = " + sueldo +
                ", aniosAntiguedad = " + aniosAntiguedad;
    }
}
