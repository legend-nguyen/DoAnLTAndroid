package com.example.doanltandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;


import java.util.ArrayList;

public class LinhVucCauHoi_form extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    ImageButton btnplay;
    AnimationDrawable background;
    private RecyclerView recyclerView;
    private LinhVucAdapter linhVucAdapter;
    private ArrayList<LinhVuc>linhVucs;
    //private String duongdan="http://10.0.2.2/Do_An_PHP/public/api/linh-vuc";
    private String id_nguoichoi;
    private String duongdan = "http://192.168.56.1/Do_An_PHP/public/api/linh-vuc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linh_vuc_cau_hoi_form);
        recyclerView=findViewById(R.id.recyclerviewdslinhvuc);
        GetAPILinhVuc getAPILinhVuc= (GetAPILinhVuc) new GetAPILinhVuc(LinhVucCauHoi_form.this,recyclerView).execute(duongdan);
        btnplay = (ImageButton) findViewById(R.id.ImageButton);
        //animation translate
        Button btn = (Button) findViewById(R.id.button);
        //final Animation animation = AnimationUtils.loadAnimation(this,R.anim.anim_translate);
        //btn.startAnimation(animation);
        //animation background
        btn.setBackgroundResource(R.drawable.anim_background_red);
        background =(AnimationDrawable) btn.getBackground();
        background.start();
        //animation rorate
        ImageView imageView = findViewById(R.id.imglogo);
        Animation animImage = AnimationUtils.loadAnimation(this,R.anim.anim_rotate);
        imageView.startAnimation(animImage);




    }
    public void play(View view){
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(this, R.raw.song);
                mediaPlayer.start();
                btnplay.setImageResource(R.drawable.play);
                Toast.makeText(this,"Đã bật âm thanh",Toast.LENGTH_SHORT).show();

            }else {
               stopplayer();
               btnplay.setImageResource(R.drawable.mute);
            }

    }
    public void stopplayer(){
        if(mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer=null;
            Toast.makeText(this,"Đã tắt âm thanh",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        stopplayer();
    }
}
