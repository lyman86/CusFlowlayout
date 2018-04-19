package ly.mysummery03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

import ly.cusflowlayout.BaseCusFlowLayout;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private BaseCusFlowLayout baseCusFlowLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        baseCusFlowLayout = findViewById(R.id.cusFlowLayout);
//        baseCusFlowLayout.addFlow(getData2());
        baseCusFlowLayout.addFlowTag(getData());
    }

    private List<InfoBean> getData2() {
        List<InfoBean> infoBeans = new ArrayList<>();
        infoBeans.add(new InfoBean("Android",10));
        infoBeans.add(new InfoBean("标签",12));
        infoBeans.add(new InfoBean("自定义控件",22));
        return infoBeans;
    }

    private List<String> getData() {
        List<String> list = new ArrayList<>();
//        List<InfoBean> infoBeans = new ArrayList<>();
//        infoBeans.add(new InfoBean("Android",10));
//        infoBeans.add(new InfoBean("标签",12));
//        infoBeans.add(new InfoBean("自定义控件",22));
//        for (InfoBean infoBean:infoBeans) {
//            list.add(infoBean.getInfo());
//        }
        list.add("Android");
        list.add("麻省理工学院");
        list.add("Ios");
        list.add("标签");
        list.add("嗯");
        list.add("对对");
        list.add("自定义控件");
        list.add("View");
        list.add("ViewGroup");
        list.add("change");
        list.add("嗯");
        list.add("Android");
        list.add("麻省理工学院");
        list.add("Ios");
        list.add("标签");
        list.add("嗯");
        list.add("对对");
        list.add("自定义控件");
        list.add("View");
        list.add("ViewGroup");
        list.add("change");
        list.add("嗯");
        return list;
    }

    public void sure(View view){
        String str = "";
        List<String>list = baseCusFlowLayout.getCurrentList();
        for (String s:list) {
            str +=s+"  ";
        }
        Log.e(TAG,str);
        Log.e(TAG,baseCusFlowLayout.getAllHeight()+"  ----");
//        List<InfoBean>list = baseCusFlowLayout.getSelect();
//        Map<Integer,String>map = baseCusFlowLayout.getMapStr();
//        for (Integer key:map.keySet()){
//            String value = map.get(key);
//            str = str + key + "   "+value+"  ---";
//        }
//        Log.e(TAG,str);
    }
}
