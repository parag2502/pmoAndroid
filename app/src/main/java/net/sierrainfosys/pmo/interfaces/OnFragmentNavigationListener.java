package net.sierrainfosys.pmo.interfaces;

import android.support.v4.app.Fragment;

public interface OnFragmentNavigationListener {
    void switchFragment(Fragment fragment, String... params);
    void popFragments(String fragmentTag);
}
