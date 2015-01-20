package rgg.campusvirtualapp.modelo;

public interface IModelo {
	/**
	 * Método encargado en pedirle a la clase TablaUsuarios realizar el login
	 * 
	 * @param username
	 *            contiene el valor del usuario (DNI)
	 * @param password
	 *            contiene el valor del password (contraseña)
	 */
	void verificarDatos(String username, String password);

	/**
	 * Método encargado en pedirle a la clase TablaAsignaturasMatriculadas las
	 * asignaturas matriculadas del Usuario registrado
	 */
	void pedirLista();

	/**
	 * Método encargado de almacenar el código de la asignatura
	 * 
	 * @param codAsignatura
	 *            contiene el valor del código.
	 */
	void setCodAsignatura(int codAsignatura);

	/**
	 * Método encargado de recuperar los datos de una asignatura
	 */
	void recuperarAsignatura();

	/**
	 * Método encargado en pedirle a la clase TablaDocumentacion un archivo en
	 * concreto.
	 * 
	 * @param archivo
	 *            contiene el nombre del archivo.
	 * @param semana
	 *            contiene el valor de la semana donde se encuentra el archivo.
	 */
	void solicitarArchivo(String archivo, int semana);

	/**
	 * Método encargado en solicitar a la clase TablaTareasSubidas que suba un
	 * archivo a una tarea en concreto.
	 * 
	 * @param archivo
	 *            contiene el archivo a subir
	 */
	void enviarArchivo(Object archivo);

	/**
	 * Método encargado de solicitar a la clase TablaForoAsignatura el envío de
	 * un tema concreto al foro.
	 * 
	 * @param tema
	 *            contiene la información del nuevo tema
	 */
	void enviarTema(Object tema);

	/**
	 * Método encargado en solicitar a la clase TablaForoAsignatura la lista de
	 * temas publicados en el foro de una asignatura.
	 */
	void obtenerListaForo();

	/**
	 * Método encargado de recuperar las notificaciones de un usuario
	 */
	void obtenerNotificacionesForo();

	/**
	 * Método encargado de recuperar las notificaciones nuevas de un usuario
	 */
	void obtenerNotificacionesForoNuevas();

	/**
	 * Método encargado en solicitar a la clase TablaUsuarios y la clase
	 * TablaAsignaturasMatriculadas todas las personas matriculadas en una misma
	 * asignatura.
	 */
	void pedirPersonas();

	/**
	 * Método encargado en solicitar a la clase TablaUsuarios los detalles de un
	 * usuario en concreto (Nombre, correo, foto).
	 * 
	 * @param username
	 *            contiene el identificador del username
	 */
	void pedirDetalles(int username);

	/**
	 * Método encargado en solicitar a la clase TablaDocumentacion los nombres
	 * de todos los archivos almacenados en una semana en concreto de una
	 * asignatura.
	 * 
	 * @param semana
	 *            contiene el valor de la semana
	 */
	void solicitarNombres(int semana);

	/**
	 * Método encargado de cerrar el modelo, cuando el usuario Usuario
	 * registrado desee salir de la aplicación.
	 */
	void cerrarModelo();

	/**
	 * Método encargado en recuperar las semanas de una asignatura
	 */
	void recuperarSemanas();

	/**
	 * Método encargado en recuperar las tareas de una asignatura
	 */
	void recuperarTareas();
}
