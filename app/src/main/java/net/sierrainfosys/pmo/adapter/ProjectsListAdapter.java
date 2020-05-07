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
import net.sierrainfosys.pmo.dto.ProjectDTO;

import java.util.ArrayList;

public class ProjectsListAdapter extends RecyclerView.Adapter<ProjectsListAdapter.CustomViewHolder> {

    private ArrayList<ProjectDTO> projectDTOs = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private Context mContext;

    public ProjectsListAdapter(Context mContext, ArrayList<ProjectDTO> projectDTOs) {
        this.projectDTOs = projectDTOs;
        this.mContext = mContext;
        this.layoutInflater = LayoutInflater.from(mContext);
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {

        public View rootView;
        private ImageView ivEdit;
        private TextView tvName, tvType, tvStatus, tvManager;

        public CustomViewHolder(View itemView) {
            super(itemView);

            rootView = itemView;
            ivEdit = (ImageView) rootView.findViewById(R.id.iv_edit);
            tvName = (TextView) rootView.findViewById(R.id.tv_name);
            tvType = (TextView) rootView.findViewById(R.id.tv_type);
            tvManager = (TextView) rootView.findViewById(R.id.tv_manager);
            tvStatus = (TextView) rootView.findViewById(R.id.tv_status);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = layoutInflater.inflate(R.layout.layout_project_item, null);
        itemView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        CustomViewHolder customViewHolder = new CustomViewHolder(itemView);
        return customViewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, final int position) {
        final ProjectDTO projectDTO = projectDTOs.get(position);

        holder.tvName.setText(projectDTO.getName());
        if(projectDTO.getStatus().equalsIgnoreCase("Active")){
            holder.tvStatus.setTextColor(mContext.getResources().getColor(R.color.colorGreen));
        } else {
            holder.tvStatus.setTextColor(mContext.getResources().getColor(R.color.colorRed));
        }
        holder.tvStatus.setText(projectDTO.getStatus());
        holder.tvType.setText(projectDTO.getType());
        holder.tvManager.setText(projectDTO.getManager());
        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEditProjectDialog(projectDTO);
            }
        });
    }

    @Override
    public int getItemCount() {
        return projectDTOs.size();
    }

    public void showEditProjectDialog(ProjectDTO projectDTO) {
        final Dialog dialog = new Dialog(mContext, R.style.CustomDialog);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);

        // retrieve display dimensions
        Rect displayRectangle = new Rect();
        Window window = ((Activity) mContext).getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);

        // inflate and adjust layout
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_layout_edit_project, null);
        layout.setMinimumWidth((int) (displayRectangle.width() * 9f));
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(layout);
        dialog.show();

        EditText etName = (EditText) dialog.findViewById(R.id.et_name);
        EditText etType = (EditText) dialog.findViewById(R.id.et_type);
        EditText etManager = (EditText) dialog.findViewById(R.id.et_manager);
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

        etName.setText(projectDTO.getName());
        etType.setText(projectDTO.getType());
        etManager.setText(projectDTO.getManager());

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
