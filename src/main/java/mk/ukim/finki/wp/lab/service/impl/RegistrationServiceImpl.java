package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Registration;
import mk.ukim.finki.wp.lab.model.Workshop;
import mk.ukim.finki.wp.lab.repository.RegistrationRepository;
import mk.ukim.finki.wp.lab.repository.WorkshopRepository;
import mk.ukim.finki.wp.lab.service.RegistrationService;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final WorkshopRepository workshopRepository;

    public RegistrationServiceImpl(RegistrationRepository registrationRepository,
                                   WorkshopRepository workshopRepository) {
        this.registrationRepository = registrationRepository;
        this.workshopRepository = workshopRepository;
    }

    @Override
    public Registration register(String workshopTitle, String participantName,
                                 String participantEmail, int numberOfSeats) {

        if (workshopTitle == null || workshopTitle.isBlank()
                || participantName == null || participantName.isBlank()
                || participantEmail == null || participantEmail.isBlank()) {
            throw new IllegalArgumentException("All fields are required");
        }

        if (numberOfSeats < 1) {
            throw new IllegalArgumentException("Number of seats must be at least 1");
        }

        Workshop workshop = workshopRepository.findByTitle(workshopTitle)
                .orElseThrow(() -> new IllegalArgumentException("Workshop not found"));

        if (numberOfSeats > workshop.getCapacity()) {
            throw new IllegalArgumentException("Not enough free seats");
        }

        workshop.setCapacity(workshop.getCapacity() - numberOfSeats);

        Registration registration = new Registration(
                workshop.getTitle(),
                participantName,
                participantEmail,
                numberOfSeats
        );

        return registrationRepository.save(registration);
    }
}