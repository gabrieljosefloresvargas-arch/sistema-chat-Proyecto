package com.G_Flores.proyectos.sistemachat;

public class Empleado extends Usuario {

    public Empleado(Usuario usuario) {
        super(usuario.getId(), usuario.getNombre(), usuario.getCorreo(), usuario.getContraseña(), Rol.EMPLEADO);
    }

    @Override
    public void mostrarMenu() {
        System.out.println("=== Menú Empleado ===");
        System.out.println("1. Ver Preguntas");
        System.out.println("2. Publicar Comentario");
    }
}