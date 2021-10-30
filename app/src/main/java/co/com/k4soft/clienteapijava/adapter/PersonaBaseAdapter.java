package co.com.k4soft.clienteapijava.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import co.com.k4soft.clienteapijava.R;
import co.com.k4soft.clienteapijava.model.Persona;

public class PersonaBaseAdapter extends BaseAdapter implements Filterable {

    private List<Persona> listaPersonasIn;
    private List<Persona> listaPersonasOut;
    private final LayoutInflater inflater;

    public PersonaBaseAdapter(Context context, List<Persona> listaPersonas) {
        this.listaPersonasOut = listaPersonas;
        this.listaPersonasIn = listaPersonas;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return listaPersonasOut.size();
    }

    @Override
    public Object getItem(int position) {
        return listaPersonasOut.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.persona_item, null);
            holder.nombre = convertView.findViewById(R.id.nombre);
            holder.documento = convertView.findViewById(R.id.documento);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.nombre.setText(listaPersonasOut.get(position).getNombre());
        holder.documento.setText(listaPersonasOut.get(position).getNumeroDocumento());
        return convertView;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                listaPersonasOut = (List<Persona>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                List<Persona> FilteredArrList = new ArrayList<>();
                if (listaPersonasIn == null) {
                    listaPersonasIn = new ArrayList<>(listaPersonasOut);
                }
                if (constraint == null || constraint.length() == 0) {
                    results.count = listaPersonasIn.size();
                    results.values = listaPersonasIn;
                } else {

                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < listaPersonasIn.size(); i++) {
                        String data = listaPersonasIn.get(i).getNombre();
                        if (data.toLowerCase().contains(constraint.toString())) {
                            FilteredArrList.add(listaPersonasIn.get(i));
                        }
                    }
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }
        };
        return filter;
    }

    class ViewHolder {

        TextView nombre;
        TextView documento;
    }
}
