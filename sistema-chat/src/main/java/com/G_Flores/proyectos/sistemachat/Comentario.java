package com.G_Flores.proyectos.sistemachat;

public class Comentario {
    private int id_usuario;
    private String nombre_usuario;
    private int id_comentario;
    private String comentario_enviado;

    public Comentario(int id_usuario, String nombre_usuario, int id_comentario, String comentario_enviado) {
        this.id_usuario = id_usuario;
        this.nombre_usuario = nombre_usuario;
        this.id_comentario = id_comentario;
        this.comentario_enviado = comentario_enviado;
    }

    public int getId_comentario() {
        return id_comentario;
    }

    public String getComentario_enviado() {
        return comentario_enviado;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }
}