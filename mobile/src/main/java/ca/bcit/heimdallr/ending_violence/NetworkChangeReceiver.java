package ca.bcit.heimdallr.ending_violence;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Owner on 3/6/16.
 */
public class NetworkChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(final Context context, final Intent intent) {

            String status = NetworkUtil.getConnectivityStatusString(context);

            Toast.makeText(context, status, Toast.LENGTH_LONG).show();
        }
}