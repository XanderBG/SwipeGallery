package com.example.xander.swipegallery;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ImageFragment extends Fragment {

    private Bitmap image;

    public ImageFragment() {
    }

    @Override
    public void setArguments(Bundle args) {
        image = args.getParcelable("image");
        super.setArguments(args);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myParentView = inflater.inflate(R.layout.fragment_image, container, false);

        ImageView imageView = (ImageView) myParentView.findViewById(R.id.image);
        imageView.setImageBitmap(image);
        return myParentView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }


}
