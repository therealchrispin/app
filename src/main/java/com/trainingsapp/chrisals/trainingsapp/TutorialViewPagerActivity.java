package com.trainingsapp.chrisals.trainingsapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class TutorialViewPagerActivity extends FragmentActivity {

    private static final int NUM_PAGES = 3;
    private ViewPager tPager;
    private PagerAdapter tAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_view_pager);

        tPager = (ViewPager) findViewById(R.id.viewpager);
        tAdapter = new TutorialViewPagerAdapter(getSupportFragmentManager());
        tPager.setAdapter(tAdapter);

        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.check(radioGroup.getChildAt(0).getId());
        radioGroup.getChildAt(0).setScaleX((float) 1.3);
        radioGroup.getChildAt(0).setScaleY((float) 1.3);

        tPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                radioGroup.check(radioGroup.getChildAt(position).getId());
                radioGroup.getChildAt(position).setScaleX((float) 1.3);
                radioGroup.getChildAt(position).setScaleY((float) 1.3);

                switch (position) {

                    case 0:
                        radioGroup.getChildAt(position + 1).setScaleX(1);
                        radioGroup.getChildAt(position + 1).setScaleY(1);
                        break;
                    case 1:
                        radioGroup.getChildAt(position - 1).setScaleX(1);
                        radioGroup.getChildAt(position - 1).setScaleY(1);
                        radioGroup.getChildAt(position + 1).setScaleX(1);
                        radioGroup.getChildAt(position + 1).setScaleY(1);
                    case 2:
                        radioGroup.getChildAt(position - 1).setScaleX(1);
                        radioGroup.getChildAt(position - 1).setScaleY(1);
                    default:
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        DB helfer = new DB(this);
        SQLiteDatabase db = helfer.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBHelper.Datenbank.COLUMN_VARNAME, "FirstTimeHere");
        values.put(DBHelper.Datenbank.COLUMN_VARVALUE, 1);

        long newRow;
        newRow = db.insert(DBHelper.Datenbank.TABLE_NAME_2, null, values);
    }


    @Override
    public void onBackPressed() {
        if (tPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            tPager.setCurrentItem(tPager.getCurrentItem() - 1);
        }

    }

    private class TutorialViewPagerAdapter extends FragmentStatePagerAdapter {
        public TutorialViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {

                case 0:
                    return new TutorialViewPagerFirstFragment();
                case 1:
                    return new TutorialViewPagerSecFragment();
                case 2:
                    return new TutorialViewPagerThridFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            String[] title = {
                    "Step 1  ->",
                    "Step 2  ->",
                    "Step 3"
            };

            return title[position];
        }
    }


    public void dosomemoreshit(View v) {
        Intent i = new Intent(this, StartActivity.class);
        startActivity(i);

    }

}
