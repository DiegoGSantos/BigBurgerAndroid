package com.bigburger.customViews;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import com.bigburger.R;
import com.bigburger.util.FontUtils;

import static com.bigburger.util.UtilKt.isStringValid;

/**
 * Created by diegosantos on 5/30/17.
 */

public class BigBurgerButton extends android.support.v7.widget.AppCompatButton implements View.OnClickListener {

    OnClickListener mListener;
    Context mContext;

    public BigBurgerButton(Context context) {
        super(context);

        mContext = context;
        initView(null);
    }

    public BigBurgerButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
        initView(attrs);
    }

    public BigBurgerButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = context;
        initView(attrs);
    }

    public void setButtonClickListener(OnClickListener onClickListener) {
        mListener = onClickListener;
    }

    void initView(AttributeSet attrs) {

        setOnClickListener(this);

        if (attrs != null) {
            TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.BigBurgerButton);

            if (ta != null) {

                String style = ta.getString(R.styleable.BigBurgerButton_typefaceOpenSans);
                ta.recycle();

                if (isStringValid(style)) {
                    FontUtils.setOpenSansFont(mContext, this, FontUtils.getFontFamily(style));
                } else {
                    FontUtils.setOpenSansFont(mContext, this, FontUtils.FontFamily.OPENSANS);
                }
            }
        }else {
            FontUtils.setOpenSansFont(mContext, this, FontUtils.FontFamily.OPENSANS);
        }
    }

    @Override
    public void onClick(final View view) {
        view.setEnabled(false);


        if (mListener != null) {
            mListener.onClick(view);
        }

        final android.os.Handler handler = new android.os.Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setEnabled(true);
            }
        }, 1500);
    }

}
