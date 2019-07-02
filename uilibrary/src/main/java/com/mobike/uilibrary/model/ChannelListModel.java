package com.mobike.uilibrary.model;

import java.util.List;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019-06-05 11:18
 */
public class ChannelListModel {

    public boolean has_more;//是否更多
    public int page;//页码
    public int total;//总数
    public String next_update_msg;//下一页文案
    public String history_descript;//历史描述
    public String history_icon;//历史图片
    public String update_msg;//更新文案

    public String slogan_picture;//slogan图片
    public String footer_picture;//底部图片;
    public int list_style;//列表样式  首页B样式 已废弃
    public boolean list_style_switch;//列表样式开关
    public int user_ab_rule_id;//ab规则:1规则对应一行一个,2规则对应淘抢购
    /**
     * 接口下发的当前样式的toggle state样式，
     * 即 切换按钮点击后的 下一个样式id
     */
    public int switch_list_style_id;

    /**
     * 接口下发的当前显示样式
     * 1: 专场列表2.0样式.
     * 2: 淘抢购样式，
     * 6：新淘抢购
     * 7：新一行两个
     * 8：一行两个
     */
    // public int list_style_id;

    public int default_style;   //默认样式（目前只有 1-一行一个；2-一行两个）
    public int one_style; //一行一个样式（100-商品流样式）
    public int two_style; //一行两个样式（1000-商品流样式）

    public String top_text;//品牌馆频道顶部文案
    public String bottom_text;//品牌馆频道低部文案

    public List<ChannelBrandItemDo> item_list;//商品或专场列表


    public String jsonStr;
    public int channel_type;// -1 为top market
    public int channel_id;

    public String encrypt; // 数据加密标识
}
