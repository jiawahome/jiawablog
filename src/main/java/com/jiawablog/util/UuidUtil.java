package com.jiawablog.util;

import java.util.UUID;

/**
 * auth: www.jiawahome.com
 */
public class UuidUtil {

    public static String uuid() {
        String id = UUID.randomUUID().toString().replace("-", "");
        return id;
    }
}
