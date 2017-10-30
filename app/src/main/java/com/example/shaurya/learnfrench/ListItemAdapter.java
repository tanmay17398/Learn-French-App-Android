package com.example.shaurya.learnfrench;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;


public class ListItemAdapter extends ArrayAdapter<ListItem>{

    private int colorResourceId;
    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context        The current context. Used to inflate the layout file.
     * @param listItems A List of AndroidFlavor objects to display in a list
     */
    public  ListItemAdapter(Activity context, ArrayList<ListItem> listItems,int colorId) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context,0,listItems);
        colorResourceId=colorId;
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        ListItem listItemx = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView frenchTextView = (TextView) listItemView.findViewById(R.id.french_textView);
        // Get the frenchText from the current ListItem object and
        // set this text on the name TextView
        frenchTextView.setText(listItemx.getFrenchTranslation());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView englishTextView = (TextView) listItemView.findViewById(R.id.english_textView);
        // Get the englishText from the current ListItem object and
        // set this text on the number TextView
        englishTextView.setText(listItemx.getEnglishTranslation());

        // Find the ImageView in the list_item.xml layout with the ID image
        ImageView imageView = (ImageView)listItemView.findViewById(R.id.image);
        // Get the image from the current ListItem object and
        // set this image on the imageView if image is available
        if(listItemx.hasImage()) {
            imageView.setImageResource(listItemx.getImageResourceId());
            //just making sure image is visible
            imageView.setVisibility(View.VISIBLE);
        }else{
            // means hiding the ImageView if not available
            imageView.setVisibility(View.GONE);
        }

        /*
        * setting the color for the container id*/
        LinearLayout container=(LinearLayout)listItemView.findViewById(R.id.container);
        int color = ContextCompat.getColor(getContext(),colorResourceId);
        container.setBackgroundColor(color);

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }

}