package com.zaraos.colorpop.presenter.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.zaraos.colorpop.model.PopInformer;
import com.zaraos.colorpop.model.constants.POPAPI;
import com.zaraos.colorpop.presenter.activities.ActivityDetail;
import com.zaraos.colorpop.presenter.utils.BundlePopUtils;
import com.zaraos.colorpop.presenter.utils.ColorPopUtils;
import com.zaraos.colorpop.presenter.utils.ColorUtils;
import com.zaraos.colorpop.view.adapters.GridItemAdapter;
import com.zaraos.colorpop.R;

/**
 * Created by Alex on 25/01/17.
 */

public class FragmentHome extends Fragment implements Toolbar.OnMenuItemClickListener {

    public static FragmentHome newInstance(Bundle args) {
        FragmentHome fragmentHome = new FragmentHome();
        fragmentHome.setArguments(null);
        fragmentHome.setHasOptionsMenu(true);
        return fragmentHome;
    }

    private View rootView;
    private Toolbar toolbar;
    private GridView gridView;
    private FloatingActionButton fab;
    private GridItemAdapter adapter;
    private ImageButton btn1;
    private ImageView btn2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        if (android.os.Build.VERSION.SDK_INT >= 19) {
            rootView.setBackgroundColor(ColorUtils.get(R.color.blue_grey_800));
            rootView.setPadding(0, ColorPopUtils.getStatusBarHeightPixels(), 0, 0);
        }

        initResources();
        return rootView;
    }

    private void initResources() {
        toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        gridView = (GridView) rootView.findViewById(R.id.list);
        fab = (FloatingActionButton) rootView.findViewById(R.id.floating_button);

        btn1 = (ImageButton) rootView.findViewById(R.id.btn_1);
        btn2 = (ImageView) rootView.findViewById(R.id.btn_2);
        btn1.setOnClickListener(btnOneClickListener());
        btn2.setOnClickListener(btnTwoClickListener());
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setTitle("AndroidColorPop");
        toolbar.inflateMenu(R.menu.main);
        toolbar.setOnMenuItemClickListener(this);

        fab.setOnClickListener(getOnClickListener());
        adapter = new GridItemAdapter(getActivity());
        gridView.setAdapter(adapter);
    }

    private OnClickListener getOnClickListener() {
        return new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityDetail.class);
                intent.putExtra(POPAPI.POP_INFORMER, new PopInformer(v));
                getActivity().startActivity(intent);
            }
        };
    }

    private OnClickListener btnOneClickListener(){
        return new OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = FragmentDetail.newInstance(null);

                BundlePopUtils.Builder.init(getActivity())
                        .setCircleColor(ColorUtils.get(R.color.app_green))
                        //.setPageColor(Color.WHITE)
                        .setBaseView(new PopInformer(v), POPAPI.POP_MODE_CENTER)
                        .informFragment(fragment);

                getActivity().getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(0, R.anim.popup_exit, 0, R.anim.popup_exit)
                        .replace(android.R.id.content, fragment)
                        .commit();
            }
        };
    }

    private OnClickListener btnTwoClickListener(){
        return new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityDetail.class);
                intent.putExtra(POPAPI.POP_INFORMER, new PopInformer(v));
                getActivity().startActivity(intent);
            }
        };
    }

    @Override
    public boolean onMenuItemClick(MenuItem arg0) {
        FragmentCover fragment = FragmentCover.newInstance(null);
        View toolbarView = toolbar.findViewById(arg0.getItemId());

        BundlePopUtils.Builder.init(getActivity())
                .setBaseView(toolbarView, POPAPI.POP_MODE_CENTER)
                .informFragment(fragment);

        getActivity().getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(0, R.anim.popup_exit, 0, R.anim.popup_exit)
                .addToBackStack(null)
                .add(android.R.id.content, fragment)
                .commit();

        return true;
    }
}
