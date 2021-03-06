package cnam.smb116.smb116_tp7;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

public class AlertDialogActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        final int period = intent.getIntExtra("period", -1);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder
                .setTitle("Alerte en question 3 du tp7/SMB116")
                .setMessage("votre délai excède 30 secondes (" + period + ") sec.)")
                .setCancelable(false)
                .setPositiveButton("OK", (dialog, id) -> AlertDialogActivity.this.finish());
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}