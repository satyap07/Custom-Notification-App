package com.example.customnotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {

    private NotificationManagerCompat notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationManager= NotificationManagerCompat.from(this);
    }

    public void showNotification(View view){
        RemoteViews collapsedView=new RemoteViews(getPackageName(),
                R.layout.notification_collapsed);
        RemoteViews expandedView=new RemoteViews(getPackageName(),
                R.layout.notification_expanded);

        Intent clickIntent=new Intent(this,NotificationReceiver.class);
        PendingIntent clickPendingIntent= PendingIntent.getBroadcast(this,0,clickIntent,0);

        collapsedView.setTextViewText(R.id.test_view_collapsed_1,"Ola World");
        expandedView.setImageViewResource(R.id.image_view_expanded,R.drawable.photo2);
        expandedView.setOnClickPendingIntent(R.id.image_view_expanded,clickPendingIntent);
        Notification notification=new NotificationCompat.Builder(this,App.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_android)
                .setCustomContentView(collapsedView)
                .setCustomBigContentView(expandedView)
                .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                .build();
        notificationManager.notify(1,notification);
    }
}
