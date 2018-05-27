package adisense.adisense.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import adisense.adisense.Adapter.MenuItemAdapter;
import adisense.adisense.DataHolder.DataHolder;
import adisense.adisense.Localizacion;
import adisense.adisense.R;

public class activity_Profile extends AppCompatActivity {


    TextView name;
    TextView email;
    TextView addres;
    TextView tlf;
    TextView balance;
    ImageView img;


    ListView listView;
    DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private ArrayAdapter<String> adapter;
    ArrayList<String> menuItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__profile);


        //ActionBar Text
        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setTitle("Profile");

        name = (TextView) findViewById(R.id.name);
        email = (TextView) findViewById(R.id.email);
        addres = (TextView) findViewById(R.id.addres);
        tlf = (TextView) findViewById(R.id.tlf);
        balance = (TextView) findViewById(R.id.balance);
        img = (ImageView)  findViewById(R.id.m);


        Picasso.get().load("https://adimeal.000webhostapp.com/Images/chema2.png").into(img);

        name.setText(DataHolder.intance.firstName.toString());
        email.setText(DataHolder.intance.email.toString());
        addres.setText(DataHolder.intance.address.toString());
        tlf.setText(DataHolder.intance.phone + "");
        balance.setText(DataHolder.intance.balance + " Tokens");



        //menu
        listView = (ListView) findViewById(R.id.list_view);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        listView.setDivider(null);
        listView.setDividerHeight(0);


        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
     ;

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        menuItems = new ArrayList<String>();
        menuItems.add("Inicio");
        menuItems.add("Adidas Events");
        menuItems.add("Exchange");
        menuItems.add("Events Map");
        menuItems.add("Ranking");
        menuItems.add("Log out");


        adapter = new MenuItemAdapter(this, menuItems);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()

        {
            @Override
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                //Toast.makeText(ProfileActivity.this, "Item: " + menuItems.get(arg2), Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawers();

                if (menuItems.get(arg2).toString().equals("Inicio")) {


                } else if (menuItems.get(arg2).toString().equals("Adidas Events")) {

                    Intent intent = new Intent(activity_Profile.this, activity_AdidasEvents.class);
                    startActivity(intent);


                } else if (menuItems.get(arg2).toString().equals("Exchange")) {

                    Intent intent = new Intent(activity_Profile.this, activity_Exchange.class);
                    startActivity(intent);


                }else if (menuItems.get(arg2).toString().equals("Events Map")) {

                    Intent intent = new Intent(activity_Profile.this, Localizacion.class);
                    startActivity(intent);


                }else if (menuItems.get(arg2).toString().equals("Ranking")) {
                    Intent intent = new Intent(activity_Profile.this, activity_Ranking.class);
                    startActivity(intent);

                } else if (menuItems.get(arg2).toString().equals("Log out")) {
                    Intent intent = new Intent(activity_Profile.this, activity_Login.class);
                    startActivity(intent);
                    finish();
                }

            }
        });
        //menu


    }


}
