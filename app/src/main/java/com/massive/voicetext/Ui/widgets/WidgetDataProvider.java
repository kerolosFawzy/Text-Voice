package com.massive.voicetext.Ui.widgets;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.massive.voicetext.R;
import com.massive.voicetext.mcontentProvider.GetDataFromCursor;
import com.massive.voicetext.mcontentProvider.GetDataFromCursorInterface;
import com.massive.voicetext.models.TextModel;

import java.util.ArrayList;


public class WidgetDataProvider implements RemoteViewsService.RemoteViewsFactory {
    private Context context;
    private ArrayList<TextModel> TextArray = new ArrayList<>();
    private GetDataFromCursorInterface getData;

    WidgetDataProvider(Context applicationContext, Intent intent) {
        this.context = applicationContext;
    }

    @Override
    public void onCreate() {
        getData = new GetDataFromCursor();
        TextArray = getData.GetData(context);
    }

    @Override
    public void onDataSetChanged() {
        TextArray = getData.GetData(context);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return TextArray.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_content);
        String text = TextArray.get(position).getText();
        remoteViews.setTextViewText(R.id.WidgetTextView, text);
        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
