package com.example.empressum.jake.Gallery;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.empressum.jake.R;

import java.io.IOException;

/**
 * Created by empressum on 30/8/16.
 */
public class OpenGalleryWithIntents extends AppCompatActivity {

    Button opengal;
    private String selectedImagePath;
    private ImageView img;
    private int PICK_IMAGE_REQUEST=1;
    Intent intent;
    TextView tv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_open);

        tv=(TextView) findViewById(R.id.tv);
        img=(ImageView)findViewById(R.id.img);

        opengal=(Button) findViewById(R.id.opengal);
        opengal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"),PICK_IMAGE_REQUEST);
            }
        });
    }

    protected void onActivityResult(int requestCode,int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode==PICK_IMAGE_REQUEST&&resultCode==RESULT_OK&& data!=null&& data.getData()!=null){

            Uri uri=data.getData();

            try{
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                img.setImageBitmap(bitmap);
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
