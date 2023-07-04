package com.example.kumarGroup.Workshop.General;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.kumarGroup.Workshop.General.CompalinFragment.AllComplainFragment;
import com.example.kumarGroup.Workshop.General.CompalinFragment.ClearComplainFragment;
import com.example.kumarGroup.Workshop.General.CompalinFragment.PendingComplainFragment;
import com.example.kumarGroup.Workshop.General.CompalinFragment.RemarkComplainFragment;

public class ComplainFragmentAdapter extends FragmentStateAdapter {


    public ComplainFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {
            case 1:
                Log.d("1st","1st");
                return new AllComplainFragment();
            case 2:
                Log.d("2st","2st");
                return new ClearComplainFragment();
            case 3:
                Log.d("2st","2st");
                return new PendingComplainFragment();
            case 4:
                Log.d("2st","2st");
                return new RemarkComplainFragment();
        }
        Log.d("elsee","elseee");
        return new RemarkComplainFragment();
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
