package Interfaces;

import Users.UsuarioSistema.Administrador;

public interface MetodosGestion <T>{

    public void agregarYguardar (T t);
    public void ingresarUsuario();
    public void mostrarDatosUsuario(T t);
    public void modificarUsuario(T t);
    public void darDeBajaUsuario(T t);
    public void darDeAltaUsuario(T t);
    public T encontrarUsuario(String dni);
}
