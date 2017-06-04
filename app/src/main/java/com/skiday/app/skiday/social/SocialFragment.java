package com.skiday.app.skiday.social;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.Toast;

import com.skiday.app.skiday.R;

/**
 * Created by msio on 4/27/17.
 */

public class SocialFragment extends Fragment implements View.OnClickListener{
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
                imageView.setImageResource(R.drawable.example_selfie);
                cancelButton.setImageResource(android.R.drawable.ic_menu_close_clear_cancel);
            }
        });

        this.cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageResource(0);
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
    public void onClick(View v) {
        //TODO: New Post
    }
}
