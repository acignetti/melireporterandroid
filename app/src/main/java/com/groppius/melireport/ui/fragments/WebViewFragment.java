package com.groppius.melireport.ui.fragments;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;

import com.groppius.melireport.R;
import com.groppius.melireport.ui.ReportMainActivity;

import java.net.URL;
import java.util.List;

/**
 * Created by julio on 12/10/14.
 */
public class WebViewFragment extends Fragment {
    protected static final String ARG_SECTION_NUMBER = "section_number";
    private Context context;
    public static WebViewFragment newInstance() {
        WebViewFragment fragment = new WebViewFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_item_report, container, false);

        WebView webView = (WebView) rootView.findViewById(R.id.meliWebView);
        webView.loadUrl("http://192.168.43.13/index.php/report/TT726719/0497eafa43804d8728d41d91d1c862cd/html/00");

        Button buttonPdf = (Button) rootView.findViewById(R.id.button_get_pdf);
        buttonPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isDownloadManagerAvailable(getActivity().getApplicationContext())) {
                    String url = "http://192.168.43.13/index.php/report/TT726719/0497eafa43804d8728d41d91d1c862cd/pdf/00";
                    DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                    request.setDescription("Some descrition");
                    request.setTitle("Some title");

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                        request.allowScanningByMediaScanner();
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    }
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "report.pdf");

                    DownloadManager manager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
                    manager.enqueue(request);

                }
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public static boolean isDownloadManagerAvailable(Context context) {
        try {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD) {
                return false;
            }
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.setClassName("com.android.providers.downloads.ui", "com.android.providers.downloads.ui.DownloadList");
            List<ResolveInfo> list = context.getPackageManager().queryIntentActivities(intent,
                    PackageManager.MATCH_DEFAULT_ONLY);
            return list.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }
}
