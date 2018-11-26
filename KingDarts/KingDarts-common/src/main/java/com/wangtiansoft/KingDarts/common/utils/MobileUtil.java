package com.wangtiansoft.KingDarts.common.utils;

import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;

/**
 * Created by simple on 2017/3/29.
 */
public class MobileUtil {
    private static final String MOBILE_PATTERN = "^1\\d{10}";

    public static void checkMobile(String mobile) throws AppRuntimeException {
        if (! mobile.matches(MOBILE_PATTERN)) {
            throw new AppRuntimeException("手机号格式不正确");
        }
    }
}
