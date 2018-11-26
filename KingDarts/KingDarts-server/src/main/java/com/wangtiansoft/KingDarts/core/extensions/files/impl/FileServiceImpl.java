package com.wangtiansoft.KingDarts.core.extensions.files.impl;

import com.wangtiansoft.KingDarts.common.utils.CoreUtil;
import com.wangtiansoft.KingDarts.core.extensions.files.FileService;
import com.wangtiansoft.KingDarts.persistence.base.BaseExample;
import com.wangtiansoft.KingDarts.persistence.dao.master.ResourceFileMapper;
import com.wangtiansoft.KingDarts.persistence.entity.ResourceFile;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/4/23 0023.
 */
@Transactional
@Service
public class FileServiceImpl implements FileService {

    @Resource
    private ResourceFileMapper resourceFileMapper;

    @Override
    public HashMap<String, String> saveFileResource(String md5, String fileName, long fileSize, String fileUrl) {
        HashMap<String, String> dbMap = this.queryFileMapByMD5s(new String[]{md5});
        if (dbMap != null && dbMap.size() > 0) {
            return dbMap;
        }
        ResourceFile picture = new ResourceFile();
        picture.setUuid(CoreUtil.uuid());
        picture.setMd5(md5);
        picture.setName(fileName);
        picture.setUrl(fileUrl);
        picture.setLenght(fileSize);
        resourceFileMapper.insert(picture);
        dbMap.put(md5, fileUrl);
        return dbMap;
    }

    @Override
    public HashMap<String, String> queryFileMapByMD5s(String[] md5s) {
        HashMap<String, String> map = new HashMap<String, String>();
        if (ArrayUtils.isEmpty(md5s)) {
            return map;
        }
        BaseExample example = new BaseExample(ResourceFile.class);
        example.createCriteria().andIn("md5", Arrays.asList(md5s));
        List<ResourceFile> resourceFileList = resourceFileMapper.selectByExample(example);
        for (ResourceFile resourceFile : resourceFileList) {
            map.put(resourceFile.getMd5(), resourceFile.getUrl());
        }
        return map;
    }

    @Override
    public Long findFileLengthByFileMd5(String fileMd5) {
        BaseExample example = new BaseExample(ResourceFile.class);
        example.createCriteria().andEqualTo(true, "md5", fileMd5);
        List<ResourceFile> resourceFileList = resourceFileMapper.selectByExample(example);
        if (resourceFileList.size() > 0){
            return resourceFileList.get(0).getLenght();
        }
        return null;
    }


    @Override
    public Long findFileLengthByFileUrl(String fileUrl) {
        BaseExample example = new BaseExample(ResourceFile.class);
        example.createCriteria().andEqualTo(true, "url", fileUrl);
        List<ResourceFile> resourceFileList = resourceFileMapper.selectByExample(example);
        if (resourceFileList.size() > 0){
            return resourceFileList.get(0).getLenght();
        }
        return null;
    }
}
