package com.example.harkkatyo;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.harkkatyo.fragments.DeadFragment;
import com.example.harkkatyo.fragments.FightAreaFragment;
import com.example.harkkatyo.fragments.HomeFragment;
import com.example.harkkatyo.fragments.TrainingAreaFragment;

public class TabPagerAdapter extends FragmentStateAdapter {
    public TabPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new TrainingAreaFragment();
            case 2:
                return new FightAreaFragment();
            case 3:
                return new DeadFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
