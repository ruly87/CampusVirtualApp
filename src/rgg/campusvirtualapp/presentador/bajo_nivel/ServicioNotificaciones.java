package rgg.campusvirtualapp.presentador.bajo_nivel;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import rgg.campusvirtualapp.AppMediador;
import rgg.campusvirtualapp.vista.R;
import rgg.campusvirtualapp.modelo.DatosNotificaciones;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;

public class ServicioNotificaciones extends IntentService {
	public ServicioNotificaciones() {
		super("ServicioNotificaciones");
	}

	int id_notificacion = 1;
	private BroadcastReceiver receptorNotificaciones = new BroadcastReceiver() {
		@SuppressWarnings("unchecked")
		@Override
		public void onReceive(Context context, Intent intent) {
			Bundle extras = intent.getExtras();
			if (intent.getAction().equals(
					AppMediador.AVISO_NOTIFICACIONES_NUEVAS)) {
				if (extras != null) {
					ArrayList<DatosNotificaciones> listaNotificaciones = (ArrayList<DatosNotificaciones>) extras
							.getSerializable(AppMediador.DATOS_NOTIFICACIONES);
					for (int i = 0; i < listaNotificaciones.size(); i++) {
						crearNotificacion(context, listaNotificaciones.get(i));
					}
				} else {
				}
			} else {
			}
			AppMediador.getInstance().unRegisterReceiver(this);

		}
	};

	@SuppressWarnings("deprecation")
	private void crearNotificacion(Context contexto, Object noti) {
		AppMediador appMediador = AppMediador.getInstance();
		Bundle extras = new Bundle();
		extras.putSerializable(AppMediador.DATOS_NOTIFICACIONES,
				(DatosNotificaciones) noti);
		Intent intentNotificacion = new Intent(contexto,
				appMediador.getVistaParaNotificacionesForoDetalle());
		intentNotificacion.addFlags(Notification.FLAG_ONGOING_EVENT);
		intentNotificacion.putExtras(extras);
		PendingIntent pendingIntent = PendingIntent.getActivity(contexto,
				id_notificacion, intentNotificacion,
				Intent.FLAG_ACTIVITY_NEW_TASK);
		Notification notificacion = new Notification.Builder(contexto)
				.setContentTitle(((DatosNotificaciones) noti).getAsunto())
				.setContentText(
						contexto.getString(R.string.autor) + " :"
								+ ((DatosNotificaciones) noti).getAutor())
				.setLargeIcon(
						BitmapFactory.decodeResource(
								appMediador.getResources(),
								R.drawable.notificacion)).getNotification();
		notificacion.flags |= Notification.FLAG_AUTO_CANCEL;
		notificacion.flags |= Notification.FLAG_SHOW_LIGHTS;
		notificacion.defaults |= Notification.DEFAULT_LIGHTS;
		notificacion.icon = R.drawable.icono;
		notificacion.contentIntent = pendingIntent;
		NotificationManager notificationManager = (NotificationManager) contexto
				.getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.notify(id_notificacion, notificacion);
		id_notificacion++;
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		Timer temporizador = new Timer();
		temporizador.scheduleAtFixedRate(new TimerTask() {

			synchronized public void run() {
				AppMediador.getInstance().getModelo()
						.obtenerNotificacionesForoNuevas();
				AppMediador
						.getInstance()
						.registerReceiver(
								receptorNotificaciones,
								new String[] { AppMediador.AVISO_NOTIFICACIONES_NUEVAS });
			}

		}, 10000, 1800000);
	}
}
