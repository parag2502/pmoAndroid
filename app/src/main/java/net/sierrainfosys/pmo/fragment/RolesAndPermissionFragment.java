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
import net.sierrainfosys.pmo.adapter.RoleListAdapter;
import net.sierrainfosys.pmo.dto.RoleDTO;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RolesAndPermissionFragment extends Fragment {

    private RecyclerView rvRoleList;
    private RoleListAdapter roleListAdapter;
    private View rootView;
    private Context context;
    private ArrayList<RoleDTO> roleDTOS = new ArrayList<>();

    public RolesAndPermissionFragment() {
        // Required empty public constructor
    }

    public static RolesAndPermissionFragment newInstance() {
        RolesAndPermissionFragment fragment = new RolesAndPermissionFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_roles_and_permission, container, false);
        context = getActivity();

        rvRoleList = (RecyclerView) rootView.findViewById(R.id.rv_role_list);
        rvRoleList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab_role);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddRoleDialog();
            }
        });

        //Test
        for (int i = 0; i < 10; i++) {
            RoleDTO roleDTO = new RoleDTO();
            if (i % 3 == 0) {
                roleDTO.setRole("Project Manager");
                roleDTO.setStatus("Active");
            } else if (i % 3 == 1) {
                roleDTO.setRole("Developer");
                roleDTO.setStatus("Inactive");
            } else {
                roleDTO.setRole("Admin");
                roleDTO.setStatus("Active");
            }
            roleDTOS.add(roleDTO);
        }

        roleListAdapter = new RoleListAdapter(context, roleDTOS);
        rvRoleList.setAdapter(roleListAdapter);
        roleListAdapter.notifyDataSetChanged();

        return rootView;
    }

    public void showAddRoleDialog() {
        final Dialog dialog = new Dialog(context, R.style.CustomDialog);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);

        // retrieve display dimensions
        Rect displayRectangle = new Rect();
        Window window = ((Activity) context).getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);

        // inflate and adjust layout
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_layout_add_role, null);
        layout.setMinimumWidth((int) (displayRectangle.width() * 9f));
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(layout);
        dialog.show();

        EditText etRole = (EditText) dialog.findViewById(R.id.et_role);

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
