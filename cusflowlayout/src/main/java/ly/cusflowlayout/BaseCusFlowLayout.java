package ly.cusflowlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseCusFlowLayout extends ViewGroup {
    /**
     * 每个标签的高度
     */
    private int flItemHeight;
    /**
     * 每个标签默认的背景
     */
    private int flItemDefBg;
    /**
     * 每个标签选中的背景
     */
    private int flItemSelectBg;
    /**
     * 每个标签字体的默认颜色
     */
    private int flItemDefTxtColor;
    /**
     * 每个标签字体的选中颜色
     */
    private int flItemSelectTxtColor;
    /**
     * 每个标签字体的大小
     */
    private int flItemTxtSize = 14;
    /**
     * 每个标签字体的左右间距
     */
    private int flItemTxtPaddingLR = 16;
    /**
     * 每个标签的左右间距
     */
    private int flItemTxtMarginLR;
    /**
     * 每个标签的上下间距
     */
    private int flItemTxtMarginTB;
    /**
     * 标签是单选还是多选（默认为单选）
     */
    private Boolean flItemMultiSelect;

    private GetFlowLayoutBoundUtil getBoundUtil;
    private int lastPos = -1;
    private boolean isCheck[] = new boolean[1000];
    protected List<TextView> tvs = new ArrayList<>();
    protected List<String> currentList = new ArrayList<>();


    public BaseCusFlowLayout(Context context) {
        this(context, null);
    }

    public BaseCusFlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseCusFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BaseCusFlowLayout, defStyleAttr, 0);
        this.initType(ta);
        ta.recycle();
    }

    private void initType(TypedArray ta) {
        flItemHeight = (int) ta.getDimension(R.styleable.BaseCusFlowLayout_flItemHeight, FlDensityUtils.dp2px(getContext(), 32));
        flItemDefBg = ta.getResourceId(R.styleable.BaseCusFlowLayout_flItemDefBg, R.drawable.lyman_fl_def_item_bg);
        flItemSelectBg = ta.getResourceId(R.styleable.BaseCusFlowLayout_flItemSelectBg, R.drawable.lyman_fl_select_item_bg);
        flItemDefTxtColor = ta.getColor(R.styleable.BaseCusFlowLayout_flItemDefTxtColor, Color.BLACK);
        flItemSelectTxtColor = ta.getColor(R.styleable.BaseCusFlowLayout_flItemSelectTxtColor, Color.WHITE);
        flItemTxtSize = ta.getInt(R.styleable.BaseCusFlowLayout_flItemTxtSize, 14);
        flItemTxtPaddingLR = (int) ta.getDimension(R.styleable.BaseCusFlowLayout_flItemTxtPaddingLR, FlDensityUtils.dp2px(getContext(), 4));
        flItemTxtMarginLR = (int) ta.getDimension(R.styleable.BaseCusFlowLayout_flItemTxtMarginLR, FlDensityUtils.dp2px(getContext(), 10));
        flItemTxtMarginTB = (int) ta.getDimension(R.styleable.BaseCusFlowLayout_flItemTxtMarginTB, FlDensityUtils.dp2px(getContext(), 10));
        flItemMultiSelect = ta.getBoolean(R.styleable.BaseCusFlowLayout_flItemMultiSelect, false);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int width, height;
        GetFlowLayoutBoundUtil getBoundUtil = new GetFlowLayoutBoundUtil(this);
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            getBoundUtil.setBound(getMeasuredWidth(), i, count);
        }
        //设置父控件的宽高
        height = (modeHeight == MeasureSpec.EXACTLY) ? sizeHeight : getBoundUtil.allHeight;
        width = (modeWidth == MeasureSpec.EXACTLY) ? sizeWidth : getBoundUtil.maxWidth;
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        getBoundUtil = new GetFlowLayoutBoundUtil(this);
        int width = getMeasuredWidth();
        for (int i = 0; i < count; i++) {
            final int pos = i;
            final View child = getChildAt(i);
            //设置cl,ct,cr,cb
            getBoundUtil.setBound(width, pos, count);
            child.layout(getBoundUtil.newCl, getBoundUtil.newCt, getBoundUtil.newCr, getBoundUtil.newCb);
            child.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!flItemMultiSelect) {
                        //单选
                        baseSingleSelect(pos);
                    } else {
                        //多选
                        baseMultiselect(pos);
                    }
                }
            });
        }
    }

    /**
     * 单选逻辑
     * @param pos
     */
    private void baseSingleSelect(int pos) {
        TextView tvTag = tvs.get(pos);
        if (lastPos == pos) {
            tvTag.setTextColor(flItemDefTxtColor);
            tvTag.setBackgroundDrawable(getResources().getDrawable(flItemDefBg));
            currentList.remove(tvTag.getText().toString());
            tagSelect(pos,false);
            lastPos = -1;
        } else {
            reSetAll();
            tvTag.setTextColor(flItemSelectTxtColor);
            tvTag.setBackgroundDrawable(getResources().getDrawable(flItemSelectBg));
            currentList.clear();
            currentList.add(tvTag.getText().toString());
            tagSelect(pos,true);
            lastPos = pos;
        }
    }

    /**
     * 多选逻辑
     *
     * @param pos
     */
    private void baseMultiselect(int pos) {
        TextView tvTag = tvs.get(pos);
        if (!isCheck[pos]) {
            tvTag.setTextColor(flItemSelectTxtColor);
            tvTag.setBackgroundDrawable(getResources().getDrawable(flItemSelectBg));
            currentList.add(tvTag.getText().toString());
            tagSelect(pos,true);
            isCheck[pos] = true;
        } else {
            tvTag.setTextColor(flItemDefTxtColor);
            tvTag.setBackgroundDrawable(getResources().getDrawable(flItemDefBg));
            currentList.remove(tvTag.getText().toString());
            tagSelect(pos,false);
            isCheck[pos] = false;
        }
    }

    public void tagSelect(int pos,boolean isCheck){}

    /**
     * 添加标签
     * @param strings
     */
    public void addFlowTag(List<String> strings) {
        removeAllViews();
        tvs.clear();
        TextView tv;
        for (int i = 0; i < strings.size(); i++) {
            tv = new TextView(getContext());
            tv.setTextColor(flItemDefTxtColor);
            tv.setTextSize(flItemTxtSize);
            tv.setGravity(Gravity.CENTER);
            tv.setPadding(flItemTxtPaddingLR, 0, flItemTxtPaddingLR, 0);
            MarginLayoutParams lp = new MarginLayoutParams(LayoutParams.WRAP_CONTENT, flItemHeight);
            lp.leftMargin = flItemTxtMarginLR;
            lp.rightMargin = flItemTxtMarginLR;
            lp.topMargin = flItemTxtMarginTB;
            lp.bottomMargin = flItemTxtMarginTB;
            tv.setText(strings.get(i));
            tv.setBackgroundDrawable(getResources().getDrawable(flItemDefBg));
            tvs.add(tv);
            addView(tv, lp);
        }
    }

    public List<String> getCurrentList() {
        return currentList;
    }


    public Map<Integer, String> getMapStr() {
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < tvs.size(); i++) {
            if (isCheck[i]) {
                map.put(i, tvs.get(i).getText().toString());
            }
        }
        return map;
    }

    /**
     * 获取整个View的高度
     *
     * @return
     */
    public int getAllHeight() {
        return getBoundUtil.getAllHeight();
    }

    private void reSetAll() {
        for (int i = 0; i < tvs.size(); i++) {
            tvs.get(i).setTextColor(flItemDefTxtColor);
            tvs.get(i).setBackgroundDrawable(getResources().getDrawable(flItemDefBg));
            isCheck[i] = false;
        }
    }
}
