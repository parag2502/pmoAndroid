package net.sierrainfosys.pmo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.sierrainfosys.pmo.R;
import net.sierrainfosys.pmo.adapter.PmoProjectListAdapter;
import net.sierrainfosys.pmo.dto.PmoProjectDTO;

import java.util.ArrayList;

public class PmoDashboardFragment extends Fragment {

    private RecyclerView rvProjectList;
    private PmoProjectListAdapter pmoProjectListAdapter;
    private View rootView;
    private Context context;
    private ArrayList<PmoProjectDTO> pmoProjectDTOS = new ArrayList<>();

    public PmoDashboardFragment() {
        // Required empty public constructor
    }

    public static PmoDashboardFragment newInstance() {
        PmoDashboardFragment fragment = new PmoDashboardFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_pmo_dashboard, container, false);
        context = getActivity();

        rvProjectList = (RecyclerView) rootView.findViewById(R.id.rv_pmo_project_list);
        rvProjectList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        //Test
        for (int i = 0; i < 5; i++) {
            PmoProjectDTO pmoProjectDTO = new PmoProjectDTO();
            if (i % 5 == 0) {
                pmoProjectDTO.setName("CHS");
            } else if (i % 5 == 1) {
                pmoProjectDTO.setName("Vocera");
            } else if (i % 5 == 2) {
                pmoProjectDTO.setName("MOA");
            } else if (i % 5 == 3) {
                pmoProjectDTO.setName("Qualtrics");
            } else {
                pmoProjectDTO.setName("VM Ware");
            }
            pmoProjectDTOS.add(pmoProjectDTO);
        }

        pmoProjectListAdapter = new PmoProjectListAdapter(context, pmoProjectDTOS);
        rvProjectList.setAdapter(pmoProjectListAdapter);
        pmoProjectListAdapter.notifyDataSetChanged();

        return rootView;
    }

}
