package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Registration;
import mk.ukim.finki.wp.lab.model.Workshop;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Workshop> workshops = null;
    public static List<Registration> registrations = null;

    @PostConstruct
    public void init() {
        workshops = new ArrayList<>();
        registrations = new ArrayList<>();

        workshops.add(new Workshop("Spring Boot Basics", "Backend", 1500.0, 15));
        workshops.add(new Workshop("Advanced React & Redux", "Frontend", 1800.0, 20));
        workshops.add(new Workshop("Docker and Kubernetes in Practice", "DevOps", 2200.0, 10));
        workshops.add(new Workshop("Machine Learning with Python", "AI", 2500.0, 0));
        workshops.add(new Workshop("Relational Database Optimization", "Database", 1200.0, 12));
        workshops.add(new Workshop("Full-Stack TypeScript", "Web", 1700.0, 8));
        workshops.add(new Workshop("Linux System Administration", "DevOps", 1400.0, 0));
        workshops.add(new Workshop("REST API Security and OAuth2", "Security", 2100.0, 5));
        workshops.add(new Workshop("UI/UX Design for Developers", "Design", 1100.0, 18));
        workshops.add(new Workshop("Introduction to Cloud Computing", "Cloud", 2000.0, 14));
    }
}