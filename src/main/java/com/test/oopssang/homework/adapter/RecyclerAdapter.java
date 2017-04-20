package com.test.oopssang.homework.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.test.oopssang.homework.R;
import com.test.oopssang.homework.data.viewdata.ViewData;
import com.test.oopssang.homework.view.InstartImageView;

import java.util.ArrayList;

/**
 * Created by sang on 2017-04-18.
 */

public class RecyclerAdapter  extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private static final int VIEWTYPE_ITEM = 1;
    private static final int VIEWTYPE_FOOTER = 2;


    /**
     * RecyclerView Item의 Click 이벤트를 처리할 리스너
     */
    public interface OnItemClickedListener {
        public void onItemClicked(int position);
    }

    private Context mContext;
    private ArrayList<ViewData> mImageList;
    private OnItemClickedListener mOnItemClickedListener;

    public RecyclerAdapter(Context context, OnItemClickedListener listener){
        this.mContext = context;
        this.mOnItemClickedListener = listener;
    }

    public void setData(ArrayList<ViewData> list){
        this.mImageList = list;
    }

    private ViewData getData(int position){
        return mImageList != null ? mImageList.get(position) : null;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        /**
         * 이미지뷰
         */
        private InstartImageView mImageView;

        private int mViewType = 0;

        public ViewHolder(View itemView, int viewType) {
            super(itemView);
            if(viewType == VIEWTYPE_ITEM){
                this.mImageView = (InstartImageView)itemView.findViewById(R.id.instargram_img);
                itemView.setOnClickListener(this);
            }else{
                this.mImageView = null;
            }
            mViewType = viewType;
        }

        @Override
        public void onClick(View v) {

        }
    }

    /**
     * ViewHolder 생성
     *
     * @param parent      아이템 최상위 뷰
     * @param viewType    ViewType
     * @return 생성된 ViewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = null;
        if(viewType == VIEWTYPE_ITEM){
            layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, null);
            InstartImageView view = (InstartImageView) layoutView.findViewById(R.id.instargram_img);
            view.setClickable(true);
            view.setFocusable(true);
            view.setFocusableInTouchMode(true);
        }else{
            layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_footer, null);
        }
        return new ViewHolder(layoutView, viewType);
    }

    /**
     *뷰타입 정하기
     */
    @Override
    public int getItemViewType(int position) {
        if (isPositionFooter(position)) {
            return VIEWTYPE_FOOTER;
        }
        return VIEWTYPE_ITEM;
    }

    /**
     *true가 반환되면 리스트의 끝임을 알수있다.
     * @param position
     * @return
     */
    private boolean isPositionFooter(int position) {
        return position == mImageList.size();
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if(holder.mViewType == VIEWTYPE_ITEM){
            ViewData data = getData(position);
            holder.mImageView.mHeight = data.getHeigth();
            holder.mImageView.mWidth = data.getWidth();
            holder.mImageView.mPosition = position;

            holder.mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mOnItemClickedListener != null){
                        mOnItemClickedListener.onItemClicked(((InstartImageView)v).mPosition);
                    }
                }
            });

            Picasso.with(mContext).load(data.getImageUrl()).into(holder.mImageView);
        }

    }

    /**
     * Footer 를 사용하기위해 Data보다 +1하여 리턴.
     * @return
     */
    @Override
    public int getItemCount() {
        return mImageList != null ? mImageList.size() + 1 : 0;
    }
}
