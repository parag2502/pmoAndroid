package net.sierrainfosys.pmo.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.Toast;

import net.sierrainfosys.pmo.R;
import net.sierrainfosys.pmo.adapter.ExpandableListNavAdapter;
import net.sierrainfosys.pmo.dto.MenuModel;
import net.sierrainfosys.pmo.fragment.KpiReportFragment;
import net.sierrainfosys.pmo.fragment.PmoDashboardFragment;
import net.sierrainfosys.pmo.fragment.ProjectsFragment;
import net.sierrainfosys.pmo.fragment.RolesAndPermissionFragment;
import net.sierrainfosys.pmo.fragment.UsersFragment;
import net.sierrainfosys.pmo.fragment.XoDashboardFragment;
import net.sierrainfosys.pmo.interfaces.OnFragmentNavigationListener;
import net.sierrainfosys.pmo.util.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnFragmentNavigationListener {

    private Context context;
    private Toolbar toolbar;
    private ExpandableListAdapter expandableListAdapter;
    private ExpandableListView expandableListView;
    private List<MenuModel> headerList = new ArrayList<>();
    private HashMap<MenuModel, List<MenuModel>> childList = new HashMap<>();
    private FragmentManager fragmentManager = null;
    private FrameLayout flFragmentContainer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(Constants.FRAG_TAG_USERS);
        setSupportActionBar(toolbar);

        context = this;

        expandableListView = findViewById(R.id.expandableListView);
        prepareMenuData();
        populateExpandableList();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        flFragmentContainer = (FrameLayout) findViewById(R.id.fl_fragment_container);

        fragmentManager = getSupportFragmentManager();
        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {

                // Update Screen action bar based on the current fragment
                Fragment currentFragment = fragmentManager.findFragmentById(R.id.fl_fragment_container);
                if (currentFragment != null) {
                    String currentFragmentTag = currentFragment.getTag();
                    if (currentFragmentTag != null) {
                        prepareActionBar(currentFragmentTag);
                    }
                }
            }
        });

        PmoDashboardFragment pmoDashboardFragment = PmoDashboardFragment.newInstance();
        switchFragment(pmoDashboardFragment, Constants.FRAG_TAG_PMO_DASHBOARD);
        /*UsersFragment usersFragment = UsersFragment.newInstance();
        switchFragment(usersFragment, Constants.FRAG_TAG_USERS);*/
    }

    public void prepareActionBar(String... params) {

        String fragmentTag = params[0];

        if (fragmentTag.equals(Constants.FRAG_TAG_PMO_DASHBOARD)) {
            toolbar.setTitle(Constants.FRAG_TAG_PMO_DASHBOARD);
        } else if (fragmentTag.equals(Constants.FRAG_TAG_XO_DASHBOARD)) {
            toolbar.setTitle(Constants.FRAG_TAG_XO_DASHBOARD);
        } else if (fragmentTag.equals(Constants.FRAG_TAG_KPI_REPORT)) {
            toolbar.setTitle(Constants.FRAG_TAG_KPI_REPORT);
        } else if (fragmentTag.equals(Constants.FRAG_TAG_PROJECTS)) {
            toolbar.setTitle(Constants.FRAG_TAG_PROJECTS);
        } else if (fragmentTag.equals(Constants.FRAG_TAG_USERS)) {
            toolbar.setTitle(Constants.FRAG_TAG_USERS);
        } else if (fragmentTag.equals(Constants.FRAG_TAG_ROLES_PERMISSION)) {
            toolbar.setTitle(Constants.FRAG_TAG_ROLES_PERMISSION);
        }
    }

    @Override
    public void switchFragment(Fragment fragment, String... params) {

        String fragmentTag = params[0];

        prepareActionBar(params);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (fragmentManager.findFragmentByTag(fragmentTag) != null) {
            fragmentManager.popBackStack();
            fragmentTransaction.add(flFragmentContainer.getId(), fragment, fragmentTag);
        }
        else {
            fragmentTransaction.add(flFragmentContainer.getId(), fragment, fragmentTag);
        }

        fragmentTransaction.addToBackStack(fragmentTag);
        fragmentTransaction.commit();
    }

    @Override
    public void popFragments(String fragmentTag) {

        fragmentManager.popBackStackImmediate(fragmentTag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            View view = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }

            FragmentManager fragmentManager = getSupportFragmentManager();
            Fragment currentFragment = fragmentManager.findFragmentById(flFragmentContainer.getId());
            if (currentFragment instanceof PmoDashboardFragment) {
                finish();
            }
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void prepareMenuData() {
        MenuModel menuModel = new MenuModel(Constants.NAV_MENU_DASHBOARD, true, true);
        headerList.add(menuModel);

        List<MenuModel> childModelsList = new ArrayList<>();
        MenuModel childModel = new MenuModel(Constants.FRAG_TAG_PMO_DASHBOARD, false, false);
        childModelsList.add(childModel);

        childModel = new MenuModel(Constants.FRAG_TAG_XO_DASHBOARD, false, false);
        childModelsList.add(childModel);

        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }

        menuModel = new MenuModel(Constants.NAV_MENU_REPORTS, true, true);
        headerList.add(menuModel);
        childModelsList = new ArrayList<>();
        childModel = new MenuModel(Constants.FRAG_TAG_KPI_REPORT, false, false);
        childModelsList.add(childModel);

        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }

        childModelsList = new ArrayList<>();
        menuModel = new MenuModel(Constants.NAV_MENU_SETTINGS, true, true);
        headerList.add(menuModel);
        childModel = new MenuModel(Constants.FRAG_TAG_PROJECTS, false, false);
        childModelsList.add(childModel);

        childModel = new MenuModel(Constants.FRAG_TAG_USERS, false, false);
        childModelsList.add(childModel);

        childModel = new MenuModel(Constants.FRAG_TAG_ROLES_PERMISSION, false, false);
        childModelsList.add(childModel);

        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }
    }

    private void populateExpandableList() {
        expandableListAdapter = new ExpandableListNavAdapter(this, headerList, childList);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                if (headerList.get(groupPosition).isGroup) {
                    if (!headerList.get(groupPosition).hasChildren) {
                        //Toast.makeText(context, headerList.get(groupPosition).menuName, Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }
                }

                return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                if (childList.get(headerList.get(groupPosition)) != null) {
                    MenuModel model = childList.get(headerList.get(groupPosition)).get(childPosition);
                    if (model.menuName.length() > 0) {
                        handlePageNavigation(model.menuName);
                        onBackPressed();
                    }
                }

                return false;
            }
        });
    }

    private void handlePageNavigation(String menuName){
        if(menuName.equalsIgnoreCase(Constants.FRAG_TAG_PMO_DASHBOARD)){
            PmoDashboardFragment pmoDashboardFragment = PmoDashboardFragment.newInstance();
            switchFragment(pmoDashboardFragment, Constants.FRAG_TAG_PMO_DASHBOARD);
        } else if(menuName.equalsIgnoreCase(Constants.FRAG_TAG_XO_DASHBOARD)){
            XoDashboardFragment xoDashboardFragment = XoDashboardFragment.newInstance();
            switchFragment(xoDashboardFragment, Constants.FRAG_TAG_XO_DASHBOARD);
        } else if(menuName.equalsIgnoreCase(Constants.FRAG_TAG_KPI_REPORT)){
            KpiReportFragment kpiReportFragment = KpiReportFragment.newInstance();
            switchFragment(kpiReportFragment, Constants.FRAG_TAG_KPI_REPORT);
        } else if(menuName.equalsIgnoreCase(Constants.FRAG_TAG_PROJECTS)){
            ProjectsFragment projectsFragment = ProjectsFragment.newInstance();
            switchFragment(projectsFragment, Constants.FRAG_TAG_PROJECTS);
        } else if(menuName.equalsIgnoreCase(Constants.FRAG_TAG_USERS)){
            UsersFragment usersFragment = UsersFragment.newInstance();
            switchFragment(usersFragment, Constants.FRAG_TAG_USERS);
        } else if(menuName.equalsIgnoreCase(Constants.FRAG_TAG_ROLES_PERMISSION)){
            RolesAndPermissionFragment rolesAndPermissionFragment = RolesAndPermissionFragment.newInstance();
            switchFragment(rolesAndPermissionFragment, Constants.FRAG_TAG_ROLES_PERMISSION);
        }
    }
}
