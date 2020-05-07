package net.sierrainfosys.pmo.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import net.sierrainfosys.pmo.R;
import net.sierrainfosys.pmo.adapter.ProjectsListAdapter;
import net.sierrainfosys.pmo.dto.ProjectDTO;

import java.util.ArrayList;

public class ProjectsFragment extends Fragment {

    private RecyclerView rvProjectList;
    private ProjectsListAdapter projectsListAdapter;
    private View rootView;
    private Context context;
    private ArrayList<ProjectDTO> projectDTOS = new ArrayList<>();
    public ProjectsFragment() {
        // Required empty public constructor
    }

    public static ProjectsFragment newInstance() {
        ProjectsFragment fragment = new ProjectsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_projects, container, false);
        context = getActivity();

        rvProjectList = (RecyclerView) rootView.findViewById(R.id.rv_project_list);
        rvProjectList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab_project);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddProjectDialog();
            }
        });

        //Test
        for (int i = 0; i < 3; i++) {
            ProjectDTO projectDTO = new ProjectDTO();
            if (i % 3 == 0) {
                projectDTO.setName("CHS");
                projectDTO.setManager("Parag Bhosale");
                projectDTO.setStatus("Active");
                projectDTO.setType("Mobile");
            } else if (i % 3 == 1) {
                projectDTO.setName("Qualtrics");
                projectDTO.setManager("Packiaraj");
                projectDTO.setStatus("Active");
                projectDTO.setType("Sales and Distribution");
            } else {
                projectDTO.setName("Vocera");
                projectDTO.setManager("Chidambaram Mani");
                projectDTO.setStatus("Inactive");
                projectDTO.setType("Finance");
            }
            projectDTOS.add(projectDTO);
        }

        projectsListAdapter = new ProjectsListAdapter(context, projectDTOS);
        rvProjectList.setAdapter(projectsListAdapter);
        projectsListAdapter.notifyDataSetChanged();

        return rootView;
    }

    public void showAddProjectDialog() {
        final Dialog dialog = new Dialog(context, R.style.CustomDialog);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);

        // retrieve display dimensions
        Rect displayRectangle = new Rect();
        Window window = ((Activity) context).getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);

        // inflate and adjust layout
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_layout_add_project, null);
        layout.setMinimumWidth((int) (displayRectangle.width() * 9f));
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(layout);
        dialog.show();

        EditText etName = (EditText) dialog.findViewById(R.id.et_name);
        EditText etType = (EditText) dialog.findViewById(R.id.et_type);
        EditText etManager = (EditText) dialog.findViewById(R.id.et_manager);

        TextView tvCancel = (TextView) dialog.findViewById(R.id.tv_cancel);
        Button btnAdd = (Button) dialog.findViewById(R.id.btn_add);

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}
