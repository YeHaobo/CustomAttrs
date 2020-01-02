package com.plug.yhb;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;


/**
 * 自定义控件
 */
public class ImageShow extends LinearLayout {

    private TextView tv;
    private ImageView img;

    public ImageShow(Context context) {
        super(context);
        initView(context);
    }

    public ImageShow(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        initAttrs(context,attrs);
    }

    public ImageShow(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        initAttrs(context,attrs);
    }

    /**初始化布局**/
    private void initView(Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.custom_view_test,null);
        tv = view.findViewById(R.id.tv);
        img = view.findViewById(R.id.image);
        img.setImageResource(R.mipmap.ic_launcher);
        tv.setText("这是问题");
        addView(view);
    }

    /**初始化控件*/
    private void initAttrs(Context context,AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ImageShow);
        int length = typedArray.getIndexCount();
        for(int i = 0;i < length;i++){
            int attr = typedArray.getIndex(i);
            if(attr == R.styleable.ImageShow_image_height){
                ViewGroup.LayoutParams params = img.getLayoutParams();
                params.height = (int)typedArray.getDimension(attr,100);
                img.setLayoutParams(params);
            }
            if(attr == R.styleable.ImageShow_image_width){
                ViewGroup.LayoutParams params = img.getLayoutParams();
                params.width = (int)typedArray.getDimension(attr,100);
                img.setLayoutParams(params);
            }
            if(attr == R.styleable.ImageShow_res){
                img.setImageResource(typedArray.getResourceId(attr,R.drawable.ic_launcher_background));
            }
            if(attr == R.styleable.ImageShow_src){
                Glide.with(this).load(typedArray.getString(attr)).into(img);
            }
            if(attr == R.styleable.ImageShow_text_gravity){
                String type = typedArray.getString(attr);
                if(type != null && type.equals("1"))
                    tv.setGravity(Gravity.CENTER);
                if(type != null && type.equals("2"))
                    tv.setGravity(Gravity.START);
                if(type != null && type.equals("3"))
                    tv.setGravity(Gravity.END);
            }
            if(attr == R.styleable.ImageShow_text_color){
                tv.setTextColor(typedArray.getColor(attr,context.getColor(R.color.colorPrimary)));
            }
            if(attr == R.styleable.ImageShow_text_size){
                tv.setTextSize(typedArray.getDimension(attr,13));
            }
            if(attr == R.styleable.ImageShow_text){
                tv.setText(typedArray.getString(attr));
            }

        }
        typedArray.recycle();
    }

}
