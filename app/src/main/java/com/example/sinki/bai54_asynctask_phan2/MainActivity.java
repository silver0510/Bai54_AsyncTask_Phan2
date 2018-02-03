package com.example.sinki.bai54_asynctask_phan2;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button btnLoad;
    ImageView imgHinh;
    ProgressDialog dialog;

    String link ="http://anhnendep.net/wp-content/uploads/2016/03/anh-girl-xinh-hot-girl-han-quoc-05.jpg";
    String link2 = "https://hinhanhdephd.com/wp-content/uploads/2017/06/anh-girl-xinh-de-thuong-nhat-nam-2017-10.jpg";
    String link3 = "http://tophinhanhdep.net/wp-content/uploads/2016/01/avatar-girl-xinh-10.jpg";

    ArrayList<String>dsHinh;
    Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControl();
        addEvents();
    }

    private void addEvents() {
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyTaiHinh();
            }
        });
    }

    private void xuLyTaiHinh() {

        Imagetask task = new Imagetask();
        task.execute(dsHinh.get(random.nextInt(3)));
    }

    private void addControl() {
        btnLoad = (Button) findViewById(R.id.btnLoad);
        imgHinh = (ImageView) findViewById(R.id.imgHinh);
        dialog = new ProgressDialog(MainActivity.this);
        dialog.setTitle("Thông báo");
        dialog.setMessage("Đang tải hình...");
        dialog.setCanceledOnTouchOutside(false);

        dsHinh = new ArrayList<>();
        dsHinh.add(link);
        dsHinh.add(link2);
        dsHinh.add(link3);
        random = new Random();
    }
    private class Imagetask extends AsyncTask<String,Void,Bitmap>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imgHinh.setImageBitmap(bitmap);
            dialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            try
            {
                String link = strings[0];
                Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(link).getContent());
                return bitmap;
            }
            catch (Exception ex)
            {
                Log.e("loi",ex.toString());
            }
            return null;
        }
    }
}
