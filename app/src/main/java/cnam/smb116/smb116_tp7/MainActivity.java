package cnam.smb116.smb116_tp7;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText inputTxt;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.inputTxt = findViewById(R.id.editTextNumber);

        /** Question 2 */
        configureHandler();
    }

    public void onClickStart(View view) {
        Intent intent = new Intent(this, DelayedToastIntentService.class);
        int time;
        if (inputTxt.getText().toString().equals("")){
            time = 0;
        }else {
            time = Integer.parseInt(String.valueOf(inputTxt.getText()));
        }
        Log.i(TAG, "onClickStart , time = "+ time);
        intent.putExtra("period", time);

        /** Question 2 */
        Messenger messager = new Messenger(handler);
        intent.putExtra("messager", messager);

        startService(intent);
    }

    @SuppressLint("HandlerLeak")
    public void configureHandler(){
        handler = new Handler() {
            public void handleMessage(Message message) {
                Bundle extras = message.getData();
                if (extras != null) {
                    int period = extras.getInt("period");
                    Toast.makeText(getApplicationContext(),"délai écoulé "+period,Toast.LENGTH_LONG).show();
                }
            }
        };
    }
}