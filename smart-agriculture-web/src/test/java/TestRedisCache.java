import com.alibaba.fastjson2.JSONObject;
import com.lxl.agro.pojo.SysModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Project : smart-agriculture-parent
 * Package : PACKAGE_NAME
 * Description : TestRedisCache
 * Author : LiuXinLei
 * createDate : 2023/5/24 15:29
 */
@SpringBootTest(classes = com.lxl.agro.SmartWebApplication.class)
public class TestRedisCache {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;


    @Test
    public void test() {
        String cacheMenu = (String) redisTemplate.opsForValue().get("1");
        if (StringUtils.hasText(cacheMenu)) {
            System.out.println("JSONObject.parseObject(cacheMenu) = " + JSONObject.parseObject(cacheMenu, List.class));
        }
    }

}
