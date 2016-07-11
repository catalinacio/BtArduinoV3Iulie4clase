package arduinogigi.btarduinov2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Connect_myBT extends AppCompatActivity {
    private final char MESSAGE_MOVE='1';
    private static final String TAG ="my error line" ;
    MyBTClass myBTClass=new MyBTClass();
    TextView textviewConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_my_bt);
        textviewConnect=(TextView) findViewById(R.id.textView_ConnectStatus);
    }

    public void ConnectMe(View view) {

        try {
            myBTClass.connectH506(view);
        }
        catch (Error error )
        {
            Log.e(TAG,"nope yet");
        }
        if(myBTClass.getConnectState()=="RUNNABLE")
        {
            textviewConnect.setText(myBTClass.getConnectState().toString());
        }
        else
        {
         Toast.makeText(getApplicationContext(),"nope",Toast.LENGTH_SHORT).show();
        }
    }

    public void sendmyCharMessage(View view) {

        myBTClass.mywrite(MESSAGE_MOVE);


    }
    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
