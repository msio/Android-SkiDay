package com.skiday.app.skiday.social;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.media.session.MediaSessionCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.Toast;

import com.skiday.app.skiday.R;
import com.skiday.app.skiday.feedback.FeedbackFragment;
import com.skiday.app.skiday.social.views.AbstractSocialCardView;

import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by msio on 4/27/17.
 */

public class SocialFragment extends Fragment{
    private static final int CAMERA_PICTURE_REQUEST = 1;

    private ImageButton cameraButton;
    private ImageButton attachmentButton;
    private ImageView imageView;
    private ImageButton cancelButton;
    private Button postButton;

    public static SocialFragment newInstance() {
        SocialFragment fragment = new SocialFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
            }
        });

        this.attachmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageResource(R.drawable.example_ski_picture);
                cancelButton.setImageResource(android.R.drawable.ic_menu_close_clear_cancel);
            }
        });

        this.postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageResource(0);
                cancelButton.setImageResource(0);
                Toast.makeText(getActivity(), "Successfully Posted!",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CAMERA_PICTURE_REQUEST && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap image = (Bitmap)extras.get("data");
            imageView.setImageBitmap(image);
            imageView.setMinimumHeight(600);
            cancelButton.setImageResource(android.R.drawable.ic_menu_close_clear_cancel);
        }
    }
}
