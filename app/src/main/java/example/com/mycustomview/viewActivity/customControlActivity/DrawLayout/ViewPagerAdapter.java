package example.com.mycustomview.viewActivity.customControlActivity.DrawLayout;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import example.com.mycustomview.R;


public class ViewPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    private String[] tabTitle = {"TAB1","TAB2","TAB3"};

    private int[] tabIcon = {
            R.drawable.ic_menu_camera,
            R.drawable.ic_menu_gallery,
            R.drawable.ic_menu_manage};

    public ViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        return ViewpaperFragment.newInstance(position+1);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        //return super.getPageTitle(position);
        return tabTitle[position];
    }
//    @Override
//    public CharSequence getPageTitle(int position) {
//
//        Drawable image = mContext.getResources().getDrawable(tabIcon[position]);
//        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
//        SpannableString sb = new SpannableString(" ");
//        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
//        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        return sb;
//    }

//    public View getTabView(int position,TabLayout.Tab tab){
//
//        View v = LayoutInflater.from(mContext).inflate(R.layout.layout_coutom_tab,null);
//        ImageView img = (ImageView)v.findViewById(R.id.tab_icon);
//        img.setImageResource(tabIcon[position]);
//        TextView tv = (TextView)v.findViewById(R.id.tab_text);
//        tv.setText(tabTitle[position]);
//        if(position == 0){
//            v.setSelected(true);
//        }
//        return v;
//
//    }


}
