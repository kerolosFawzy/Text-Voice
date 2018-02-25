package com.massive.voicetext.Ui.widgets;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by minafaw on 2/25/2018.
 */

public class FavWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new WidgetDataProvider(this.getApplicationContext());
    }
}
