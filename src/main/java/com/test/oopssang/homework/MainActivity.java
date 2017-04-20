package com.test.oopssang.homework;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.test.oopssang.homework.adapter.RecyclerAdapter;
import com.test.oopssang.homework.data.instargramdata.Items;
import com.test.oopssang.homework.data.viewdata.ViewData;
import com.test.oopssang.homework.network.InstargramService;
import com.test.oopssang.homework.network.RetrofitManager;
import com.test.oopssang.homework.utils.Utils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mSearchView;
    private EditText mEditText;
    private RelativeLayout mLoading;
    private RecyclerAdapter mRecyclerAdapter;
    private RecyclerView mRecyclerView;
    private InstargramService mService;
    private ArrayList<ViewData> mListData;
    private Fragment mFragment;
    LinearLayoutManager mLayoutManager;
    private boolean mMoreAvailable = false;
    private String mUserid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setLayout();

        mService = RetrofitManager.getInstargramService();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public void onBackPressed() {
        if(mFragment != null){
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.remove(mFragment);
            fragmentTransaction.commitAllowingStateLoss();
            mFragment = null;
            return;
        }

        super.onBackPressed();
    }
    /**
     * 레이아웃 구성
     */
    private void setLayout() {
        mLoading = (RelativeLayout) findViewById(R.id.loading);
        mSearchView = (ImageView) findViewById(R.id.search_btn);
        mEditText = (EditText) findViewById(R.id.search_edit);
        mRecyclerView = (RecyclerView) findViewById(R.id.listview);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mSearchView.setOnClickListener(this);

        mRecyclerAdapter = new RecyclerAdapter(getApplicationContext(), new RecyclerAdapter.OnItemClickedListener() {
            @Override
            public void onItemClicked(int position) {
                Log.d("sang", "sang test onItemClicked: " +position);
                startDetailFragment(position);
            }
        });
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(mMoreAvailable){
                    int totalItemCount = mLayoutManager.getItemCount();
                    int lastVisibleItemPosition = mLayoutManager.findLastCompletelyVisibleItemPosition();
                    if(totalItemCount >0 && lastVisibleItemPosition != RecyclerView.NO_POSITION && (totalItemCount -1 <= lastVisibleItemPosition)){
                        // data 추가 요청
                        Log.e("sang", "data 추가 요청 ");
                        startRetrofit(mUserid, mListData.get(mListData.size() - 1).getId());
                    }
                }
            }
        });
    }

    private void startDetailFragment(int position){
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        mFragment = DetailFragment.newInstance(mListData, position, mMoreAvailable, new DetailFragment.CallBack() {
            @Override
            public void requestServerData() {
                startRetrofit(mUserid, mListData.get(mListData.size() - 1).getId());
            }
        });
        if (mFragment != null) {
            fragmentTransaction.replace(R.id.fragment_area, mFragment);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            try {
                fragmentTransaction.commitAllowingStateLoss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void startRetrofit(String userId) {
        startRetrofit(userId, "0");
    }

    /**
     * Instargram API 연동
     * onResponse()를 통해 응답결과가 리턴 된다.
     */
    private void startRetrofit(String userId, String maxId) {
        mLoading.setVisibility(View.VISIBLE);
        mService.getdata(userId, maxId).enqueue(new Callback<Items>() {
            @Override
            public void onResponse(Call<Items> call, Response<Items> response) {
                mLoading.setVisibility(View.GONE);
                Items result = response.body();
                if (result != null) {
                    if(mListData == null){
                        mListData = Utils.changeData(result.getItem());
                    }else{
                        Utils.addData(mListData, result.getItem());
                    }
                    mMoreAvailable = result.isMore_available();
                    if(mFragment == null){
                        mRecyclerAdapter.setData(mListData);
                        mRecyclerAdapter.notifyDataSetChanged();
                    }else{
                        ((DetailFragment)mFragment).requestList(mListData, mMoreAvailable);
                    }
                } else {
                    Toast.makeText(MainActivity.this, "onResponse Data null", Toast.LENGTH_SHORT).show();
                    Log.e("sang", "onResponse Data null ");
                }
            }

            @Override
            public void onFailure(Call<Items> call, Throwable t) {
                mLoading.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "onFailure", Toast.LENGTH_SHORT).show();
                Log.d("sang", "sang test onFailure: " + t.getMessage());
            }
        });
    }


    @Override
    public void onClick(View v) {
        Log.d("sang", "onClick");
        switch (v.getId()) {
            case R.id.search_btn: {
                if (mEditText != null && mEditText.getText() != null && mEditText.getText().length() != 0) {
                    mListData = null;
                    mUserid = mEditText.getText().toString();
                    startRetrofit(mUserid);
                }
            }
            default:
        }
    }
}
