package rgg.campusvirtualapp.vista;

import java.util.ArrayList;

import rgg.campusvirtualapp.modelo.DatosParticipantes;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

public class AdaptadorParticipantes extends BaseAdapter implements Filterable {

	public Context context;
	public ArrayList<DatosParticipantes> participanteArrayList;
	public ArrayList<DatosParticipantes> orig;

	@SuppressWarnings("unchecked")
	public AdaptadorParticipantes(Context context, Object participanteArrayList) {
		super();
		this.context = context;
		this.participanteArrayList = (ArrayList<DatosParticipantes>) participanteArrayList;
	}

	public class ParticipantesHolder {
		TextView name;
	}

	@SuppressLint("DefaultLocale")
	public Filter getFilter() {
		return new Filter() {

			@Override
			protected FilterResults performFiltering(CharSequence constraint) {
				final FilterResults oReturn = new FilterResults();
				final ArrayList<DatosParticipantes> results = new ArrayList<DatosParticipantes>();
				if (orig == null)
					orig = participanteArrayList;
				if (constraint != null) {
					if (orig != null && orig.size() > 0) {
						for (final DatosParticipantes g : orig) {
							if (g.getNombre().toLowerCase()
									.contains(constraint.toString()))
								results.add(g);
						}
					}
					oReturn.values = results;
				}
				return oReturn;
			}

			@SuppressWarnings("unchecked")
			@Override
			protected void publishResults(CharSequence constraint,
					FilterResults results) {
				participanteArrayList = (ArrayList<DatosParticipantes>) results.values;
				notifyDataSetChanged();
			}
		};
	}

	public void notifyDataSetChanged() {
		super.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return participanteArrayList.size();
	}

	@Override
	public Object getItem(int position) {
		return participanteArrayList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ParticipantesHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.participante, parent, false);
			holder = new ParticipantesHolder();
			holder.name = (TextView) convertView
					.findViewById(R.id.participante);
			convertView.setTag(holder);
		} else {
			holder = (ParticipantesHolder) convertView.getTag();
		}
		holder.name.setId(position);
		holder.name.setText(participanteArrayList.get(position).getNombre());
		return convertView;

	}

}
