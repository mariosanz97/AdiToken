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

import adisense.adisense.DataHolder.HolderExchange;
import adisense.adisense.R;


public class ExchangeAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<String> idEvento;
    private List<String> nameEvent;
    private List<String> Prize;
    private List<String> Foto;

    public ExchangeAdapter(Context context, List<String> idEvento, List<String> nameEvent, List<String> Prize, List<String> Foto) {
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

        View v = inflater.inflate(R.layout.exchange_item_cell, parent, false);

        HolderExchange holder = new HolderExchange();


        holder.nameEvent = (TextView) v.findViewById(R.id.eventName);
        holder.Prize = (TextView) v.findViewById(R.id.prize);
        holder.img = (ImageView) v.findViewById(R.id.img);

        System.out.println(Foto.get(position));

        holder.nameEvent.setText(nameEvent.get(position));
        holder.Prize.setText(Prize.get(position)+" Tokens");
        Picasso.get().load(Foto.get(position)).into(holder.img);



        return v;

    }
}
