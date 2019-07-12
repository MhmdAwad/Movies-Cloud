package com.mhmdawad.moviescloud.MoviesFragments;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentAdapter  extends FragmentPagerAdapter {


    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        if (i == 0)
            return new PopularFragment();

         else if (i == 1)
            return new NowPlayingFragment();

         else if (i == 2)
            return new TopRatedFragment();

         else
            return new UpcomingFragment();



    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0)
            return "Popular";

        else if (position == 1)
            return "Now Playing";

        else if (position == 2)
            return "Top Rated";

        else
            return "UpComing";

    }
}
