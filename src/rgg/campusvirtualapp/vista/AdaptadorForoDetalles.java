package rgg.campusvirtualapp.vista;

import java.util.ArrayList;

import rgg.campusvirtualapp.modelo.DatosHilo;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdaptadorForoDetalles extends BaseAdapter {

	Context context;
	ArrayList<DatosHilo> data;

	// private static LayoutInflater inflater = null;

	@SuppressWarnings("unchecked")
	public AdaptadorForoDetalles(Context context, Object data) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.data = (ArrayList<DatosHilo>) data;
		// inflater = (LayoutInflater) context
		// .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public class ForoDetalleHolder {
		TextView autor;
		TextView asunto;
		TextView mensaje;
		TextView respuesta;
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

		ForoDetalleHolder holder;
		View vi = convertView;
		if (vi == null) {
			vi = LayoutInflater.from(context).inflate(
					R.layout.foroasignaturadetalles_respuesta, parent, false);
			holder = new ForoDetalleHolder();
			holder.asunto = (TextView) vi.findViewById(R.id.asunto);
			holder.autor = (TextView) vi.findViewById(R.id.remitente);
			holder.mensaje = (TextView) vi.findViewById(R.id.mensaje);
			holder.respuesta = (TextView) vi.findViewById(R.id.TVRespuestas);
			vi.setTag(holder);
		}

		else {
			holder = (ForoDetalleHolder) vi.getTag();
		}
		if (getCount() == (position + 1)) {
			vi.setId(data.get(position).getPosicion());
			holder.asunto.setText(data.get(position).getAsunto());
			holder.autor.setText(data.get(position).getAutor());
			holder.mensaje.setText(data.get(position).getMensaje());
			holder.respuesta.setText("");
		} else {
			vi.setId(data.get(position).getPosicion());
			holder.asunto.setText(data.get(position).getAsunto());
			holder.autor.setText(data.get(position).getAutor());
			holder.mensaje.setText(data.get(position).getMensaje());
		}
		return vi;
	}

}