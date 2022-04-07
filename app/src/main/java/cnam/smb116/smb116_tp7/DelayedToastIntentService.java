package cnam.smb116.smb116_tp7;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DelayedToastIntentService extends IntentService {

    private static final String TAG = DelayedToastIntentService.class.getSimpleName();
    @SuppressWarnings("deprecation")
    final Handler mHandler = new Handler();
    private int period;

    public DelayedToastIntentService() {
        super("name");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            period = intent.getIntExtra("period", 10);
        }
        Log.i(TAG, "onHandleIntent, period = " + period);

        /** Question 1 */
//         toast();

        /** Question 2 */
//        if (intent != null) {
//            messenger(intent);
//        }

        /** Question 3 */
        int TRENTE_SECONDES = 30;
        if (period > TRENTE_SECONDES){
            alert();
        }else {
            if (intent != null) {
                messenger(intent);
            }
        }

    }

    void toast() {
        SystemClock.sleep(period * 1000L);
        mHandler.post(() -> Toast.makeText(getApplicationContext(), "délai écoulé: " + period, Toast.LENGTH_SHORT).show());
    }

    void messenger(Intent intent){
        SystemClock.sleep(period * 1000L);
        Bundle extras = intent.getExtras();
        Messenger messager = (Messenger) extras.get("messager");
        Message msg = Message.obtain();
        Bundle bundle = new Bundle();
        bundle.putInt("period", period);
        msg.setData(bundle);
        try {
            messager.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    void alert(){
        mHandler.post(() -> {
            Intent intent = new Intent(getApplicationContext(), AlertDialogActivity.class);
            intent.putExtra("period", period);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
    }
}
