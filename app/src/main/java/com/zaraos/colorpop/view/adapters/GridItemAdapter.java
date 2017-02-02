package com.zaraos.colorpop.view.adapters;

import android.content.res.Resources;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zaraos.colorpop.model.constants.MODE;
import com.zaraos.colorpop.R;
import com.zaraos.colorpop.presenter.fragments.FragmentList;
import com.zaraos.colorpop.presenter.utils.BundlePopUtils;

/**
 * Created by Alex on 26/01/17.
 */

public class GridItemAdapter extends BaseAdapter {
    FragmentActivity fragment_activity;
    private LayoutInflater layout_inflater;
    private int app_blue_color;
    private int app_green_color;
    private int app_red_color;
    private int app_amber_color;
    private int app_white_color;

    public GridItemAdapter(FragmentActivity fragment_activity) {
        this.fragment_activity = fragment_activity;
        layout_inflater = LayoutInflater.from(fragment_activity);
        Resources res = fragment_activity.getResources();
        app_blue_color = res.getColor(R.color.app_blue);
        app_green_color = res.getColor(R.color.app_green);
        app_red_color = res.getColor(R.color.app_red);
        app_amber_color = res.getColor(R.color.app_amber);
        app_white_color = res.getColor(R.color.app_white);
    }

    @Override
    public int getCount() {
        return 20;
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
            convertView = layout_inflater.inflate(R.layout.fragment_home_grid,
                    parent, false);
            ViewHolder holder = new ViewHolder();
            holder.cirlce = (ImageView) convertView.findViewById(R.id.circle);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            convertView.setTag(holder);
        }
        final int item_color = getItemColor(position);
        final ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.title.setText("Grid Item " + (position + 1));
        if (item_color == app_green_color) {
            holder.cirlce.setImageResource(R.drawable.shape_circle_green);
        } else if (item_color == app_blue_color) {
            holder.cirlce.setImageResource(R.drawable.shape_circle_blue);
        } else if (item_color == app_red_color) {
            holder.cirlce.setImageResource(R.drawable.shape_circle_red);
        } else if (item_color == app_amber_color) {
            holder.cirlce.setImageResource(R.drawable.shape_circle_amber);
        } else if (item_color == app_white_color) {
            holder.cirlce.setImageResource(R.drawable.shape_circle_white);
        }
        convertView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentList fragment = FragmentList.newInstance(null);
                BundlePopUtils.Builder.init(fragment_activity)
                        .setCircleColor(item_color)
                        .setBaseView(holder.cirlce, MODE.CENTER)
                        .informFragment(fragment);

                fragment_activity.getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(0, R.anim.popup_exit, 0, R.anim.popup_exit)
                        .addToBackStack(null)
                        .add(android.R.id.content, fragment)
                        .commit();
            }
        });
        return convertView;
    }

    private static class ViewHolder {
        ImageView cirlce;
        TextView title;
    }

    private int getItemColor(int pos) {
        if (pos == 1) {
            return app_green_color;
        }
        if (pos == 2) {
            return app_white_color;
        }
        if (pos % 6 == 0) {
            return app_amber_color;
        } else if (pos % 4 == 0) {
            return app_blue_color;
        } else if (pos % 3 == 0) {
            return app_green_color;
        }
        return app_red_color;
    }
}