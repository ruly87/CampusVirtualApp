package rgg.campusvirtualapp.vista;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import rgg.campusvirtualapp.modelo.DatosNotificaciones;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdaptadorNotificacionesForo extends BaseAdapter {

	Context context;
	ArrayList<DatosNotificaciones> data;

	@SuppressWarnings("unchecked")
	public AdaptadorNotificacionesForo(Context context, Object data) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.data = (ArrayList<DatosNotificaciones>) data;

	}

	public class ForoHolder {
		TextView autor;
		TextView asunto;
		TextView hora;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		ForoHolder holder;
		View vi = convertView;

		if (vi == null) {
			vi = LayoutInflater.from(context).inflate(
					R.layout.relative_noti_foro, parent, false);
			holder = new ForoHolder();
			holder.asunto = (TextView) vi.findViewById(R.id.asunto);
			holder.autor = (TextView) vi.findViewById(R.id.autor);
			holder.hora = (TextView) vi.findViewById(R.id.horapublicacion);
			vi.setTag(holder);
		} else {
			holder = (ForoHolder) vi.getTag();
		}

		vi.setId(position);
		holder.asunto.setText(data.get(position).getAsunto());
		holder.autor.setText(context.getString(R.string.autor) + ": "
				+ data.get(position).getAutor());
		holder.hora.setText(context.getString(R.string.publicado)
				+ " "
				+ new StringBuilder(new SimpleDateFormat(
						"dd/MM/yyyy 'de' HH:mm", Locale.UK).format((Date) data
						.get(position).getHorapublicacion())).toString());

		return vi;
	}

}