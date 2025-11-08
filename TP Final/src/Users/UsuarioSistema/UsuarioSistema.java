package Users.UsuarioSistema;

public abstract class UsuarioSistema {

    protected String idUsuario;
    protected String userName;
    protected String contrasena;
    protected String nombre;
    protected String apellido;
    protected String email;
    protected boolean activo;
    protected String telefono;
    protected String dni;
    protected String direccion;

    public UsuarioSistema(String userName, String contrasena, String nombre, String apellido, String email, String telefono, String dni, String direccion) {
        this.idUsuario = "USER-" + (long)(Math.random() * 900000) + 100000;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.activo = true;
        this.userName = userName;
        this.telefono = telefono;
        this.dni = dni;
        this.direccion = direccion;
    }

    public UsuarioSistema() {
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNombreCompleto() {

        return this.nombre + " " + this.apellido;
    }

    @Override
    public String toString() {
        return "idUsuario = '" + idUsuario + '\'' +
                ", nombre = '" + nombre + '\'' +
                ", apellido = '" + apellido + '\'' +
                ", email = '" + email + '\'' +
                ", contrasena = '" + contrasena + '\'' +
                ", activo = " + activo +
                '}';
    }
}
