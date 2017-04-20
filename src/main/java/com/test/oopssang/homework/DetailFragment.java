package com.test.oopssang.homework;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.test.oopssang.homework.adapter.RecyclerAdapter;
import com.test.oopssang.homework.data.viewdata.ViewData;

import java.util.ArrayList;

/**
 * Created by sang on 2017-04-19.
 */

public class DetailFragment extends Fragment {

    private static final String START_POSITION = "START_POSITION";
    private static final String MORE_AVAILABLE = "MORE_AVAILABLE";
    private static CallBack sCallBack;

    private Context mContext;
    private RecyclerView mRecyclerView;
    private RecyclerAdapter mRecyclerAdapter;
    private LinearLayoutManager mLayoutManager;
    private View mView;
    private boolean mMoreAvailable = false;

    public interface CallBack{
        public void requestServerData();
    }

    public static Fragment newInstance(ArrayList<ViewData> data, int position, boolean moreAvailable, CallBack callBack) {
        DetailFragment fragment = new DetailFragment();
        Bundle b = new Bundle();
        b.putParcelableArrayList(ViewData.class.getName(), data);
        b.putInt(START_POSITION, position);
        b.putBoolean(MORE_AVAILABLE, moreAvailable);
        fragment.setArguments(b);

        sCallBack = callBack;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity.getApplicationContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mContext == null) {
            mContext = getActivity().getApplicationContext();
        }
        mView = inflater.inflate(R.layout.detail_fragment, container, false);
        ArrayList<ViewData> data = getArguments().getParcelableArrayList(ViewData.class.getName());
        int position = getArguments().getInt(START_POSITION);
        setLayout(mView, data, position);

        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mRecyclerView.scrollToPosition(getArguments().getInt(START_POSITION));
        mMoreAvailable = getArguments().getBoolean(MORE_AVAILABLE);


    }

    @Override
    public void onDestroyView() {
        mView = null;
        super.onDestroyView();
    }

    public void requestList(ArrayList<ViewData> data, boolean moreAvailable){
        mRecyclerAdapter.setData(data);
        mRecyclerAdapter.notifyDataSetChanged();
        mMoreAvailable = moreAvailable;
    }

    private void setLayout(View view, ArrayList<ViewData> data, int position) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_listview);
        mLayoutManager = new LinearLayoutManager(mContext);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)mRecyclerView.getLayoutParams();
        params.height = getRecyclerHeight(data.get(0).getHeigth(), data.get(0).getWidth());
        mRecyclerView.setLayoutParams(params);

        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerAdapter = new RecyclerAdapter(mContext, null);
        mRecyclerAdapter.setData(data);
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerView.scrollToPosition(position);
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(mMoreAvailable){
                    int totalItemCount = mLayoutManager.getItemCount();
                    int lastVisibleItemPosition = mLayoutManager.findLastCompletelyVisibleItemPosition();
                    if(totalItemCount >0 && lastVisibleItemPosition != RecyclerView.NO_POSITION && (totalItemCount -1 <= lastVisibleItemPosition)){
                        // data 추가 요청
                        if(sCallBack != null){
                            sCallBack.requestServerData();
                        }
                    }
                }
            }
        });
    }

    private int getRecyclerHeight(int height, int width){
        int defaultWidth = ((WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();

        return defaultWidth * height / width;
    }


}
