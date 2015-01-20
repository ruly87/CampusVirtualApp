package rgg.campusvirtualapp.vista;

import java.util.ArrayList;

import rgg.campusvirtualapp.AppMediador;
import rgg.campusvirtualapp.modelo.DatosHilo;
import rgg.campusvirtualapp.presentador.IPresentadorForoAsignatura;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

public class VistaForoAsignatura extends Activity implements
		IVistaForoAsignatura, OnClickListener {
	private AppMediador appMediador;
	private Button nuevoTema;
	private ListView foro;
	private IPresentadorForoAsignatura presentadorForoAsignatura;
	private ProgressDialog barra;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.foroasignatura_vista);
		appMediador = AppMediador.getInstance();
		appMediador.setVistaForoAsignatura(this);
		presentadorForoAsignatura = appMediador.getPresentadorForoAsignatura();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		setContentView(R.layout.foroasignatura_vista);
		foro = (ListView) this.findViewById(R.id.listforo);
		nuevoTema = (Button) this.findViewById(R.id.nuevotema);
		nuevoTema.setOnClickListener(this);
		presentadorForoAsignatura.obtenerListaForo();
		presentadorForoAsignatura.actualizaPreferencias();
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
			presentadorForoAsignatura.tratarMenu(1);
			return true;
		}
		if (id == R.id.menu_ayuda) {
			presentadorForoAsignatura.tratarMenu(2);
			return true;
		}
		if (id == R.id.menu_notificacion) {
			presentadorForoAsignatura.tratarMenu(3);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setListaForo(Object listaForo) {
		foro.setAdapter(new AdaptadorForo(this,
				(ArrayList<DatosHilo>) listaForo));
		foro.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> padre, View vista,
					int posicion, long id) {
				presentadorForoAsignatura.tratarItem(((DatosHilo) ((AdaptadorForo) foro
						.getAdapter()).getItem(posicion)).getPosicion());
			}
		});
	}
	@Override
	public void onClick(View v) {
		if (v.equals(nuevoTema))
			presentadorForoAsignatura.tratarItem(-1);

	}

	@Override
	public void mostrarProgreso() {
		barra = new ProgressDialog(this);
		barra.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		barra.setIndeterminate(true);
		barra.setMessage(getString(R.string.mensaje_foro));
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
			presentadorForoAsignatura.actualizaPreferencias();
	}

	@Override
	public void tratarFormato(Object tipo) {
		Formato.cambiarTypeface(this,
				(LinearLayout) this.findViewById(R.id.viewforo), tipo);
	}

}
