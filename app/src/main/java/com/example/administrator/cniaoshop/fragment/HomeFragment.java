package com.example.administrator.cniaoshop.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.administrator.cniaoshop.R;
import com.example.administrator.cniaoshop.adapter.DividerItemDecortion;
import com.example.administrator.cniaoshop.adapter.HomeCategoryAdapter;
import com.example.administrator.cniaoshop.bean.Banner;
import com.example.administrator.cniaoshop.bean.HomeCategory;
import com.example.administrator.cniaoshop.http.BaseCallback;
import com.example.administrator.cniaoshop.http.OkHttpHelper;
import com.example.administrator.cniaoshop.http.SpotsCallback;
import com.example.administrator.cniaoshop.widget.CnToolbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 作者：created by weidiezeng on 2019/3/26 17:48
 * 邮箱：1067875902@qq.com
 */
public class HomeFragment extends Fragment{
    private SliderLayout sliderShow;
    private PagerIndicator pagerIndicator;
    private RecyclerView recyclerView;
    private HomeCategoryAdapter homeCategoryAdapter;

    private Gson gson=new Gson();
    private List<Banner>mbanners;
    private OkHttpHelper httpHelper=OkHttpHelper.getInstance();
   // private CnToolbar cnToolbar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view =inflater.inflate(R.layout.fragment_home,container,false);



       // cnToolbar=view.findViewById(R.id.toolbar);
        sliderShow =  view.findViewById(R.id.slider);
       //pagerIndicator=view.findViewById(R.id.custom_indicator);
        requestImages();
        //initSlider();
        initRecyclerView(view);
        return view;
    }
    private void requestImages(){
          String url="http://112.124.22.238:8081/course_api/banner/query?type=1";
//
//        OkHttpClient client=new OkHttpClient();
//        RequestBody body=new FormBody.Builder()
//                .add("type","1")
//                .build();
//        Request request=new Request.Builder()
//                .url(url)
//                .post(body)
//                .build();
//
//        //异步请求
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//
//
//                if(response.isSuccessful()){
//                    String json=response.body().string();
//                    Type type=new TypeToken<List<Banner>>(){}.getType();
//                    mbanners=gson.fromJson(json,type);
//
//                    initSlider();
//                }
//            }
//        });

       httpHelper.get(url, new SpotsCallback<List<Banner>>(getContext()) {
           @Override
           public void onSuccess(Response response, List<Banner> banners) {

               mbanners=banners;
               initSlider();
           }

           @Override
           public void onError(Response response, int code, Exception e) {

           }
       });


    }

    //初始化recyclerView
    private void initRecyclerView(View view) {
        recyclerView=view.findViewById(R.id.recycler_view);

        List<HomeCategory> datas=new ArrayList<>(15);
        datas.add(new HomeCategory("热门活动", R.mipmap.img_big_1, R.mipmap.img_1_small1, R.mipmap.img_1_small2));
        datas.add(new HomeCategory("有利可图", R.mipmap.img_big_4, R.mipmap.img_4_small1, R.mipmap.img_4_small2));
        datas.add(new HomeCategory("品牌街", R.mipmap.img_big_2, R.mipmap.img_2_small1, R.mipmap.img_2_small2));
        datas.add(new HomeCategory("金融街 包赚翻", R.mipmap.img_big_3, R.mipmap.img_3_small1, R.mipmap.imag_3_small2));
        datas.add(new HomeCategory("超值购", R.mipmap.img_big_0, R.mipmap.img_0_small1, R.mipmap.img_0_small2));

        homeCategoryAdapter=new HomeCategoryAdapter(datas);
        recyclerView.setAdapter(homeCategoryAdapter);
        recyclerView.addItemDecoration(new DividerItemDecortion());
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
    }

    private void  initSlider(){
        if(mbanners!=null){
            for(Banner banner:mbanners){
                TextSliderView textSliderView=new TextSliderView(this.getActivity());
                textSliderView.image(banner.getImgUrl());
                textSliderView.description(banner.getName());
                textSliderView.setScaleType(BaseSliderView.ScaleType.Fit);
                sliderShow.addSlider(textSliderView);
            }
        }


//        textSliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
//            @Override
//            public void onSliderClick(BaseSliderView slider) {
//
//            }
//        });



        sliderShow.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        //sliderShow.setCustomIndicator(pagerIndicator);//设置指示器
        sliderShow.setCustomAnimation(new DescriptionAnimation());
        sliderShow.setPresetTransformer(SliderLayout.Transformer.RotateUp);//切换效果
        sliderShow.setDuration(3000);//切换时间

        //轮播广告事件监听
        sliderShow.addOnPageChangeListener(new ViewPagerEx.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (sliderShow != null) {
            sliderShow.stopAutoCycle();
        }
    }
}
