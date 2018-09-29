package com.example.zq.play_android.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.TextView;
import android.widget.Toast;

import com.example.zq.play_android.R;
import com.example.zq.play_android.base.BaseActivity;

import com.example.zq.play_android.home.login.LoginActivity;
import com.example.zq.play_android.knowledge.KnowledgeFragment;
import com.example.zq.play_android.navigation.NavigationFragment;
import com.example.zq.play_android.project.ProjectFragment;
import com.hjm.bottomtabbar.BottomTabBar;


public class HomeActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private BottomTabBar bottomtab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomtab = (BottomTabBar) findViewById(R.id.bottomtab);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //todo 获取头部局
        View headerView = navigationView.getHeaderView(0);
        TextView nav_login = headerView.findViewById(R.id.nav_login);
        nav_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        initData();
    }

    private void initData() {
        bottomtab.init(getSupportFragmentManager())
                .setImgSize(70,70)
                .setFontSize(10)
                .setTabPadding(4,6,10)
                .setChangeColor(getResources().getColor(R.color.home_huang), Color.DKGRAY)
                .addTabItem("首页",R.drawable.icon_home_pager_selected,R.drawable.icon_home_pager_not_selected, HomeFragment.class)
                .addTabItem("知识体系",R.drawable.icon_knowledge_hierarchy_selected,R.drawable.icon_knowledge_hierarchy_not_selected ,KnowledgeFragment.class)
                .addTabItem("导航",R.drawable.icon_navigation_selected,R.drawable.icon_navigation_not_selected, NavigationFragment.class)
                .addTabItem("项目",R.drawable.icon_project_selected,R.drawable.icon_project_not_selected, ProjectFragment.class)
                .isShowDivider(false)
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name) {
                        switch (position){
                            case R.layout.fragment_home:
                                break;
                            case R.layout.fragment_knowledge:
                                break;
                            case R.layout.fragment_navigation:
                                break;
                            case R.layout.fragment_project:
                                break;
                        }
                    }
                });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_wanAndroid) {
            // Handle the camera action
            Toast.makeText(this, "首页", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_collection) {
            Toast.makeText(this, "收藏", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_gear) {
            Toast.makeText(this, "设置", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_about) {
            Toast.makeText(this, "关于我们", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_exit) {
            Toast.makeText(this, "退出登录", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
