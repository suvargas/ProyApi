package basicRestAsured;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BasicJunit {
    @BeforeEach
    public void setup(){
        System.out.println("setup");
    }

    @AfterEach
    public void cleanup(){
        System.out.println("cleanup");
    }
    @Test
    public void verifySomething(){
        System.out.println("test");
    }

    @Test
    public void verifySomething2(){
        System.out.println("test2");
    }

}