package br.senai.sp.agendaja.ConversaoImagem;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class ConverterImagem {

  public static Bitmap byteArrayParaBitmap(byte[] fotoArray){
    Bitmap bitmap = BitmapFactory.decodeByteArray(fotoArray,0,fotoArray.length);
    return bitmap;
  }

  public static byte[] bitmapParaByteArray(ImageView imageView){


    BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
    Bitmap bm = drawable.getBitmap();
    Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bm,100,100,true);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    bitmapReduzido.compress(Bitmap.CompressFormat.PNG,0,outputStream);
    byte[] fotoArray = outputStream.toByteArray();

    return fotoArray;
  }


}
