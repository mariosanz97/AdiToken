package adisense.adisense.Activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import adisense.adisense.Adapter.RankingAdapter;
import adisense.adisense.PeticionPostServidorJsonArr;
import adisense.adisense.R;

public class activity_Ranking extends AppCompatActivity implements PeticionPostServidorJsonArr.CallBack {

    PeticionPostServidorJsonArr clientConnection;

    ListView listView2;
    DrawerLayout drawerLayout;
    private ArrayAdapter<String> adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        //ActionBar Text
        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setTitle("Ranking");

        doRequest("https://adimeal.000webhostapp.com/BD/ranking.php");


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


        clientConnection = new PeticionPostServidorJsonArr();
        clientConnection.POST_JSON(nfcUrl, obj, header, this);

    }


    public void OnSuccess(JSONArray Res) throws JSONException {

        JSONObject User = new JSONObject();

        System.out.println("-->" + Res.get(1));

        ArrayList<String> Name = new ArrayList<>();
        ArrayList<String> Puntos = new ArrayList<>();

        for (int i = 1; i < Res.length(); i++) {

            User = Res.getJSONObject(i);
            Name.add(User.getString("FirstName")+" "+ User.get("LastName"));
            Puntos.add(User.getString("BalanceMensual"));

        }


        listView2 = (ListView) findViewById(R.id.lista);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout2);

        listView2.setDivider(null);
        listView2.setDividerHeight(0);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        adapter2 = new RankingAdapter(this, Name, Puntos);
        listView2.setAdapter(adapter2);

    }

    @Override
    public void OnError(String Error) {
        System.out.println("aa" + Error);
    }
}
