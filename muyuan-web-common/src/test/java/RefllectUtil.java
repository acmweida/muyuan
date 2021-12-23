import com.muyuan.common.repo.jdbc.page.Page;
import com.muyuan.common.result.Result;
import com.muyuan.common.util.JSONUtil;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.core.ReflectUtils;

import java.beans.PropertyDescriptor;
import java.util.HashMap;

public class RefllectUtil {
    public static void main(String[] args) {

        PropertyDescriptor[] beanGetters = ReflectUtils.getBeanGetters(Page.class);
        for (PropertyDescriptor descriptor : beanGetters) {
            System.out.println(descriptor.getName());
            System.out.println(descriptor.getWriteMethod());
        }
    }

    @Test
    public void newInstanceTest() throws InstantiationException, IllegalAccessException {
        HashMap data = new HashMap();
        UserDTO user = new UserDTO();
        user.setUsername("muyuan");
        data.put("code",200);
        data.put("msg","ok");
        data.put("data",user);

        System.out.println(JSONUtil.coverValue(data, Result.class));
    }
}
