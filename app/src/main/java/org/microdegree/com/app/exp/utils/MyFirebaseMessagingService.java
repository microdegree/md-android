package org.microdegree.com.app.exp.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.microdegree.com.app.exp.R;
import org.microdegree.com.app.exp.data.model.NotificationModel;
import org.microdegree.com.app.exp.data.repo.NotificationsRepository;
import org.microdegree.com.app.exp.ui.notification.NotificationViewModel;
import org.microdegree.com.app.exp.ui.splash.SplashActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {


        // Check if message contains a data payload.
//        if (remoteMessage.getNotification().size() > 0) {
//            Map<String,String> data = remoteMessage.getData();
//                 if(data.get("notificationId")!=null && !Objects.requireNonNull(data.get("notificationId")).isEmpty()){
//                NotificationModel model=new NotificationModel();
//                model.setNotificationId(data.get("notificationId"));
//                model.setNotificationTitle(data.get("notificationTitle"));
//                model.setCreatedTimestamp(data.get("createdTimestamp"));
//                model.setNotificationImgUrl(data.get("notificationImgUrl"));
//                model.setNotificationBody(data.get("notificationBody"));
//                NotificationsRepository mNotificationRepo = NotificationsRepository.getInstance();
//                mNotificationRepo.setNotification(model);
//
//               //  sendNotification(remoteMessage.getData());
//                 }
//
//        }

        // Check if message contains a notification payload.
        if (remoteMessage.getData().size()  > 0) {
             Map<String,String> data = remoteMessage.getData();
            Bitmap bitmap = null;
            if(data.get("image")!=null){
                 bitmap = getBitmapFromURL(data.get("image"));

            }

            Long tsLong = System.currentTimeMillis()/1000;

            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = df.format(c.getTime());
            String ts = tsLong.toString();
                NotificationModel model=new NotificationModel();
                model.setNotificationId(ts);
                model.setNotificationTitle(data.get("title"));
                model.setCreatedTimestamp(formattedDate);
                model.setNotificationImgUrl(data.get("image"));
                model.setNotificationBody(data.get("body"));
                NotificationsRepository mNotificationRepo = NotificationsRepository.getInstance();
                mNotificationRepo.setNotification(model);

                sendNotification(data,bitmap,remoteMessage);

        }


    }
    public Bitmap getBitmapFromURL(String strURL) {
        try {
            URL url = new URL(strURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);

            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public void onNewToken(String token) {
    }



    private void sendNotification(Map<String,String>  data,Bitmap image,RemoteMessage remoteMessage) {
        Intent notificationIntent = new Intent(this, SplashActivity.class);
        notificationIntent.putExtra( "NotificationMessage" , remoteMessage ) ;
        notificationIntent.addCategory(Intent. CATEGORY_LAUNCHER ) ;
        notificationIntent.setAction(Intent. ACTION_MAIN ) ;
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, notificationIntent,
                PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.BigPictureStyle s = new NotificationCompat.BigPictureStyle().bigPicture(image);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, getString(R.string.default_notification_channel_id))
                        .setSmallIcon(R.drawable.logo)
                        .setChannelId(getString(R.string.default_notification_channel_id))
                        .setContentTitle(data.get("title"))
                        .setContentText(data.get("body"))
                        .setAutoCancel(true)
                      //  .setLargeIcon(image)
                        .setStyle(s)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    getString(R.string.default_notification_channel_id),
                    getString(R.string.default_notification_channel_name),
                    NotificationManager.IMPORTANCE_HIGH);

            notificationManager.createNotificationChannel(channel);
        }
        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}