package rgg.campusvirtualapp.vista;

import rgg.campusvirtualapp.AppMediador;
import rgg.campusvirtualapp.presentador.IPresentadorNotificacionesForoDetalle;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

public class VistaNotificacionesForoDetalle extends Activity implements
		IVistaNotificacionesForoDetalle {
	private AppMediador appMediador;
	private IPresentadorNotificacionesForoDetalle presentadorNotificacionesForoDetalle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notificacionesforodetalle_vista);
		appMediador = AppMediador.getInstance();
		appMediador.setVistaNotificacionesForoDetalle(this);
		presentadorNotificacionesForoDetalle = appMediador
				.getPresentadorNotificacionesForoDetalle();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		setContentView(R.layout.notificacionesforodetalle_vista);
		presentadorNotificacionesForoDetalle.tratarItem(getIntent());
		presentadorNotificacionesForoDetalle.actualizaPreferencias();
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
			presentadorNotificacionesForoDetalle.tratarMenu(1);
			return true;
		}
		if (id == R.id.menu_ayuda) {
			presentadorNotificacionesForoDetalle.tratarMenu(2);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void setNotificacion(String mensaje, String autor, String asunto) {
		/**
		 * Asigna el cuerpo del mensaje de la notificación.
		 */
		((TextView) this.findViewById(R.id.mensaje)).setText(mensaje);
		((TextView) this.findViewById(R.id.remitente)).setText(autor);
		((TextView) this.findViewById(R.id.asunto)).setText(asunto);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1)
			presentadorNotificacionesForoDetalle.actualizaPreferencias();
	}

	@Override
	public void tratarFormato(Object tipo) {
		Formato.cambiarTypeface(this,
				(LinearLayout) this.findViewById(R.id.viewnotiforodetalle),
				tipo);
	}
}
