package com.example.administrator.cniaoshop.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.cniaoshop.R;
import com.example.administrator.cniaoshop.bean.HomeCategory;

import java.util.List;

/**
 * 作者：created by weidiezeng on 2019/3/28 12:05
 * 邮箱：1067875902@qq.com
 */
public class HomeCategoryAdapter extends RecyclerView.Adapter<HomeCategoryAdapter.ViewHolder> {


    private  int VIEW_TYPE_L=0;
    private  int VIEW_TYPE_R=1;
    private LayoutInflater inflater;
    private List<HomeCategory> mDatas;

    public HomeCategoryAdapter(List<HomeCategory>datas){
        mDatas=datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {

        inflater=LayoutInflater.from(viewGroup.getContext());
        if(type==VIEW_TYPE_R){
            return new ViewHolder(inflater.inflate(R.layout.template_home_cardview2,viewGroup,false));
        }
        return new ViewHolder(inflater.inflate(R.layout.template_home_cardview,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        HomeCategory category=mDatas.get(i);
        viewHolder.textTitle.setText(category.getName());
        viewHolder.imageViewBig.setImageResource(category.getImgBig());
        viewHolder.imageViewSmallTop.setImageResource(category.getImgSmallTop());
        viewHolder.getImageViewSmallBottom.setImageResource(category.getImgSmallBottom());

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(position%2==0){
            return VIEW_TYPE_R;
        }else return VIEW_TYPE_L;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView textTitle;
        ImageView imageViewBig;
        ImageView imageViewSmallTop;
        ImageView getImageViewSmallBottom;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle=itemView.findViewById(R.id.text_title);
            imageViewBig=itemView.findViewById(R.id.imgview_big);
            imageViewSmallTop=itemView.findViewById(R.id.imgview_small_top);
            getImageViewSmallBottom=itemView.findViewById(R.id.imgview_small_bottom);

        }
    }
}
