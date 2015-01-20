package rgg.campusvirtualapp.modelo;

public interface IModelo {
	/**
	 * M�todo encargado en pedirle a la clase TablaUsuarios realizar el login
	 * 
	 * @param username
	 *            contiene el valor del usuario (DNI)
	 * @param password
	 *            contiene el valor del password (contrase�a)
	 */
	void verificarDatos(String username, String password);

	/**
	 * M�todo encargado en pedirle a la clase TablaAsignaturasMatriculadas las
	 * asignaturas matriculadas del Usuario registrado
	 */
	void pedirLista();

	/**
	 * M�todo encargado de almacenar el c�digo de la asignatura
	 * 
	 * @param codAsignatura
	 *            contiene el valor del c�digo.
	 */
	void setCodAsignatura(int codAsignatura);

	/**
	 * M�todo encargado de recuperar los datos de una asignatura
	 */
	void recuperarAsignatura();

	/**
	 * M�todo encargado en pedirle a la clase TablaDocumentacion un archivo en
	 * concreto.
	 * 
	 * @param archivo
	 *            contiene el nombre del archivo.
	 * @param semana
	 *            contiene el valor de la semana donde se encuentra el archivo.
	 */
	void solicitarArchivo(String archivo, int semana);

	/**
	 * M�todo encargado en solicitar a la clase TablaTareasSubidas que suba un
	 * archivo a una tarea en concreto.
	 * 
	 * @param archivo
	 *            contiene el archivo a subir
	 */
	void enviarArchivo(Object archivo);

	/**
	 * M�todo encargado de solicitar a la clase TablaForoAsignatura el env�o de
	 * un tema concreto al foro.
	 * 
	 * @param tema
	 *            contiene la informaci�n del nuevo tema
	 */
	void enviarTema(Object tema);

	/**
	 * M�todo encargado en solicitar a la clase TablaForoAsignatura la lista de
	 * temas publicados en el foro de una asignatura.
	 */
	void obtenerListaForo();

	/**
	 * M�todo encargado de recuperar las notificaciones de un usuario
	 */
	void obtenerNotificacionesForo();

	/**
	 * M�todo encargado de recuperar las notificaciones nuevas de un usuario
	 */
	void obtenerNotificacionesForoNuevas();

	/**
	 * M�todo encargado en solicitar a la clase TablaUsuarios y la clase
	 * TablaAsignaturasMatriculadas todas las personas matriculadas en una misma
	 * asignatura.
	 */
	void pedirPersonas();

	/**
	 * M�todo encargado en solicitar a la clase TablaUsuarios los detalles de un
	 * usuario en concreto (Nombre, correo, foto).
	 * 
	 * @param username
	 *            contiene el identificador del username
	 */
	void pedirDetalles(int username);

	/**
	 * M�todo encargado en solicitar a la clase TablaDocumentacion los nombres
	 * de todos los archivos almacenados en una semana en concreto de una
	 * asignatura.
	 * 
	 * @param semana
	 *            contiene el valor de la semana
	 */
	void solicitarNombres(int semana);

	/**
	 * M�todo encargado de cerrar el modelo, cuando el usuario Usuario
	 * registrado desee salir de la aplicaci�n.
	 */
	void cerrarModelo();

	/**
	 * M�todo encargado en recuperar las semanas de una asignatura
	 */
	void recuperarSemanas();

	/**
	 * M�todo encargado en recuperar las tareas de una asignatura
	 */
	void recuperarTareas();
}
