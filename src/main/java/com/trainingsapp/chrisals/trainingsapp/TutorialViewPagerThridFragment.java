package com.trainingsapp.chrisals.trainingsapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created by chris.als on 03.06.16.
 */
public class TutorialViewPagerThridFragment extends Fragment {

    @Override
    public ViewGroup onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstacesState) {

        ViewGroup FragmentThrid = (ViewGroup) inflater.inflate(R.layout.tutorial_fragment_third, container, false);

        return FragmentThrid;

    }

}
