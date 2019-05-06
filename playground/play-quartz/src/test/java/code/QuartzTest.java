package code;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class QuartzTest {

    @Test
    public void whenContextIsBootstrapped() {
        System.out.println("test");
    }
}
