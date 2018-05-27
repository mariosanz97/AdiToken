package adisense.adisense.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import adisense.adisense.DataHolder.HolderEventadidas;
import adisense.adisense.R;


public class AdidasEventAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<String> idEvento;
    private List<String> nameEvent;
    private List<String> Prize;
    private List<String> Foto;

    public AdidasEventAdapter(Context context, List<String> idEvento, List<String> nameEvent, List<String> Prize, List<String> Foto) {
        super(context, 0);
        this.idEvento = idEvento;
        this.context = context;
        this.nameEvent = nameEvent;
        this.Prize = Prize;
        this.Foto = Foto;
    }

    @Override
    public int getCount() {
        return idEvento.size();
    }


    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.adidasevent_item_cell, parent, false);

        HolderEventadidas holder = new HolderEventadidas();


        holder.nombre = (TextView) v.findViewById(R.id.eventName);
        holder.precio = (TextView) v.findViewById(R.id.prize);
        holder.foto = (ImageView) v.findViewById(R.id.img);

        holder.nombre.setText(nameEvent.get(position));
        holder.precio.setText(Prize.get(position)+" Tokens");
        Picasso.get().load(Foto.get(position)).into(holder.foto);



        return v;

    }
}
