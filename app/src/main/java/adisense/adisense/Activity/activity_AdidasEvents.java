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

import adisense.adisense.Adapter.AdidasEventAdapter;
import adisense.adisense.DataHolder.AdidasEventHolder;
import adisense.adisense.DataHolder.DataHolder;
import adisense.adisense.PeticionPostServidorJsonArr;
import adisense.adisense.R;

public class activity_AdidasEvents extends AppCompatActivity implements PeticionPostServidorJsonArr.CallBack{

    PeticionPostServidorJsonArr peticionPostServidorJsonArr;
    ListView listView4;
    DrawerLayout drawerLayout4;
    private ArrayAdapter<String> adapter4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__adidas_events);

        //ActionBar Text
        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setTitle("Adidas Events");


        doRequest("https://adimeal.000webhostapp.com/BD/getEvento.php?idUser=" + DataHolder.intance.idUser);






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
            prize.add(User.getString("numToken"));
            foto.add(User.getString("rutaFoto"));
            des.add(User.getString("descripcion"));

        }


        listView4 = (ListView) findViewById(R.id.lista);
        drawerLayout4 = (DrawerLayout) findViewById(R.id.drawer_layout3);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapter4 = new AdidasEventAdapter(this, idEvento, nameEvento, prize, foto);
        listView4.setAdapter(adapter4);


        listView4.setOnItemClickListener(new AdapterView.OnItemClickListener()

        {
            @Override
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                //Toast.makeText(ProfileActivity.this, "Item: " + menuItems.get(arg2), Toast.LENGTH_SHORT).show();
                //drawerLayout3.closeDrawers();

                AdidasEventHolder.intance.id = idEvento.get(arg2);
                AdidasEventHolder.intance.nombre = nameEvento.get(arg2);
                AdidasEventHolder.intance.des = des.get(arg2);
                AdidasEventHolder.intance.foto = foto.get(arg2);
                AdidasEventHolder.intance.precio = prize.get(arg2);


                Intent intent = new Intent(activity_AdidasEvents.this, activity_Event.class);
                startActivity(intent);



            }
        });





    }

    @Override
    public void OnError(String Error) {

    }
}
