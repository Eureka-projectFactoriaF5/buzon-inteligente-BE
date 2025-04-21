package com.f5.buzon_inteligente_BE.locker;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class LockerService {

    private final LockerRepository lockerRepository;

    public LockerService(LockerRepository lockerRepository) {
        this.lockerRepository = lockerRepository;
    }

    public Optional<Locker> getRandomLocker() {
        List<Locker> lockers = lockerRepository.findAll();
        if (lockers.isEmpty()) {
            return Optional.empty();
        }
        Random random = new Random();
        Locker selectedLocker = lockers.get(random.nextInt(lockers.size()));
        return Optional.of(selectedLocker);
    }
}
