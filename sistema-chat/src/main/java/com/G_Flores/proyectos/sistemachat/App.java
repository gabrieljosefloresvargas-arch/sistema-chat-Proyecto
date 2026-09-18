package com.G_Flores.proyectos.sistemachat;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    //atributos
	private List<Usuario> usuarios;
    private Gestion_pregunta gestionPreguntas;
    private Usuario usuarioActual;
    private Scanner scanner;
    private int contadorPreguntas = 1;
    
    public static void main(String[] args) {
        App sistema = new App();
        sistema.ejecutar();
    }
    
    //constructor app
    public App() {
        this.usuarios = new ArrayList<>();
        this.gestionPreguntas = new Gestion_pregunta();
        this.scanner = new Scanner(System.in);
        this.usuarioActual = null;
    }
    
    //inicializamos  datos de los dos usuarios a probar
    private void inicializarDatos() {
        // Datos de prueba
        Usuario adminBase = new Usuario(1, "Admin", "admin@chat.com", "admin123", Rol.ADMINISTRADOR);
        Usuario empBase = new Usuario(2, "Gabriel_F", "gaci@chat.com", "nuevo456", Rol.EMPLEADO);
        
        usuarios.add(new Administrador(adminBase));
        usuarios.add(new Empleado(empBase));
        
        //creamos una preguntas
        // Pregunta inicial de prueba
        Pregunta pInicial = new Pregunta(contadorPreguntas++, "¿como hacer jugo de limon?", "Necesito ayuda, como hago limonada.", "Gabriel_F", true);
        pInicial.agregarComentario(new Comentario(1, "Admin", 1, "Lava el limón con agua y córtalo por la mitad.\n"
        		+ "	Exprime el jugo del limón directamente en tu vaso. Si quieres, usa un colador pequeño para evitar que pasen las semillas.\n"
        		+ "	Añade el azúcar o el endulzante y revuelve bien con una cuchara hasta que se disuelva en el jugo.\n"
        		+ "	Vierte el agua fría en el vaso y mezcla otra vez.\n"
        		+ "	Agrega los cubos de hielo, decora con una rodaja de limón si lo deseas y disfruta"));
        gestionPreguntas.agregarPregunta(pInicial);
    }
    
    //metodo para iniciar sesion
    private boolean login() {
        System.out.println("\n--- INICIO DE SESIÓN ---");
        System.out.print("Ingrese Correo: ");
        String correo = scanner.nextLine();
        System.out.print("Ingrese Contraseña: ");
        String pass = scanner.nextLine();
        
        // Verificar credenciales
        for (Usuario u : usuarios) {
            if (u.iniciarSesion(correo, pass)) {
                this.usuarioActual = u;
                System.out.println("\n ¡Bienvenido(a), " + u.getNombre() + " (" + u.getRol() + ")!");
                return true;
            }
        }
        System.out.println(" Credenciales incorrectas. Intente nuevamente.");
        return false;
    }
    
    //metodo para ejecutar el sistema
    public void ejecutar() {
        // Inicializar datos de prueba
    	inicializarDatos();

        while (true) {
            System.out.println("\n========== SISTEMA CHAT ==========");
            System.out.println("1. Iniciar Sesión");
            System.out.println("2. Salir del Sistema");
            System.out.print("Seleccione una opción: ");
            // Leer la opción del usuario
            String opcionIniciar = scanner.nextLine();
            
            if (opcionIniciar.equals("1")) {
                if (login()) {
                    menuPrincipal();
                }
            } else if (opcionIniciar.equals("2")){
                System.out.println("¡Gracias por usar el Sistema Chat! Hasta luego.");
                break;
            } else {
                System.out.println("Opción no válida.");
            }
        }
    }
    
    //metodo para mostrar el menu principal dependiendo del rol del usuario
    private void menuPrincipal() {
        boolean sesionActiva = true;

        while (sesionActiva) 
        {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            if (usuarioActual.getRol() == Rol.EMPLEADO) 
            {
                System.out.println("1. Ver todas las preguntas");
                System.out.println("2. Buscar preguntas");
                System.out.println("3. Crear una nueva pregunta");
                System.out.println("4. Agregar comentario a una pregunta");
                System.out.println("5. Cerrar Sesión");
                System.out.print("Seleccione una opción: ");

                String opcion = scanner.nextLine();
                switch (opcion) 
                {
                    case "1":
                        gestionPreguntas.mostrarTodas();
                        break;
                    case "2":
                        buscarPreguntas();
                        break;
                    case "3":
                        crearPregunta();
                        break;
                    case "4":
                        agregarComentario();
                        break;
                    case "5":
                        sesionActiva = false;
                        usuarioActual = null;
                        System.out.println("Sesión cerrada.");
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }
                
            } else if (usuarioActual.getRol() == Rol.ADMINISTRADOR) 
            	{
                System.out.println("1. Ver todas las preguntas");
                System.out.println("2. Buscar preguntas");
                System.out.println("3. Cambiar estado de una pregunta (Abrir/Cerrar)");
                System.out.println("4. Eliminar una pregunta");
                System.out.println("5. Listar usuarios del sistema");
                System.out.println("6. Cerrar Sesión");
                System.out.print("Seleccione una opción: ");
                String opcion = scanner.nextLine();
                
                switch (opcion) 
                	{
                    case "1":
                        gestionPreguntas.mostrarTodas();
                        break;
                    case "2":
                        buscarPreguntas();
                        break;
                    case "3":
                        cambiarEstadoPregunta();
                        break;
                    case "4":
                        eliminarPregunta();
                        break;
                    case "5":
                        listarUsuarios();
                        break;
                    case "6":
                        sesionActiva = false;
                        usuarioActual = null;
                        System.out.println("Sesión cerrada.");
                        break;
                    default:
                        System.out.println("Opción inválida.");
                	}
            	}
        }
    }

    private void buscarPreguntas() {
        System.out.print("Ingrese término o palabra clave a buscar: ");
        String criterio = scanner.nextLine();
        List<Pregunta> encontradas = gestionPreguntas.buscar(criterio);

        if (encontradas.isEmpty()) {
            System.out.println("No se encontraron preguntas con ese criterio.");
        } else {
            System.out.println("\n--- RESULTADOS DE BÚSQUEDA ---");
            for (Pregunta p : encontradas) {
                System.out.println("[" + p.getId() + "] " + p.getTitulo() + " (por " + p.getAutor() + ")");
            }
        }
    }

    private void crearPregunta() {
        System.out.print("Ingrese el título de la pregunta: ");
        String titulo = scanner.nextLine();
        System.out.print("Ingrese la descripción o detalle: ");
        String desc = scanner.nextLine();

        Pregunta nueva = new Pregunta(contadorPreguntas++, titulo, desc, usuarioActual.getNombre(), true);
        gestionPreguntas.agregarPregunta(nueva);
    }

    private void agregarComentario() {
        gestionPreguntas.mostrarTodas();
        System.out.print("Ingrese el ID de la pregunta que desea comentar: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Pregunta p = gestionPreguntas.buscarPorId(id);

            if (p != null) {
                if (!p.getEstado()) {
                    System.out.println(" No se pueden agregar comentarios a una pregunta cerrada.");
                    return;
                }
                System.out.print("Ingrese su comentario: ");
                String texto = scanner.nextLine();
                int idComentario = p.getListaComentarios().size() + 1;
                
                Comentario com = new Comentario(usuarioActual.getId(), usuarioActual.getNombre(), idComentario, texto);
                p.agregarComentario(com);
                System.out.println(" Comentario publicado.");
            } else {
                System.out.println(" No se encontró una pregunta con ese ID.");
            }
        } catch (NumberFormatException e) {
            System.out.println(" ID no válido.");
        }
    }

    private void cambiarEstadoPregunta() {
        gestionPreguntas.mostrarTodas();
        System.out.print("Ingrese el ID de la pregunta a modificar: ");
        try 
        {
            int id = Integer.parseInt(scanner.nextLine());
            Pregunta p = gestionPreguntas.buscarPorId(id);

            if (p != null) 
            {
                System.out.print("¿Desea (1) Abrir o (2) Cerrar la pregunta?: ");
                String op = scanner.nextLine();
                if (op.equals("1")) {
                    p.setEstado(true);
                    System.out.println("Pregunta Abierta.");
                } else if (op.equals("2")) {
                    p.setEstado(false);
                    System.out.println("Pregunta Cerrada.");
                } else {
                    System.out.println("Opción inválida.");
                }
            } else	
            	{
                System.out.println("Pregunta no encontrada.");
            	}
            
        } catch (NumberFormatException e) {
            System.out.println("ID no válido.");
        }
    }

    private void eliminarPregunta() 
    {
        gestionPreguntas.mostrarTodas();
        System.out.print("Ingrese el ID de la pregunta a eliminar: ");
        
        // Validar que el ID ingresado sea un número
        try {
            int id = Integer.parseInt(scanner.nextLine());
            boolean eliminada = gestionPreguntas.eliminarPregunta(id);
            if (eliminada) {
                System.out.println(" Pregunta eliminada correctamente.");
            } else {
                System.out.println(" No se encontró la pregunta.");
            }
        } 
        
        // Manejar la excepción si el ID no es un número válido
        catch (NumberFormatException e) {
            System.out.println(" ID no válido.");
        }
    }

    private void listarUsuarios() {
        System.out.println("\n--- LISTA DE USUARIOS ---");
        for (Usuario u : usuarios) {
            System.out.println("ID: " + u.getId() + " | Nombre: " + u.getNombre() + " | Correo: " + u.getCorreo() + " | Rol: " + u.getRol());
        }
    }

}