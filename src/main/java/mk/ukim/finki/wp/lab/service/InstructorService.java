package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Instructor;

import java.util.List;
import java.util.Optional;

public interface InstructorService {
    List<Instructor> findAll();
    Optional<Instructor> findById(Long id);
}