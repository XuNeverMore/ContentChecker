package com.nevermore.oceans.pagingload;

/**
 * Created by Administrator on 2017/8/28 0028.
 */

import android.support.annotation.NonNull;

import java.util.List;

/**
 * 分页加载管理辅助类
 *
 * @ClassName:MultiPageLoadHelper
 * @PackageName:com.bluemobi.wenwanstyle.httprequest
 * @Create On 2017/8/28 0028   13:33
 * @Site:http://www.handongkeji.com
 * @author:xuchuanting
 * @Copyrights 2017/8/28 0028 handongkeji All rights reserved.
 */

public class PagingLoadHelper implements IEvent {

    public int currentPage = 1;
    public int pageSize = 20;

    private IRequest mRequest;
    private BasePagingLoadDelegate mDelegate;

    public PagingLoadHelper(@NonNull IRequest mRequest, @NonNull BasePagingLoadDelegate mDelegate) {
        this.mRequest = mRequest;
        this.mDelegate = mDelegate;
        init();
    }

    private void init() {
        mDelegate.setEvent(this);
        mDelegate.setRefreshAndLoadMoreListener();
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    @Override
    public void onPulldown() {
        currentPage = 1;
        mRequest.onRequest(currentPage, pageSize);
    }

    @Override
    public void onPullup() {
        currentPage++;
        mRequest.onRequest(currentPage, pageSize);
    }

    private boolean hasMoreEnable = true;

    public void setHasMoreEnable(boolean enable) {
        hasMoreEnable = enable;
    }


    public void onRequestComplete(List list) {
        int dataSize = list == null ? 0 : list.size();
        onRequestComplete(dataSize);
    }

    public void onComplete() {
        if (currentPage == 1) {
            mDelegate.onRefreshComplete();
        } else {
            mDelegate.onLoadComplete();
        }
    }

    public void onRequestComplete(int dataSize) {
        if (currentPage == 1) {
            mDelegate.onRefreshComplete();
        } else {
            mDelegate.onLoadComplete();
        }

        if (hasMoreEnable) {

            if (currentPage != 1 && dataSize < pageSize) {
                mDelegate.setHasMore(false);
                if(noMoreListener!=null){
                    noMoreListener.onNoMoreData();
                }

            } else {
                mDelegate.setHasMore(true);
            }

        }
    }

    private OnNoMoreListener noMoreListener;

    public void setNoMoreListener(OnNoMoreListener noMoreListener) {
        this.noMoreListener = noMoreListener;
    }

    public interface OnNoMoreListener{
        void onNoMoreData();
    }


}
