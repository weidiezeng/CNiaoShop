package com.example.administrator.cniaoshop.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.example.administrator.cniaoshop.widget.CnToolbar;

/**
 * 作者：created by weidiezeng on 2019/3/26 17:48
 * 邮箱：1067875902@qq.com
 */
public class HomeFragment extends Fragment{
    private SliderLayout sliderShow;
    private PagerIndicator pagerIndicator;
   // private CnToolbar cnToolbar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


            View view =inflater.inflate(R.layout.fragment_home,container,false);



       // cnToolbar=view.findViewById(R.id.toolbar);
        sliderShow =  view.findViewById(R.id.slider);
        pagerIndicator=view.findViewById(R.id.custom_indicator);
        initSlider();
        return view;
    }
    private void  initSlider(){
        TextSliderView textSliderView = new TextSliderView(this.getActivity());
        textSliderView
                .description("IT生活")
                .image("https://img.cniao5.com/5608cae6Nbb1a39f9.jpg");


        TextSliderView textSliderView1 = new TextSliderView(this.getActivity());
        textSliderView
                .description("母婴萌宝")
                .image("https://img.cniao5.com/5608b7cdN218fb48f.jpg");


        TextSliderView textSliderView2 = new TextSliderView(this.getActivity());
        textSliderView
                .description("音响狂欢")
                .image("https://img.cniao5.com/5608f3b5Nc8d90151.jpg");


        textSliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
            @Override
            public void onSliderClick(BaseSliderView slider) {

            }
        });

        sliderShow.addSlider(textSliderView);
        sliderShow.addSlider(textSliderView1);
        sliderShow.addSlider(textSliderView2);

        sliderShow.setCustomIndicator(pagerIndicator);//设置指示器
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
}
