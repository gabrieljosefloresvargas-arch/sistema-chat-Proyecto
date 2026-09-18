package com.G_Flores.proyectos.sistemachat;

public class Administrador extends Usuario {

    public Administrador(Usuario usuario) {
        super(usuario.getId(), usuario.getNombre(), usuario.getCorreo(), usuario.getContraseña(), Rol.ADMINISTRADOR);
    }

    public void inhabilitarEmpleado(boolean estado) {
        System.out.println("Estado del empleado actualizado a: " + estado);
    }

    public void borrarEmpleado(Empleado empleado) {
        System.out.println("Empleado " + empleado.getNombre() + " eliminado.");
    }

    public void borrarPregunta(Pregunta pregunta) {
        System.out.println("Pregunta '" + pregunta.getTitulo() + "' eliminada.");
    }

    @Override
    public void mostrarMenu() {
        System.out.println("=== Menú Administrador ===");
        System.out.println("1. Gestionar Preguntas");
        System.out.println("2. Gestionar Empleados");
    }
}