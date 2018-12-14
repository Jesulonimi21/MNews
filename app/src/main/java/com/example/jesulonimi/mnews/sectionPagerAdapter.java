package com.example.jesulonimi.mnews;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class sectionPagerAdapter extends FragmentPagerAdapter {
    public sectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:TechNews techNews=new TechNews();
            return techNews;
            case 1:PoliticsNews politicsNews=new PoliticsNews();
            return politicsNews;
            case 2:GameNews gameNews=new GameNews();
            return gameNews;
            default:return null;
        }

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
      switch (position){
          case 0: return "Tech news";
          case 1 :return "Nigeria news";
          case 2:return  "Global news";
          default: return null;
      }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
