package com.bigburger.customViews;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.bigburger.R;
import com.bigburger.util.FontUtils;

import static com.bigburger.util.FontUtils.setOpenSansFont;

/**
 * Created by diegosantos on 5/30/17.
 */

public class BigBurgerTextView extends android.support.v7.widget.AppCompatTextView {

    Context mContext;

    public BigBurgerTextView(Context context) {
        super(context);

        mContext = context;

        setTypefaceRoboto(null);
    }

    public BigBurgerTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;

        setTypefaceRoboto(attrs);
    }

    public BigBurgerTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = context;

        setTypefaceRoboto(attrs);
    }

    void setTypefaceRoboto(AttributeSet attrs) {
        String style = null;

        if (attrs != null) {
            TypedArray ta = mContext.obtainStyledAttributes(attrs, R.styleable.BigBurgerTextView);
            style = ta.getString(R.styleable.BigBurgerTextView_typefaceOpenSans);
            ta.recycle();
            if (style != null && style != "") {
                setOpenSansFont(mContext, this, FontUtils.getFontFamily(style));
            } else {
                setOpenSansFont(mContext, this, FontUtils.FontFamily.OPENSANS);
            }
        } else {
            setOpenSansFont(mContext, this, FontUtils.FontFamily.OPENSANS);
        }
    }

    void setTypeface(String style) {
        if (style != null && style != "") {
            setOpenSansFont(mContext, this, FontUtils.getFontFamily(style));
        }
    }
}
