package adisense.adisense.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import adisense.adisense.DataHolder.MenuItemViewHolder;
import adisense.adisense.R;


public class MenuItemAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<String> items;

    public MenuItemAdapter(Context context, List<String> menuItems) {
        super(context, 0, menuItems);
        this.items = menuItems;
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }


    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.menu_item_cell, parent, false);

        adisense.adisense.DataHolder.MenuItemViewHolder holder = new MenuItemViewHolder();


        holder.Nombre = (TextView) v.findViewById(R.id.itemName);
        holder.Nombre.setText(items.get(position));

        holder.img = (ImageView) v.findViewById(R.id.img);

        if (items.get(position).equals("Inicio")) {
            holder.img.setImageResource(R.mipmap.home);
        }if (items.get(position).equals("Events Map")) {
            holder.img.setImageResource(R.mipmap.find);
        }if (items.get(position).equals("Adidas Events")) {
            holder.img.setImageResource(R.mipmap.user);
        }if (items.get(position).equals("Ranking")) {
            holder.img.setImageResource(R.mipmap.winner);
        }else if (items.get(position).equals("Exchange")) {
            holder.img.setImageResource(R.mipmap.training);
        }else if (items.get(position).equals("Log out")) {
            holder.img.setImageResource(R.mipmap.exit);
        }


        return v;

    }
}
