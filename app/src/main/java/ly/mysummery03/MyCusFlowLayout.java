package ly.mysummery03;

import android.content.Context;
import android.util.AttributeSet;
import java.util.ArrayList;
import java.util.List;
import ly.cusflowlayout.BaseCusFlowLayout;

public class MyCusFlowLayout extends BaseCusFlowLayout {

    private List<InfoBean>select;
    private List<InfoBean>list;

    public MyCusFlowLayout(Context context) {
        this(context,null);
    }

    public MyCusFlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyCusFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        select = new ArrayList<>();
    }


    @Override
    public void tagSelect(int pos, boolean isCheck) {
        if (isCheck){
            select.add(list.get(pos));
        }else{
            select.remove(list.get(pos));
        }
    }

    public List<InfoBean> getSelect() {
        return select;
    }

    public void addFlow(List<InfoBean>list){
        this.list = list;
        List<String>strs = new ArrayList<>();
        for (InfoBean bean:list){
            strs.add(bean.getInfo());
        }
        addFlowTag(strs);
    }


}
