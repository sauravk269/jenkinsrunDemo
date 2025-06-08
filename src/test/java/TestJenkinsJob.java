import org.testng.annotations.Test;

public class TestJenkinsJob extends BaseTest{
    @Test
    public void m1(){
        System.out.println("Inside m1");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void m2(){
        System.out.println("Inside m2");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
