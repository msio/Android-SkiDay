package com.skiday.app.skiday.social;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.os.EnvironmentCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.skiday.app.skiday.MainActivity;
import com.skiday.app.skiday.R;
import com.skiday.app.skiday.dao.ISocialMediaDAO;
import com.skiday.app.skiday.dao.SocialMediaPostContract;
import com.skiday.app.skiday.dto.SocialMediaAttachment;
import com.skiday.app.skiday.dto.SocialMediaFeedback;
import com.skiday.app.skiday.dto.SocialMediaPostDTO;

import org.joda.time.LocalDateTime;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_OK;

/**
 * Created by msio on 4/27/17.
 */

public class SocialFragment extends Fragment{
    public static final String DEBUGING_EXAMPLE_LOCATION = "aus";

    private static final int CAMERA_PICTURE_REQUEST = 1;
    private static final int GALLERY_PICTURE_REQUEST = 2;

    private ImageButton cameraButton;
    private ImageButton attachmentButton;
    private ImageView imageView;
    private ImageButton cancelButton;
    private Button postButton;
    private EditText text;

    private Bitmap attachment;

    private ISocialMediaDAO socialMediaDAO;

    public static SocialFragment newInstance(ISocialMediaDAO database) {
        SocialFragment fragment = new SocialFragment();
        fragment.setSocialMediaDAO(database);
        return fragment;
    }

    public ISocialMediaDAO getSocialMediaDAO() {
        return socialMediaDAO;
    }

    public void setSocialMediaDAO(ISocialMediaDAO socialMediaDAO) {
        this.socialMediaDAO = socialMediaDAO;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Social");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_social, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.cameraButton = (ImageButton) view.findViewById(R.id.camera_button);
        assert(this.cameraButton != null);

        this.cancelButton = (ImageButton) view.findViewById(R.id.cancel_button);
        assert(this.cancelButton != null);

        this.imageView = (ImageView) view.findViewById(R.id.imageView);
        assert(this.imageView != null);

        this.attachmentButton = (ImageButton) view.findViewById(R.id.attach_button);
        assert(this.attachmentButton != null);

        this.postButton = (Button) view.findViewById(R.id.post_button);
        assert(this.postButton != null);

        this.text = (EditText)view.findViewById(R.id.text);
        assert(this.text != null);

        this.cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(cameraIntent.resolveActivity(getActivity().getPackageManager()) != null){
                    startActivityForResult(cameraIntent, CAMERA_PICTURE_REQUEST);
                }
            }
        });

        this.cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageResource(0);
                imageView.setMaxHeight(1);
                imageView.setMinimumHeight(1);
                cancelButton.setImageResource(0);
                attachment = null;
            }
        });

        this.attachmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), GALLERY_PICTURE_REQUEST);

                cancelButton.setImageResource(android.R.drawable.ic_menu_close_clear_cancel);
            }
        });

        this.postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SocialMediaPostDTO socialMediaPost = new SocialMediaPostDTO();
                socialMediaPost.setGeoLocation(DEBUGING_EXAMPLE_LOCATION);
                socialMediaPost.setText(text.getText() + "");

                SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                socialMediaPost.setTimestamp(format.format(new Date()));

                SocialMediaFeedback facebook = new SocialMediaFeedback("Facebook", "likes");
                socialMediaPost.addFeedback(facebook);
                SocialMediaFeedback twitter = new SocialMediaFeedback("Twitter", "retweets");
                socialMediaPost.addFeedback(twitter);

                if(attachment != null){
                    String path = saveImage(attachment);

                    if(path == null){
                        Log.wtf("SocialFragment", "Image Saving Failed");
                    }

                    SocialMediaFeedback instagram = new SocialMediaFeedback("Instagram", "likes");
                    socialMediaPost.addFeedback(instagram);

                    SocialMediaAttachment attachment = new SocialMediaAttachment();
                    attachment.setMimeType(attachment.getMimeType());
                    attachment.setReference(path);
                    socialMediaPost.setSocialMediaAttachment(attachment);
                }

                socialMediaDAO.addSocialMediaPost(socialMediaPost);


                imageView.setImageResource(0);
                cancelButton.setImageResource(0);

                Toast.makeText(getActivity(), "Successfully Posted!",
                        Toast.LENGTH_SHORT).show();

                getActivity().getSupportFragmentManager().popBackStackImmediate();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CAMERA_PICTURE_REQUEST && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            attachment = (Bitmap)extras.get("data");
            imageView.setImageBitmap(attachment);
            imageView.setMinimumHeight(600);
            cancelButton.setImageResource(android.R.drawable.ic_menu_close_clear_cancel);
        }else if(requestCode == GALLERY_PICTURE_REQUEST && resultCode == RESULT_OK){
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);
                imageView.setImageBitmap(bitmap);
                attachment = bitmap;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private String saveImage(Bitmap image){
        File file = getFileForStorage();
        if(file == null){
            Log.wtf("SocialFragment", "No file given to store bitmap!");
            return null;
        }

        try {
            FileOutputStream stream = new FileOutputStream(file);
            image.compress(Bitmap.CompressFormat.PNG, 90, stream);
            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file.getAbsolutePath();
    }

    private File getFileForStorage(){
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath() + "/");

        if(!file.exists()){
            if(!file.mkdirs())
                Log.wtf("SocialFragment", "UNABLE TO CREATE DIRECTORIES AND FILE");
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String name = LocalDateTime.now().toString();
        file = new File(file.getAbsolutePath(), name);
        return file;
    }
}
