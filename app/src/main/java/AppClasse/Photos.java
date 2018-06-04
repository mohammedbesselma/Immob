package AppClasse;

import android.content.Context;
import android.widget.Toast;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import java.util.ArrayList;
import java.util.UUID;

import Adapter.Constans;

/**
 * Created by Mohammed on 14/05/2018.
 */

public class Photos {

    int ID_photo ;
    String URL_photo;
    private static final String UPLOAD_URL = Constans.getIPadress()+"/android_upload/insert_image.php";



    public void Inserer(ArrayList<String>selectionResult, Context ctx,String id){

        for (int i = 0;i<selectionResult.size();i++){


            try {
                String uploadId = UUID.randomUUID().toString();

                //Creating a multi part request
                new MultipartUploadRequest(ctx, uploadId, UPLOAD_URL)
                        .addFileToUpload(selectionResult.get(i), "image") //Adding file
                        .addParameter("ID_immob", id) //Adding text parameter to the request
                        .setNotificationConfig(new UploadNotificationConfig())
                        .setMaxRetries(2)
                        .startUpload(); //Starting the upload
            } catch (Exception exc) {
                Toast.makeText(ctx, exc.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }


    }

}
