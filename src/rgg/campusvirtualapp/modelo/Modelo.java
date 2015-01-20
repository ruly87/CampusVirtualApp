package rgg.campusvirtualapp.modelo;

import com.parse.ParseUser;

public class Modelo implements IModelo {
	private int codAsignatura;
	private int username;

	@Override
	public void verificarDatos(String username, String password) {
		this.username = Integer.parseInt(username);
		TablaUsuarios.comprobarLogin(username, password);
	}

	@Override
	public void pedirLista() {
		TablaAsignaturasMatriculadas.solicitarLista(username);
	}

	@Override
	public void setCodAsignatura(int codAsignatura) {
		this.codAsignatura = codAsignatura;
	}

	@Override
	public void recuperarAsignatura() {
		TablaAsignaturas.obtenerNombreAsignatura(codAsignatura);
	}

	@Override
	public void solicitarArchivo(String archivo, int semana) {
		TablaDocumentacion.descargarArchivo(codAsignatura, semana, archivo);
	}

	@Override
	public void enviarArchivo(Object archivo) {
		TablaTareasSubidas.subirTarea(archivo, username);
	}

	@Override
	public void enviarTema(Object tema) {
		((DatosHilo) tema).setCodAsignatura(codAsignatura);
		((DatosHilo) tema).setUsername(username);
		TablaForoAsignatura.publicarTema(tema);
	}

	@Override
	public void obtenerListaForo() {
		TablaForoAsignatura.obtenerForo(codAsignatura);
	}

	@Override
	public void obtenerNotificacionesForo() {
		TablaNotificaciones.obtenerNotificaciones(username);
	}

	@Override
	public void obtenerNotificacionesForoNuevas() {
		TablaNotificaciones.obtenerNotificacionesNuevas(username);
	}

	@Override
	public void pedirPersonas() {
		TablaUsuarios.obtenerNombres(username, codAsignatura);
	}

	@Override
	public void pedirDetalles(int username) {
		TablaUsuarios.obtenerDetalles(username);
	}

	@Override
	public void solicitarNombres(int semana) {
		TablaDocumentacion.obtenerDocumentacion(semana, codAsignatura);
	}

	@Override
	public void cerrarModelo() {
		ParseUser.logOut();

	}

	@Override
	public void recuperarSemanas() {
		TablaDocumentacion.obtenerSemana(codAsignatura);
	}

	@Override
	public void recuperarTareas() {
		TablaTareas.obtenerTareas(codAsignatura);
	}

}
