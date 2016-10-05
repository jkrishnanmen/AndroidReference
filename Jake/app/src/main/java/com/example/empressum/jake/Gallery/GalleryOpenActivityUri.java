package com.example.empressum.jake.Gallery;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.empressum.jake.R;

public class GalleryOpenActivityUri extends AppCompatActivity {

    Button opengal;
    private String selectedImagePath;
    private ImageView img;
    private static final int SELECT_PICTURE=1;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_open);

        tv=(TextView) findViewById(R.id.tv);
        img=(ImageView)findViewById(R.id.img);

        opengal=(Button) findViewById(R.id.opengal);
        opengal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"),SELECT_PICTURE);
            }
        });
    }

    public void onActivityResult(int requestCode,int resultCode, Intent data){
        if(resultCode==RESULT_OK){
            if(requestCode==SELECT_PICTURE){
                Uri selectedImageUri= data.getData();//working
                selectedImagePath=getPath(selectedImageUri);//error
                System.out.println("Image Path:"+ selectedImagePath);
                img.setImageURI(selectedImageUri);// not working
                tv.setText(data.getData().toString());// working
                tv.setText(selectedImageUri.toString());// working
                //new code

//                String filemanagerstring=selectedImageUri.getPath();//new code
//                String chosenpath;
//                if(selectedImagePath!=null){
//                    chosenpath=selectedImagePath;
//                }else{
//                    chosenpath=filemanagerstring;
//                }
//                selectedImageUri=Uri.parse(chosenpath);
//                tv.setText(selectedImageUri.toString());
////                img.setImageURI(null);
//                img.setImageURI(selectedImageUri);
            }
        }
    }

    public String getPath(Uri uri){
//        String wholeID= DocumentsContract.getDocumentId(uri);//new code
//        String id=wholeID.split(":")[1];//new code
//        String[] column={MediaStore.Images.Media.DATA};//new code
        String sel=MediaStore.Images.Media._ID + "=?";
        String[] projection ={MediaStore.Images.Media.DATA};// working
//        tv.setText(projection.toString());// working
        Cursor cursor=getContentResolver().query(uri,projection,null,null,null);// working- OLD CODE
//        Cursor cursor= getContentResolver().query(uri,projection,null,null,null);//new code
        if(cursor!=null){
            int column_index=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            selectedImagePath=cursor.getString(column_index);
        }else{
            selectedImagePath=null;
        }

        if(selectedImagePath==null){
            selectedImagePath=uri.getPath();
        }
        return selectedImagePath;
//        int column_index=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);// old code
//        tv.setText(Integer.toString(column_index));
//        cursor.moveToFirst();//old code
//        tv.setText(cursor.toString());// working
//        return cursor.getString(column_index);
    }
}
