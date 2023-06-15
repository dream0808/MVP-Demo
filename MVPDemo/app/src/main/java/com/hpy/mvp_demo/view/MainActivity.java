package com.hpy.mvp_demo.view;

import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.os.Message;
import com.hpy.mvp_demo.R;
import com.hpy.mvp_demo.databinding.ActivityMainBinding;
import com.hpy.mvp_demo.presenter.Presenter;

import android.widget.Button;
import android.widget.TextView;
import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity implements IView{


    Button btn;
    TextView textView;
    MyHandle myHandle = new MyHandle(this);

    Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);


        btn = findViewById(R.id.button1);
        textView = findViewById(R.id.textView1);


        presenter = new Presenter(this);

        btn.setOnClickListener(view -> {
            presenter.loadData();
        });


    }


    @Override
    public void showLoadingProgress(String message) {
       textView.setText(message);
    }

    @Override
    public void showData(String text) {
        textView.setText(text);
    }

    private static class MyHandle extends Handler{

        //弱引用，防止内存泄露
     WeakReference<MainActivity> weakReference;

     public MyHandle(MainActivity activity){
         weakReference = new WeakReference<>(activity);
     }

        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == 1) {
                weakReference.get().textView.setText(msg.what);
            }
        }
    }
}