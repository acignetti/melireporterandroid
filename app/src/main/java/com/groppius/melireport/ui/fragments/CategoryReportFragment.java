package com.groppius.melireport.ui.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.groppius.melireport.R;
import com.groppius.melireport.ui.ReportMainActivity;

/**
 * Created by julio on 12/10/14.
 */
public class CategoryReportFragment extends Fragment {

    protected static final String ARG_SECTION_NUMBER = "section_number";

    public static CategoryReportFragment newInstance(int sectionNumber) {
        CategoryReportFragment fragment = new CategoryReportFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public CategoryReportFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_report_main, container, false);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((ReportMainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }
}
