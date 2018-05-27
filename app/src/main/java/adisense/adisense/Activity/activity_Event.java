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

import adisense.adisense.DataHolder.AdidasEventHolder;
import adisense.adisense.R;

public class activity_Event extends AppCompatActivity {

    TextView nombre;
    TextView precio;
    Button btnNFC;
    TextView Descripcion;
    ImageView Foto;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__event);

        //ActionBar Text
        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setTitle("Events");

        nombre = (TextView) findViewById(R.id.eventName);
        precio = (TextView) findViewById(R.id.prize);
        btnNFC = (Button) findViewById(R.id.btnNFC);
        Descripcion = (TextView) findViewById(R.id.Des);
        Foto = (ImageView) findViewById(R.id.img);

        System.out.println("aaaaaaa"+AdidasEventHolder.intance.nombre);

        nombre.setText(AdidasEventHolder.intance.nombre);
        precio.setText(AdidasEventHolder.intance.precio);
        Descripcion.setText(AdidasEventHolder.intance.des);

        Picasso.get().load(AdidasEventHolder.intance.foto).into(Foto);


        btnNFC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity_Event.this, adisense.adisense.NFCRead.activity_nfcRead.class);
                startActivity(intent);

            }
        });


    }
}
