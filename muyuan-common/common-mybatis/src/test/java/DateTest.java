import java.util.Date;

/**
 * @ClassName DateTest
 * Description TODO
 * @Author 2456910384
 * @Date 2022/5/30 10:34
 * @Version 1.0
 */

public class DateTest {
    public static void main(String[] args) {
        Date now = new Date();
        java.sql.Date sql = new java.sql.Date(now.getTime());
        System.out.println(sql.toString());
    }


}
