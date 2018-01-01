package ie.itvolks.danielsz.projectattila;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pManager;
import android.nfc.NfcAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;



public class MainActivity extends AppCompatActivity {

    //Created adaptors to query system
    NfcAdapter mNfcAdapter;
    BluetoothAdapter mBtAdapter;
    WifiManager mWifiManager;
    WifiP2pManager mWifiP2pManager;

    //Static log entry header
    public static final String logTag = "ATTILA";

    //Used to get handles to the toggle buttons
    ToggleButton toggleButtonNFC;
    ToggleButton toggleButtonBT;
    ToggleButton toggleButtonWiFI;
    ToggleButton toggleButtonWiFIP2P;
    TextView testField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toggleButtonNFC = findViewById(R.id.toggleButtonNFC);
        toggleButtonBT = findViewById(R.id.toggleButtonBT);
        toggleButtonWiFI = findViewById(R.id.toggleButtonWIFI);
        toggleButtonWiFIP2P = findViewById(R.id.toggleButtonWIFIP2P);
        testField = findViewById(R.id.testField);

        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        mBtAdapter = BluetoothAdapter.getDefaultAdapter();


        //Check states of adaptors and set UI accordingly
        try {

            //NFC
            if (!mNfcAdapter.isEnabled()) {
                Toast.makeText(this, "NFC is NOT enabled", Toast.LENGTH_LONG).show();
                toggleButtonNFC.setChecked(false);
            }

            if (mNfcAdapter.isEnabled()) {
                Toast.makeText(this, "NFC is enabled", Toast.LENGTH_LONG).show();
                toggleButtonNFC.setChecked(true);
            }

            // BlueTooth
            if (!mBtAdapter.isEnabled()) {
                Toast.makeText(this, "BlueTooth is NOT enabled", Toast.LENGTH_LONG).show();
                toggleButtonBT.setChecked(false);
            }

            if (mBtAdapter.isEnabled()) {
                Toast.makeText(this, "BlueTooth is enabled", Toast.LENGTH_LONG).show();
                toggleButtonBT.setChecked(true);
            }

            //WI-FI + P2P
            // TODO: Add code to check state and report to UI (more difficult as BT and NFC, need a manager)
            mWifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);

            if (mWifiManager.isWifiEnabled()) {
                Toast.makeText(this, "Wireless is enabled", Toast.LENGTH_LONG).show();
                toggleButtonWiFI.setChecked(true);
            }

            if (!mWifiManager.isWifiEnabled()) {
                Toast.makeText(this, "Wireless is NOT enabled", Toast.LENGTH_LONG).show();
                toggleButtonWiFI.setChecked(false);
            }


            //TODO: Doesn't seem to work, needs investigation!
            if (getSystemService(Context.WIFI_P2P_SERVICE).equals(mWifiP2pManager.WIFI_P2P_STATE_DISABLED)){
                Toast.makeText(this, "Wireless P2P is NOT enabled", Toast.LENGTH_LONG).show();
                toggleButtonWiFIP2P.setChecked(false);
            }

//            if (mWifiP2pManager.WIFI_P2P_STATE_ENABLED){
//                Toast.makeText(this, "Wireless P2P is enabled", Toast.LENGTH_LONG).show();
//                toggleButtonWiFIP2P.setChecked(true);
//            }


        } catch (Exception e) {
            Log.e(logTag, e.toString());
        }

        //TODO: Add logic to handle user toggle switches 
    }
}
