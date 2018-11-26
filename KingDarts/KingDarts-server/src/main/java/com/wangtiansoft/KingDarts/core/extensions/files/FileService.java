package com.wangtiansoft.KingDarts.core.extensions.files;

import java.util.HashMap;

/**
 * Created by weitong on 18/1/3.
 */
public interface FileService {

    //  保存文件信息到数据库
    public HashMap<String, String> saveFileResource(String md5, String fileName, long fileSize, String fileUrl);
    //  根据文件md5获取文件地址
    HashMap<String, String> queryFileMapByMD5s(String[] md5s);
    //  根据文件md5查询文件大小
    Long findFileLengthByFileMd5(String fileMd5);
    //  根据文件url查询文件大小
    Long findFileLengthByFileUrl(String fileUrl);

}
