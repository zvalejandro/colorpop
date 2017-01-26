package com.zaraos.colorpop.view.adapters;

import android.content.res.Resources;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zaraos.colorpop.presenter.utils.BundleInformerUtils;
import com.zaraos.colorpop.R;
import com.zaraos.colorpop.presenter.fragments.FragmentList;

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
        return 100;
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
            convertView = layout_inflater.inflate(R.layout.adapter_grid,
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
            holder.cirlce.setImageResource(R.drawable.green_circle);
        } else if (item_color == app_blue_color) {
            holder.cirlce.setImageResource(R.drawable.blue_circle);
        } else if (item_color == app_red_color) {
            holder.cirlce.setImageResource(R.drawable.red_circle);
        } else if (item_color == app_amber_color) {
            holder.cirlce.setImageResource(R.drawable.grey_circle);
        } else if (item_color == app_white_color) {
            holder.cirlce.setImageResource(R.drawable.white_circle);
        }
        convertView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                BundleInformerUtils informer = new BundleInformerUtils(
                        fragment_activity);
                informer.setCircleColor(item_color);
                boolean is_views_behind_status_bar = false;
                if (android.os.Build.VERSION.SDK_INT >= 19) {
                    is_views_behind_status_bar = true;
                }
                informer.setBaseView(holder.cirlce,
                        BundleInformerUtils.MODE_CENTER, is_views_behind_status_bar);
                FragmentList fragment = FragmentList.newInstance(null);
                informer.informColorPopFragment(fragment);
                FragmentTransaction transaction = fragment_activity
                        .getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(0, R.anim.abc_popup_exit, 0,
                        R.anim.abc_popup_exit);
                transaction.addToBackStack(null);
                transaction.add(android.R.id.content, fragment);
                transaction.commit();
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