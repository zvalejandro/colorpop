package com.zaraos.colorpop.presenter.activities;

import com.zaraos.colorpop.presenter.fragments.FragmentHome;

public class ActivityHome extends ActivityMain {

    @Override
    public void initFragment() {
        addFragment(FragmentHome.newInstance(null));
    }

}