package com.hpy.mvp_demo.presenter;

import com.hpy.mvp_demo.model.Model;
import com.hpy.mvp_demo.view.IView;

public class Presenter implements IPresenter, Model.LoadDataCallback{

    private final IView mView;
    private final Model mModel;


    public Presenter(IView mView) {
        this.mView = mView;
        this.mModel = new Model();
    }

    @Override
    public void success(String taskId) {
        mView.showData(taskId);
    }

    @Override
    public void failure() {

    }

    @Override
    public void loadData() {
        mView.showLoadingProgress("加载数据中...");
        mModel.getData(Presenter.this);
    }
}
