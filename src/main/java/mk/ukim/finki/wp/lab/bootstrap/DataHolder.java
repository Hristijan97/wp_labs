package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Instructor;
import mk.ukim.finki.wp.lab.model.Registration;
import mk.ukim.finki.wp.lab.model.Workshop;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Workshop> workshops = null;
    public static List<Registration> registrations = null;
    public static List<Instructor> instructors = null;

    @PostConstruct
    public void init() {
        workshops = new ArrayList<>();
        registrations = new ArrayList<>();
        instructors = new ArrayList<>();

        Instructor inst1 = new Instructor((long) (Math.random() * 1000), "Linus", "Torvalds", "linus@example.com", "Linux & Open Source");
        Instructor inst2 = new Instructor((long) (Math.random() * 1000), "Ada", "Lovelace", "ada@example.com", "Algorithms & Programming");
        Instructor inst3 = new Instructor((long) (Math.random() * 1000), "Grace", "Hopper", "grace@example.com", "Compilers & Systems");

        instructors.add(inst1);
        instructors.add(inst2);
        instructors.add(inst3);

        workshops.add(new Workshop("Spring Boot Basics", "Backend", 1500.0, 15, inst2));
        workshops.add(new Workshop("Advanced React & Redux", "Frontend", 1800.0, 20, inst2));
        workshops.add(new Workshop("Docker and Kubernetes in Practice", "DevOps", 2200.0, 10, inst3));
        workshops.add(new Workshop("Machine Learning with Python", "AI", 2500.0, 0, inst1)); // capacity 0
        workshops.add(new Workshop("Relational Database Optimization", "Database", 1200.0, 12, inst2));
        workshops.add(new Workshop("Full-Stack TypeScript", "Web", 1700.0, 8, inst2));
        workshops.add(new Workshop("Linux System Administration", "DevOps", 1400.0, 0, inst3)); // capacity 0
        workshops.add(new Workshop("REST API Security and OAuth2", "Security", 2100.0, 5, inst2));
        workshops.add(new Workshop("UI/UX Design for Developers", "Design", 2300.0, 18, inst1));
        workshops.add(new Workshop("Introduction to Cloud Computing", "Cloud", 2000.0, 14, inst3));
    }
}