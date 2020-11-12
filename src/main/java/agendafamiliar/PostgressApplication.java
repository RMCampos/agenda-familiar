package agendafamiliar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "agendafamiliar")
public class PostgressApplication {

    public static void main(String[] args) {
        //System.out.println("123456: " + new BCryptPasswordEncoder().encode("123456"));
        SpringApplication.run(PostgressApplication.class, args);
    }
}
