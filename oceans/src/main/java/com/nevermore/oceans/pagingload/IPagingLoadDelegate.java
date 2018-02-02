package com.nevermore.oceans.pagingload;

/**
 * Created by Administrator on 2017/8/28 0028.
 */

public interface IPagingLoadDelegate {

    //刷新完成
    void onRefreshComplete();
    //加载完成
    void onLoadComplete();

    //设置是否可以下拉
    void setPulldownEnable(boolean enable);

    //设置是否可以上啦
    void setPullupEnable(boolean enable);

    //设置有无更多数据
    void setHasMore(boolean hasMore);

    void setRefreshAndLoadMoreListener();
}
