package net.colbourn.carepriorities.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import net.colbourn.carepriorities.R;
import net.colbourn.carepriorities.api.ClientProvider;
import net.colbourn.carepriorities.model.Client;
import net.colbourn.carepriorities.plugins.LocalDatabase.LocalDatabaseClientProvider;
import net.colbourn.carepriorities.plugins.LocalDatabase.model.ClientDSO;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditClient extends AppCompatActivity {

    private static final int REQUEST_TAKE_PHOTO = 1;
//    private static final int CAMERA_REQUEST = 1888;

    private ImageView clientImage;
    private String currentPhotoPath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_client);
        initControls();
    }

    protected void initControls()
    {
        initPhoto();
        initButtons();

    }

    protected void initButtons()
    {
        Button save = findViewById(R.id.editClientViewSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveClientDetails();
            }
        });
        Button cancel = findViewById(R.id.editClientViewCancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               cancelAndExit();
            }
        });
    }

    protected void cancelAndExit()
    {
        this.finish();
    }

    protected void saveClientDetails()
    {
        ClientProvider clientProvider = new LocalDatabaseClientProvider();
        Client client = new Client(findViewById(R.id.editClientName).toString()); // TODO toString?
        client.setPhoto(currentPhotoPath);
        ((LocalDatabaseClientProvider) clientProvider).writeClient(client);
        this.finish();
    }


    protected void initPhoto()
    {
        clientImage = (ImageView)findViewById(R.id.editClientImage);
        clientImage.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(EditClient.this.getPackageManager()) != null) {
                    // Create the File where the photo should go
                    File photoFile = null;
                    try {
                        photoFile = createImageFile();
                    } catch (IOException ex) {
                        Log.e("ImageError","Imagefile not created because " + ex.getLocalizedMessage());
                    }
                    // Continue only if the File was successfully created
                    if (photoFile != null) {
                        Uri photoURI = FileProvider.getUriForFile( EditClient.this,
                                "com.example.android.fileprovider",
                                photoFile);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                        startActivityForResult(intent, REQUEST_TAKE_PHOTO);
                    }
                }

            }
        });
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == Activity.RESULT_OK)
        {
            Bitmap photo = BitmapFactory.decodeFile(currentPhotoPath);
            clientImage.setImageBitmap(photo);
        }
    }


}
