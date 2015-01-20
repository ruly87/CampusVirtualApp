package rgg.campusvirtualapp.vista.test;

import rgg.campusvirtualapp.modelo.DatosAsignatura;
import rgg.campusvirtualapp.vista.AdaptadorAsignaturas;
import rgg.campusvirtualapp.vista.VistaAsignaturas;
import rgg.campusvirtualapp.vista.VistaLogin;
import rgg.campusvirtualapp.vista.R;
import android.annotation.SuppressLint;
import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class ManejoDatosTest1 extends
		ActivityInstrumentationTestCase2<VistaLogin> {

	public ManejoDatosTest1() {
		super(VistaLogin.class);
	}
	private VistaLogin vistaLogin;
	private Button iniciarSesion;
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		setActivityInitialTouchMode(false);
		vistaLogin = (VistaLogin)getActivity();
		iniciarSesion = (Button)vistaLogin.findViewById(R.id.iniciarSesion);
	}
	public void testMostrarAsignaturas() throws Exception {
		final EditText dni = (EditText)vistaLogin.findViewById(R.id.dni_alumno);
		final EditText password = (EditText)vistaLogin.findViewById(R.id.password_alumno);
		vistaLogin.runOnUiThread(new Runnable() {
			@SuppressLint("NewApi")
			@Override
			public void run() {
				dni.setText("1234");
				password.setText("1234");
				iniciarSesion.callOnClick();
			}
		});
		// se presiona el botón Iniciar sesion
		
		// se añade un monitor para testear la actividad VistaAsignaturas
		ActivityMonitor monitor = getInstrumentation().addMonitor(VistaAsignaturas.class.getName(), null, false);
		// se espera a que se presente la vista de asignaturas
		VistaAsignaturas vistaAsignaturas = (VistaAsignaturas) getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
		// esperamos 6 sg. porque los datos vienen de la nube
		Thread.sleep(6000);
		assertNotNull("La vista es null", vistaAsignaturas);
		// si llega aquí, la vista asignaturas está en foreground. Si falla la línea anterior, aumentar el tiempo
		ListView lista = (ListView)vistaAsignaturas.findViewById(R.id.listaAsignaturas);
		// comprueba que la lista está en la pantalla
		ViewAsserts.assertOnScreen(vistaAsignaturas.getWindow().getDecorView(),lista);
		// comprueba que tiene 2 elementos y si están en el orden esperado
		assertEquals("La cantidad no es correcta",2,lista.getCount());
		assertEquals("Asignatura 0 no es correcta","Programación en Entornos Multidispositivos", ((DatosAsignatura)((AdaptadorAsignaturas)lista.getAdapter()).getItem(0)).getNombre());
		assertEquals("Asignatura 1 no es correcta","Programación Web", ((DatosAsignatura)((AdaptadorAsignaturas)lista.getAdapter()).getItem(1)).getNombre());
	}
	

}
