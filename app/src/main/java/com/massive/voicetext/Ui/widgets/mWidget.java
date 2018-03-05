package com.massive.voicetext.Ui.widgets;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import com.massive.voicetext.R;
import com.massive.voicetext.Ui.FavouritActivity;

public class mWidget extends AppWidgetProvider {
    static String ACTION = "Clicked";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.m_widget);

        Intent intent = new Intent(context, FavouritActivity.class);
        intent.setAction(ACTION);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        views.setOnClickPendingIntent(R.id.widgetLinearMainLayout, pendingIntent);

        Intent MainIntent = new Intent(context, FavWidgetService.class);
        MainIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        MainIntent.setData(Uri.parse(MainIntent.toUri(Intent.URI_INTENT_SCHEME)));
        views.setRemoteAdapter(R.id.ContentList, MainIntent);


        appWidgetManager.updateAppWidget(appWidgetId, views);
    }


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.m_widget);
            Intent intent = new Intent(context, FavWidgetService.class);
            views.setRemoteAdapter(R.id.ContentList, intent);
            views.setEmptyView(R.id.ContentList, R.id.EmptyView);
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (intent.getAction().equals(ACTION)) {
//            Toast.makeText(context, "Clicked", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

