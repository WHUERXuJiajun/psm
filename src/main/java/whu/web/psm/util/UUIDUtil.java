package whu.web.psm.util;

import java.util.UUID;

public class UUIDUtil {
    /**
     * 获取随机ID
     * @author  xsy
     * @return ID随机字符串
     */
    public static String getId(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
