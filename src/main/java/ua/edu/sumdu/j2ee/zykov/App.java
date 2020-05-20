package ua.edu.sumdu.j2ee.zykov;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.edu.sumdu.j2ee.zykov.config.SpringConfig;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args ) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    }
}
