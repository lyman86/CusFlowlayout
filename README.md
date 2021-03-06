# CusFlowlayout
第一步 在项目里的build.gradle里加上 
<br>
maven { url 'https://jitpack.io' }
<br>
<br>
第二部 在app的build.gradle里加上 
<br>
compile 'com.github.lyman86:CusFlowlayout:v1.0.0'
<br>
# 属性自定义标签有如下
<br>
1.每个标签的高度：flItemHeight
<br>
2.每个标签的默认的背景（也就是未选中的颜色）：flItemDefBg
<br>
3.每个标签的选中的背景：flItemSelectBg
<br>
4.每个标签的默认的字体颜色（也就是字体未选中的颜色）：flItemDefTxtColor
<br>
5.每个标签的字体选中的颜色：flItemSelectTxtColor
<br>
6.每个标签的字体的大小：flItemTxtSize
<br>
7.每个标签的字体的左右间距：flItemTxtPaddingLR
<br>
8.每个标签标签的左右间距：flItemTxtMarginLR
<br>
9.每个标签的上下间距：flItemTxtMarginTB
<br>
10.是否多选（默认为单选）：flItemMultiSelect
<br>

![image](https://github.com/lyman86/CusFlowlayout/blob/master/app/screenshots/Screenshot_1524117570.png)

# 简单用法
<br>
## 第一步  在xml引入基类 ly.cusflowlayout.BaseCusFlowLayout  加上需要的属性
<br>

    <ly.cusflowlayout.BaseCusFlowLayout
        android:id="@+id/cusFlowLayout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="@android:color/holo_purple"

        lyman:flItemDefBg="@drawable/lyman_fl_def_item_bg2"
        lyman:flItemDefTxtColor="@color/colorPrimaryDark"
        lyman:flItemHeight="30dp"
        lyman:flItemMultiSelect="true"
        lyman:flItemSelectBg="@drawable/lyman_fl_select_item_bg2"
        lyman:flItemSelectTxtColor="@color/colorAccent"
        lyman:flItemTxtMarginLR="6dp"
        lyman:flItemTxtMarginTB="8dp"
        lyman:flItemTxtPaddingLR="10dp"
        lyman:flItemTxtSize="14" />
<br>
## 第二步  在activity里调用该代码即可  baseCusFlowLayout.addFlowTag(getData());
<br>
## 第三步  获取选中的List<String>列表  List<String>list = baseCusFlowLayout.getCurrentList();
<br>
### 注：若List列表放的不是String类型，而是自己定义的bean类则需要继承BaseCusFlowLayout.那么在xml引入的名称就是自己定义的名称 MyCusFlowLayout
<br> 
<br>   

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

<br> 
        

