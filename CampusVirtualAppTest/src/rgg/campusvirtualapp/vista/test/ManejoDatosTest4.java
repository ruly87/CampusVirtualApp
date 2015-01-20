package rgg.campusvirtualapp.vista.test;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import rgg.campusvirtualapp.modelo.DatosHilo;
import rgg.campusvirtualapp.modelo.TablaForoAsignatura;
import rgg.campusvirtualapp.vista.VistaLogin;
import android.test.ActivityInstrumentationTestCase2;
import rgg.campusvirtualapp.AppMediador;
import rgg.campusvirtualapp.modelo.TablaAsignaturasMatriculadas;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;


public class ManejoDatosTest4 extends
		ActivityInstrumentationTestCase2<VistaLogin> {
	
	private CountDownLatch contador;

	public ManejoDatosTest4() {
		super(VistaLogin.class);
		contador = new CountDownLatch(1);//Inicializo el contador a uno
	}
	
	@SuppressWarnings("unused")
	protected void setUp() throws Exception {
		super.setUp();
		setActivityInitialTouchMode(false);
		VistaLogin vistaLogin = (VistaLogin)getActivity();
	}
	
	public void testInsertarTema() throws Exception {
		
		DatosHilo hilo = new DatosHilo("Mensaje de prueba", "Raúl García Gaspar", 
0, Calendar.getInstance().getTime(), "Este es un mensaje de prueba para probar la publicación del nuevo tema", 0, 36, 43741, 78718868);
		esperarForo(43741);
		TablaForoAsignatura.publicarTema(hilo);
		contador.await(2000, TimeUnit.MILLISECONDS);//Espera a que el contador llege a 0 o sea interrumpido (¿¿contador.countDown() hasta que contador==0??)
		
	}
	

	public void esperarForo(final int codAsignatura){
		final AppMediador appMediador = AppMediador.getInstance();
		BroadcastReceiver receptorTemas = new BroadcastReceiver() {
			@Override
			public void onReceive(Context contexto, Intent intent) {

				if (intent.getAction().equals(AppMediador.AVISO_TEMA_ENVIADO)) {

					try {
						List<ParseObject> lista = recuperarDatos(codAsignatura);
						//Dado que solo puede haber un idHilo por cada foro asignatura solo puede haber un resultado
						if(lista!=null){
							assertEquals("El id no es el correcto",36, lista.get(0).getNumber(TablaForoAsignatura.CAMPO_ID).intValue());
							assertEquals("El cuerpo no es el correcto", "Este es un mensaje de prueba para probar la publicación del nuevo tema", lista.get(0).getString(TablaForoAsignatura.CAMPO_MENSAJE));
							assertEquals("El asunto no es el correcto", "Mensaje de prueba", lista.get(0).getString(TablaForoAsignatura.CAMPO_ASUNTO));
						}
						else{
							assertNotNull("No se pudo insertar",lista);
						}
						
						contador.countDown();//Decremento el contador
						
					} catch (ParseException e) {
					}
					
				}
				appMediador.unRegisterReceiver(this);
			}
		};

		appMediador.registerReceiver(receptorTemas,
				new String[] { AppMediador.AVISO_TEMA_ENVIADO });

	}
	
	@SuppressWarnings("unchecked")
	public List<ParseObject> recuperarDatos(int codAsignatura) throws ParseException {
		
		@SuppressWarnings("rawtypes")
		ParseQuery query = new ParseQuery(
				TablaForoAsignatura.nombreTabla);
		query.whereEqualTo(TablaForoAsignatura.CAMPO_CODIGO,
				codAsignatura);
		query.whereEqualTo(TablaForoAsignatura.CAMPO_ID, 36);
		query.addAscendingOrder(TablaAsignaturasMatriculadas.CAMPO_USUARIO);
		return query.find();
		
	}
	

}
