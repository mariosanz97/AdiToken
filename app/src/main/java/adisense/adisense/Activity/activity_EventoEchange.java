package adisense.adisense.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import adisense.adisense.DataHolder.EventHolder;
import adisense.adisense.R;

public class activity_EventoEchange extends AppCompatActivity {


    TextView nombre;
    TextView precio;
    Button btnNFC;
    TextView Descripcion;
    ImageView Foto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__evento);

        //ActionBar Text
        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setTitle("Exchange");

        nombre = (TextView) findViewById(R.id.eventName);
        precio = (TextView) findViewById(R.id.prize);
        btnNFC = (Button) findViewById(R.id.btnNFC);
        Descripcion = (TextView) findViewById(R.id.Des);
        Foto = (ImageView) findViewById(R.id.img);


        nombre.setText(EventHolder.intance.nombre);
        precio.setText(EventHolder.intance.precio);
        Descripcion.setText(EventHolder.intance.des);

        Picasso.get().load(EventHolder.intance.foto).into(Foto);


        btnNFC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity_EventoEchange.this, adisense.adisense.NFCRead.activity_nfcEvento.class);
                startActivity(intent);

            }
        });

    }
}
