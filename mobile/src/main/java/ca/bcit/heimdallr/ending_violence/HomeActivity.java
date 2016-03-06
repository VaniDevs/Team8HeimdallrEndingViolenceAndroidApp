package ca.bcit.heimdallr.ending_violence;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Set a toolbar which will replace the action bar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Setup the viewPager
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        // Setup the Tabs
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        // By using this method the tabs will be populated according to viewPager's count and

        // This method ensures that tab selection events update the ViewPager and page changes update the selected tab.
        tabLayout.setupWithViewPager(viewPager);
    }

    private class MyPagerAdapter extends FragmentStatePagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            switch(pos) {

                case 0: return Fragment1.newInstance();
                case 1: return Fragment2.newInstance();
                case 2: return Fragment3.newInstance();
                default: return Fragment1.newInstance();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if(position == 0){
                return "Home";
            } else if(position == 1){
                return "Profile Edit";
            } else {
                return "Profile";
            }
        }
    }

    /* FRAGMENT 2
    public void addChild(View v){
        System.out.println("ADD CHILD");
        View rootView = inflater.inflate(R.layout.activity_home,
                container, false);
        LinearLayout myLayout = (LinearLayout) rootView.findViewById(R.id.Children);

        LayoutParams lp = new LayoutParams( LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        for(int l=0; l<4; l++)
        {
            TextView a = new TextView(HomeActivity.this);
            a.setTextSize(15);
            a.setLayoutParams(lp);
            a.setId(l);
            a.setText((l + 1) + ": something");
            myLayout.addView(a);
        }

    }
    */
}
