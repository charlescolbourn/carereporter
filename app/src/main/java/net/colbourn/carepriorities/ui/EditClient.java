package net.colbourn.carepriorities.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import net.colbourn.carepriorities.R;
import net.colbourn.carepriorities.api.ClientProvider;
import net.colbourn.carepriorities.model.Client;
import net.colbourn.carepriorities.plugins.LocalDatabase.LocalDatabaseClientProvider;
import net.colbourn.carepriorities.plugins.LocalDatabase.model.ClientDSO;

public class EditClient extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 1888;
    private ImageView clientImage;

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
        ((LocalDatabaseClientProvider) clientProvider).writeClient(client);
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
//                intent.putExtra(MediaStore.EXTRA_OUTPUT,
//                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI.getPath());
                startActivityForResult(intent, CAMERA_REQUEST);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK)
        {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            clientImage.setImageBitmap(photo);
        }
    }




}
