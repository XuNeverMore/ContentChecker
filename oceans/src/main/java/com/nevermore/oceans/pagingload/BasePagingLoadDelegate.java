package com.nevermore.oceans.pagingload;

/**
 * Created by Administrator on 2017/8/28 0028.
 */

public abstract class BasePagingLoadDelegate implements IPagingLoadDelegate {

    protected IEvent mEvent;

    public IEvent getmEvent() {
        return mEvent;
    }

    public void setEvent(IEvent mEvent) {
        this.mEvent = mEvent;
    }

    protected void checkSetEvent(){
        if(mEvent==null){
            throw new IllegalStateException("has not set Event");
        }
    }



    @Override
    public void setPulldownEnable(boolean enable) {

    }

    @Override
    public void setPullupEnable(boolean enable) {


    }

    @Override
    public void setHasMore(boolean hasMore) {

    }
}
