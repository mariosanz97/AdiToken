package adisense.adisense.Adapter;

/**
 * Created by mario on 11/05/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import adisense.adisense.DataHolder.ViewHolder;
import adisense.adisense.R;


public class RankingAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<String> Name;
    private List<String> Puntos;
    int cont;


    public RankingAdapter(Context context, List<String> Name, List<String> Puntos) {
        super(context, 0);
        this.Name = Name;
        this.context = context;
        this.Puntos = Puntos;
    }

    @Override
    public int getCount() {
        return Name.size();
    }

    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.ranking_item_cell, parent, false);

        adisense.adisense.DataHolder.ViewHolder holder = new ViewHolder();


        System.out.println("---salmon->"+Name.get(position));

        holder.line = (LinearLayout) v.findViewById(R.id.line);
        holder.numm = (TextView) v.findViewById(R.id.num);
        holder.namee = (TextView) v.findViewById(R.id.itemName);
        holder.balancee = (TextView) v.findViewById(R.id.balance);

        if (position==0){
            holder.line.setBackgroundColor(Color.parseColor("#ffbd00"));
        }else if(position==1){
            holder.line.setBackgroundColor(Color.parseColor("#C0C0C0"));
        }else if(position==2){
            holder.line.setBackgroundColor(Color.parseColor("#CD7F32"));
        }else{
            holder.line.setBackgroundColor(Color.parseColor("#ffffff"));
        }

        holder.numm.setText(position+1+"");
        holder.namee.setText(  Name.get(position));
        holder.balancee.setText( Puntos.get(position));


        return v;

    }
}
