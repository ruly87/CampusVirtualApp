package rgg.campusvirtualapp.vista;

import java.util.ArrayList;

import rgg.campusvirtualapp.AppMediador;
import rgg.campusvirtualapp.presentador.IPresentadorForoAsignaturaDetalle;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

public class VistaForoAsignaturaDetalle extends Activity implements
		IVistaForoAsignaturaDetalle, OnClickListener {
	private AppMediador appMediador;
	private Button responderTema;
	private IPresentadorForoAsignaturaDetalle presentadorForoAsignaturaDetalle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.foroasignaturadetalle_vista);
		appMediador = (AppMediador) getApplication();
		appMediador.setVistaForoAsignaturaDetalle(this);
		presentadorForoAsignaturaDetalle = appMediador
				.getPresentadorForoAsignaturaDetalle();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		setContentView(R.layout.foroasignaturadetalle_vista);
		presentadorForoAsignaturaDetalle.tratarHilo(getIntent());
		responderTema = (Button) this.findViewById(R.id.respondertema);
		responderTema.setOnClickListener(this);
		presentadorForoAsignaturaDetalle.actualizaPreferencias();
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
			presentadorForoAsignaturaDetalle.tratarMenu(1);
			return true;
		}
		if (id == R.id.menu_ayuda) {
			presentadorForoAsignaturaDetalle.tratarMenu(2);
			return true;
		}
		if (id == R.id.menu_notificacion) {
			presentadorForoAsignaturaDetalle.tratarMenu(3);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setListaHilo(Object listaForo) {
		((ListView) this.findViewById(R.id.ForoAsignaturaHilo))
				.setAdapter(new AdaptadorForoDetalles(this,
						(ArrayList<LinearLayout>) listaForo));
	}

	@Override
	public void onClick(View v) {
		if (v.equals(responderTema))
			presentadorForoAsignaturaDetalle.tratarItem();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1)
			presentadorForoAsignaturaDetalle.actualizaPreferencias();
	}

	@Override
	public void tratarFormato(Object tipo) {
		Formato.cambiarTypeface(this,
				(LinearLayout) this.findViewById(R.id.ViewForoDetalles), tipo);
	}

}
