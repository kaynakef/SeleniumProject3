import org.testng.annotations.Test;

public class TestAll {
    @Test
    public void allTests() throws InterruptedException {
        Task1 test1=new Task1();
        Task2 test2=new Task2();
        Task3 test3=new Task3();
        Task4 test4=new Task4();
        test1.Task1();
        test2.Test2();
        test3.Task3();
        test4.Task4();


    }

}
