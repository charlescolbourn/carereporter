package net.colbourn.carepriorities.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.colbourn.carepriorities.R;
import net.colbourn.carepriorities.model.DiaryEvent;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class ViewElementDiaryHourAdapter extends ArrayAdapter<ViewElementDiaryHour> {

    public ViewElementDiaryHourAdapter(@NonNull Context context, List<ViewElementDiaryHour> elements) {
        super(context, 0, elements);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewElementDiaryHour item = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.diary_hour_list_view, parent, false);
        }

        TextView time = (TextView) convertView.findViewById(R.id.diary_view_hour_time);
        LinearLayout iconBox = (LinearLayout) convertView.findViewById(R.id.diary_view_hour_icon_space);

        time.setText(item.getTime());
        for (Bitmap icon : item.getIcons()) {

            ImageView iconView = new ImageView(iconBox.getContext());
            iconView.setImageBitmap(icon);
            iconBox.addView(iconView);

        }
        return convertView;
    }
}
