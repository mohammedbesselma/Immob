package com.example.mohammed.immob;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.erikagtierrez.multiple_media_picker.Gallery;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import java.util.ArrayList;
import java.util.UUID;

import Adapter.ImageAdapter;
import AppClasse.BienImmobilier;
import AppClasse.Utilisateur;

public class Ajouterimmob extends AppCompatActivity implements View.OnClickListener {

    private static final String UPLOAD_URL = "http://192.168.1.12/android_upload/insert_image.php";
    private static final int IMAGE_REQUEST_CODE = 3;
    private static final int STORAGE_PERMISSION_CODE = 123;
    Utilisateur utilisateur;



    private Button btnup,btnchos;
    private ArrayList<Bitmap> bitmap;
    private Uri filePath;
    GridView gridView;
    Spinner Type,Ville;
    RatingBar Etat;
    EditText Prix,Discription;

    static final int OPEN_MEDIA_PICKER = 1;
    public static  ArrayList<String> selectionResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouterimmob);



        selectionResult=new ArrayList<>();

        Type=(Spinner)findViewById(R.id.immobtype);
        Ville=(Spinner)findViewById(R.id.immobville);
        Etat=(RatingBar)findViewById(R.id.immobetat);
        Prix=(EditText) findViewById(R.id.immobprix);
        Discription=(EditText)findViewById(R.id.immobdiscription);


        gridView = (GridView)findViewById(R.id.mygridview);
        btnchos=(Button)findViewById(R.id.immobimagechoix);
        btnup=(Button)findViewById(R.id.immobenregistrer);



        requestStoragePermission();

        btnup.setOnClickListener(this);
        btnchos.setOnClickListener(this);
        bitmap= new ArrayList<>();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle!=null){

             utilisateur=(Utilisateur)bundle.get("Utilisateur");

        }
    }

    @Override
    public void onClick(View view) {
        if(view == btnchos){
            Intent intent= new Intent(this, Gallery.class);
            // Set the title
            intent.putExtra("title","Select media");
            // Mode 1 for both images and videos selection, 2 for images only and 3 for videos!
            intent.putExtra("mode",2);
            intent.putExtra("maxSelection",3); // Optional
            startActivityForResult(intent,OPEN_MEDIA_PICKER);

        }else if(view == btnup){

            BienImmobilier bienImmobilier=new BienImmobilier();
            bienImmobilier.setType(Type.getSelectedItem().toString());
            bienImmobilier.setVille(Ville.getSelectedItem().toString());
            bienImmobilier.setEtat(Etat.getRating());
            bienImmobilier.setPrix(Float.parseFloat(Prix.getText().toString()));
            bienImmobilier.setDiscription(Discription.getText().toString());
            bienImmobilier.AjouterImmobilier(getApplicationContext(),String.valueOf(utilisateur.getID_Utilisateur()),selectionResult);


        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // Check which request we're responding to
        if (requestCode == OPEN_MEDIA_PICKER) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK && data != null) {

                selectionResult=data.getStringArrayListExtra("result");

                for (int i =0;i<selectionResult.size();i++){

                    bitmap.add(BitmapFactory.decodeFile(selectionResult.get(i)));
                }




                ImageAdapter imageAdapter= new ImageAdapter(getApplicationContext(),bitmap);
                gridView.setAdapter(imageAdapter);
    }}}



    public void uploadMultipart() {


        //getting the actual path of the image

for (int i = 0;i<selectionResult.size();i++){


    try {
        String uploadId = UUID.randomUUID().toString();

        //Creating a multi part request
        new MultipartUploadRequest(this, uploadId, UPLOAD_URL)
                .addFileToUpload(selectionResult.get(i), "image") //Adding file
                .addParameter("caption", "caption") //Adding text parameter to the request
                .setNotificationConfig(new UploadNotificationConfig())
                .setMaxRetries(2)
                .startUpload(); //Starting the upload
    } catch (Exception exc) {
        Toast.makeText(this, exc.getMessage(), Toast.LENGTH_SHORT).show();
    }

}
        //Uploading code

    }



    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }

    //This method will be called when the user will tap on allow or deny
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if (requestCode == STORAGE_PERMISSION_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Displaying a toast
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }

}


