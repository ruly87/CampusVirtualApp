package rgg.campusvirtualapp.vista;

import java.util.ArrayList;

import rgg.campusvirtualapp.AppMediador;
import rgg.campusvirtualapp.presentador.IPresentadorDocumentacion;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class VistaDocumentacion extends Activity implements
		IVistaDocumentacion, OnClickListener {
	private AppMediador appMediador;
	private IPresentadorDocumentacion presentadorDocumentacion;
	private LinearLayout doc;
	private ProgressDialog barra;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.documentacion_vista);
		appMediador = AppMediador.getInstance();
		appMediador.setVistaDocumentacion(this);
		presentadorDocumentacion = appMediador.getPresentadorDocumentacion();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		setContentView(R.layout.documentacion_vista);
		doc = (LinearLayout) this.findViewById(R.id.linearDocumentacion);
		presentadorDocumentacion.pedirNombresArchivos(getIntent());
		this.findViewById(R.id.inicio).setOnClickListener(this);
		presentadorDocumentacion.actualizaPreferencias();
		invalidateOptionsMenu();
		super.onStart();
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
			presentadorDocumentacion.tratarMenu(1);
			return true;
		}
		if (id == R.id.menu_ayuda) {
			presentadorDocumentacion.tratarMenu(2);
			return true;
		}
		if (id == R.id.menu_notificacion) {
			presentadorDocumentacion.tratarMenu(3);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setListaArchivos(Object listaArchivos) {
		for (int i = 0; i < ((ArrayList<LinearLayout>) listaArchivos).size(); i++)
			doc.addView(((ArrayList<LinearLayout>) listaArchivos).get(i));

	}

	@Override
	public void crearAlerta(final int identificador) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(getString(R.string.titulo_alerta_documentacion));
		builder.setPositiveButton(getString(R.string.ver),
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						appMediador.getPresentadorDocumentacion()
								.tratarAccionVer(identificador);
					}
				});
		builder.setNegativeButton(getString(R.string.descargar),
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						appMediador.getPresentadorDocumentacion()
								.tratarAccionDescargar(identificador);
					}
				});
		builder.create();
		builder.show();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.equals(this.findViewById(R.id.inicio))) {
			appMediador.getPresentadorDocumentacion().volverInicio();
		}
	}

	@Override
	public void tratarBoton(View v) {
		crearAlerta(v.getId());
	}

	@Override
	public void mostrarProgreso(String mensaje) {
		barra = new ProgressDialog(this);
		barra.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		barra.setIndeterminate(true);
		barra.setMessage(mensaje);
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
			presentadorDocumentacion.actualizaPreferencias();
	}

	@Override
	public void tratarFormato(Object tipo) {
		Formato.cambiarTypeface(this,
				(LinearLayout) this.findViewById(R.id.viewdocumentacion), tipo);
	}

}
