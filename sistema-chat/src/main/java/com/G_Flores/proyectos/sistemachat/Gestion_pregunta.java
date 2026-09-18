package com.G_Flores.proyectos.sistemachat;

import java.util.ArrayList;
import java.util.List;

public class Gestion_pregunta {
    private List<Pregunta> listaPreguntas;

    public Gestion_pregunta() {
        this.listaPreguntas = new ArrayList<>();
    }

    public List<Pregunta> getListaPreguntas() {
        return listaPreguntas;
    }

    public void mostrarTodas() {
        if (listaPreguntas.isEmpty()) {
            System.out.println("No hay preguntas registradas.");
            return;
        }
        for (Pregunta p : listaPreguntas) {
            String estadoStr = p.getEstado() ? "Abierta" : "Cerrada";
            System.out.println("----------------------------------------");
            System.out.println("ID: " + p.getId() + " | Título: " + p.getTitulo() + " | Autor: " + p.getAutor() + " | Estado: " + estadoStr);
            System.out.println("Descripción: " + p.getDescripcion());
            
            if (!p.getListaComentarios().isEmpty()) {
                System.out.println("  Comentarios:");
                for (Comentario c : p.getListaComentarios()) {
                    System.out.println("   - [" + c.getNombre_usuario() + "]: " + c.getComentario_enviado());
                }
            } else {
                System.out.println("  (Sin comentarios aún)");
            }
        }
        System.out.println("----------------------------------------");
    }

    public List<Pregunta> buscar(String criterio) {
        List<Pregunta> resultado = new ArrayList<>();
        for (Pregunta p : listaPreguntas) {
            if (p.getTitulo().toLowerCase().contains(criterio.toLowerCase()) || 
                p.getDescripcion().toLowerCase().contains(criterio.toLowerCase())) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    public void agregarPregunta(Pregunta p) {
        listaPreguntas.add(p);
        System.out.println(" Pregunta agregada con éxito.");
    }

    public Pregunta buscarPorId(int id) {
        for (Pregunta p : listaPreguntas) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public boolean eliminarPregunta(int id) {
        Pregunta p = buscarPorId(id);
        if (p != null) {
            listaPreguntas.remove(p);
            return true;
        }
        return false;
    }
}