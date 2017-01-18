package com.grability.appsstore.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by killerwilmer on 15/01/17.
 */

public class ActivityUtils {
    public static boolean hasInternet(Activity a) {
        boolean hasConnectedWifi = false;
        boolean hasConnectedMobile = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) a.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo[] netInfo = cm.getAllNetworkInfo();
            for (NetworkInfo ni : netInfo) {
                if (ni.getTypeName().equalsIgnoreCase("wifi"))
                    if (ni.isConnected())
                        hasConnectedWifi = true;
                if (ni.getTypeName().equalsIgnoreCase("mobile"))
                    if (ni.isConnected())
                        hasConnectedMobile = true;
            }
            return hasConnectedWifi || hasConnectedMobile;
        }
        catch (Exception ex) {
            Log.e("Error_internet", ex.getMessage());
        }
        return false;
    }

    public static void showDialog(Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);

        builder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }

}
