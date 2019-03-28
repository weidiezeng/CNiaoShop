package com.example.administrator.cniaoshop.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.administrator.cniaoshop.R;

/**
 * 作者：created by weidiezeng on 2019/3/26 19:53
 * 邮箱：1067875902@qq.com
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class CnToolbar extends Toolbar {


    private LayoutInflater mInflater;

    private View mView;
    private TextView mTextTitle;
    private EditText mSearchView;
    private ImageButton mRightButton;
    public CnToolbar(Context context) {
        this(context,null);
    }

    public CnToolbar(Context context, AttributeSet attributeSet) {
        this(context,attributeSet,0);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public CnToolbar(Context context, AttributeSet attributeSet, int def) {
        super(context,attributeSet,def);
        initView();

        setContentInsetsRelative(10,10);
        if(attributeSet!=null){
            final TintTypedArray a=TintTypedArray.obtainStyledAttributes(getContext()
            ,attributeSet,R.styleable.CnToolBar,def,0);

            @SuppressLint("RestrictedApi")
            final Drawable rightIcon=a.getDrawable(R.styleable.CnToolBar_rightButtonIcon);
            if(rightIcon!=null){
                setRigthButtonIcon(rightIcon);
            }

            boolean isShowSearchView=a.getBoolean(R.styleable.CnToolBar_isShowSearchView,false);

            if(isShowSearchView){
                showSearchView();
                hideTitleView();
            }
//            CharSequence rightButtonText = a.getText(R.styleable.CnToolBar_rightButtonText);
//            if(rightButtonText !=null){
//                setRightButtonText(rightButtonText);
//            }
            a.recycle();
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initView(){
        if(mView==null)
        {
            mInflater = LayoutInflater.from(getContext());
            mView = mInflater.inflate(R.layout.toolbar, null);


            mTextTitle = mView.findViewById(R.id.toolbar_title);
            mSearchView =  mView.findViewById(R.id.toolbar_searchview);
            mRightButton =  mView.findViewById(R.id.toolbar_rightButton);

            LayoutParams layoutParams  = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                    ,ViewGroup.LayoutParams.WRAP_CONTENT,Gravity.CENTER_HORIZONTAL);
            addView(mView,layoutParams);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void setRigthButtonIcon(Drawable icon){
        if(mRightButton!=null){
           mRightButton.setImageDrawable(icon);
            mRightButton.setVisibility(VISIBLE);
        }
    }


//    public void  setRightButtonIcon(Drawable icon){
        //setRightButtonIcon(getResources().getDrawable(icon));
//    }
    public void setmRightButtonOnClickListener(OnClickListener listener){
        mRightButton.setOnClickListener(listener);
    }

//    public void setRightButtonText(CharSequence text){
//        mRightButton.setText(text);
//        mRightButton.setVisibility(VISIBLE);
//    }

//    public void setRightButtonText(int id){
//        setRightButtonText(getResources().getString(id));
//    }



    public ImageButton getRightButton(){

        return this.mRightButton;
    }

    @Override
    public void setTitle(int resId) {
        super.setTitle(resId);
    }
    @Override
    public void setTitle(CharSequence title) {
        initView();
        if(mTextTitle!=null)
        {
            mTextTitle.setText(title);
            showTitleView();
        }
    }


    public  void showSearchView(){

        if(mSearchView !=null)
            mSearchView.setVisibility(VISIBLE);

    }


    public void hideSearchView(){
        if(mSearchView !=null)
            mSearchView.setVisibility(GONE);
    }

    public void showTitleView(){
        if(mTextTitle !=null)
            mTextTitle.setVisibility(VISIBLE);
    }


    public void hideTitleView() {
        if (mTextTitle != null)
            mTextTitle.setVisibility(GONE);

    }
}
