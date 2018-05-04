package com.example.jude.musicstructureapp;

import android.content.Context;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class CategoryAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public CategoryAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new SongsFragment();
        } else if (position == 1) {
            return new AuthorsFragment();
        } else {
            return new MusicPlayerFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.songs_fragment_name);
        } else if (position == 1) {
            return mContext.getString(R.string.authors_fragment_name);
        } else {
            return mContext.getString(R.string.music_player_fragment_name);
        }
    }
}
