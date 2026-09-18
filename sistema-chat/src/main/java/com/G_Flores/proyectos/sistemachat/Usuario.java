package com.G_Flores.proyectos.sistemachat;

public class Usuario {
    // Atributos
	private int id_usuario;
    private String nombre_usuario;
    private String correo;
    private String contraseña;
    protected Rol rol;
    
    // Constructor sin parámetros, sobrecargado para permitir la creación de un objeto Usuario sin inicializar sus atributos
    public Usuario() {}
    
    //polimorfismo de sobrecarga de métodos, se puede crear un objeto Usuario con todos sus atributos inicializados
    public Usuario(int id_usuario, String nombre_usuario, String correo, String contraseña, Rol rol) {
        this.id_usuario = id_usuario;
        this.nombre_usuario = nombre_usuario;
        this.correo = correo;
        this.contraseña = contraseña;
        this.rol = rol;
    }

    public void iniciarSesion(int id_usuario, String nombre_usuario, String correo, String contraseña, Rol rol) {
        this.id_usuario = id_usuario;
        this.nombre_usuario = nombre_usuario;
        this.correo = correo;
        this.contraseña = contraseña;
        this.rol = rol;
    }
    
    //metodo para iniciar sesión, se compara el correo y la contraseña ingresados con los del objeto Usuario
    public boolean iniciarSesion(String correo, String contraseña) 
    {
        return this.correo.equalsIgnoreCase(correo) && this.contraseña.equals(contraseña);
    }
    
    //metodo para mostrar el menú de opciones del usuario, se puede sobreescribir en las clases hijas para mostrar un menú diferente según el rol del usuario
    public void mostrarMenu() 
    {
        System.out.println("Menú Base de Usuario");
    }
    
    // Getters y Setters
    public int getId() 
    { 
    	return id_usuario; 
    }
    public void setId(int id) 
    { 
    	this.id_usuario = id; 
    }
    
    public String getNombre() 
    { 
    	return nombre_usuario; 
    }
    public void setNombre(String nombre) 
    { 
    	this.nombre_usuario = nombre; 
    }
    
    public String getCorreo() 
    { 
    	return correo;
    }
    public void setCorreo(String correo) 
    { 
    	this.correo = correo; 
    }
    
    public String getContraseña() 
    { 
    	return contraseña; 
    }
    public void setContraseña(String contraseña) 
    { 
    	this.contraseña = contraseña; 
    }
    
    public Rol getRol() 
    { 
    	return rol; 
    }
}