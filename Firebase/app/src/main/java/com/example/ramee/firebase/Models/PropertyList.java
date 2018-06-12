package com.example.ramee.firebase.Models;

/**
 * Created by ramee on 28/03/2018.
 */

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Property;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ramee.firebase.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by ramee on 26/03/2018.
 */

public class PropertyList extends ArrayAdapter<Properties> {

    private Activity context;
    private List<Properties> propertyList;

    public PropertyList(Activity context, List<Properties> propertyList) {
        super(context, R.layout.list_layout, propertyList);
        this.context = context;
        this.propertyList = propertyList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView textViewLocation = (TextView)listViewItem.findViewById(R.id.textViewLocation);
        TextView textViewType = (TextView)listViewItem.findViewById(R.id.textViewType);
        TextView textViewRental = (TextView)listViewItem.findViewById(R.id.textViewRental);
        TextView textViewLength = (TextView)listViewItem.findViewById(R.id.textViewLength);
        TextView textViewAddress = (TextView)listViewItem.findViewById(R.id.textViewAddress);
        TextView textViewPostcode = (TextView)listViewItem.findViewById(R.id.textViewPostcode);
        TextView textViewManagementType = (TextView)listViewItem.findViewById(R.id.textViewManagementType);
        TextView textViewManagementName = (TextView)listViewItem.findViewById(R.id.textViewManagementName);


        Properties properties = propertyList.get(position);

        textViewLocation.setText(properties.getPropertyLocation());
        textViewType.setText(properties.getPropertyType());

        textViewRental.setText(properties.getPropertyRental());
        textViewLength.setText(properties.getPropertyLength());

        textViewAddress.setText(properties.getPropertyAddress());
        textViewPostcode.setText(properties.getPropertyPostcode());

        textViewManagementType.setText(properties.getManagementType());
        textViewManagementName.setText(properties.getManagementName());

        return listViewItem;
    }
}
