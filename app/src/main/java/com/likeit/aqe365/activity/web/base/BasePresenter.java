package com.likeit.aqe365.activity.web.base;

/**
 * @author gangan
 */
public abstract class BasePresenter implements BasePresenterInterface {
    private boolean mIsFirstStart = true;

    @Override
    public final void start() {
        start(mIsFirstStart);
        mIsFirstStart = false;
    }

    protected abstract void start(boolean isFirstStart);

    @Override
    public void destroy() {

    }
}
