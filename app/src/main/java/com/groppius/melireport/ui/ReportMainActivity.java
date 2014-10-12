package com.groppius.melireport.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.groppius.melireport.synchro.SyncTask;
import com.groppius.melireport.ui.fragments.CategoryReportFragment;
import com.groppius.melireport.ui.fragments.ItemReportFragment;
import com.groppius.melireport.ui.fragments.LocationReportFragment;
import com.groppius.melireport.ui.fragments.NavigationDrawerFragment;
import com.groppius.melireport.R;
import com.groppius.melireport.entities.buyer.Buyer;
import com.groppius.melireport.entities.buyer.BuyerRepository;
import com.groppius.melireport.ui.fragments.PaymentTypeReportFragment;


public class ReportMainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_main);

        SyncTask syncTask = new SyncTask(this);
        syncTask.execute();

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));


    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        Fragment reportFragment = null;
        switch (position) {
            case 1:
                mTitle = getString(R.string.title_section_item);
                reportFragment = (ItemReportFragment)
                        getFragmentManager().findFragmentById(R.id.navigation_drawer);
                break;
            case 2:
                mTitle = getString(R.string.title_section_category);
                reportFragment = (CategoryReportFragment)
                        getFragmentManager().findFragmentById(R.id.navigation_drawer);
                break;
            case 3:
                mTitle = getString(R.string.title_section_payment_type);
                reportFragment = (PaymentTypeReportFragment)
                        getFragmentManager().findFragmentById(R.id.navigation_drawer);
                break;
            case 4:
                mTitle = getString(R.string.title_section_location);
                reportFragment = (LocationReportFragment)
                        getFragmentManager().findFragmentById(R.id.navigation_drawer);
                break;
        }

        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        Fragment reportFragment = null;
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section_item);
                reportFragment = (ItemReportFragment)
                        getFragmentManager().findFragmentById(R.id.navigation_drawer);
                break;
            case 2:
                mTitle = getString(R.string.title_section_category);
                reportFragment = (CategoryReportFragment)
                        getFragmentManager().findFragmentById(R.id.navigation_drawer);
                break;
            case 3:
                mTitle = getString(R.string.title_section_payment_type);
                reportFragment = (PaymentTypeReportFragment)
                        getFragmentManager().findFragmentById(R.id.navigation_drawer);
                break;
            case 4:
                mTitle = getString(R.string.title_section_location);
                reportFragment = (LocationReportFragment)
                        getFragmentManager().findFragmentById(R.id.navigation_drawer);
                break;
        }

    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.report_main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
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

}
