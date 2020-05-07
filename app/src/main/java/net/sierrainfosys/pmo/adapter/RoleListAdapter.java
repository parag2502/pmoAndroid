package net.sierrainfosys.pmo.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
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
import net.sierrainfosys.pmo.dto.RoleDTO;

import java.util.ArrayList;

public class RoleListAdapter extends RecyclerView.Adapter<RoleListAdapter.CustomViewHolder> {

    private ArrayList<RoleDTO> roleDTOS = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private Context mContext;

    public RoleListAdapter(Context mContext, ArrayList<RoleDTO> roleDTOS) {
        this.roleDTOS = roleDTOS;
        this.mContext = mContext;
        this.layoutInflater = LayoutInflater.from(mContext);
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {

        public View rootView;
        private ImageView ivEdit;
        private Button btnPermission;
        private TextView tvRole, tvStatus;

        public CustomViewHolder(View itemView) {
            super(itemView);

            rootView = itemView;
            ivEdit = (ImageView) rootView.findViewById(R.id.iv_edit);
            btnPermission = (Button) rootView.findViewById(R.id.btn_permission);
            tvRole = (TextView) rootView.findViewById(R.id.tv_role);
            tvStatus = (TextView) rootView.findViewById(R.id.tv_status);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = layoutInflater.inflate(R.layout.layout_role_item, null);
        itemView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        CustomViewHolder customViewHolder = new CustomViewHolder(itemView);
        return customViewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, final int position) {
        final RoleDTO roleDTO = roleDTOS.get(position);

        holder.tvRole.setText(roleDTO.getRole());
        if(roleDTO.getStatus().equalsIgnoreCase("Active")){
            holder.tvStatus.setTextColor(mContext.getResources().getColor(R.color.colorGreen));
        } else {
            holder.tvStatus.setTextColor(mContext.getResources().getColor(R.color.colorRed));
        }
        holder.tvStatus.setText(roleDTO.getStatus());
        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEditRoleDialog(roleDTO);
            }
        });
        holder.btnPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showEditProjectDialog(roleDTO);
            }
        });
    }

    @Override
    public int getItemCount() {
        return roleDTOS.size();
    }

    public void showEditRoleDialog(RoleDTO roleDTO) {
        final Dialog dialog = new Dialog(mContext, R.style.CustomDialog);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);

        // retrieve display dimensions
        Rect displayRectangle = new Rect();
        Window window = ((Activity) mContext).getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);

        // inflate and adjust layout
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_layout_edit_role, null);
        layout.setMinimumWidth((int) (displayRectangle.width() * 9f));
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(layout);
        dialog.show();

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

        etRole.setText(roleDTO.getRole());

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
