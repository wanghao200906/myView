package example.com.mycustomview.viewActivity.customControlActivity.SlideDrawLayout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import example.com.mycustomview.R;
import example.com.mycustomview.view.DrawSurfaceView;

public class ViewpaperFragment extends Fragment {

    private TextView tvViewpager;

    private static final String INDEX = "index";

    private int mPage;

    private ListView listView;

    public static ViewpaperFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(INDEX, page);
        ViewpaperFragment fragment = new ViewpaperFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(INDEX);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.slide_content_main, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DrawSurfaceView drawsurfaceview = (DrawSurfaceView) view.findViewById(R.id.drawsurfaceview);
        if (mPage == 1) {
            drawsurfaceview.setVisibility(View.VISIBLE);
        } else {
            drawsurfaceview.setVisibility(View.GONE);

        }
    }


}
