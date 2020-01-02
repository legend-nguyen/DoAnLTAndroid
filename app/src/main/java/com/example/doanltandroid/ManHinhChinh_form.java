package com.example.doanltandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.squareup.picasso.Picasso;

public class ManHinhChinh_form extends AppCompatActivity {
    TextView txt;
    TextView txt2;
    String ten_dang_nhap;
    String email;
    String credit;
    String id;
    String hinh_dai_dien;
    String diem_cao_nhat;
    ImageView img;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chinh_form);
        txt=findViewById(R.id.txtUsername);
        txt2=findViewById(R.id.txtCredit);
        Intent intent = getIntent();
        hinh_dai_dien = intent.getStringExtra("hinh_dai_dien");
        diem_cao_nhat = intent.getStringExtra("diem_cao_nhat");
        credit = intent.getStringExtra("credit");
        ten_dang_nhap = intent.getStringExtra("ten_dang_nhap");
        email = intent.getStringExtra("email");
        credit = intent.getStringExtra("credit");
        txt2.setText(credit);
        id = intent.getStringExtra("id");
        img = findViewById(R.id.imghinhdaidienql);
        String url = "http://10.0.2.2:8080/Do_An_PHP/public/img/"+hinh_dai_dien;
        Picasso.with(this).load(url).into(img);
        sharedPreferences=getSharedPreferences("nguoichoi",MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.putString("id_nguoichoi",id);
        editor.commit();
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            txt.setText(personName);
        }





    }
    public void QuanLiTaiKhoan(View view){
        Intent intent = new Intent(ManHinhChinh_form.this,QuanLyTaiKhoan_form.class);
        intent.putExtra("ten_dang_nhap",ten_dang_nhap);
        intent.putExtra("email",email);
        intent.putExtra("id",id);
        intent.putExtra("diem_cao_nhat",diem_cao_nhat);
        intent.putExtra("hinh_dai_dien",hinh_dai_dien);
        intent.putExtra("credit",credit);
        startActivity(intent);

    }
    public void TroChoiMoi(View view){
        Intent intent=new Intent(ManHinhChinh_form.this,LinhVucCauHoi_form.class);
        startActivity(intent);
    }
    public void LichSuChoi(View view){
        Intent intent=new Intent(ManHinhChinh_form.this,LichSuChoi_form.class);
        intent.putExtra("id_nguoichoi",sharedPreferences.getString("id_nguoichoi",""));
        startActivity(intent);

    }
}
