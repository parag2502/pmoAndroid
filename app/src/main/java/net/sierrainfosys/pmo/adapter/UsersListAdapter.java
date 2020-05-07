package net.sierrainfosys.pmo.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import net.sierrainfosys.pmo.R;
import net.sierrainfosys.pmo.dto.UserDTO;

import java.util.ArrayList;

public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.CustomViewHolder> {

    private ArrayList<UserDTO> userDTOS = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private Context mContext;

    public UsersListAdapter(Context mContext, ArrayList<UserDTO> userDTOS) {
        this.userDTOS = userDTOS;
        this.mContext = mContext;
        this.layoutInflater = LayoutInflater.from(mContext);
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {

        public View rootView;
        private ImageView ivEdit;
        private TextView tvName, tvRole, tvDesignation, tvStatus, tvLocation, tvEmail;

        public CustomViewHolder(View itemView) {
            super(itemView);

            rootView = itemView;
            ivEdit = (ImageView) rootView.findViewById(R.id.iv_edit);
            tvName = (TextView) rootView.findViewById(R.id.tv_name);
            tvRole = (TextView) rootView.findViewById(R.id.tv_role);
            tvDesignation = (TextView) rootView.findViewById(R.id.tv_designation);
            tvStatus = (TextView) rootView.findViewById(R.id.tv_status);
            tvLocation = (TextView) rootView.findViewById(R.id.tv_location);
            tvEmail = (TextView) rootView.findViewById(R.id.tv_email);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = layoutInflater.inflate(R.layout.layout_user_item, null);
        itemView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        CustomViewHolder customViewHolder = new CustomViewHolder(itemView);
        return customViewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, final int position) {
        final UserDTO userDTO = userDTOS.get(position);

        holder.tvName.setText(userDTO.getName());
        holder.tvRole.setText(userDTO.getRole());
        if(userDTO.getStatus().equalsIgnoreCase("Active")){
            holder.tvStatus.setTextColor(mContext.getResources().getColor(R.color.colorGreen));
        } else {
            holder.tvStatus.setTextColor(mContext.getResources().getColor(R.color.colorRed));
        }
        holder.tvStatus.setText(userDTO.getStatus());
        holder.tvDesignation.setText(userDTO.getDesignation());
        holder.tvLocation.setText(userDTO.getLocation());
        holder.tvEmail.setText(userDTO.getEmail());
        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEditUserDialog(userDTO);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userDTOS.size();
    }

    public void showEditUserDialog(UserDTO userDTO) {
        final Dialog dialog = new Dialog(mContext, R.style.CustomDialog);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);

        // retrieve display dimensions
        Rect displayRectangle = new Rect();
        Window window = ((Activity) mContext).getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);

        // inflate and adjust layout
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_layout_edit_user, null);
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
        final RadioButton rbActive = (RadioButton) dialog.findViewById(R.id.rb_active);
        final RadioButton rbInactive = (RadioButton) dialog.findViewById(R.id.rb_inactive);

        rbActive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rbActive.setChecked(true);
                rbInactive.setChecked(false);
            }
        });

        rbInactive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rbActive.setChecked(false);
                rbInactive.setChecked(true);
            }
        });

        rbActive.setChecked(true);

        etFirstName.setText(userDTO.getName());
        etEmail.setText(userDTO.getEmail());
        etLocation.setText(userDTO.getLocation());
        etDesignation.setText(userDTO.getDesignation());
        etRole.setText(userDTO.getRole());

        TextView tvCancel = (TextView) dialog.findViewById(R.id.tv_cancel);
        Button btnUpdate = (Button) dialog.findViewById(R.id.btn_update);

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}
