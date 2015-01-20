package rgg.campusvirtualapp.vista;

import java.util.ArrayList;

import rgg.campusvirtualapp.AppMediador;
import rgg.campusvirtualapp.modelo.DatosNotificaciones;
import rgg.campusvirtualapp.presentador.IPresentadorNotificacionesForo;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class VistaNotificacionesForo extends Activity implements
		IVistaNotificacionesForo {
	private AppMediador appMediador;
	private ProgressDialog barra;
	private ListView notificaciones;
	private IPresentadorNotificacionesForo presentadorNotificacionesForo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notificacionesforo_vista);
		appMediador = AppMediador.getInstance();
		appMediador.setVistaNotificacionesForo(this);
		presentadorNotificacionesForo = appMediador
				.getPresentadorNotificacionesForo();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		setContentView(R.layout.notificacionesforo_vista);
		notificaciones = (ListView) this.findViewById(R.id.listNotiforo);
		presentadorNotificacionesForo.solicitarListaNotificaciones();
		presentadorNotificacionesForo.actualizaPreferencias();
		invalidateOptionsMenu();
		super.onStart();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.submain, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		int id = item.getItemId();
		if (id == R.id.menu_configuracion) {
			presentadorNotificacionesForo.tratarMenu(1);
			return true;
		}
		if (id == R.id.menu_ayuda) {
			presentadorNotificacionesForo.tratarMenu(2);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setListaNotificaciones(Object listaNotificaciones) {
		/**
		 * Asigna la lista de notificaciones del foro recibidos, dentro de dicho
		 * Object almacena el asunto y el nombre del participante que ha
		 * generado la notificación.
		 */
		notificaciones.setAdapter(new AdaptadorNotificacionesForo(this,
				(ArrayList<DatosNotificaciones>) listaNotificaciones));
		notificaciones
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> padre, View vista,
							int posicion, long id) {
						presentadorNotificacionesForo
								.tratarItem((DatosNotificaciones) ((AdaptadorNotificacionesForo) notificaciones
										.getAdapter()).getItem(posicion));
					}
				});
	}

	@Override
	public void mostrarProgreso() {
		barra = new ProgressDialog(this);
		barra.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		barra.setIndeterminate(true);
		barra.setMessage(getString(R.string.mensaje_notificaciones));
		barra.show();
	}

	@Override
	public void eliminarProgreso() {
		barra.dismiss();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1)
			presentadorNotificacionesForo.actualizaPreferencias();
	}

	@Override
	public void tratarFormato(Object tipo) {
		Formato.cambiarTypeface(this, notificaciones, tipo);
	}

}
