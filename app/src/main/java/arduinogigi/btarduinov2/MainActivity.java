package arduinogigi.btarduinov2;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_ENABLE_BT = 0;
    MyBTClass myBTClass=new MyBTClass();

    Button button_bt;
    Set<BluetoothDevice> pairedDevices;
    private BluetoothAdapter mBluetoothAdapter;
    Switch switch_Btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

            }



   public void init() {
        button_bt = (Button) findViewById(R.id.bt_action);

        switch_Btn=(Switch) findViewById(R.id.switch_BT);
        actionInit();
    }


    public void onClickConnect(View view) {
        Intent intent = new Intent(this,Connect_myBT.class);
        startActivityForResult(intent,1);
    }

    public void sendmyCharMessage(View view) {
       //myBTClass.mywrite(MESSAGE_MOVE.getBytes());
Toast.makeText(getApplicationContext(),"1235",Toast.LENGTH_SHORT).show();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String result=data.getStringExtra("result");
                Toast.makeText(getApplicationContext(),"result ok"+result,Toast.LENGTH_SHORT).show();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result

                Toast.makeText(getApplicationContext(),"result cancelled",Toast.LENGTH_SHORT).show();
            }
        }
    }//onActivityResult

    private void actionInit() {
        switch_Btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(!b)
                {
                    disable_Bluetooth();
                }
                else
                {
                    enable_Bluetooth();
                }

            }
        });
    }

    /**THIS CODE IS NEEDED FOR  INTENT RESULT ACTIVITY IN NEW WINDOW
     *
      Intent intent = new Intent();
     intent.putExtra(EXTRA_DEVICE_ADDRESS, address);
     // Set result and finish this Activity
     setResult(Activity.RESULT_OK, intent);
     finish();

     */
    private  void disable_Bluetooth(){
        mBluetoothAdapter= BluetoothAdapter.getDefaultAdapter();
        if(mBluetoothAdapter.isEnabled())
        {
            mBluetoothAdapter.disable();
        }
    }
    private void enable_Bluetooth() {
        CheckBt();
        // to add swtich activated()
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
    }

    private void CheckBt() {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (!mBluetoothAdapter.isEnabled()) {
            Toast.makeText(getApplicationContext(), "Bluetooth Disabled !",
                    Toast.LENGTH_SHORT).show();
        }
        if (mBluetoothAdapter == null) {
            Toast.makeText(getApplicationContext(),
                    "Bluetooth null !", Toast.LENGTH_SHORT).show();
        }
    }




}

/*
    public void enableBT(View view) {

        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            // Device does not support Bluetooth
            Toast.makeText(getApplicationContext(), "no BT", Toast.LENGTH_SHORT).show();
        }

        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);

        }
    }

    public void getBTvisible(View v) {

        Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(getVisible, 0);
    }

    public void startBT(View v) {
        enableBT(v);
        getBTvisible(v);
        Toast.makeText(getApplicationContext(), "started bt", Toast.LENGTH_SHORT).show();


    }



    }*/

