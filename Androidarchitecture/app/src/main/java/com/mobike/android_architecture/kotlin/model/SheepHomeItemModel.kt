package com.mobike.android_architecture.kotlin.model

import java.io.Serializable

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019-04-30 10:17
 */
class SheepHomeItemModel : Serializable/*,MultiItemEntity*/ {
    var num_iid: Long = 0  // 淘宝id
    var user_type: Int = 0 // 商品类型   0-淘宝；1-天猫；
    var coupon_amount_str: String? = null // 优惠额度文案
    var title_display: String? = null // 标题
    var pict_url: String? = null  // 图片
    var reserve_price_str: String? = null // 原价文案
    var zk_final_price_str: String? = null //  券后价文案
    var volume_str: String? = null  //  已抢200件
    var redirect_url: String? = null // 跳转协议
    var is_new: Boolean = false   // 是否今日上新
    var reserve_price: String? = null  // 原价
    var zk_final_price: String? = null  // 最终价
    var rebate_amount_str: String? = null // 在返XX元
    var rebate_amount: Float = 0.toFloat() // 在返XX元
    var allowance_amount_str: String? = null // X元羊毛补贴
    var zhuan_tip_str: String? = null // 下单再赚多少^元^
    var shop_icon: String? = null     // 商品icon
    var is_zhuan: Boolean? = null     // 搜索弹框判断是否是赚赚商品
    var zhuan_str: String? = null     // V2.6.0新增 分享赚文案
    var position: Int = 0 //返利商城位置
    var shop_title: String? = null //店铺名
    //public List<SheepPromotionModel> promotion;  //V2.6.0新增标签数组字段

    //推荐商品添加字段
    var union_item_str: String? = null
    // public List<WeChatUserModel> wechat_list = new ArrayList<>();
    var msg_num_str: String? = null
    var item_num: Int = 0
    var redirect_recommend_url: String? = null
    var avatar_url: String? = null
    var recommend_str: String? = null
    var showRecommendType = -1 //1只显示title ，2全部不显示  3全部显示

    //自定义字段，非接口下发
    //    public int itemType = ItemStyle.TYPE_DATA_ONE;
    //    public int loadingStatus = LoadingView.STATUS_LOADING;
    var loadingMsg = ""
    var loadingHeight: Int = 0

    //自定义数据，非接口下发数据，推荐词在列表中的位置
    //    public int listPosition;
    //    public List<HomeHotWordModel.HotWordList> hotWordList;
    //    public HomeHotWordModel homeHotWordModel;
    //    public RedPacketModel redPacketModel;
    //    public String todayTagPic;
    //    public String prompt_top;
    //    public int itemHotWordType=ItemStyle.TYPE_HOT_WORD_THREE;

    //    @Override
    //    public int getItemType() {
    //        return itemType;
    //    }
}
