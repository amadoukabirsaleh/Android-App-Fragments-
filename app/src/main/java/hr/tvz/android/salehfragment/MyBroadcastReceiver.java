package hr.tvz.android.salehfragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.TextView;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    TextView batteryLevel;
    MyBroadcastReceiver(){
    }

    MyBroadcastReceiver(TextView b){
        this.batteryLevel=b;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        /*------------FOR BATTERY BROADCAST--------------------------------*/

        int percentage = intent.getIntExtra("level",0);
           if (percentage!=0){
               batteryLevel.setText(percentage+"%");
           }
        /*------------------------------------------------------------*/

        /*------------FOR CONNECTIVITY BROADCAST--------------------------------*/
        if(ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){
            boolean noConnectivity = intent.getBooleanExtra(
                    ConnectivityManager.EXTRA_NO_CONNECTIVITY, false
            );

            if(noConnectivity){
                Toast.makeText(context,"No internet connection",Toast.LENGTH_SHORT).show();

            }else {
                Toast.makeText(context, "Connected to internet",Toast.LENGTH_SHORT).show();
            }
        }
         /*------------------------------------------------------------*/
    }
}
