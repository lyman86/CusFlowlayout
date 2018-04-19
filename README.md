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
第一步
<br>
        <ly.cusflowlayout.BaseCusFlowLayout
        <br>
        android:id="@+id/cusFlowLayout"
        <br>
        android:layout_width="match_parent"
        <br>
        android:layout_height="wrap_content"
        <br>
        android:background="@android:color/holo_purple"
        <br>
        <br>
        lyman:flItemDefBg="@drawable/lyman_fl_def_item_bg2"
        <br>
        lyman:flItemDefTxtColor="@color/colorPrimaryDark"
        <br>
        lyman:flItemHeight="30dp"
        <br>
        lyman:flItemMultiSelect="true"
        <br>
        lyman:flItemSelectBg="@drawable/lyman_fl_select_item_bg2"
        <br>
        lyman:flItemSelectTxtColor="@color/colorAccent"
        <br>
        lyman:flItemTxtMarginLR="6dp"
        <br>
        lyman:flItemTxtMarginTB="8dp"
        <br>
        lyman:flItemTxtPaddingLR="10dp"
        <br>
        lyman:flItemTxtSize="14" />
        <br>

