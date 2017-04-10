package com.example.android.testgridviewmoviedatabase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by shiri on 09-Apr-17.
 */

public class ImageGridAdapter extends ArrayAdapter {
    private Context context;
    private LayoutInflater inflater;

    private String[] posterPathUrls;

    public ImageGridAdapter(Context context, String[] posterPathUrls) {
        super(context, R.layout.gridview_item_image, posterPathUrls);

        this.context = context;
        this.posterPathUrls = posterPathUrls;

        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null)
            convertView = inflater.inflate(R.layout.gridview_item_image, parent, false);

        /*String temporaryTag = NetworkUtils.buildPosterPathURL().toString();
        String posterTag = temporaryTag + posterPathUrls[position];*/

        Picasso
                .with(context)
                .load(NetworkUtils.buildPosterPathURL(posterPathUrls[position]).toString())
                .into((ImageView) convertView);

        return convertView;
    }
}
