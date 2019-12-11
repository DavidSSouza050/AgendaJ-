package br.senai.sp.agendaja.Paths;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

public class RealPathPhoto {

  public String getRealPathFromUri(Uri uri, Context context){
    String[] projection = {MediaStore.Images.Media.DATA};
    CursorLoader loader = new CursorLoader(context.getApplicationContext(), uri, projection, null, null, null);
    Cursor cursor = loader.loadInBackground();
    int columnIdx = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
    cursor.moveToFirst();
    String result = cursor.getString(columnIdx);
    cursor.close();
    return result;
  }

}
