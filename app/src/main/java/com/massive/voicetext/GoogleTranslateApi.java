//package com.massive.voicetext;
//
//import android.os.AsyncTask;
//
//import com.google.cloud.translate.Translate;
//import com.google.cloud.translate.Translate.TranslateOption;
//import com.google.cloud.translate.TranslateOptions;
//import com.google.cloud.translate.Translation;
//
//
//public class GoogleTranslateApi extends AsyncTask<String, Void, String> {
////    public static String translateTextWithOptionsAndModel(String sourceText, String targetLang) {
////
////        Translate translate = TranslateOptions.getDefaultInstance().getService();
////
////        Translation translation = translate.translate(sourceText, TranslateOption.sourceLanguage("en"), TranslateOption.targetLanguage(targetLang));
////        return translation.getTranslatedText();
////    }
//
//
//    NotifyChange notifyChange;
//
//    public GoogleTranslateApi(NotifyChange notifyChange) {
//        this.notifyChange = notifyChange;
//    }
//
//    @Override
//    protected String doInBackground(String... strings) {
//        Translate translate = TranslateOptions.getDefaultInstance().getService();
//        Translation translation = translate.translate(strings[0], TranslateOption.sourceLanguage("en"), TranslateOption.targetLanguage("ar"));
//        return translation.getTranslatedText();
//    }
//
//    @Override
//    protected void onPostExecute(String s) {
//        notifyChange.GetData(s);
//    }
//
//
//}
