package com.zaraos.colorpop.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zaraos.colorpop.R;

/**
 * Created by Alex on 26/01/17.
 */

public class ListItemAdapter extends BaseAdapter {
    private LayoutInflater inflater;

    public ListItemAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 50;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fragment_list_item, parent, false);
            ViewHolder holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.title);
            convertView.setTag(holder);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.title.setText("List Item " + (position + 1));
        return convertView;
    }

    private static class ViewHolder {
        public TextView title;
    }
}