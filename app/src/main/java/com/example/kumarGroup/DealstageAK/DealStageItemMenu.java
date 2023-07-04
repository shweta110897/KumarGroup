package com.example.kumarGroup.DealstageAK;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.kumarGroup.R;
import com.google.android.material.tabs.TabLayout;

public class DealStageItemMenu extends AppCompatActivity {

    TabLayout dealstage_TabLayout;
    ViewPager2 dealstage_viewPager;
    DelStageMenuFragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_stage_item_menu);

        dealstage_TabLayout=findViewById(R.id.dealstage_TabLayout);
        dealstage_viewPager=findViewById(R.id.dealstage_ViewPager);


        FragmentManager fm =getSupportFragmentManager();
        adapter = new DelStageMenuFragmentAdapter(fm,getLifecycle());
        dealstage_viewPager.setAdapter(adapter);


        dealstage_TabLayout.addTab(dealstage_TabLayout.newTab().setText("Negotiation"));
        dealstage_TabLayout.addTab(dealstage_TabLayout.newTab().setText("Final"));

        dealstage_TabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                dealstage_viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        dealstage_viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                dealstage_TabLayout.selectTab(dealstage_TabLayout.getTabAt(position));
            }
        });


    }

    public class DelStageMenuFragmentAdapter extends FragmentStateAdapter {
        public DelStageMenuFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            Fragment fragment = null;
            if (position == 0)
            {
                fragment = new NegotiationINQ();
            }
            else if (position == 1)
            {
                fragment = new FinalINQ();
            }
            return fragment;

        }

        @Override
        public int getItemCount() {
            return 2;
        }
    }

}