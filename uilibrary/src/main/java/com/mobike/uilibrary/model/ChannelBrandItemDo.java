package com.mobike.uilibrary.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019-06-05 11:19
 */
public class ChannelBrandItemDo implements Serializable {
    public long id;
    public long brand_area_id;
    public String name;
    public String picture;
    public String item_id;


    /**
     * 老的：
     * 1-自建+淘客（淘客>自建）
     * 2-百川
     * <p/>
     * 新的：5.3.3及之后版本
     * 1 - 老淘客;
     * 2 - 跳百川;
     * 3 - 走自建;
     * 4 - 启用新的SDK跳转到新的淘宝H5页面;
     */

    public int tag_icon;
    public String promotion_ids;
    public String original_price;
    public String vip_price;
    public String price_btn;
    public int stock;
    public int tb_stock;

    public int shop_type;//'店铺类型（所属平台）：1-天猫；2-淘宝'
    public int item_type;
    public int promotion_type;//0<0不显示，1-限时秒杀 2-超级爆款 3-秒杀模块 4,新人福利，  5，大促标签 -自定义促销样式
    public int is_new;
    public String purchase_btn; //抢购按钮
    public String btn_text; //抢购文案
    public String sttag_text;
    /**
     * 1限量N件， 2仅剩N件， 3 抢光
     * 4 结束 ，5 new，6 下架(删除)
     */
    public int sttag_type;

    /**
     * item_count_msg=“商品54” 柚子街app1.1 经期5.4.1，首页接口，专场商品数量string：
     */
    public String item_count_msg = null;

    public List<String> promotion_text_arr;
    public int timer_type;

    /**
     * 今日上新tag标签 -- 首页频道商品
     */
    public String new_brand_picture;

    public int down_count;
    public int redirect_type;
    public String redirect_url;
    public String adv_picture;

    public boolean iSactive() {
        return is_active == 1;
    }

    public int is_active; //商品是否有效 V2.5.1新增 收藏

    public List<ItemTagsDo> promotion_voarr;

    public ItemTagsDo promotion;

    public ItemTagsDo sec_kill;

    /**
     * 销售价前的“专享价”或“券后价”文本
     */
    public String price_text;


    /************未知字段***********/
    public String updated_at;
    public String brand_area_tag;
    public String update_msg;
    public String slogan_picture;

    /**
     * V2.7.0新增补贴tags
     * 一行一个&一行两个补贴tags，图片圆角样式才有
     */
    public List<ItemTagsDo> one_style_promotion_tag_arr;
    public List<ItemTagsDo> two_style_promotion_tag_arr;

    /**
     * 自定义 促销图片
     */
    public String promotion_image;
    /**
     * 促销标签 商品缩略图的统一角标
     */
    public String promotion_tag;
    /**
     * 促销名称  双列样式显示名称
     */
    public String promotion_name;
    /**
     * 优惠券 新样式 新增字段
     */
    public String promotion_lab;          // 100元券
    public String original_price_writing; // 淘宝价 原价 天猫价
    public String vip_price_writing;      // 券后价 专享价


    /**
     * 淘抢购样式新增字段
     */
    public String sub_name; //副标题
    public int title_tag; //标题左边标签
    public String picture_tag; //商品促销标签 下发图片链接
    public String sub_title_tag;

    /**
     * 品牌团频道字段
     */
    public String main_title; //品牌团主标题
    public String sub_title;  //品牌团副标题(运营促销字段)
    public String sub_title_icon; //品牌团 新增 副标题icon
    public int style_type; //品牌专场展示样式 0,单品样式,1、2、3长图样式,3.长图样式布局变化,4.商品透出样式；
    public String item_text;  //倒计时文案
    //public List<TaeChildItemModel> sub_item_list; // style_type=4才有，商品透出字段
    public int scrollPos = 0; //品牌透出样式滚动位置记录


    /************************淘汰的字段 start*/
    //    public int id;
    public int brand_id;


    public String start_at;
    public String end_at;

    public int coin_amount;
    public double purchase_price;
    public int order_count;
    public String promotion_custom;
    public int redirect_brand_area_id;
    public int activity_id = 0;
    public int is_liked;
    public int item_shop_type; //'店铺类型（所属平台）：1-天猫；2-淘宝'

    /************淘汰的字段 end**********/

    public int viewType = 0;//0 正常数据，10000顶部浮层，10001 好货推荐底部浮层，10002 今日上新结束 ,10003 item FooterView
    public boolean isGoodsEnd;//是否好货推荐结束
    public String history_descript;//历史商品文案
    public String history_icon;//历史图片
    public boolean isItemEnd;//是最后一个item
    public String footerStr;
    public String footerUrl;

    public int exposureTimes;//曝光次数
    public boolean isSession;//是否为专场

    public void emptySection() {
        viewType = 0;
        isGoodsEnd = false;
        history_descript = "";
        history_icon = "";
        isItemEnd = false;
        footerStr = "";
        footerUrl = "";
    }

    public class ItemTagsDo implements Serializable {
        /**
         * type :
         * 1，超级爆款或者限时秒杀，
         * 2自定义，
         * 3优惠券，
         * 4拍下减，
         * 5已抢XX件，
         * 6勾选标签
         * 7vipPrice
         * 8柚币数目标签
         * 9淘礼金补贴
         * is_text : true
         * picture : null
         * text : 券后价19.8元
         */

        public int type;
        public boolean is_text;
        public String picture;
        public String text;
        public int style_type;//1 显示标签前面的“柚”图片或者柚币图片 0 不显示
    }
}
