package com.example.administrator.cniaoshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.administrator.cniaoshop.bean.Tab;
import com.example.administrator.cniaoshop.fragment.CartFragment;
import com.example.administrator.cniaoshop.fragment.CategoryFragment;
import com.example.administrator.cniaoshop.fragment.HomeFragment;
import com.example.administrator.cniaoshop.fragment.HotFragment;
import com.example.administrator.cniaoshop.fragment.MineFragment;
import com.example.administrator.cniaoshop.widget.FragmentTabHost;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FragmentTabHost mTabhost;
    private LayoutInflater inflater;
    private List<Tab> tabs = new ArrayList<>(5);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTab();

    }

    //初始化FragmentTabhost
    private void initTab() {
        Tab tab_home = new Tab(HomeFragment.class, R.string.home, R.drawable.selector_icon_home);
        Tab tab_hot = new Tab(HotFragment.class, R.string.hot, R.drawable.selector_icon_hot);
        Tab tab_category = new Tab(CategoryFragment.class, R.string.catagory, R.drawable.selector_icon_category);
        Tab tab_cart = new Tab(CartFragment.class, R.string.cart, R.drawable.selector_icon_cart);
        Tab tab_mine = new Tab(MineFragment.class, R.string.mine, R.drawable.selector_icon_mine);

        tabs.add(tab_home);
        tabs.add(tab_hot);
        tabs.add(tab_category);
        tabs.add(tab_cart);
        tabs.add(tab_mine);

        inflater = LayoutInflater.from(this);
        mTabhost = findViewById(R.id.tabhost);
        mTabhost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        for (Tab tab : tabs) {
            TabHost.TabSpec tabSpec = mTabhost.newTabSpec(getString(tab.getTitle()));
            tabSpec.setIndicator(buildIndicator(tab));
            mTabhost.addTab(tabSpec, tab.getFragment(), null);

        }
        mTabhost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        mTabhost.setCurrentTab(0);
    }

    private View buildIndicator(Tab tab) {
        View view = inflater.inflate(R.layout.tab_indicator, null);
        ImageView imageView = view.findViewById(R.id.icon_tab);
        TextView textView = view.findViewById(R.id.txt_indicator);

        imageView.setBackgroundResource(tab.getIcon());
        textView.setText(tab.getTitle());
        return view;
    }

}
