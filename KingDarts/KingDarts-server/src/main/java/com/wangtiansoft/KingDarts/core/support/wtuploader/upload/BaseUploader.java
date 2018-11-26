package com.wangtiansoft.KingDarts.core.support.wtuploader.upload;

import com.wangtiansoft.KingDarts.common.utils.FileUtil;
import com.wangtiansoft.KingDarts.config.utils.ApplicationContextUtil;
import com.wangtiansoft.KingDarts.core.extensions.files.FileService;
import com.wangtiansoft.KingDarts.core.stubs.PluginManager;
import com.wangtiansoft.KingDarts.core.stubs.file.BaseFilePluginStub;
import com.wangtiansoft.KingDarts.core.support.ueditor.define.AppInfo;
import com.wangtiansoft.KingDarts.core.support.ueditor.define.BaseState;
import com.wangtiansoft.KingDarts.core.support.ueditor.define.FileType;
import com.wangtiansoft.KingDarts.core.support.ueditor.define.State;
import com.wangtiansoft.KingDarts.core.support.ueditor.upload.StorageManager;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2017/4/20 0020.
 */
public class BaseUploader {

    public static final State save(HttpServletRequest request, Map<String, Object> conf) {
        FileItemStream fileStream = null;
        boolean isAjaxUpload = request.getHeader("X_Requested_With") != null;

        if (!ServletFileUpload.isMultipartContent(request)) {
            return new BaseState(false, AppInfo.NOT_MULTIPART_CONTENT);
        }

        ServletFileUpload upload = new ServletFileUpload(
                new DiskFileItemFactory());

        if (isAjaxUpload) {
            upload.setHeaderEncoding("UTF-8");
        }

        File physicalFile = null;
        InputStream is = null;
        try {
            FileItemIterator iterator = upload.getItemIterator(request);

            while (iterator.hasNext()) {
                fileStream = iterator.next();
                if (!fileStream.isFormField())
                    break;
                fileStream = null;
            }
            //  验证流是否存在
            if (fileStream == null) {
                return new BaseState(false, AppInfo.NOTFOUND_UPLOAD_DATA);
            }
            String originFileName = fileStream.getName();
            String suffix = FileType.getSuffixByFilename(originFileName);
            originFileName = originFileName.substring(0, originFileName.length() - suffix.length());
            //  验证文件类型是否合法
            if (!validType(suffix, (String[]) conf.get("allowFiles"))) {
                return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
            }
            //  验证文件大小是否合法
            long maxSize = ((Long) conf.get("maxSize")).longValue();
            //  保存文件路径
            File physicalPath = new File(System.getProperty("java.io.tmpdir") + "wtfiles" + File.separator);
            if (!physicalPath.exists()) physicalPath.mkdirs();
            String physicalFileString = physicalPath.getAbsolutePath() + File.separator + UUID.randomUUID().toString().replaceAll("-", "") + suffix;
            //  存储到本地
            is = fileStream.openStream();
            State storageState = StorageManager.saveFileByInputStream(is, physicalFileString, maxSize);
            if (storageState.isSuccess()) {
                physicalFile = new File(physicalFileString);
                String md5 = FileUtil.md5(physicalFile);
                //  文件落地
                BaseFilePluginStub filePluginStub = PluginManager.getInstance().getFilePluginStub();
                String fileId = filePluginStub.upload(physicalFile);
                //  保存md5记录
                FileService fileService = ApplicationContextUtil.getBean(FileService.class);
                fileService.saveFileResource(md5, originFileName, physicalFile.length(), fileId);
                storageState.putInfo("url", fileId);
                storageState.putInfo("type", suffix);
                storageState.putInfo("original", originFileName + suffix);
            }

            return storageState;
        } catch (FileUploadException e) {
            return new BaseState(false, AppInfo.PARSE_REQUEST_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseState(false, AppInfo.IO_ERROR);
        } finally {
            if (is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (physicalFile != null && physicalFile.exists()){
                physicalFile.delete();
            }
        }
    }

    private static boolean validType(String type, String[] allowTypes) {
        List<String> list = Arrays.asList(allowTypes);
        return list.contains(type);
    }
}
