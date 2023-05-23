import cn.hutool.crypto.SecureUtil;
import org.junit.jupiter.api.Test;

/**
 * Project : smart-agriculture-parent
 * Package : PACKAGE_NAME
 * Description : TestMD5
 * Author : LiuXinLei
 * createDate : 2023/5/22 15:35
 */
public class TestMD5 {

    @Test
    void test() {
        System.out.println("SecureUtil.md5(\"123456\") = " + SecureUtil.md5("123456"));
    }

}
