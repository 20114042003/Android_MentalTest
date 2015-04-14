package com.cx.project.mentaltest.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import com.cx.project.mentaltest.R;

public class DataManagerUtil {
	  private final int BUFFER_SIZE = 500000;
      //��������ݿ���ļ�����
      public static final String DB_NAME = "mentaltest";
      //�ù��̵İ�����
      public static final String PACKAGE_NAME = "com.cx.project.mentaltest";
      //���ֻ����д�����ݿ��λ��       
      public static final String DB_PATH = "/data"
                             + Environment.getDataDirectory().getAbsolutePath() + "/"
                             + PACKAGE_NAME;
      private SQLiteDatabase database;
      private Context mContext;
      public DataManagerUtil(Context context) {
                  mContext = context;
       }
      public SQLiteDatabase openDatabase() {
               return    database = this.openDatabase(DB_PATH + "/" + DB_NAME);
       }
      
      public void closeDatabase(){
    	  database.close();
      }
      
      private SQLiteDatabase openDatabase(String dbfile) {
                 try {
                      //�ж����ݿ��ļ��Ƿ���ڣ����������ֱ�ӵ��룬����ֱ�Ӵ�
                     if (!(new File(dbfile).exists())) {
                     InputStream is =  mContext.getResources().openRawResource(R.raw.mentaltest);
                     FileOutputStream fos = new FileOutputStream(dbfile);
                     byte[] buffer = new byte[BUFFER_SIZE];
                     int count = 0;
                     while ((count = is.read(buffer)) > 0) {
                             fos.write(buffer, 0, count);
                     }
                     fos.close();
                     is.close();
             }
           SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbfile,
            null);
           return db;
         } catch (FileNotFoundException e) {
             Log.e("Database", "File not found");
             e.printStackTrace();
       } catch (IOException e) {
             Log.e("Database", "IO exception");
            e.printStackTrace();
      }
            return null;
      }

}
