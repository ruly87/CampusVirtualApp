package rgg.campusvirtualapp.vista.test;

import rgg.campusvirtualapp.modelo.DatosParticipantes;
import rgg.campusvirtualapp.vista.AdaptadorParticipantes;
import rgg.campusvirtualapp.vista.VistaAsignaturas;
import rgg.campusvirtualapp.vista.VistaLogin;
import rgg.campusvirtualapp.vista.VistaPaginaAsignatura;
import rgg.campusvirtualapp.vista.VistaParticipantes;
import rgg.campusvirtualapp.vista.R;
import android.annotation.SuppressLint;
import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class ManejoDatosTest2 extends
		ActivityInstrumentationTestCase2<VistaLogin> {

	public ManejoDatosTest2() {
		super(VistaLogin.class);
	}
	private VistaLogin vistaLogin;
	private Button iniciarSesion;
	protected void setUp() throws Exception {
		super.setUp();
		setActivityInitialTouchMode(false);
		vistaLogin = (VistaLogin)getActivity();
		iniciarSesion = (Button)vistaLogin.findViewById(R.id.iniciarSesion);
	}
	public void testMostrarParticipantes() throws Exception {
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
		// esperamos 6 sg. porque los datos vienen de la nube
		Thread.sleep(6000);
		assertNotNull("La vista es null", vistaAsignaturas);
		final ListView lista  = (ListView)vistaAsignaturas.findViewById(R.id.listaAsignaturas);
		vistaAsignaturas.runOnUiThread(new Runnable() {
			@SuppressLint("NewApi")
			@Override
			public void run() {
				lista.performItemClick(lista.getChildAt(0), 0, lista.getAdapter().getItemId(0));

				Log.i("depurador", "he entrado en el run de la lista");
			}
		});
		
		// se añade un monitor para testear la actividad VistaAsignaturas
		ActivityMonitor monitorPaginaAsignatura = getInstrumentation().addMonitor(VistaPaginaAsignatura.class.getName(), null, false);
		VistaPaginaAsignatura vistaPaginaAsignatura = (VistaPaginaAsignatura) getInstrumentation().waitForMonitorWithTimeout(monitorPaginaAsignatura, 2000);
		Thread.sleep(5000);
		assertNotNull("La vista es null", vistaPaginaAsignatura);
		final Button participantes = (Button) vistaPaginaAsignatura.findViewById(R.id.boton_participantes);
		vistaPaginaAsignatura.runOnUiThread(new Runnable() {
			@SuppressLint("NewApi")
			@Override
			public void run() {
				participantes.callOnClick();
			}
		});
		ActivityMonitor monitorParticipantes = getInstrumentation().addMonitor(VistaParticipantes.class.getName(), null, false);
		VistaParticipantes vistaParticipantes = (VistaParticipantes) getInstrumentation().waitForMonitorWithTimeout(monitorParticipantes, 3000);
		Thread.sleep(6000);
		assertNotNull("La vista es null", vistaParticipantes);
		//se obtiene la lista de participantes
		final ListView listaParticipantes = (ListView)vistaParticipantes.findViewById(R.id.listaParticipantes);
		//Se comprueba que la cantidad de participantes es la correcta y en el orden correcto.
		assertEquals("La cantidad no es correcta",8,listaParticipantes.getCount());
		assertEquals("Alumno no es correcto","Alejandro Concepción Hernández", ((DatosParticipantes)((AdaptadorParticipantes)listaParticipantes.getAdapter()).getItem(0)).getNombre());
		assertEquals("Alumno no es correcto","Andrés Marcos Arrowsmith Patiño", ((DatosParticipantes)((AdaptadorParticipantes)listaParticipantes.getAdapter()).getItem(1)).getNombre());
		
		Thread.sleep(2000);
		this.sendKeys(KeyEvent.KEYCODE_BACK);
	}
	
}
