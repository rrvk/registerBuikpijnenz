package nl.rrvk.etenenzmeetingen;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import nl.rrvk.etenenzmeetingen.adapter.DrawerAdapter;
import nl.rrvk.etenenzmeetingen.fragments.FragmentPoep;
import nl.rrvk.etenenzmeetingen.listeners.DrawerListener;
import nl.rrvk.etenenzmeetingen.utils.FragmentUtils;

public class MainActivity extends AppCompatActivity {
    // the toolbar is the main bar with the titel etc
    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private RecyclerView mDrawerListView;
    private DrawerAdapter mAdapter;
    private String[] mDrawerItems;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDrawer();
        initToolbar();
        determineFragment();
    }

    public void toggleDrawer() {
        if (mDrawerLayout.isDrawerOpen(mDrawerListView)) {
            mDrawerLayout.closeDrawer(mDrawerListView);
        } else {
            mDrawerLayout.openDrawer(mDrawerListView);
        }
    }

    private void initDrawer(){
        //mDrawerIcons = getResources().obtainTypedArray(R.array.nav_icons);
        mDrawerItems = getResources().getStringArray(R.array.drawer_options);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerListView = (RecyclerView) findViewById(R.id.left_drawer);
        //mAdapter = new DrawerAdapter(mDrawerItems, mDrawerIcons, new DrawerListener(getApplicationContext(), mDrawerLayout));
        mAdapter = new DrawerAdapter(mDrawerItems, new DrawerListener(getApplicationContext(), mDrawerLayout));
        mDrawerListView.setHasFixedSize(true);
        mDrawerListView.setAdapter(mAdapter);
        mDrawerListView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleDrawer();
            }
        });
    }

    private void determineFragment() {
        mFragmentManager = getSupportFragmentManager();
        FragmentUtils.init(mFragmentManager);

        Fragment fragment = new FragmentPoep();
        FragmentUtils.replace(fragment);
    }

    public void setDrawerHeader(String title){
        mAdapter.setHeader(title);
    }
}
