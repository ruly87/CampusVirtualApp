package rgg.campusvirtualapp;

import java.io.File;

import rgg.campusvirtualapp.modelo.IModelo;
import rgg.campusvirtualapp.presentador.IPresentadorAsignaturas;
import rgg.campusvirtualapp.presentador.IPresentadorAyuda;
import rgg.campusvirtualapp.presentador.IPresentadorDetallesParticipantes;
import rgg.campusvirtualapp.presentador.IPresentadorDocumentacion;
import rgg.campusvirtualapp.presentador.IPresentadorForoAsignatura;
import rgg.campusvirtualapp.presentador.IPresentadorForoAsignaturaDetalle;
import rgg.campusvirtualapp.presentador.IPresentadorForoAsignaturaNuevoTema;
import rgg.campusvirtualapp.presentador.IPresentadorForoAsignaturaResponderTema;
import rgg.campusvirtualapp.presentador.IPresentadorGaleriaDocumentacion;
import rgg.campusvirtualapp.presentador.IPresentadorGaleriaTarea;
import rgg.campusvirtualapp.presentador.IPresentadorLogin;
import rgg.campusvirtualapp.presentador.IPresentadorNotificacionesForo;
import rgg.campusvirtualapp.presentador.IPresentadorNotificacionesForoDetalle;
import rgg.campusvirtualapp.presentador.IPresentadorPaginaAsignatura;
import rgg.campusvirtualapp.presentador.IPresentadorParticipantes;
import rgg.campusvirtualapp.presentador.IPresentadorTarea;
import rgg.campusvirtualapp.presentador.PresentadorAsignaturas;
import rgg.campusvirtualapp.presentador.PresentadorAyuda;
import rgg.campusvirtualapp.presentador.PresentadorDetallesParticipantes;
import rgg.campusvirtualapp.presentador.PresentadorDocumentacion;
import rgg.campusvirtualapp.presentador.PresentadorForoAsignatura;
import rgg.campusvirtualapp.presentador.PresentadorForoAsignaturaDetalle;
import rgg.campusvirtualapp.presentador.PresentadorForoAsignaturaNuevoTema;
import rgg.campusvirtualapp.presentador.PresentadorForoAsignaturaResponderTema;
import rgg.campusvirtualapp.presentador.PresentadorGaleriaDocumentacion;
import rgg.campusvirtualapp.presentador.PresentadorGaleriaTarea;
import rgg.campusvirtualapp.presentador.PresentadorLogin;
import rgg.campusvirtualapp.presentador.PresentadorNotificacionesForo;
import rgg.campusvirtualapp.presentador.PresentadorNotificacionesForoDetalle;
import rgg.campusvirtualapp.presentador.PresentadorPaginaAsignatura;
import rgg.campusvirtualapp.presentador.PresentadorParticipantes;
import rgg.campusvirtualapp.presentador.PresentadorTarea;
import rgg.campusvirtualapp.presentador.bajo_nivel.ServicioNotificaciones;
import rgg.campusvirtualapp.vista.IVistaAsignaturas;
import rgg.campusvirtualapp.vista.IVistaAyuda;
import rgg.campusvirtualapp.vista.IVistaDetallesParticipantes;
import rgg.campusvirtualapp.vista.IVistaDocumentacion;
import rgg.campusvirtualapp.vista.IVistaForoAsignatura;
import rgg.campusvirtualapp.vista.IVistaForoAsignaturaDetalle;
import rgg.campusvirtualapp.vista.IVistaForoAsignaturaNuevoTema;
import rgg.campusvirtualapp.vista.IVistaForoAsignaturaResponderTema;
import rgg.campusvirtualapp.vista.IVistaGaleriaDocumentacion;
import rgg.campusvirtualapp.vista.IVistaGaleriaTarea;
import rgg.campusvirtualapp.vista.IVistaLogin;
import rgg.campusvirtualapp.vista.IVistaNotificacionesForoDetalle;
import rgg.campusvirtualapp.vista.R;
import rgg.campusvirtualapp.vista.VistaAsignaturas;
import rgg.campusvirtualapp.vista.VistaAyuda;
import rgg.campusvirtualapp.vista.VistaDetallesParticipantes;
import rgg.campusvirtualapp.vista.VistaDocumentacion;
import rgg.campusvirtualapp.vista.VistaForoAsignatura;
import rgg.campusvirtualapp.vista.VistaForoAsignaturaDetalle;
import rgg.campusvirtualapp.vista.VistaForoAsignaturaNuevoTema;
import rgg.campusvirtualapp.vista.VistaForoAsignaturaResponderTema;
import rgg.campusvirtualapp.vista.VistaGaleriaDocumentacion;
import rgg.campusvirtualapp.vista.VistaGaleriaTarea;
import rgg.campusvirtualapp.vista.VistaLogin;
import rgg.campusvirtualapp.vista.IVistaNotificacionesForo;
import rgg.campusvirtualapp.vista.IVistaPaginaAsignatura;
import rgg.campusvirtualapp.vista.IVistaParticipantes;
import rgg.campusvirtualapp.vista.IVistaTarea;
import rgg.campusvirtualapp.vista.VistaNotificacionesForo;
import rgg.campusvirtualapp.vista.VistaNotificacionesForoDetalle;
import rgg.campusvirtualapp.vista.VistaPaginaAsignatura;
import rgg.campusvirtualapp.vista.VistaParticipantes;
import rgg.campusvirtualapp.vista.VistaTarea;
import rgg.campusvirtualapp.vista.Preferencias;

import com.parse.Parse;
import com.parse.ParseACL;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;

@SuppressWarnings("rawtypes")
public class AppMediador extends Application {
	private static AppMediador singleton;
	// Preferencias
	private Class preferencias;
	// Presentadores
	private IPresentadorLogin presentadorLogin;
	private IPresentadorAsignaturas presentadorAsignaturas;
	private IPresentadorPaginaAsignatura presentadorPaginaAsignatura;
	private IPresentadorGaleriaDocumentacion presentadorGaleriaDocumentacion;
	private IPresentadorDocumentacion presentadorDocumentacion;
	private IPresentadorGaleriaTarea presentadorGaleriaTarea;
	private IPresentadorTarea presentadorTarea;
	private IPresentadorForoAsignatura presentadorForoAsignatura;
	private IPresentadorForoAsignaturaDetalle presentadorForoAsignaturaDetalle;
	private IPresentadorForoAsignaturaNuevoTema presentadorForoAsignaturaNuevoTema;
	private IPresentadorForoAsignaturaResponderTema presentadorForoAsignaturaResponderTema;
	private IPresentadorParticipantes presentadorParticipantes;
	private IPresentadorDetallesParticipantes presentadorDetallesParticipantes;
	private IPresentadorAyuda presentadorAyuda;
	private IPresentadorNotificacionesForo presentadorNotificacionesForo;
	private IPresentadorNotificacionesForoDetalle presentadorNotificacionesForoDetalle;
	// Vistas
	private IVistaLogin vistaLogin;
	private IVistaAsignaturas vistaAsignaturas;
	private IVistaAyuda vistaAyuda;
	private IVistaPaginaAsignatura vistaPaginaAsignatura;
	private IVistaForoAsignatura vistaForoAsignatura;
	private IVistaForoAsignaturaDetalle vistaForoAsignaturaDetalle;
	private IVistaForoAsignaturaNuevoTema vistaForoAsignaturaNuevoTema;
	private IVistaForoAsignaturaResponderTema vistaForoAsignaturaResponderTema;
	private IVistaParticipantes vistaParticipantes;
	private IVistaDetallesParticipantes vistaDetallesParticipantes;
	private IVistaGaleriaDocumentacion vistaGaleriaDocumentacion;
	private IVistaDocumentacion vistaDocumentacion;
	private IVistaGaleriaTarea vistaGaleriaTarea;
	private IVistaTarea vistaTarea;
	private IVistaNotificacionesForo vistaNotificacionesForo;
	private IVistaNotificacionesForoDetalle vistaNotificacionesForoDetalle;
	// Modelo
	private IModelo modelo;
	// Servicios
	@SuppressWarnings("unused")
	private ServicioNotificaciones servicioNotificaciones;
	// constantes de petición y notificación
	public static String AVISO_REGISTRO = "rgg.campusvirtualapp.REGISTRO_FINALIZADO";
	public static String AVISO_LOGIN = "rgg.campusvirtualapp.LOGIN_FINALIZADO";
	public static String AVISO_DATOS = "rgg.campusvirtualapp.ALUMNOS_RECUPERADOS";
	public static String AVISO_ASIGNATURAS = "rgg.campusvirtualapp.ASIGNATURAS_RECUPERADAS";
	public static String AVISO_SEMANAS = "rgg.campusvirtualapp.SEMANAS_RECUPERADAS";
	public static String AVISO_DOCUMENTACION = "rgg.campusvirtualapp.DOCUMENTACION_RECUPERADOS";
	public static String AVISO_ARCHIVO = "rgg.campusvirtualapp.ARCHIVO_RECUPERADOS";
	public static String AVISO_TAREAS = "rgg.campusvirtualapp.TAREAS_RECUPERADAS";
	public static String AVISO_TAREA_SUBIDA = "rgg.campusvirtualapp.TAREA_SUBIDA";
	public static String AVISO_PARTICIPANTES = "rgg.campusvirtualapp.PARTICIPANTE_RECUPERADOS";
	public static String AVISO_DETALLES_PARTICIPANTES = "rgg.campusvirtualapp.DETALLES_PARTICIPANTE_RECUPERADOS";
	public static String AVISO_FORO = "rgg.campusvirtualapp.FORO_RECUPERADO";
	public static String AVISO_TEMA_ENVIADO = "rgg.campusvirtualapp.TEMA_SUBIDO";
	public static String AVISO_NOTIFICACIONES = "rgg.campusvirtualapp.NOTIFICACIONES_RECUPERADAS";
	public static String AVISO_NOTIFICACIONES_NUEVAS = "rgg.campusvirtualapp.NOTIFICACIONES_NUEVAS_RECUPERADAS";
	public static String DATOS_ALUMNO = "DatosAlumno";
	public static String DATOS_ASIGNATURA = "DatosAsignatura";
	public static String DATOS_DOCUMENTACION = "DatosDocumentacion";
	public static String DATOS_ASIGNATURAS = "ArrayDatosAsignatura";
	public static String DATOS_TAREAS = "DatosTareas";
	public static String DATOS_ARRAY_PARTICIPANTES = "ArrayDatosParticipantes";
	public static String DATOS_PARTICIPANTES = "DatosParticipantes";
	public static String DATOS_ARRAY_FORO = "ArrayDatosHilo";
	public static String DATOS_FORO = "DatosHilo";
	public static String DATOS_NUEVO_TEMA = "DatosHiloNuevoTema";
	public static String DATOS_NOTIFICACIONES = "DatosNotificaciones";
	public static String IDENTIFICADOR_ASIGNATURA = "IdentificadorAsignatura";
	public static String TITULO = "titulo";

	public static AppMediador getInstance() {
		return singleton;
	}

	// Métodos de lanzamiento de actividades, servicios, receptores
	// broadcast,...
	public void launchActivity(Class actividadInvocada, Object invocador,
			Bundle extras) {
		Intent i = new Intent(this, actividadInvocada);
		if (extras != null)
			i.putExtras(extras);
		if (!invocador.getClass().equals(Activity.class))
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(i);
	}

	public void launchActivity(Uri uri) {
		Intent intent = new Intent(Intent.ACTION_SENDTO);
		intent.setData(uri);
		Intent finalIntent = Intent.createChooser(intent,
				getString(R.string.gestor_correo));
		finalIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(finalIntent);
	}

	public void launchActivity(File archivo) {
		Uri uri = Uri.fromFile(archivo);
		Intent intent = new Intent(Intent.ACTION_VIEW);
		// Check what kind of file you are trying to open, by comparing the url
		// with extensions.
		// When the if condition is matched, plugin sets the correct intent
		// (mime) type,
		// so Android knew what application to use to open the file
		if (archivo.toString().contains(".doc")
				|| archivo.toString().contains(".docx")) {
			// Word document
			intent.setDataAndType(uri, "application/msword");
		} else if (archivo.toString().contains(".pdf")) {
			// PDF file
			intent.setDataAndType(uri, "application/pdf");
		} else if (archivo.toString().contains(".ppt")
				|| archivo.toString().contains(".pptx")) {
			// Powerpoint file
			intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
		} else if (archivo.toString().contains(".xls")
				|| archivo.toString().contains(".xlsx")) {
			// Excel file
			intent.setDataAndType(uri, "application/vnd.ms-excel");
		} else if (archivo.toString().contains(".zip")
				|| archivo.toString().contains(".rar")) {
			// WAV audio file
			intent.setDataAndType(uri, "application/x-wav");
		} else if (archivo.toString().contains(".rtf")) {
			// RTF file
			intent.setDataAndType(uri, "application/rtf");
		} else if (archivo.toString().contains(".wav")
				|| archivo.toString().contains(".mp3")) {
			// WAV audio file
			intent.setDataAndType(uri, "audio/x-wav");
		} else if (archivo.toString().contains(".gif")) {
			// GIF file
			intent.setDataAndType(uri, "image/gif");
		} else if (archivo.toString().contains(".jpg")
				|| archivo.toString().contains(".jpeg")
				|| archivo.toString().contains(".png")) {
			// JPG file
			intent.setDataAndType(uri, "image/jpeg");
		} else if (archivo.toString().contains(".txt")) {
			// Text file
			intent.setDataAndType(uri, "text/plain");
		} else if (archivo.toString().contains(".3gp")
				|| archivo.toString().contains(".mpg")
				|| archivo.toString().contains(".mpeg")
				|| archivo.toString().contains(".mpe")
				|| archivo.toString().contains(".mp4")
				|| archivo.toString().contains(".avi")) {
			// Video files
			intent.setDataAndType(uri, "video/*");
		} else {
			// if you want you can also define the intent type for any other
			// file

			// additionally use else clause below, to manage other unknown
			// extensions
			// in this case, Android will show all applications installed on the
			// device
			// so you can choose which application to use
			intent.setDataAndType(uri, "*/*");
		}

		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}

	public void launchActivityForResult(Class actividadInvocada,
			Activity actividadInvocadora, int requestCode, Bundle extras) {
		Intent i = new Intent(actividadInvocadora, actividadInvocada);
		if (extras != null)
			i.putExtras(extras);
		actividadInvocadora.startActivityForResult(i, requestCode);
	}

	public void launchActivityForResult(Activity actividadInvocadora,
			String tipo, int requestCode) {
		Intent i = new Intent(Intent.ACTION_GET_CONTENT);
		i.setType(tipo);
		i.addCategory(Intent.CATEGORY_OPENABLE);
		actividadInvocadora.startActivityForResult(
				Intent.createChooser(i, getString(R.string.gestor)),
				requestCode);
	}

	public void launchService(Class servicioInvocado, Bundle extras) {
		Intent i = new Intent(this, servicioInvocado);
		if (extras != null)
			i.putExtras(extras);
		startService(i);
	}

	public void stopService(Class servicioInvocado) {
		Intent i = new Intent(this, servicioInvocado);
		stopService(i);
	}

	public void registerReceiver(BroadcastReceiver receptor, String[] acciones) {
		for (int i = 0; i < acciones.length; i++)
			LocalBroadcastManager.getInstance(this).registerReceiver(receptor,
					new IntentFilter(acciones[i]));
	}

	public void unRegisterReceiver(BroadcastReceiver receptor) {
		LocalBroadcastManager.getInstance(this).unregisterReceiver(receptor);
	}

	public void sendBroadcast(String accion, Bundle extras) {
		Intent intent = new Intent();
		intent.setAction(accion);
		if (extras != null)
			intent.putExtras(extras);
		LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
	}

	// Métodos de iniciación de Parse
	private void iniciarParse() {
		String PARSE_APPLICATION_ID = "JlQvCUlzoQi65CyddMPwJJnDrPUBAoxpCQ1tikwl";
		String PARSE_CLIENT_KEY = "CJxLSV2E0OynFADcUcf3WLqDyzUsVu6qaeQxsTzQ";
		Parse.initialize(this, PARSE_APPLICATION_ID, PARSE_CLIENT_KEY);
		ParseACL defaultACL = new ParseACL();
		// se permite la lectura y escritura de todos los objetos creados. ¡No
		// hay control de seguridad!
		defaultACL.setPublicReadAccess(true);
		defaultACL.setPublicWriteAccess(true);
		ParseACL.setDefaultACL(defaultACL, true);
	}

	public void borrarPaginaAsignatura() {
		presentadorGaleriaDocumentacion = null;
		presentadorDocumentacion = null;
		presentadorGaleriaTarea = null;
		presentadorTarea = null;
		presentadorForoAsignatura = null;
		presentadorForoAsignaturaDetalle = null;
		presentadorForoAsignaturaNuevoTema = null;
		presentadorForoAsignaturaResponderTema = null;
		presentadorParticipantes = null;
		presentadorDetallesParticipantes = null;
	}

	// Método que crea la aplicación
	@Override
	public void onCreate() {
		super.onCreate();
		iniciarParse();
		preferencias = Preferencias.class;
		presentadorLogin = null;
		presentadorAsignaturas = null;
		presentadorPaginaAsignatura = null;
		presentadorGaleriaDocumentacion = null;
		presentadorDocumentacion = null;
		presentadorGaleriaTarea = null;
		presentadorTarea = null;
		presentadorForoAsignatura = null;
		presentadorForoAsignaturaDetalle = null;
		presentadorForoAsignaturaNuevoTema = null;
		presentadorForoAsignaturaResponderTema = null;
		presentadorParticipantes = null;
		presentadorDetallesParticipantes = null;
		presentadorAyuda = null;
		presentadorNotificacionesForo = null;
		presentadorNotificacionesForoDetalle = null;
		singleton = this;
	}

	// Métodos accessor de las vistas
	public IVistaLogin getVistaLogin() {
		return vistaLogin;
	}

	public void setVistaLogin(IVistaLogin vistaLogin) {
		this.vistaLogin = vistaLogin;
	}

	public IVistaAsignaturas getVistaAsignaturas() {
		return vistaAsignaturas;
	}

	public void setVistaAsignaturas(IVistaAsignaturas vistaAsignaturas) {
		this.vistaAsignaturas = vistaAsignaturas;
	}

	public IVistaAyuda getVistaAyuda() {
		return vistaAyuda;
	}

	public void setVistaAyuda(IVistaAyuda vistaAyuda) {
		this.vistaAyuda = vistaAyuda;
	}

	public IVistaPaginaAsignatura getVistaPaginaAsignatura() {
		return vistaPaginaAsignatura;
	}

	public void setVistaPaginaAsignatura(
			IVistaPaginaAsignatura vistaPaginaAsignatura) {
		this.vistaPaginaAsignatura = vistaPaginaAsignatura;
	}

	public IVistaForoAsignatura getVistaForoAsignatura() {
		return vistaForoAsignatura;
	}

	public void setVistaForoAsignatura(IVistaForoAsignatura vistaForoAsignatura) {
		this.vistaForoAsignatura = vistaForoAsignatura;
	}

	public IVistaForoAsignaturaDetalle getVistaForoAsignaturaDetalle() {
		return vistaForoAsignaturaDetalle;
	}

	public void setVistaForoAsignaturaDetalle(
			IVistaForoAsignaturaDetalle vistaForoAsignaturaDetalle) {
		this.vistaForoAsignaturaDetalle = vistaForoAsignaturaDetalle;
	}

	public IVistaForoAsignaturaNuevoTema getVistaForoAsignaturaNuevoTema() {
		return vistaForoAsignaturaNuevoTema;
	}

	public void setVistaForoAsignaturaNuevoTema(
			IVistaForoAsignaturaNuevoTema vistaForoAsignaturaNuevoTema) {
		this.vistaForoAsignaturaNuevoTema = vistaForoAsignaturaNuevoTema;
	}

	public IVistaForoAsignaturaResponderTema getVistaForoAsignaturaResponderTema() {
		return vistaForoAsignaturaResponderTema;
	}

	public void setVistaForoAsignaturaResponderTema(
			IVistaForoAsignaturaResponderTema vistaForoAsignaturaResponderTema) {
		this.vistaForoAsignaturaResponderTema = vistaForoAsignaturaResponderTema;
	}

	public IVistaParticipantes getVistaParticipantes() {
		return vistaParticipantes;
	}

	public void setVistaParticipantes(IVistaParticipantes vistaParticipantes) {
		this.vistaParticipantes = vistaParticipantes;
	}

	public IVistaDetallesParticipantes getVistaDetallesParticipantes() {
		return vistaDetallesParticipantes;
	}

	public void setVistaDetallesParticipantes(
			IVistaDetallesParticipantes vistaDetallesParticipantes) {
		this.vistaDetallesParticipantes = vistaDetallesParticipantes;
	}

	public IVistaGaleriaDocumentacion getVistaGaleriaDocumentacion() {
		return vistaGaleriaDocumentacion;
	}

	public void setVistaGaleriaDocumentacion(
			IVistaGaleriaDocumentacion vistaGaleriaDocumentacion) {
		this.vistaGaleriaDocumentacion = vistaGaleriaDocumentacion;
	}

	public IVistaDocumentacion getVistaDocumentacion() {
		return vistaDocumentacion;
	}

	public void setVistaDocumentacion(IVistaDocumentacion vistaDocumentacion) {
		this.vistaDocumentacion = vistaDocumentacion;
	}

	public IVistaGaleriaTarea getVistaGaleriaTarea() {
		return vistaGaleriaTarea;
	}

	public void setVistaGaleriaTarea(IVistaGaleriaTarea vistaGaleriaTarea) {
		this.vistaGaleriaTarea = vistaGaleriaTarea;
	}

	public IVistaTarea getVistaTarea() {
		return vistaTarea;
	}

	public void setVistaTarea(IVistaTarea vistaTarea) {
		this.vistaTarea = vistaTarea;
	}

	public IVistaNotificacionesForo getVistaNotificacionesForo() {
		return vistaNotificacionesForo;
	}

	public void setVistaNotificacionesForo(
			IVistaNotificacionesForo vistaNotificacionesForo) {
		this.vistaNotificacionesForo = vistaNotificacionesForo;
	}

	public IVistaNotificacionesForoDetalle getVistaNotificacionesForoDetalle() {
		return vistaNotificacionesForoDetalle;
	}

	public void setVistaNotificacionesForoDetalle(
			IVistaNotificacionesForoDetalle vistaNotificacionesForoDetalle) {
		this.vistaNotificacionesForoDetalle = vistaNotificacionesForoDetalle;
	}

	public Class getPreferencias() {
		return preferencias;
	}

	public void setPreferencias(Class preferencias) {
		this.preferencias = preferencias;
	}

	// Métodos de creación y eliminación de presentadores

	public IPresentadorLogin getPresentadorLogin() {
		if (presentadorLogin == null)
			presentadorLogin = new PresentadorLogin();
		return presentadorLogin;
	}

	public void removePresentadorLogin() {
		presentadorLogin = null;
	}

	public IPresentadorAsignaturas getPresentadorAsignaturas() {
		if (presentadorAsignaturas == null)
			presentadorAsignaturas = new PresentadorAsignaturas();
		return presentadorAsignaturas;
	}

	public void removePresentadorAsignaturas() {
		presentadorAsignaturas = null;
	}

	public IPresentadorPaginaAsignatura getPresentadorPaginaAsignatura() {
		if (presentadorPaginaAsignatura == null)
			presentadorPaginaAsignatura = new PresentadorPaginaAsignatura();
		return presentadorPaginaAsignatura;
	}

	public void removePresentadorPaginaAsignatura() {
		presentadorPaginaAsignatura = null;
	}

	public IPresentadorGaleriaDocumentacion getPresentadorGaleriaDocumentacion() {
		if (presentadorGaleriaDocumentacion == null)
			presentadorGaleriaDocumentacion = new PresentadorGaleriaDocumentacion();
		return presentadorGaleriaDocumentacion;
	}

	public void removePresentadorGaleriaDocumentacion() {
		presentadorGaleriaDocumentacion = null;
	}

	public IPresentadorDocumentacion getPresentadorDocumentacion() {
		if (presentadorDocumentacion == null)
			presentadorDocumentacion = new PresentadorDocumentacion();
		return presentadorDocumentacion;
	}

	public void removePresentadorDocumentacion() {
		presentadorDocumentacion = null;
	}

	public IPresentadorGaleriaTarea getPresentadorGaleriaTarea() {
		if (presentadorGaleriaTarea == null)
			presentadorGaleriaTarea = new PresentadorGaleriaTarea();
		return presentadorGaleriaTarea;
	}

	public void removePresentadorGaleriaTarea() {
		presentadorGaleriaTarea = null;
	}

	public IPresentadorTarea getPresentadorTarea() {
		if (presentadorTarea == null)
			presentadorTarea = new PresentadorTarea();
		return presentadorTarea;
	}

	public void removePresentadorTarea() {
		presentadorTarea = null;
	}

	public IPresentadorForoAsignatura getPresentadorForoAsignatura() {
		if (presentadorForoAsignatura == null)
			presentadorForoAsignatura = new PresentadorForoAsignatura();
		return presentadorForoAsignatura;
	}

	public void removePresentadorForoAsignatura() {
		presentadorForoAsignatura = null;
	}

	public IPresentadorForoAsignaturaDetalle getPresentadorForoAsignaturaDetalle() {
		if (presentadorForoAsignaturaDetalle == null)
			presentadorForoAsignaturaDetalle = new PresentadorForoAsignaturaDetalle();
		return presentadorForoAsignaturaDetalle;
	}

	public void removePresentadorForoAsignaturaDetalle() {
		presentadorForoAsignaturaDetalle = null;
	}

	public IPresentadorForoAsignaturaNuevoTema getPresentadorForoAsignaturaNuevoTema() {
		if (presentadorForoAsignaturaNuevoTema == null)
			presentadorForoAsignaturaNuevoTema = new PresentadorForoAsignaturaNuevoTema();
		return presentadorForoAsignaturaNuevoTema;
	}

	public void removePresentadorForoAsignaturaNuevoTema() {
		presentadorForoAsignaturaNuevoTema = null;
	}

	public IPresentadorForoAsignaturaResponderTema getPresentadorForoAsignaturaResponderTema() {
		if (presentadorForoAsignaturaResponderTema == null)
			presentadorForoAsignaturaResponderTema = new PresentadorForoAsignaturaResponderTema();
		return presentadorForoAsignaturaResponderTema;
	}

	public void removePresentadorForoAsignaturaResponderTema() {
		presentadorForoAsignaturaResponderTema = null;
	}

	public IPresentadorParticipantes getPresentadorParticipantes() {
		if (presentadorParticipantes == null)
			presentadorParticipantes = new PresentadorParticipantes();
		return presentadorParticipantes;
	}

	public void removePresentadorParticipantes() {
		presentadorParticipantes = null;
	}

	public IPresentadorDetallesParticipantes getPresentadorDetallesParticipantes() {
		if (presentadorDetallesParticipantes == null)
			presentadorDetallesParticipantes = new PresentadorDetallesParticipantes();
		return presentadorDetallesParticipantes;
	}

	public void removePresentadorDetallesParticipantes() {
		presentadorDetallesParticipantes = null;
	}

	public IPresentadorAyuda getPresentadorAyuda() {
		if (presentadorAyuda == null)
			presentadorAyuda = new PresentadorAyuda();
		return presentadorAyuda;
	}

	public void removePresentadorAyuda() {
		presentadorAyuda = null;
	}

	public IPresentadorNotificacionesForo getPresentadorNotificacionesForo() {
		if (presentadorNotificacionesForo == null)
			presentadorNotificacionesForo = new PresentadorNotificacionesForo();
		return presentadorNotificacionesForo;
	}

	public void removePresentadorNotificacionesForo() {
		presentadorNotificacionesForo = null;
	}

	public IPresentadorNotificacionesForoDetalle getPresentadorNotificacionesForoDetalle() {
		if (presentadorNotificacionesForoDetalle == null)
			presentadorNotificacionesForoDetalle = new PresentadorNotificacionesForoDetalle();
		return presentadorNotificacionesForoDetalle;
	}

	public void removePresentadorNotificacionesForoDetalle() {
		presentadorNotificacionesForoDetalle = null;
	}

	// Métodos accessor del modelo
	public IModelo getModelo() {
		return modelo;
	}

	public void setModelo(IModelo modelo) {
		this.modelo = modelo;
	}

	// Métodos de navegación
	public Class getVistaParaLogin() {
		return VistaLogin.class;
	}

	public Class getVistaParaAsignaturas() {
		return VistaAsignaturas.class;
	}

	public Class getVistaParaAyuda() {
		return VistaAyuda.class;
	}

	public Class getVistaParaPaginaAsignatura() {
		return VistaPaginaAsignatura.class;
	}

	public Class getVistaParaForoAsignatura() {
		return VistaForoAsignatura.class;
	}

	public Class getVistaParaForoAsignaturaDetalle() {
		return VistaForoAsignaturaDetalle.class;
	}

	public Class getVistaParaForoAsignaturaNuevoTema() {
		return VistaForoAsignaturaNuevoTema.class;
	}

	public Class getVistaParaForoAsignaturaResponderTema() {
		return VistaForoAsignaturaResponderTema.class;
	}

	public Class getVistaParaParticipantes() {
		return VistaParticipantes.class;
	}

	public Class getVistaParaDetallesParticipantes() {
		return VistaDetallesParticipantes.class;
	}

	public Class getVistaParaGaleriaDocumentacion() {
		return VistaGaleriaDocumentacion.class;
	}

	public Class getVistaParaDocumentacion() {
		return VistaDocumentacion.class;
	}

	public Class getVistaParaGaleriaTarea() {
		return VistaGaleriaTarea.class;
	}

	public Class getVistaParaTarea() {
		return VistaTarea.class;
	}

	public Class getVistaParaNotificacionesForo() {
		return VistaNotificacionesForo.class;
	}

	public Class getVistaParaNotificacionesForoDetalle() {
		return VistaNotificacionesForoDetalle.class;
	}

	public Class getParaPreferencias() {
		return Preferencias.class;
	}

	public Class getParaServicioNotificaciones() {
		return ServicioNotificaciones.class;
	}

}
