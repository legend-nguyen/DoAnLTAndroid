package com.example.doanltandroid;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

public class PostAPIQuenMatKhau extends AsyncTask<Void,Void,String> {
    Context context;
    String duongdan;
    String Matkhau;
    NguoiChoi nguoiChoi;
    public PostAPIQuenMatKhau(Context context ,String duongdan, String edtMatkhau) {
        this.context = context;
        this.duongdan=duongdan;
        this.Matkhau = edtMatkhau;
        nguoiChoi = new NguoiChoi();
        nguoiChoi.setMat_khau(edtMatkhau);
    }
    @Override
    protected String doInBackground(Void... voids) {
        return this.send();
    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(s==null){
            Toast.makeText(context, "Kết nối lũng", Toast.LENGTH_SHORT).show();
        }
        else {
            if(s=="error"){
                Toast.makeText(context, "Toang", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(context, "Thành công!!", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private String send(){
        final HttpURLConnection connection = ReadAPI.connectionPOST(duongdan);
        if(connection==null){
            return null;
        }
        try {
            OutputStream outputStream=connection.getOutputStream();
            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(outputStream,"UTF-8");
            BufferedWriter bufferedWriter=new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(new NCQuenMatKhauPackagepackage(nguoiChoi).getPackage());
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            int response_thanhcong=connection.getResponseCode();
            if(response_thanhcong == connection.HTTP_OK){
                BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder stringBuilder=new StringBuilder();
                String line;
                while ((line=bufferedReader.readLine())!=null){
                    stringBuilder.append(line);
                }
                bufferedReader.close();
                return stringBuilder.toString();
            }
            else {
                return String.valueOf(response_thanhcong);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
