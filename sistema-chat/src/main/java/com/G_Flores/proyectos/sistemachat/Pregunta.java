package com.G_Flores.proyectos.sistemachat;

import java.util.ArrayList;
import java.util.List;

public class Pregunta {
    private int id;
    private String titulo;
    private String descripcion;
    private String autor;
    private boolean estado;
    private List<Comentario> listaComentarios;
    
    // metodo constructor
    public Pregunta(int id, String titulo, String descripcion, String autor, boolean estado) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.autor = autor;
        this.estado = estado;
        this.listaComentarios = new ArrayList<>();
    }
    
    
    public void agregarComentario(Comentario c) 
    {
        this.listaComentarios.add(c);
    }

    public int getId() 
    { 
    	return id; 
    }
    
    public String getTitulo() 
    { 
    	return titulo; 
    }
    
    public String getDescripcion() 
    { 
    	return descripcion; 
    }
    
    public String getAutor() 
    { 
    	return autor; 
    }
    
    public boolean getEstado() 
    { 
    	return estado; 
    }
    public void setEstado(boolean estado) 
    { 
    	this.estado = estado; 
    }
    
    public List<Comentario> getListaComentarios() 
    { 
    	return listaComentarios; 
    }
}