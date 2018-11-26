package com.wangtiansoft.KingDarts.core.stubs.file;

import com.wangtiansoft.KingDarts.constants.Constants;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by Administrator on 2017/10/21 0021.
 */
public class QiniuKODOPluginStub extends BaseFilePluginStub {

    @Override
    public String getStubName() {
        return "Qiniu KODO";
    }

    @Override
    public String getStubType() {
        return "file";
    }

    @Override
    public void setup() {

    }

    @Override
    public void config() {

    }

    @Override
    public int getStubStateValue() {
        if (this.getPlugins() == null){
            return StubState.NotConfig.ordinal();
        }else if (this.getPlugins().getIs_publish() == Constants.False){
            return StubState.Pending.ordinal();
        }
        return StubState.Active.ordinal();
    }

    @Override
    public String getBaseFileUrl() {
        return "";
    }


    @Override
    public String upload(File file) {
        return null;
    }

    @Override
    public String upload(FileInputStream fileInputStream) {
        return null;
    }

}
