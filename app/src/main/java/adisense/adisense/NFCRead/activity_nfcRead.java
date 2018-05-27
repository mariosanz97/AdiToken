package adisense.adisense.NFCRead;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import adisense.adisense.Activity.activity_Profile;
import adisense.adisense.DataHolder.DataHolder;
import adisense.adisense.DataHolder.EventHolder;
import adisense.adisense.PeticionPostServidor;
import adisense.adisense.R;

public class activity_nfcRead extends AppCompatActivity implements PeticionPostServidor.CallBack {

    public static final String TAG = activity_nfcRead.class.getSimpleName();
    public NfcAdapter mNfcAdapter;

    PeticionPostServidor PeticionPostServidor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc_read);


        //For hiding android actionbar
        ActionBar myActionBar = getSupportActionBar();
        myActionBar.hide();

        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);

        if (mNfcAdapter == null) {
            // Stop here, we definitely need NFC
            //Toast.makeText(this, "This device doesn't support NFC.", Toast.LENGTH_LONG).show();
            finish();
            return;

        }


        if (!mNfcAdapter.isEnabled()) {
            System.out.println("no enable");
        } else {
            System.out.println("enable");
            //onNewIntent(getIntent());
        }


    }


    @Override
    protected void onNewIntent(Intent intent) {

        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

        Log.d(TAG, "onNewIntent: " + intent.getAction());
        // TODO: handle Intent


        // Toast.makeText(this, getString(R.string.message_tag_detected), Toast.LENGTH_SHORT).show();
        Ndef ndef = Ndef.get(tag);
        System.out.println(tag);

        onNfcDetected(ndef);

    }

    public void onNfcDetected(Ndef ndef) {
        readFromNFC(ndef);
    }


    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        IntentFilter ndefDetected = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
        IntentFilter techDetected = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);
        IntentFilter[] nfcIntentFilter = new IntentFilter[]{techDetected, tagDetected, ndefDetected};
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);


        if (mNfcAdapter != null) {
            System.out.println("NfcAdapter no es null  --> " + mNfcAdapter);
            mNfcAdapter.enableForegroundDispatch(this, pendingIntent, nfcIntentFilter, null);
        } else {
            System.out.println("NfcAdapter es null");
        }


    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mNfcAdapter != null)
            mNfcAdapter.disableForegroundDispatch(this);
    }


    private void readFromNFC(Ndef ndef) {

        try {

            if (ndef == null) {
                System.out.println("ndef esta nulo");
            } else {
                ndef.connect();
                NdefMessage ndefMessage = ndef.getNdefMessage();
                String message = new String(ndefMessage.getRecords()[0].getPayload());

                System.out.println("MENSAJE RECIBIDO:----" + message.trim());

                if (message.trim().equals(EventHolder.intance.id)) {
                    doRequest("https://adimeal.000webhostapp.com/BD/setPoints.php?idUser=" + DataHolder.intance.idUser + "&idEvento=" + message.trim());
                } else {
                    Toast.makeText(this, "Not this event", Toast.LENGTH_SHORT).show();
                }


                ndef.close();
            }


        } catch (IOException | FormatException e) {
            System.out.println("exception");
            e.printStackTrace();

        }
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


        PeticionPostServidor = new PeticionPostServidor();
        PeticionPostServidor.POST_JSON(nfcUrl, obj, header, this);

    }


    @Override
    public void OnSuccess(JSONObject Res) throws JSONException {

        System.out.println("sc" + Res);


        String mensaje = Res.getString("balance");

        if (mensaje.equals("0")) {
            Toast.makeText(this, "Puntos ya Conseguidos!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Tokens añadidos: " + mensaje, Toast.LENGTH_SHORT).show();
            DataHolder.intance.balance = Integer.parseInt(mensaje.toString());

        }

        Intent intent = new Intent(activity_nfcRead.this, activity_Profile.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void OnError(String Error) {
        System.out.println("NScs " + Error);
    }
}
