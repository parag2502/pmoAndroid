package net.sierrainfosys.pmo.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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
import net.sierrainfosys.pmo.adapter.UsersListAdapter;
import net.sierrainfosys.pmo.dto.UserDTO;

import java.util.ArrayList;

public class UsersFragment extends Fragment {

    private RecyclerView rvUserList;
    private UsersListAdapter usersListAdapter;
    private View rootView;
    private Context context;
    private ArrayList<UserDTO> userDTOS = new ArrayList<>();

    public UsersFragment() {
        // Required empty public constructor
    }

    public static UsersFragment newInstance() {
        UsersFragment fragment = new UsersFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_users, container, false);
        context = getActivity();

        rvUserList = (RecyclerView) rootView.findViewById(R.id.rv_user_list);
        rvUserList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab_user);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddUserDialog();
            }
        });

        //Test
        for (int i = 0; i < 10; i++) {
            UserDTO userDTO = new UserDTO();
            if (i % 3 == 0) {
                userDTO.setName("Parag Bhosale");
                userDTO.setRole("Project Manager");
                userDTO.setStatus("Active");
                userDTO.setDesignation("Android Developer");
                userDTO.setLocation("Pune");
                userDTO.setEmail("p.bhosale@gmail.com");
            } else if (i % 3 == 1) {
                userDTO.setName("Chidambaram Mani");
                userDTO.setRole("Developer");
                userDTO.setStatus("Inactive");
                userDTO.setDesignation("PHP Developer");
                userDTO.setLocation("Pune");
                userDTO.setEmail("c.mani@gmail.com");
            } else {
                userDTO.setName("Packiaraj");
                userDTO.setRole("Admin");
                userDTO.setStatus("Active");
                userDTO.setDesignation("Tech Lead");
                userDTO.setLocation("Coimbatore");
                userDTO.setEmail("p.nallamuthu@gmail.com");
            }
            userDTOS.add(userDTO);
        }

        usersListAdapter = new UsersListAdapter(context, userDTOS);
        rvUserList.setAdapter(usersListAdapter);
        usersListAdapter.notifyDataSetChanged();

        return rootView;
    }

    public void showAddUserDialog() {
        final Dialog dialog = new Dialog(context, R.style.CustomDialog);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);

        // retrieve display dimensions
        Rect displayRectangle = new Rect();
        Window window = ((Activity) context).getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);

        // inflate and adjust layout
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_layout_add_user, null);
        layout.setMinimumWidth((int) (displayRectangle.width() * 9f));
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(layout);
        dialog.show();

        EditText etFirstName = (EditText) dialog.findViewById(R.id.et_first_name);
        EditText etLastName = (EditText) dialog.findViewById(R.id.et_last_name);
        EditText etEmail = (EditText) dialog.findViewById(R.id.et_email);
        EditText etPassword = (EditText) dialog.findViewById(R.id.et_password);
        EditText etLocation = (EditText) dialog.findViewById(R.id.et_location);
        EditText etDesignation = (EditText) dialog.findViewById(R.id.et_designation);
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
