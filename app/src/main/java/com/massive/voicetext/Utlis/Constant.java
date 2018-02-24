package com.massive.voicetext.Utlis;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by minafaw on 2/23/2018.
 */

public class Constant {
    public static String AUTHORTY = "com.massive.voicetext";
    private static final Uri BASE_URI = Uri.parse("content://" + AUTHORTY);
    public static String PATH = "textTable";

    public static final class Entry implements BaseColumns {
        public static final Uri FULL_URI = BASE_URI.buildUpon().appendPath(PATH).build();

        public static String DATABASE_TABLE = "textTable";

        public static String ID = "ID";
        public static String TEXT = "text";
    }
}
