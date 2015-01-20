package rgg.campusvirtualapp.vista;

import java.io.File;

import rgg.campusvirtualapp.AppMediador;
import rgg.campusvirtualapp.presentador.IPresentadorTarea;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class VistaTarea extends Activity implements IVistaTarea,
		OnClickListener {
	private AppMediador appMediador;
	private TextView textoRuta;
	private File archivo;
	private ProgressDialog barra;
	private IPresentadorTarea presentadorTarea;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		appMediador = AppMediador.getInstance();
		appMediador.setVistaTarea(this);
		setContentView(R.layout.tarea_vista);
		presentadorTarea = appMediador.getPresentadorTarea();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		setContentView(R.layout.tarea_vista);
		this.findViewById(R.id.inicio).setOnClickListener(this);
		textoRuta = (TextView) this.findViewById(R.id.textoruta);
		this.findViewById(R.id.boton_enviar).setOnClickListener(this);
		presentadorTarea.tratarTarea(getIntent());
		;
		invalidateOptionsMenu();
		archivo = null;
		presentadorTarea.actualizaPreferencias();
		invalidateOptionsMenu();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		int id = item.getItemId();
		if (id == R.id.menu_configuracion) {
			presentadorTarea.tratarMenu(1);
			return true;
		}
		if (id == R.id.menu_ayuda) {
			presentadorTarea.tratarMenu(2);
			return true;
		}
		if (id == R.id.menu_notificacion) {
			presentadorTarea.tratarMenu(3);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void setTextoDetalles(String textoDetalle) {
		((TextView) this.findViewById(R.id.descripcionTarea))
				.setText(textoDetalle);
	}

	@Override
	public void crearAlerta(String mensaje) {
		/**
		 * 
		 */
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(mensaje);
		builder.setNeutralButton(getString(R.string.ok),
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		builder.create();
		builder.show();

	}

	@Override
	public void onClick(View v) {
		if (v.equals(this.findViewById(R.id.inicio)))
			presentadorTarea.volverInicio();
		if (v.equals(this.findViewById(R.id.boton_enviar)))
			presentadorTarea.tratarAccion(archivo);
	}

	/**
	 * 
	 * @param v
	 */
	public void tratarExaminar(View v) {
		presentadorTarea.tratarAccion();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		switch (requestCode) {
		case 1:
			presentadorTarea.actualizaPreferencias();
			break;
		case 2:
			if (resultCode == RESULT_OK) {
				// Obtengo la dirección del archivo seleccionado
				Uri uri = data.getData();
				// Obtengo el nombre del fichero y lo pongo en la vista
				textoRuta.setText((uri.getPathSegments()).get((uri
						.getPathSegments()).size() - 1));
				// Almaceno el file para posteriormente subirlo
				archivo = new File(uri.getPath());
			}
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void mostrarProgreso() {
		barra = new ProgressDialog(this);
		barra.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		barra.setIndeterminate(true);
		barra.setCancelable(false);
		barra.setMessage(getString(R.string.mensaje_tarea));
		barra.show();
	}

	@Override
	public void eliminarProgreso() {
		barra.dismiss();
	}

	@Override
	public void tratarFormato(Object tipo) {
		Formato.cambiarTypeface(this,
				(LinearLayout) this.findViewById(R.id.viewtarea), tipo);
	}

}
