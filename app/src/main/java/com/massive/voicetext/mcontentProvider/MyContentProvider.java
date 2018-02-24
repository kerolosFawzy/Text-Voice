package com.massive.voicetext.mcontentProvider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.massive.voicetext.Utlis.Constant;
import com.massive.voicetext.database.SqliteHelperClass;

public class MyContentProvider extends ContentProvider {
    private SqliteHelperClass mDbHelper;
    public static final int TASKS = 100;
    public static final int TASK_WITH_ID = 101;
    public static UriMatcher matcher = uriMatcher();

    @Override
    public boolean onCreate() {
        Context context = getContext();
        mDbHelper = new SqliteHelperClass(context);
        return true;
    }

    public static UriMatcher uriMatcher() {
        UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

        matcher.addURI(Constant.AUTHORTY, Constant.PATH, TASKS);
        matcher.addURI(Constant.AUTHORTY, Constant.PATH + "/#", TASK_WITH_ID);

        return matcher;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final SQLiteDatabase database = mDbHelper.getReadableDatabase();

        int match = matcher.match(uri);

        Cursor returnCruser;

        switch (match) {
            case TASKS:
                returnCruser = database.query(Constant.Entry.DATABASE_TABLE, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        try {
            returnCruser.setNotificationUri(getContext().getContentResolver(), uri);
        } catch (NullPointerException e) {
            Log.e("Cursor", e.getMessage());
        }
        return returnCruser;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        final SQLiteDatabase liteDatabase = mDbHelper.getWritableDatabase();
        int match = matcher.match(uri);
        Uri returnUri = null;
        switch (match) {
            case TASKS:
                long id = liteDatabase.insert(Constant.Entry.DATABASE_TABLE, null, contentValues);
                if (id > 0) returnUri = ContentUris.withAppendedId(Constant.Entry.FULL_URI, id);
                break;
            default:
                //throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        try {
            getContext().getContentResolver().notifyChange(uri, null);
        } catch (NullPointerException e) {
            Log.e("NullPointerException", e.getMessage());
        }
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        final SQLiteDatabase liteDatabase = mDbHelper.getWritableDatabase();
        int match = matcher.match(uri);
        int deleted;
        String id = uri.getPathSegments().get(1);
        deleted = liteDatabase.delete(Constant.Entry.DATABASE_TABLE, "ID=?", new String[]{id});
//        switch (match) {
//            case TASK_WITH_ID:
//                String id = uri.getPathSegments().get(1);
//                deleted = liteDatabase.delete(Constant.Entry.DATABASE_TABLE, "ID=?", new String[]{id});
//                break;
//            default:
//                throw new UnsupportedOperationException("Unknown uri: " + uri);
//        }
        if (deleted != 0) {
            try {
                getContext().getContentResolver().notifyChange(uri, null);
            } catch (NullPointerException e) {
                Log.e("NullPointerException", e.getMessage());
            }
        }
        return deleted;
    }


    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
