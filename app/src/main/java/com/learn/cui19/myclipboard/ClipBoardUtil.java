package com.learn.cui19.myclipboard;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;

/**
 * Created by cui19 on 2016/10/19.
 */
public class ClipBoardUtil {
    public static String getClipBoardText() {
        Context context = MyApplication.getInstance();
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        if (clipboardManager.hasPrimaryClip()) {
            if (clipboardManager.getPrimaryClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                ClipData clipData = clipboardManager.getPrimaryClip();
                ClipData.Item item = clipData.getItemAt(0);
                return item.getText().toString();
            }
        }
        return null;
    }

    public static void setClipBoardText(String clipBoardText) {
        Context context = MyApplication.getInstance();
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData cd = ClipData.newPlainText("cl", clipBoardText);
        clipboardManager.setPrimaryClip(cd);
    }
}
