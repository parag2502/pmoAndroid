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
public class XoDashboardFragment extends Fragment {


    public XoDashboardFragment() {
        // Required empty public constructor
    }

    public static XoDashboardFragment newInstance() {
        XoDashboardFragment fragment = new XoDashboardFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_xo_dashboard, container, false);
    }

}
