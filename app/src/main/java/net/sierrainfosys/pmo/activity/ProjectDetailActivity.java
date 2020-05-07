package net.sierrainfosys.pmo.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.sierrainfosys.pmo.R;

public class ProjectDetailActivity extends AppCompatActivity {

    private Context context;
    private TextView tvProjectName;
    private LinearLayout layoutPreparation, layoutDesign, layoutDevelopment, layoutTesting, layoutImplementation;
    private LinearLayout layoutExpPreparation, layoutExpDesign, layoutExpDevelopment, layoutExpTesting, layoutExpImplementation;
    private boolean isVisiblePreparation = false, isVisibleDesign = false,
            isVisibleDevelopment = false, isVisibleTesting = false, isVisibleImplementation = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);
        context = this;

        String strProjectName = getIntent().getStringExtra("project_name");

        tvProjectName = findViewById(R.id.tv_project_name);

        layoutPreparation = findViewById(R.id.layout_preparation);
        layoutDesign = findViewById(R.id.layout_design);
        layoutDevelopment = findViewById(R.id.layout_development);
        layoutTesting = findViewById(R.id.layout_testing);
        layoutImplementation = findViewById(R.id.layout_implementation);

        layoutExpPreparation = findViewById(R.id.layout_expand_preparation);
        layoutExpDesign = findViewById(R.id.layout_expand_design);
        layoutExpDevelopment = findViewById(R.id.layout_expand_development);
        layoutExpTesting = findViewById(R.id.layout_expand_testing);
        layoutExpImplementation = findViewById(R.id.layout_expand_implementation);

        layoutPreparation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isVisiblePreparation){
                    layoutExpPreparation.setVisibility(View.GONE);
                } else {
                    layoutExpPreparation.setVisibility(View.VISIBLE);
                }
                isVisiblePreparation = !isVisiblePreparation;
            }
        });

        layoutDesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isVisibleDesign){
                    layoutExpDesign.setVisibility(View.GONE);
                } else {
                    layoutExpDesign.setVisibility(View.VISIBLE);
                }
                isVisibleDesign = !isVisibleDesign;
            }
        });

        layoutDevelopment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isVisibleDevelopment){
                    layoutExpDevelopment.setVisibility(View.GONE);
                } else {
                    layoutExpDevelopment.setVisibility(View.VISIBLE);
                }
                isVisibleDevelopment = !isVisibleDevelopment;
            }
        });

        layoutTesting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isVisibleTesting){
                    layoutExpTesting.setVisibility(View.GONE);
                } else {
                    layoutExpTesting.setVisibility(View.VISIBLE);
                }
                isVisibleTesting = !isVisibleTesting;
            }
        });

        layoutImplementation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isVisibleImplementation){
                    layoutExpImplementation.setVisibility(View.GONE);
                } else {
                    layoutExpImplementation.setVisibility(View.VISIBLE);
                }
                isVisibleImplementation = !isVisibleImplementation;
            }
        });

        tvProjectName.setText(strProjectName);
    }
}
