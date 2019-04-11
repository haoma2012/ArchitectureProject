//package com.mobike.uilibrary.ui;
//
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//import com.mobike.uilibrary.R;
//import com.mobike.uilibrary.base.BaseFragment;
//import org.greenrobot.eventbus.EventBus;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
///**
// * 首页频道推荐页 改造NewSheepHomeFragment 移除滚动效果&下拉刷新
// * Created by yangdehao@xiaoyouzi.com  on 2019/2/15 下午3:51
// */
//public class SheepHomeChannelFragment extends BaseFragment implements ISheepHomeView, BaseQuickAdapter.RequestLoadMoreListener {
//    private static final String TAG = SheepHomeChannelFragment.class.getSimpleName();
//    public static final String CHANNEL_ID = "home_channel_id";
//    public static final String CHANNEL_RECOMMEND = "home_is_recommended";
//    // container
//    private RelativeLayout mContainer;
//    private RecyclerView mRecyclerView;
//    private LoadingView mWholeLoadingView;
//    // headerView
//    private View mHeadView;
//    private RelativeLayout mVideoLayout;
//    private TextView mTabTitle;
//    private LoaderImageView mTabTitleIcon;
//    private EcoVideoHelper videoHelper;
//    private BetterRecyclerView mRecommendRecyclerView;
//    private RecommendAdapter mRecommendAdapter;
//    private SheepHomeAdapter mSheepHomeAdapter;
//    private EcoLoadMoreView mEcoLoadMoreView;
//    private boolean isLoadMore;//防止重复加载数据
//
//    private SheepFloatDialogManager mDialogManager;
//    private SheepHomePresenter mSheepHomePresenter;
//    private List<HomeHotWordModel> mHotWordGroup = new ArrayList<>();
//    private List<RedPacketModel> mRedPacketData = new ArrayList<>();
//    private HomeRecommendModel mRecommendData;
//    private SheepHomeParams mSheepHomeParams;
//
//    private int mStatusBarBg;
//    private String mTodayTagPic = "";
//    private int mItemPosition;
//    private boolean isHomeTab = true;
//    private boolean mHasShowedRedPacket;
//    private int mRedPacketPosition = -1;
//    private int mHotWordCount = 0;
//    private boolean isShowToast;
//    private String mRedPacketContent;
//    private int mAddIndex = -1;
//    private int mAreadyAddCount;
//    // 请求参数
//    public int channel_id;   // 频道id
//    public int is_index; // 是否推荐 1：是推荐 0：非推荐
//
//
//    public static SheepHomeChannelFragment newInstance(Bundle bundle) {
//        SheepHomeChannelFragment fragment = new SheepHomeChannelFragment();
//        fragment.setArguments(bundle);
//        return fragment;
//    }
//
//    @Override
//    protected int getLayout() {
//        return R.layout.fragment_sheep_home_channel;
//    }
//
//    @Override
//    public String getPageName() {
//        return "index";
//    }
//
//    @Override
//    protected void onStartEnter() {
////        if (isWhiteTitleBar())// 非新页面不执行 start  进入事件
//        super.onStartEnter();
//    }
//
//
//    @Override
//    protected void initLogic(Bundle savedInstanceState) {
//        super.initLogic(savedInstanceState);
//        //EcoStatusBarController.setStatusBarColor(getActivity(), getResources().getColor(R.color.sheep_status_bar_bg));
//        titleBarCommon.setCustomTitleBar(-1);
//        getIntentData();
//        if (savedInstanceState != null) { // 取save保存的数据
//            channel_id = savedInstanceState.getInt(CHANNEL_ID, 1);
//            is_index = savedInstanceState.getInt(CHANNEL_RECOMMEND, 1);
//        }
//        initView();
//        initHeaderView();
//        initAdapter();
//        initListener();
//    }
//
//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        if (outState != null) {
//            outState.putInt(CHANNEL_ID, channel_id);
//            outState.putInt(CHANNEL_RECOMMEND, is_index);
//        }
//    }
//
//    // 获取Intent值
//    private void getIntentData() {
//        Bundle bundle = getArguments();
//        if (bundle != null) {
//            if (ProtocolUtil.isFromUri(bundle)) {
//                String params = ProtocolUtil.getParamByBundle(bundle);
//                if (!TextUtils.isEmpty(params)) {
//                    try {
//                        JSONObject jsonParams = new JSONObject(params);
//                        channel_id = EcoStringUtils.getJsonInt(jsonParams, CHANNEL_ID);
//                        is_index = EcoStringUtils.getJsonInt(jsonParams, CHANNEL_RECOMMEND);
//                    } catch (Exception e) {
//                        LogUtils.d(TAG, e);
//                    }
//                }
//            } else {
//                channel_id = bundle.getInt(CHANNEL_ID, 1);
//                is_index = bundle.getInt(CHANNEL_RECOMMEND, 1);
//            }
//        }
//    }
//
//
//    @Override
//    protected void initView(View view) {
//        mContainer = getRootView().findViewById(R.id.channel_container);
//        mRecyclerView = getRootView().findViewById(R.id.sheep_channel_recycler_view);
//        mWholeLoadingView = getRootView().findViewById(R.id.sheep_home_whole_loading);
//
//        mSheepHomePresenter = new SheepHomePresenter(this);
//        mSheepHomeParams = new SheepHomeParams();
//
//        mDialogManager = new SheepFloatDialogManager(getActivity());
//    }
//
//    private void initHeaderView() {
//        mSheepHomeAdapter = new SheepHomeAdapter(getActivity());
//        mSheepHomeAdapter.setFragment(this);
//        mHeadView = ViewUtil.getEcoLayoutInflater(getActivity()).inflate(R.layout.header_sheep_home, null);
//        mRecommendRecyclerView = mHeadView.findViewById(R.id.header_recommond);
//        mVideoLayout = mHeadView.findViewById(R.id.video_layout);
//        mTabTitleIcon = mHeadView.findViewById(R.id.header_tab_title_icon);
//        mTabTitle = mHeadView.findViewById(R.id.header_tab_title);
//        mRecommendRecyclerView.setNestedScrollingEnabled(false);
//        mSheepHomeAdapter.setOnLoadMoreListener(this, mRecyclerView);
//        mEcoLoadMoreView = new EcoLoadMoreView();
//        mSheepHomeAdapter.setLoadMoreView(mEcoLoadMoreView);
//        mSheepHomeAdapter.setEnableLoadMore(false);
//        mSheepHomeAdapter.addHeaderView(mHeadView);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//
//        mRecyclerView.setLayoutManager(linearLayoutManager);
//        mRecyclerView.setAdapter(mSheepHomeAdapter);
//
//    }
//
//    private void initListener() {
//        mWholeLoadingView.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                if (EcoNetWorkStatusUtils.assertNetWorkIsValid(getActivity())) {
////                    if (mWholeLoadingView.getStatus() == LoadingView.STATUS_LOADING) {
////                        return;
////                    }
//                    mSheepHomePresenter.loadMarkData(mSheepHomeParams);
//                }
//            }
//        });
//
//        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                int lastVisibleItem = ((LinearLayoutManager) mRecyclerView.getLayoutManager()).findLastVisibleItemPosition();
//                if (lastVisibleItem > 0) {
//                    mItemPosition = lastVisibleItem;//除去一个 header
//                }
//
//                if (dy > mHeadView.getHeight()) {
//                    if (videoHelper != null) {
//                        videoHelper.stopPlayVideo();
//                    }
//                }
//
//                if (mItemPosition < 10) {//将tab导航也算作item了
//                    mEcoKeyTopView.hideAkeyTopView();
//                } else {
//                    mEcoKeyTopView.showAkeyTopView();
//                }
//
//            }
//        });
//        mEcoKeyTopView.setOnAKeyTopLisener(new EcoAKeyTopView.OnAKeyTopClickListener() {
//            @Override
//            public void OnAKeyTopClick() {
//                scrollToTop();
//                // 发个事件通知TabHome展开顶部
//                EventBus.getDefault().post(new HomeExpandEvent());
//
//            }
//        });
//    }
//
//    @Override
//    protected void initData() {
//        super.initData();
//        loadData();
//    }
//
//    public void scrollToTop() {
//        mEcoKeyTopView.hideAkeyTopView();
//        mRecyclerView.fling(0, 0);
//        mRecyclerView.scrollToPosition(0);
//        mItemPosition = 0;
//    }
//
//
//    public void handleRefresh() {
//        if (mSheepHomePresenter == null) {
//            return;
//        }
//
//        if (mSheepHomeParams == null) {
//            mSheepHomeParams = new SheepHomeParams();
//        }
//        mSheepHomeParams.page = 1;
//        mSheepHomeParams.isRefresh = true;
//        mAddIndex = -1;
//        mAreadyAddCount = 0;
//        mSheepHomePresenter.loadMarkData(mSheepHomeParams);
//    }
//
//    private void initAdapter() {
//        mRecommendAdapter = new RecommendAdapter(getActivity());
//        mRecommendAdapter.setFragment(this);
//        mRecommendAdapter.setViewPath("hotword_index");
//        mRecommendRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
//        int dp9 = (int) getResources().getDimension(R.dimen.dp_value_9);
//        int dp10 = (int) getResources().getDimension(R.dimen.dp_value_10);
//        mRecommendRecyclerView.addItemDecoration(new LinearHorItemDecoration(dp10, 0, 0, dp9));
//        mRecommendRecyclerView.setAdapter(mRecommendAdapter);
//    }
//
//    private void loadData() {
//        if (mSheepHomePresenter == null) {
//            mSheepHomePresenter = new SheepHomePresenter(this);
//        }
//        if (mSheepHomeParams == null) {
//            mSheepHomeParams = new SheepHomeParams();
//        }
//        mSheepHomePresenter.loadMarkData(mSheepHomeParams);
//        mSheepHomePresenter.loadFloatData();
//        mSheepHomePresenter.loadMsgCount(true);
//    }
//
//    private void testPush() {
//        PushNotifyDo notifyDo = new PushNotifyDo();
//        notifyDo.title = "自定义消息";
//        notifyDo.content = "测试笑嘻嘻嘻嘻\n 笑嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻阿打发的故事的故事";
//        notifyDo.redirect_url = EcoScheme.URI_SALE_CLASSIFY;
//        mDialogManager.showPushDialog(mContainer, notifyDo);
//    }
//
//    private Handler popupHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            testPush();
//
//            EventBus.getDefault().post(new SheepMsgCountEvent(new MsgCountDo()));
//        }
//    };
//
//    @Override
//    public void updateNavList(List<HomeNavItemModel> list) {
//
//    }
//
//    /**
//     * 更新首页可伸缩头部数据
//     *
//     * @param homeSearchModel
//     */
//    @Override
//    public void updateMarketTitle(HomeSearchModel homeSearchModel) {
////        if (!StringUtils.isNull(homeSearchModel.bar_color)) {
////            mStatusBarBg = ColorUtils.parseColor(homeSearchModel.bar_color, getResources().getColor(R.color.sheep_status_bar_bg));
////            EcoStatusBarController.setStatusBarColor(getActivity(), mStatusBarBg);
////        }
//    }
//
//    @Override
//    public void updateVideo(HomeMarketModel homeMarketModel) {
//        // 注释掉Banner&视频 显示在TabHome里了
////        videoHelper = new EcoVideoHelper(getActivity());
////        View videoLayout = videoHelper.getVideoLayout();
////        mVideoLayout.removeAllViews();
////        if (videoLayout == null) {
////            mVideoLayout.setVisibility(View.GONE);
////        } else {
////            mVideoLayout.addView(videoLayout);
////        }
////        videoHelper.updateVideoViewUI(homeMarketModel);
////        mVideoLayout.setVisibility(View.GONE);
//    }
//
//    @Override
//    public void updateTabMsg(HomeMarketModel marketModel) {
//        mTodayTagPic = marketModel.item_new_tag_image;
//        if (!StringUtils.isNull(marketModel.item_top_image)) {
//            ViewUtil.showHideView(mTabTitle, false);
//            ViewUtil.showHideView(mTabTitleIcon, true);
//            SheepHomeDataManager.updateTabTitleImage(mTabTitleIcon, marketModel.item_top_image);
//        } else {
//            ViewUtil.showHideView(mTabTitleIcon, false);
//            ViewUtil.showHideView(mTabTitle, true);
//            mTabTitle.setText(marketModel.item_top_title);
//        }
//    }
//
//    @Override
//    public void updateHotWord(List<HomeHotWordModel> hotWordGroup) {
//        mHotWordGroup.clear();
//        mHotWordGroup.addAll(hotWordGroup);
//    }
//
//    @Override
//    public void refreshHotWord() {
//        if (mHotWordGroup.size() > 0) {
//            mRecommendAdapter.setNewData(HotWordListUtils.handleHotWordList(mHotWordGroup.get(0)));
//        } else {
//            mRecommendAdapter.setNewData(null);
//        }
//    }
//
//    @Override
//    public void updateFloatDialog(HomeFloatModel data) {
//        if (isHomeTab) {
//            mDialogManager.showDialog(data);
//        }
//    }
//
//    @Override
//    public void updatePushNotify(PushNotifyDo data) {
//        mDialogManager.showPushDialog(mContainer, data);
//    }
//
//    @Override
//    public void updateRedPacket(List<RedPacketModel> maskData) {
//        mRedPacketData.clear();
//        mRedPacketPosition = -1;
//        if (maskData != null && maskData.size() != 0) {
//            mRedPacketData.addAll(maskData);
//            if (isHomeTab) {
//                if (!mHasShowedRedPacket) {
//                    mHasShowedRedPacket = true;
//                    for (RedPacketModel model : mRedPacketData) {
//                        if (model.display_type == 2) {
//                            mDialogManager.showRedPacketDialog(model);
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    @Override
//    public void updateRecommend(HomeRecommendModel recommendData) {
//        mRecommendData = recommendData;
//    }
//
//    @Override
//    public void updateItems(SheepHomeModel itemList, SheepHomeParams sheepHomeParams) {
//        removeRepeateData(itemList.item_list, sheepHomeParams);
//        addMarketToItemList(itemList.item_list, handleList(), sheepHomeParams.page);
//        addRecommendData(itemList.item_list, sheepHomeParams.page);
//        if (sheepHomeParams.isRefresh) {
//            sheepHomeParams.isRefresh = false;
//        }
//
//        if (sheepHomeParams.isEnd) {
//            mSheepHomeAdapter.setEnableLoadMore(false);
//        } else {
//            mSheepHomeAdapter.setEnableLoadMore(true);
//        }
//        //addMarketData(itemList.item_list);
//
//        if (mHotWordGroup != null && mHotWordGroup.size() > 1) {
//            mSheepHomeAdapter.setHotWordGroupSize(mHotWordGroup.get(1).position);
//        }
//
//        if (sheepHomeParams.page > 1) {
//            mSheepHomeAdapter.addData(itemList.item_list);
//            mSheepHomeAdapter.loadMoreComplete();
//            isLoadMore = false;
//        } else {
//            mSheepHomeAdapter.setNewData(itemList.item_list);
//        }
//    }
//
//    private void addRecommendData(List<SheepHomeItemModel> itemList, int page) {
//        if (page == 1 && mRecommendData != null && mRecommendData.item_list != null && mRecommendData.item_list.size() > 0) {
//            SheepHomeItemModel modelRecommend = mRecommendData.item_list.get(0);
//            modelRecommend.itemType = SheepHomeAdapter.TYPE_HOME_REOMMEMD;
//            modelRecommend.wechat_list = mRecommendData.wechat_list;
//            modelRecommend.msg_num_str = mRecommendData.msg_num_str;
//            modelRecommend.item_num = mRecommendData.item_num;
//            modelRecommend.redirect_recommend_url = mRecommendData.redirect_url;
//            for (int i = 0; i < itemList.size(); i++) {
//                SheepHomeItemModel firstModel = itemList.get(i);
//                if (firstModel.itemType == ItemStyle.TYPE_DATA_ONE
//                        || firstModel.itemType == SheepHomeAdapter.TYPE_NEW_MEMBER_DETAIL) {
//                    itemList.add(i, modelRecommend);
//                    break;
//                }
//            }
//        }
//    }
//
//    private void addMarketToItemList(List<SheepHomeItemModel> itemList, List<SheepHomeItemModel> marketList, int page) {
//        //减去header和正在加载中的item
//        int itemCount = page == 1 ? 0 : mSheepHomeAdapter.getItemCount() - 2;
//        int totalCount = itemCount + itemList.size();
//        if (marketList != null) {
//            for (int i = 0; i < marketList.size(); i++) {
//                if (i > mAddIndex) {
//                    SheepHomeItemModel model = marketList.get(i);
//                    int position = 0;
//                    if (model.itemType == SheepHomeAdapter.TYPE_RED_PACKET ||
//                            model.itemType == SheepHomeAdapter.TYPE_ASSISTANCE_RED_PACKET
//                            || model.itemType == SheepHomeAdapter.TYPE_WECHAT_BIND_RED_PACKET
//                            || model.itemType == SheepHomeAdapter.TYPE_WECHAT_ASSISTANCE_RED_PACKET) {
//                        position = model.redPacketModel.flow_rank;
//                    } else {
//                        position = model.homeHotWordModel.position;
//                    }
//                    if (position >= itemCount && position <= totalCount) {
//                        position = position - itemCount + mAreadyAddCount;
//                        if (position >= 0) {
//                            if (position < itemList.size()) {
//                                itemList.add(position, model);
//                            } else {
//                                itemList.add(model);
//                            }
//                            mAddIndex = i;
//                            mAreadyAddCount++;
//                        }
//                    } else {
//                        if (mSheepHomeParams.isEnd && totalCount <= position) {
//                            itemList.add(itemList.size() - 1, model);
//                            mAddIndex = i;
//                            mAreadyAddCount++;
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    /**
//     * 合并红包和热搜词数据
//     */
//    private List<SheepHomeItemModel> handleList() {
//
//        List<SheepHomeItemModel> redPacketModels = new ArrayList<>();
//
//        for (int i = 0; i < mRedPacketData.size(); i++) {
//            RedPacketModel redPacketModel = mRedPacketData.get(i);
//            if (redPacketModel.now >= redPacketModel.start_at && redPacketModel.now <= redPacketModel.end_at) {
//                // 1.红包方案
//                if (redPacketModel.display_type == 1) {
//                    SheepHomeItemModel model = new SheepHomeItemModel();
//                    model.redPacketModel = redPacketModel;
//                    model.itemType = SheepHomeAdapter.TYPE_RED_PACKET;
//                    redPacketModels.add(model);
//                } else if (redPacketModel.display_type == 3) { // 3.微信好友助力商品流
//                    WeChatAssistanceModel wechat_assistance = redPacketModel.wechat_assistance;
//                    if (wechat_assistance != null) {
//                        // 是否显示过
//                        boolean hasShowed = SharedPreferencesUtil.getBoolean(getApplicationContext(),
//                                EcoConstants.HOME_WECHAT_ASSISTANCE_FLAG + wechat_assistance.wechat_assistance_flag,
//                                false);
//                        if (!hasShowed) {
//                            SheepHomeItemModel model = new SheepHomeItemModel();
//                            model.redPacketModel = redPacketModel;
//                            model.itemType = SheepHomeAdapter.TYPE_WECHAT_ASSISTANCE_RED_PACKET;
//                            redPacketModels.add(model);
//                        }
//                    }
//                } else if (redPacketModel.display_type == 4) { // 4.助力红包 根据id判断是否显示
//                    // 是否显示过助力红包
//                    boolean showRedPacket = SharedPreferencesUtil.getBoolean(getApplicationContext(),
//                            EcoConstants.HOME_ASSISTANCE_RED_PACKET_ID + redPacketModel.id, false);
//                    if (!showRedPacket) {
//                        SheepHomeItemModel model = new SheepHomeItemModel();
//                        model.redPacketModel = redPacketModel;
//                        model.itemType = SheepHomeAdapter.TYPE_ASSISTANCE_RED_PACKET;
//                        redPacketModels.add(model);
//                    }
//                } else if (redPacketModel.display_type == 5) { // 5.微信绑定红包
//                    // 是否显示过微信绑定
//                    boolean showWeChatBindPacket = SharedPreferencesUtil.getBoolean(getApplicationContext(),
//                            EcoConstants.HOME_WECHAT_BIND_PACKET_ID + redPacketModel.id, false);
//                    if (!showWeChatBindPacket) {
//                        SheepHomeItemModel model = new SheepHomeItemModel();
//                        model.redPacketModel = redPacketModel;
//                        model.itemType = SheepHomeAdapter.TYPE_WECHAT_BIND_RED_PACKET;
//                        redPacketModels.add(model);
//                    }
//                }
//            }
//        }
//
//        List<SheepHomeItemModel> hotWordModels = new ArrayList<>();
//        for (int i = 0; i < mHotWordGroup.size(); i++) {
//            if (i > 0) {
//                HomeHotWordModel homeHotWordModel = mHotWordGroup.get(i);
//                SheepHomeItemModel model = new SheepHomeItemModel();
//                model.itemType = ItemStyle.TYPE_RECOMMEND;
//                model.listPosition = i;
//                model.homeHotWordModel = homeHotWordModel;
//                model.hotWordList = HotWordListUtils.handleHotWordList(homeHotWordModel);
//                model.prompt_top = homeHotWordModel.prompt_top;
//                hotWordModels.add(model);
//            }
//        }
//
//        if (redPacketModels.size() == 0) {
//            return hotWordModels;
//        }
//
//        if (hotWordModels.size() == 0) {
//            return redPacketModels;
//        }
//       /* List<SheepHomeItemModel> mergeList = new ArrayList<>();
//        int hotWordSize = hotWordModels.size();
//        int redPacketSize = redPacketModels.size();
//        int maxCount = Math.max(hotWordSize,redPacketSize);
//
//        for (int i = 0 ;i < maxCount;i++){
//            SheepHomeItemModel hotWordModel = null;
//            SheepHomeItemModel redPacketModel = null;
//            if(i < hotWordSize){
//                hotWordModels.get(i);
//            }
//
//        }*/
//        int addIndex = -1;
//        for (int i = 0; i < hotWordModels.size(); i++) {
//            SheepHomeItemModel hotWordModel = hotWordModels.get(i);
//            for (int j = 0; j < redPacketModels.size(); j++) {
//                SheepHomeItemModel redPacketModel = redPacketModels.get(j);
//                if (hotWordModel.homeHotWordModel != null && redPacketModel.redPacketModel != null) {
//                    if (j > addIndex) {
//                        if (redPacketModel.redPacketModel.flow_rank < hotWordModel.homeHotWordModel.position) {
//                            hotWordModels.add(i, redPacketModel);
//                            addIndex = j;
//                        } else if (redPacketModel.redPacketModel.flow_rank == hotWordModel.homeHotWordModel.position) {
//                            if (i != hotWordModels.size() - 1) {
//                                hotWordModels.add(i + 1, redPacketModel);
//                            } else {
//                                hotWordModels.add(redPacketModel);
//                            }
//                            addIndex = j;
//                        } else {
//                            if (i != hotWordModels.size() - 1) {
//                                SheepHomeItemModel nextHotWordModel = hotWordModels.get(i + 1);
//                                if (redPacketModel.redPacketModel.flow_rank < nextHotWordModel.homeHotWordModel.position) {
//                                    hotWordModels.add(i + 1, redPacketModel);
//                                    addIndex = j;
//                                }
//                            } else {
//                                hotWordModels.add(redPacketModel);
//                                addIndex = j;
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return hotWordModels;
//    }
//
//
//    private List<SheepHomeItemModel> removeRepeateData(List<SheepHomeItemModel> itemList, SheepHomeParams sheepHomeParams) {
//        List<SheepHomeItemModel> data = mSheepHomeAdapter.getData();
//        if (data != null) {
//            Iterator<SheepHomeItemModel> it = itemList.iterator();
//            while (it.hasNext()) {
//                SheepHomeItemModel model = it.next();
//                //设置商品流类型
//                if (model.num_iid != 0) {
//                    //设置今日上新
//                    if (model.is_new) {
//                        model.todayTagPic = mTodayTagPic;
//                    }
//                    if (!StringUtils.isNull(model.zhuan_tip_str)) {
//                        model.itemType = SheepHomeAdapter.TYPE_NEW_MEMBER_DETAIL;
//                    } else {
//                        model.itemType = ItemStyle.TYPE_DATA_ONE;
//                    }
//                }
//                removeRepeatRecommend(model, it);
//
//                if (sheepHomeParams.page > 1) {
//                    for (int j = 0; j < data.size(); j++) {
//                        SheepHomeItemModel model1 = data.get(j);
//                        //判断是不是商品的model，如果是，进行去重
//                        if (model.num_iid != 0 && model.num_iid == model1.num_iid) {
//                            it.remove();
//                        }
//                    }
//                }
//            }
//        }
//        return itemList;
//    }
//
//    private void removeRepeatRecommend(SheepHomeItemModel model, Iterator<SheepHomeItemModel> iterator) {
//        if (mRecommendData != null) {
//            SheepHomeItemModel modelRecommend = mRecommendData.item_list.get(0);
//            if (model.num_iid == modelRecommend.num_iid) {
//                iterator.remove();
//            }
//        }
//    }
//
//    @Override
//    public void updateLoading(int statusHidden, String content) {
//        if (statusHidden == LoadingView.STATUS_NODATA) {
//            if (NetWorkStatusUtils.hasNetWork(getActivity())) {
//                mWholeLoadingView.setStatus(LoadingView.STATUS_NODATA);
//            } else {
//                mWholeLoadingView.setContent(LoadingView.STATUS_NONETWORK, getResources().getString(R.string.no_network));
//            }
//            return;
//        }
//        if (StringUtils.isNull(content)) {
//            mWholeLoadingView.setStatus(statusHidden);
//        } else {
//            mWholeLoadingView.setContent(statusHidden, content);
//        }
//    }
//
//    @Override
//    public void updateNoData(SheepHomeParams sheepHomeParams) {
//        if (sheepHomeParams.page == 1) {
////            if (mSmartRefreshLayout.isRefreshing()) {
////                mSmartRefreshLayout.finishRefresh();
////            }
//            //如果有数据情况下刷新无数据不处理
//            if (mSheepHomeAdapter.getData() != null && mSheepHomeAdapter.getData().size() > 0) {
//
//            } else {
//                List<SheepHomeItemModel> list = new ArrayList<>();
//                SheepHomeItemModel model = new SheepHomeItemModel();
//                model.itemType = ItemStyle.ITEM_TYPE_LOADING;
//                model.loadingStatus = LoadingView.STATUS_NODATA;
//                if (NetWorkStatusUtils.hasNetWork(getActivity())) {
//                    model.loadingMsg = getResources().getString(R.string.no_data_refresh);
//                } else {
//                    model.loadingMsg = getResources().getString(R.string.no_network_to_refresh);
//                }
//                list.add(model);
//                mSheepHomeAdapter.setNewData(list);
//            }
//
//        } else {
//            mSheepHomeAdapter.loadMoreFail();
//            isLoadMore = false;
//            mSheepHomeParams.page -= 1;
//        }
//
//    }
//
//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        if (mSheepHomeAdapter != null && !ListUtils.isEmpty(mSheepHomeAdapter.getData())) {
//            try {
//                MeetyouBiAgent.updatePageHiddenChanged(this, !hidden);
//            } catch (Exception e) {
//
//            }
//        }
//        if (mStatusBarBg != 0) {
//            if (hidden) {
//                if (mDialogManager != null && mDialogManager.isShowPushDialog()) {
//                    mDialogManager.dismissPushDialog();
//                }
//
//                EcoStatusBarController.setStatusBarColor(getActivity(), getResources().getColor(R.color.sheep_status_bar_bg));
//            } else {
//                EcoStatusBarController.setStatusBarColor(getActivity(), mStatusBarBg);
//                if (videoHelper != null) {
//                    videoHelper.stopPlayVideo();
//                    videoHelper.hidePlayBotton();
//                }
//            }
//        }
//    }
//
//    public void onEventMainThread(RedPacketEvent event) {
//        if (event.isShowToast()) {
//            isShowToast = event.isShowToast();
//            mRedPacketContent = event.getContent();
//        } else {
//            SharedPreferencesUtil.saveBoolean(getApplicationContext(), EcoDoorConst.IS_SHOWED_RED_PACKET_TOAST, true);
//        }
//
//        if (mSheepHomeAdapter != null) {
//            List<SheepHomeItemModel> data = mSheepHomeAdapter.getData();
//            if (data != null && data.size() > 0) {
//                for (int i = 0; i < data.size(); i++) {
//                    SheepHomeItemModel model = data.get(i);
//                    if (model.itemType == SheepHomeAdapter.TYPE_RED_PACKET) {
//                        data.remove(model);
//                    }
//                }
//                mSheepHomeAdapter.setNewData(data);
//            }
//        }
//    }
//
//    /**
//     * 微信激活绑定红包
//     *
//     * @param event WeChatRedPacketEvent
//     */
//    public void onEventMainThread(WeChatRedPacketEvent event) {
//        if (event == null || getActivity() == null || getActivity().isFinishing()) {
//            return;
//        }
//        // show toast
//        String message = event.getMessage();
//        if (!StringUtils.isNull(message)) {
//            ToastUtils.showToast(getApplicationContext(), message);
//        }
//        // 激活成功 隐藏
//        if (mSheepHomeAdapter != null && event.getBindSuccess()) {
//            List<SheepHomeItemModel> data = mSheepHomeAdapter.getData();
//            if (data != null && data.size() > 0) {
//                for (int i = 0; i < data.size(); i++) {
//                    SheepHomeItemModel model = data.get(i);
//                    if (model != null && model.itemType == SheepHomeAdapter.TYPE_WECHAT_BIND_RED_PACKET) {
//                        // 记录微信绑定成功不展示 除非id变更再展示
//                        RedPacketModel redPacketModel = model.redPacketModel;
//                        if (redPacketModel != null) {
//                            SharedPreferencesUtil.saveBoolean(getApplicationContext(),
//                                    EcoConstants.HOME_WECHAT_BIND_PACKET_ID + redPacketModel.id,
//                                    true);
//                        }
//                        data.remove(model);
//                    }
//                }
//                mSheepHomeAdapter.setNewData(data);
//            }
//        }
//    }
//
//    /**
//     * 红包消费后的处理
//     *
//     * @param event RemoveRedPacketEvent
//     */
////    public void onEventMainThread(RemoveRedPacketEvent event) {
////        if (event == null || getActivity() == null || getActivity().isFinishing()) {
////            return;
////        }
////        // 隐藏红包类型的item
////        int redPacketType = event.getRedPacketType();
////        if (mSheepHomeAdapter != null) {
////            List<SheepHomeItemModel> data = mSheepHomeAdapter.getData();
////            if (data != null && data.size() > 0) {
////                for (int i = 0; i < data.size(); i++) {
////                    SheepHomeItemModel model = data.get(i);
////                    if (model != null && model.itemType == redPacketType) {
////                        LogUtils.d(TAG, "点击的位置item类型: " + redPacketType +
////                                " model.itemType = " + model.itemType);
////                        data.remove(model);
////                    }
////                }
////                mSheepHomeAdapter.setNewData(data);
////            }
////        }
////    }
//
////    /**
////     * 重复点击，展开搜索框
////     *
////     * @param event
////     */
////    public void onEventMainThread(TabReSelectEvent event) {
////
////        if (event != null) {
////
////            if (mWholeLoadingView.getVisibility() == View.VISIBLE) {
////                if (EcoNetWorkStatusUtils.assertNetWorkIsValid(getActivity())) {
////                    mSheepHomePresenter.loadMarkData(mSheepHomeParams);
////                }
////            } else {
////                if (firstOffSet == 0) {
////                    if (mRecyclerView.canScrollVertically(-1)) {
////                        mRecyclerView.fling(0, 0);
////                        mRecyclerView.scrollToPosition(0);
////                    } else {
//////                        if (!mSmartRefreshLayout.isRefreshing()) { //不是刷新状态走自动刷新
//////                            mSmartRefreshLayout.autoRefresh(0, 250, 1);
//////                        }
////                    }
////                } else {
////                    //mAppBarLayout.setExpanded(true);
////                    scrollToTop();
////                }
////            }
////        }
////    }
//
//    /**
//     * 判断当前是在哪个tab上，因为Fragment有EcoBaseFragment和EcoWebViewFragment 没法统一处理
//     *
//     * @param event
//     */
////    public void onEventMainThread(CurrentTab event) {
////        isHomeTab = event.isHome();
////    }
//
//
//    /**
//     * 登录成功后 刷新首页数据
//     *
//     * @param event EcoUserLoginEvent
//     */
////    public void onEventMainThread(EcoUserLoginEvent event) {
////        if (event != null && event.isStatus()) {
////            if (mSheepHomeAdapter != null && mSheepHomePresenter != null) {
////                handleRefresh();
////            }
////        }
////    }
//
//    /**
//     * 退出登录事件 刷新首页数据
//     */
////    public void onEventMainThread(ExitLoginEvent event) {
////        if (event != null) {
////            if (mSheepHomeAdapter != null && mSheepHomePresenter != null) {
////                handleRefresh();
////            }
////        }
////    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//
////        if (mDialogManager != null && mDialogManager.isShowPushDialog()) {
////            mDialogManager.dismissPushDialog();
////        }
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
////        if (isShowToast && !SharedPreferencesUtil.getBoolean(getApplicationContext(), EcoDoorConst.IS_SHOWED_RED_PACKET_TOAST, false)) {
////            if (!StringUtils.isNull(mRedPacketContent)) {
////                ToastUtils.showToast(getApplicationContext(), mRedPacketContent);
////            }
////            SharedPreferencesUtil.saveBoolean(getApplicationContext(), EcoDoorConst.IS_SHOWED_RED_PACKET_TOAST, true);
////        }
////
////        if (videoHelper != null) {
////            videoHelper.hidePlayBotton();
////        }
//
////        popupHandler.sendEmptyMessageDelayed(0, 1000);
//    }
//
//
////    @Override
////    public void onLoadMoreRequested() {
////        if (!isLoadMore && !mSheepHomeParams.isEnd) {
////            mSheepHomeParams.page += 1;
////            mSheepHomePresenter.loadItemData(mSheepHomeParams);
////        } else {
////            mSheepHomeAdapter.setEnableLoadMore(false);
////        }
////        isLoadMore = true;
////    }
//}
