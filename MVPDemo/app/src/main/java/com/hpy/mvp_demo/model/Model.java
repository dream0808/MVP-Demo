package com.hpy.mvp_demo.model;

public class Model implements IModel {


    @Override
    public void getData(LoadDataCallback callback) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    sleep(2000);
                    String data = "获取到的数据";
                    callback.success(data);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }.start();
    }

    public interface LoadDataCallback{
        void success(String taskId);
        void failure();
    }
}
