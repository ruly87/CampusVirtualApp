package rgg.campusvirtualapp.vista.test;

import rgg.campusvirtualapp.vista.VistaAsignaturas;
import rgg.campusvirtualapp.vista.VistaGaleriaTarea;
import rgg.campusvirtualapp.vista.VistaLogin;
import rgg.campusvirtualapp.vista.VistaPaginaAsignatura;
import rgg.campusvirtualapp.vista.VistaTarea;
import rgg.campusvirtualapp.vista.R;
import android.annotation.SuppressLint;
import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class ManejoDatosTest3 extends
		ActivityInstrumentationTestCase2<VistaLogin> {

	public ManejoDatosTest3() {
		super(VistaLogin.class);
	}
	private VistaLogin vistaLogin;
	private Button iniciarSesion;
	protected void setUp() throws Exception {
		super.setUp();
		setActivityInitialTouchMode(false);
		vistaLogin = (VistaLogin)getActivity();
		iniciarSesion = (Button) vistaLogin.findViewById(R.id.iniciarSesion);
	}
	public void testMostrarTareas() throws Exception {
		final EditText dni = (EditText)vistaLogin.findViewById(R.id.dni_alumno);
		final EditText password = (EditText)vistaLogin.findViewById(R.id.password_alumno);
		vistaLogin.runOnUiThread(new Runnable() {
			@SuppressLint("NewApi")
			@Override
			public void run() {
				dni.setText("1234");
				password.setText("1234");
				iniciarSesion.callOnClick();// se presiona el botón Iniciar sesion
			}
		});
		// se añade un monitor para testear la actividad VistaAsignaturas
		ActivityMonitor monitor = getInstrumentation().addMonitor(VistaAsignaturas.class.getName(), null, false);
		// se espera a que se presente la vista de asignaturas
		VistaAsignaturas vistaAsignaturas = (VistaAsignaturas) getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
		// esperamos 5 sg. porque los datos vienen de la nube
		Thread.sleep(6000);
		assertNotNull("La vista es null", vistaAsignaturas);
		final ListView lista  = (ListView)vistaAsignaturas.findViewById(R.id.listaAsignaturas);
		vistaAsignaturas.runOnUiThread(new Runnable() {
			@SuppressLint("NewApi")
			@Override
			public void run() {
				lista.performItemClick(lista.getChildAt(0), 0, lista.getAdapter().getItemId(0));
			}
		});
		
		// se añade un monitor para testear la actividad VistaAsignaturas
		ActivityMonitor monitorPaginaAsignatura = getInstrumentation().addMonitor(VistaPaginaAsignatura.class.getName(), null, false);
		VistaPaginaAsignatura vistaPaginaAsignatura = (VistaPaginaAsignatura) getInstrumentation().waitForMonitorWithTimeout(monitorPaginaAsignatura, 2000);
		Thread.sleep(5000);
		assertNotNull("La vista es null", vistaPaginaAsignatura);
		final Button tareas = (Button) vistaPaginaAsignatura.findViewById(R.id.boton_tareas);
		vistaPaginaAsignatura.runOnUiThread(new Runnable() {
			@SuppressLint("NewApi")
			@Override
			public void run() {
				tareas.callOnClick();
			}
		});
		ActivityMonitor monitorGaleriaTareas= getInstrumentation().addMonitor(VistaGaleriaTarea.class.getName(), null, false);
		VistaGaleriaTarea vistaGaleriaTarea = (VistaGaleriaTarea) getInstrumentation().waitForMonitorWithTimeout(monitorGaleriaTareas, 2000);
		Thread.sleep(3000);
		assertNotNull("La vista es null", vistaGaleriaTarea);
		LinearLayout linearGaleriaTarea = (LinearLayout)vistaGaleriaTarea.findViewById(R.id.linearGaleriaTarea);
		LinearLayout linearFilaGaleria = (LinearLayout) linearGaleriaTarea.getChildAt(0);
		final Button botonTarea =  (Button) linearFilaGaleria.getChildAt(0).findViewById(0);
		assertEquals("El nombre no de la tarea no es correcto", "Trabajo práctico 2", botonTarea.getText());
		Thread.sleep(3000);
		vistaGaleriaTarea.runOnUiThread(new Runnable() {
			@SuppressLint("NewApi")
			@Override
			public void run() {
				botonTarea.callOnClick();
			}
		});
		ActivityMonitor monitorTareas= getInstrumentation().addMonitor(VistaTarea.class.getName(), null, false);
		VistaTarea vistaTarea = (VistaTarea) getInstrumentation().waitForMonitorWithTimeout(monitorTareas, 2000);
		Thread.sleep(3000);
		assertNotNull("La vista es null", vistaTarea);
		TextView textoDetalles = (TextView) vistaTarea.findViewById(R.id.descripcionTarea);
		//Se comprueba que los detalles de la tarea son correctos
		assertEquals("Los detalles de la tarea no es correcto", "Enlace para subir el pdf del prototipo en Balsamiq  Mockups", textoDetalles.getText());
		
	}
	
	

}
