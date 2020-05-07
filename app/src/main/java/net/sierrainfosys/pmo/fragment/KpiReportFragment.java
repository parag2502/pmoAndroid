package net.sierrainfosys.pmo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.sierrainfosys.pmo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class KpiReportFragment extends Fragment {


    public KpiReportFragment() {
        // Required empty public constructor
    }

    public static KpiReportFragment newInstance() {
        KpiReportFragment fragment = new KpiReportFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kpi_report, container, false);
    }

}
