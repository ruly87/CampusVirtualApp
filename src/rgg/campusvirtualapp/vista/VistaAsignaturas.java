package rgg.campusvirtualapp.vista;

import java.util.ArrayList;

import rgg.campusvirtualapp.AppMediador;
import rgg.campusvirtualapp.modelo.DatosAsignatura;
import rgg.campusvirtualapp.presentador.IPresentadorAsignaturas;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class VistaAsignaturas extends Activity implements IVistaAsignaturas {

	private AppMediador appMediador;
	private IPresentadorAsignaturas presentadorAsignaturas;
	private ProgressDialog barra;
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		appMediador = AppMediador.getInstance();
		appMediador.setVistaAsignaturas(this);
		presentadorAsignaturas = appMediador.getPresentadorAsignaturas();

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		setContentView(R.layout.asignaturas_vista);
		listView = (ListView) this.findViewById(R.id.listaAsignaturas);
		presentadorAsignaturas.obtenerLista();
		invalidateOptionsMenu();
		super.onStart();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.asignaturas_vista, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		int id = item.getItemId();
		if (id == R.id.menu_configuracion) {
			presentadorAsignaturas.tratarMenu(1);
			return true;
		}
		if (id == R.id.menu_ayuda) {
			presentadorAsignaturas.tratarMenu(2);
			return true;
		}
		if (id == R.id.menu_notificacion) {
			presentadorAsignaturas.tratarMenu(3);
			return true;
		}
		if (id == R.id.menu_salir) {
			presentadorAsignaturas.solicitarSalir();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setListaAsignaturas(Object listaAsignaturas) {
		listView.setAdapter(new AdaptadorAsignaturas(this,
				(ArrayList<DatosAsignatura>) listaAsignaturas));
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> padre, View vista,
					int posicion, long id) {
				presentadorAsignaturas
						.tratarItem((DatosAsignatura) ((AdaptadorAsignaturas) listView
								.getAdapter()).getItem(posicion));
			}
		});
	}

	@Override
	public void crearAlertaSalir(String mensaje) {

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(getString(R.string.alerta));
		builder.setMessage(mensaje);
		builder.setPositiveButton(R.string.si,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						presentadorAsignaturas.accionSalir();
					}
				});
		builder.setNegativeButton(R.string.no,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		builder.create();
		builder.show();
	}

	@Override
	public void mostrarProgreso() {
		barra = new ProgressDialog(this);
		barra.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		barra.setIndeterminate(true);
		barra.setMessage(getString(R.string.mensaje_asignaturas));
		barra.setCancelable(false);
		barra.show();
	}

	@Override
	public void eliminarProgreso() {
		barra.dismiss();
	}

	@Override
	public void crearAlerta(String titulo, String mensaje) {

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(titulo);
		builder.setMessage(mensaje);
		builder.setPositiveButton(R.string.ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		builder.create();
		builder.show();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1)
			presentadorAsignaturas.actualizaPreferencias();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(getString(R.string.alerta));
		builder.setMessage(getString(R.string.deslogear));
		builder.setPositiveButton(R.string.si,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						appMediador.getModelo().cerrarModelo();
						appMediador.stopService(appMediador
								.getParaServicioNotificaciones());
						finish();
					}
				});
		builder.setNegativeButton(R.string.no,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		builder.create();
		builder.show();

	}

	@Override
	public void tratarFormato(Object tipo) {
		Formato.cambiarTypeface(this, listView, tipo);
	}
}
