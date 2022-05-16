import com.example.demo.Control.MyDemo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyDemo.class)
@Profile("dev")
public class DemoTest {

    @Resource
    private MyDemo myDemo;

    @Test
    public void test1(){
        System.out.println(myDemo.myTest("apple"));
    }
}
