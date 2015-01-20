package rgg.campusvirtualapp.vista;

import rgg.campusvirtualapp.AppMediador;
import rgg.campusvirtualapp.presentador.IPresentadorParticipantes;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;

public class VistaParticipantes extends Activity implements
		IVistaParticipantes, SearchView.OnQueryTextListener {
	private AppMediador appMediador;
	private SearchView mSearchView;
	private ListView mListView;
	private ProgressDialog barra;
	private AdaptadorParticipantes adaptadorParticipantes = null;
	private IPresentadorParticipantes presentadorParticipantes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.participantes_vista);
		appMediador = AppMediador.getInstance();
		appMediador.setVistaParticipantes(this);
		presentadorParticipantes = appMediador.getPresentadorParticipantes();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		setContentView(R.layout.participantes_vista);
		mSearchView = (SearchView) findViewById(R.id.searchParticipante);
		mListView = (ListView) findViewById(R.id.listaParticipantes);
		mListView.setTextFilterEnabled(true);
		presentadorParticipantes.obtenerDatos();
		setupSearchView();
		presentadorParticipantes.actualizaPreferencias();
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
			presentadorParticipantes.tratarMenu(1);
			return true;
		}
		if (id == R.id.menu_ayuda) {
			presentadorParticipantes.tratarMenu(2);
			return true;
		}
		if (id == R.id.menu_notificacion) {
			presentadorParticipantes.tratarMenu(3);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void setListaPersonas(Object listaPersonas) {
		adaptadorParticipantes = new AdaptadorParticipantes(this, listaPersonas);
		mListView.setAdapter(adaptadorParticipantes);
		this.mListView
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> padre, View vista,
							int posicion, long id) {
						presentadorParticipantes
								.solicitarDetalles(adaptadorParticipantes
										.getItem(posicion));
					}
				});
	}

	@Override
	public boolean onQueryTextSubmit(String query) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onQueryTextChange(String newText) {
		// TODO Auto-generated method stub
		if (TextUtils.isEmpty(newText)) {
			adaptadorParticipantes.getFilter().filter("");
		} else {
			adaptadorParticipantes.getFilter().filter(newText);
		}
		return true;
	}

	private void setupSearchView() {
		mSearchView.setOnQueryTextListener(this);
		mSearchView.setSubmitButtonEnabled(true);
		mSearchView.setQueryHint(getString(R.string.buscar));
	}

	@Override
	public void mostrarProgreso() {
		barra = new ProgressDialog(this);
		barra.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		barra.setIndeterminate(true);
		barra.setMessage(getString(R.string.mensaje_participantes));
		barra.show();
	}

	@Override
	public void eliminarProgreso() {
		barra.dismiss();
	}

	public void tratarInicio(View v) {
		if (v.getId() == R.id.inicio)
			presentadorParticipantes.volverInicio();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1)
			presentadorParticipantes.actualizaPreferencias();
	}

	@Override
	public void tratarFormato(Object tipo) {
		Formato.cambiarTypeface(this,
				(LinearLayout) this.findViewById(R.id.viewparticipantes), tipo);
	}
}
