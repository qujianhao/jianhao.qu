package com.wangtiansoft.KingDarts.core.stubs.file;

import com.wangtiansoft.KingDarts.core.stubs.BasePluginStub;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2017/10/21 0021.
 */
public abstract class BaseFilePluginStub extends BasePluginStub {

    //  文件路径
    public abstract String getBaseFileUrl();
    //  上传
    public abstract String upload(File file) throws IOException;
    //  上传
    public abstract String upload(FileInputStream fileInputStream);

}
