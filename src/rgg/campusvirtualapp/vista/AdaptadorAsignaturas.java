package rgg.campusvirtualapp.vista;

import java.util.ArrayList;

import rgg.campusvirtualapp.modelo.DatosAsignatura;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdaptadorAsignaturas extends BaseAdapter {

	Context context;
	ArrayList<DatosAsignatura> data;
	private static LayoutInflater inflater = null;

	public AdaptadorAsignaturas(Context context, ArrayList<DatosAsignatura> data) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.data = data;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
		View vi = convertView;
		if (vi == null)
			vi = inflater.inflate(R.layout.fila_asignaturas, null);
		TextView text = (TextView) vi.findViewById(R.id.textoAsignatura);
		text.setText(data.get(position).getNombre());
		return vi;
	}

}