package com.sanjeet.ahagurureducation.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.sanjeet.ahagurureducation.ui.fragments.StudentFragment;
import com.sanjeet.ahagurureducation.ui.fragments.TestFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {


    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Hardcoded in this order, you'll want to use lists and make sure the titles match
        switch (position) {
            case 0:
                return new StudentFragment();
            case 1:
                return new TestFragment();
            default:
                return null;

        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
