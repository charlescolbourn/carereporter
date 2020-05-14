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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;

public class ViewElementDiaryHourAdapter extends ArrayAdapter<ViewElementDiaryHour> {

    private Map<Integer,View> hourViews = new HashMap<>();

    public ViewElementDiaryHourAdapter(@NonNull Context context, List<ViewElementDiaryHour> elements) {
        super(context, 0, elements);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.v(ViewElementDiaryHourAdapter.class.getName(), String.format("Position called is %d", position));
        return hourViews.get(position) != null ? hourViews.get(position) : createNewHourView(position, parent);
    }

    private View createNewHourView(int position, ViewGroup parent) {
        ViewElementDiaryHour item = getItem(position);
        Log.v(ViewElementDiaryHourAdapter.class.getName(), "Time of item is " + item.getTime());
        View hourView = LayoutInflater.from(getContext()).inflate(R.layout.diary_hour_list_view, parent, false);
        TextView time = (TextView) hourView.findViewById(R.id.diary_view_hour_time);
        LinearLayout iconBox = (LinearLayout) hourView.findViewById(R.id.diary_view_hour_icon_space);
        Log.v(ViewElementDiaryHourAdapter.class.getName(), "Inserting " + item.getIcons().size() + " in time " + item.getTime() + " at position " + position);
        time.setText(item.getTime());

        for (Bitmap icon : item.getIcons()) {
            Log.v(ViewElementDiaryHourAdapter.class.getName(), "Adding icon in hour " + position);
            ImageView iconView = new ImageView(iconBox.getContext());
            iconView.setImageBitmap(icon);
            iconBox.addView(iconView);
        }

        hourViews.put(position,hourView);
        return hourView;
    }
}
