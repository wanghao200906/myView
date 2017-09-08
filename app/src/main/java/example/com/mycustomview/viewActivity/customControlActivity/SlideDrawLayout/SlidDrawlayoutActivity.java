package example.com.mycustomview.viewActivity.customControlActivity.SlideDrawLayout;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import example.com.mycustomview.R;
import example.com.mycustomview.base.BaseActivity;

/**
 * Created by wanghao on 2017/8/22.
 */

public class SlidDrawlayoutActivity extends BaseActivity
{

    private SlidingDrawLayout slideMenu1;
    private ListView lv_menu;
    private MenuAdapter menuAdapter;
    private ImageView iv_menu;


    private static final String TAG = "DrawlayoutActivity";
    private CoordinatorLayout right;
    private NavigationView left;
    private boolean isDrawer = false;

    private ViewPager mViewPager;

    private TabLayout mTabLayout;

    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slide_drawlayout);

        initView();

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mTabLayout = (TabLayout) findViewById(R.id.layout_tab);
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), this);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(1);
        mTabLayout.setupWithViewPager(mViewPager);

        mViewPager.addOnAdapterChangeListener(new ViewPager.OnAdapterChangeListener() {
            @Override
            public void onAdapterChanged(@NonNull ViewPager viewPager, @Nullable PagerAdapter oldAdapter, @Nullable PagerAdapter newAdapter) {
                viewPager.getCurrentItem();
            }
        });

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if(position!=0){
                    SlidingDrawLayout.ENABLE_TOUCH=false;
                }else{
                    SlidingDrawLayout.ENABLE_TOUCH=true;

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void initView() {
        slideMenu1 = (SlidingDrawLayout) findViewById(R.id.slideMenu1);
        lv_menu = (ListView) findViewById(R.id.lv_menu);
        iv_menu = (ImageView) findViewById(R.id.iv_menu);
        menuAdapter = new MenuAdapter(this);
        lv_menu.setAdapter(menuAdapter);

        iv_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slideMenu1.toggle();
            }
        });
        slideMenu1.setOnStatusListener(new SlidingDrawLayout.OnStatusListener() {

            @Override
            public void statusChanged(SlidingDrawLayout.Status status) {
                if (status == SlidingDrawLayout.Status.Open) {
                    Toast.makeText(SlidDrawlayoutActivity.this, "Open", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SlidDrawlayoutActivity.this, "Close", Toast.LENGTH_SHORT).show();
                }

            }
        });

        lv_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                slideMenu1.toggle();

            }
        });
    }


}
