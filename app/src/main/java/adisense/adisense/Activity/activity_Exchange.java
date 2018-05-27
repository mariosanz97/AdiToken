package adisense.adisense.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import adisense.adisense.Adapter.ExchangeAdapter;
import adisense.adisense.DataHolder.DataHolder;
import adisense.adisense.DataHolder.EventHolder;
import adisense.adisense.PeticionPostServidorJsonArr;
import adisense.adisense.R;

public class activity_Exchange extends AppCompatActivity implements PeticionPostServidorJsonArr.CallBack{

    PeticionPostServidorJsonArr peticionPostServidorJsonArr;
    ListView listView3;
    DrawerLayout drawerLayout3;
    private ArrayAdapter<String> adapter3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__exchange);

        //ActionBar Text
        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setTitle("Exchange Events");


        doRequest("https://adimeal.000webhostapp.com/BD/getEventoCanjeable.php?idUser=" + DataHolder.intance.idUser);





    }

    private void doRequest(String nfcUrl) {

        System.out.println("LA URL ES ==================>" + nfcUrl);

        JSONObject obj = new JSONObject();
        JSONObject header = new JSONObject();

        try {
            obj.put("", "");
            obj.put("", "");
        } catch (Exception e) {
            e.printStackTrace();
        }


        peticionPostServidorJsonArr = new PeticionPostServidorJsonArr();
        peticionPostServidorJsonArr.POST_JSON(nfcUrl, obj, header, this);

    }

    @Override
    public void OnSuccess(JSONArray Res) throws JSONException {

        ArrayList<String> idEvento = new ArrayList<>();
        ArrayList<String> nameEvento = new ArrayList<>();
        ArrayList<String> prize = new ArrayList<>();
        ArrayList<String> foto = new ArrayList<>();
        ArrayList<String> des = new ArrayList<>();


        JSONObject User = new JSONObject();

        for (int i = 1; i < Res.length(); i++) {

            User = Res.getJSONObject(i);
            idEvento.add(User.getString("idEvento"));
            nameEvento.add(User.getString("nameEvento"));
            prize.add(User.getString("costeEvento"));
            foto.add(User.getString("rutaFoto"));
            des.add(User.getString("descripcion"));

        }


        listView3 = (ListView) findViewById(R.id.lista);
        drawerLayout3 = (DrawerLayout) findViewById(R.id.drawer_layout3);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapter3 = new ExchangeAdapter(this, idEvento, nameEvento, prize, foto);
        listView3.setAdapter(adapter3);


        listView3.setOnItemClickListener(new AdapterView.OnItemClickListener()

        {
            @Override
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                //Toast.makeText(ProfileActivity.this, "Item: " + menuItems.get(arg2), Toast.LENGTH_SHORT).show();
                //drawerLayout3.closeDrawers();

               EventHolder.intance.id = idEvento.get(arg2);
                EventHolder.intance.nombre = nameEvento.get(arg2);
                EventHolder.intance.des = des.get(arg2);
                EventHolder.intance.foto = foto.get(arg2);
                EventHolder.intance.precio = prize.get(arg2);


                Intent intent = new Intent(activity_Exchange.this, activity_EventoEchange.class);
                startActivity(intent);



            }
        });

    }

    @Override
    public void OnError(String Error) {

        System.out.println(Error+"as");

    }
}
