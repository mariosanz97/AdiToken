package adisense.adisense.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

import adisense.adisense.DataHolder.DataHolder;
import adisense.adisense.PeticionPostServidor;
import adisense.adisense.R;

public class activity_Login extends AppCompatActivity implements PeticionPostServidor.CallBack {


    EditText user;
    EditText pass;
    Button btnLogin;
    PeticionPostServidor clientConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__login);

        user = (EditText) findViewById(R.id.user);
        pass = (EditText) findViewById(R.id.pass);
        btnLogin = (Button) findViewById(R.id.btnlogin);


        //Background Image Animation
        ImageView img_animation = (ImageView) findViewById(R.id.bgg);

        TranslateAnimation animation = new TranslateAnimation(0.0f, 400.0f, 0.0f, 0.0f);//  new TranslateAnimation(xFrom,xTo, yFrom,yTo)
        animation.setDuration(17000);  // animation duration
        animation.setRepeatCount(500);  // animation repeat count
        animation.setRepeatMode(2);   // repeat animation (left to right, right to left )
        img_animation.startAnimation(animation);


        //ActionBar Text
        //For hiding android actionbar
        ActionBar myActionBar = getSupportActionBar();
        myActionBar.hide();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
                JSONObject datos = new JSONObject();
                JSONObject x = new JSONObject();
                try {
                    datos.put("email", "mario.s_97@hotmail.com");
                    datos.put("password", "albondiga");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                String url = "http://adimeal.000webhostapp.com/BD/loginBD.php";

                PeticionPostServidor.POST_JSON(url, datos, x, activity_Login.this);
                System.out.println("Enviado");

*/
                doRequest("https://adimeal.000webhostapp.com/BD/loginBD.php?email=" + user.getText() + "&password=" + pass.getText());


            }
        });

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


        clientConnection = new PeticionPostServidor();
        clientConnection.POST_JSON(nfcUrl, obj, header, this);

    }

    @Override
    public void OnSuccess(JSONObject Res) throws JSONException {

        System.out.println("------>" + Res.toString());


        DataHolder.intance.idUser = Integer.parseInt(Res.getString("idUsuarios"));
        DataHolder.intance.email = Res.getString("Email");
        DataHolder.intance.firstName = Res.getString("FirstName");
        DataHolder.intance.lastName = Res.getString("LastName");
        DataHolder.intance.phone = Integer.parseInt(Res.getString("Phone"));
        DataHolder.intance.address = Res.getString("Address");
        DataHolder.intance.balance = Integer.parseInt(Res.getString("Balance"));


        Intent intent = new Intent(getBaseContext(), activity_Profile.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void OnError(String Error) {
        System.out.println("---ERR--->" + Error);
    }
}
