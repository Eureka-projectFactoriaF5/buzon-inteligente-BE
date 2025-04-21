package com.f5.buzon_inteligente_BE.locker;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LockerService {

    private final LockerRepository lockerRepo;

    public LockerService(LockerRepository lockerRepo) {
        this.lockerRepo = lockerRepo;
    }

    @Transactional(readOnly = true)
    public Locker getRandomLocker() {
        List<Locker> lockers = lockerRepo.findAll();
        if (lockers.isEmpty()) {
            throw new RuntimeException("No lockers available to assign");
        }
        int idx = ThreadLocalRandom.current().nextInt(lockers.size());
        return lockers.get(idx);
    }
}
