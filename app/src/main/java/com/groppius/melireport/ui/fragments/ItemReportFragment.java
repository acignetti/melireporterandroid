package com.groppius.melireport.ui.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.groppius.melireport.R;
import com.groppius.melireport.ui.ReportMainActivity;

/**
 * Created by julio on 12/10/14.
 */
public class ItemReportFragment extends Fragment {

    protected static final String ARG_SECTION_NUMBER = "section_number";

    Button buttonGenerate;
    Button buttonSave;

    public static ItemReportFragment newInstance(int sectionNumber) {
        ItemReportFragment fragment = new ItemReportFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public ItemReportFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_item_filter, container, false);

        buttonGenerate = (Button) rootView.findViewById(R.id.button_generate_report_item);
        buttonGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = WebViewFragment.newInstance();
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, fragment)
                        .commit();
            }
        });

        buttonSave =  (Button) rootView.findViewById(R.id.button_save_item);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((ReportMainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }
}

