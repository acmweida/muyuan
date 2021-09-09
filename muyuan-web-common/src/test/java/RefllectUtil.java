import com.muyuan.common.repo.jdbc.page.Page;
import org.springframework.cglib.core.ReflectUtils;

import java.beans.PropertyDescriptor;

public class RefllectUtil {
    public static void main(String[] args) {

        PropertyDescriptor[] beanGetters = ReflectUtils.getBeanGetters(Page.class);
        for (PropertyDescriptor descriptor : beanGetters) {
            System.out.println(descriptor.getName());
            System.out.println(descriptor.getWriteMethod());
        }
    }
}
