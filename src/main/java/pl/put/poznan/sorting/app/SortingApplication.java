package pl.put.poznan.sorting.app;

import javax.swing.*;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import pl.put.poznan.sorting.gui.SortingFrame;

@SpringBootApplication(scanBasePackages = {"pl.put.poznan.sorting.rest"})
public class SortingApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SortingApplication.class)
                .headless(false)
                .run(args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            for (var arg : args) {
                if ("--gui".equals(arg))
                    SwingUtilities.invokeLater(SortingFrame::new);
            }
        };
    }
}