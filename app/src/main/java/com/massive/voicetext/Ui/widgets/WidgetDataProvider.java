package com.massive.voicetext.Ui.widgets;

import android.content.Context;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.massive.voicetext.GetDataFromCursor;
import com.massive.voicetext.GetDataFromCursorInterface;
import com.massive.voicetext.R;
import com.massive.voicetext.models.TextModel;

import java.util.ArrayList;


public class WidgetDataProvider implements RemoteViewsService.RemoteViewsFactory {
    private Context context;
    private ArrayList<TextModel> TextArray;
    private GetDataFromCursorInterface getData;

    WidgetDataProvider(Context applicationContext) {
        this.context = applicationContext;
        getData = new GetDataFromCursor();
        TextArray = new ArrayList<>();
    }

    @Override
    public void onCreate() {
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
        remoteViews.setTextViewText(R.id.WidgetTextView, TextArray.get(position).getText());
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
