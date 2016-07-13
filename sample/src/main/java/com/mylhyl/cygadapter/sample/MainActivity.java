package com.mylhyl.cygadapter.sample;

import android.content.ContentUris;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements MainFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, MainFragment.newInstance())
                    .commitAllowingStateLoss();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Fragment fragment = null;
        int type = (int) ContentUris.parseId(uri);
        switch (type) {
            case 0:
                fragment = SimpleFragment.newInstance();
                break;
            case 1:
                fragment = MultiViewTypeFragment.newInstance();
                break;
            case 2:
                fragment = CheckedChangedFragment.newInstance();
                break;
            case 3:
                fragment = CheckedClickFragment.newInstance();
                break;
            case 4:
                fragment = CheckedChoiceModeFragment.newInstance();
                break;
        }
        if (fragment != null)
            getSupportFragmentManager().beginTransaction()
                    .replace(android.R.id.content, fragment)
                    .addToBackStack(null)
                    .commitAllowingStateLoss();
    }
}
