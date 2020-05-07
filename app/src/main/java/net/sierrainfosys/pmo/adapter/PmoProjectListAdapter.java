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
import net.sierrainfosys.pmo.activity.ProjectDetailActivity;
import net.sierrainfosys.pmo.dto.PmoProjectDTO;

import java.util.ArrayList;

public class PmoProjectListAdapter extends RecyclerView.Adapter<PmoProjectListAdapter.CustomViewHolder> {

    private ArrayList<PmoProjectDTO> projectDTOs = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private Context mContext;

    public PmoProjectListAdapter(Context mContext, ArrayList<PmoProjectDTO> projectDTOs) {
        this.projectDTOs = projectDTOs;
        this.mContext = mContext;
        this.layoutInflater = LayoutInflater.from(mContext);
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {

        public View rootView;
        private ImageView ivOverallHealth, ivScope, ivSchedule, ivBudget, ivRiskIssues;
        private TextView tvName;

        public CustomViewHolder(View itemView) {
            super(itemView);

            rootView = itemView;
            //ivOverallHealth = (ImageView) rootView.findViewById(R.id.iv_overall_health);
            tvName = (TextView) rootView.findViewById(R.id.tv_name);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = layoutInflater.inflate(R.layout.layout_pmo_project_item, null);
        itemView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        CustomViewHolder customViewHolder = new CustomViewHolder(itemView);
        return customViewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, final int position) {
        final PmoProjectDTO projectDTO = projectDTOs.get(position);

        holder.tvName.setText(projectDTO.getName());

        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, ProjectDetailActivity.class);
                i.putExtra("project_name", projectDTO.getName());
                mContext.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return projectDTOs.size();
    }

}
