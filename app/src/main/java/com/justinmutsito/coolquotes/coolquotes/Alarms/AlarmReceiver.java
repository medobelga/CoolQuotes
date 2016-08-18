package com.justinmutsito.coolquotes.coolquotes.Alarms;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;

import com.justinmutsito.coolquotes.coolquotes.Notifications.NotificationActivity;
import com.justinmutsito.coolquotes.coolquotes.R;

/**
 * Created by justin on 8/18/16.
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,10,new Intent(context, NotificationActivity.class),0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setSound(sound);
        builder.setContentTitle(context.getString(R.string.hello));
        builder.setContentText(context.getString(R.string.day_quote));
        builder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(10,builder.build());
    }
}
