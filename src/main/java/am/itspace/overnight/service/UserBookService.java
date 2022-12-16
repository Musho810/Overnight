package am.itspace.overnight.service;

import am.itspace.overnight.entity.Room;
import am.itspace.overnight.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserBookService {

    private final RoomRepository roomRepository;

    public Page<Room> findProductSearchResult(Pageable pageable, int regionId, Date startDate,
                                              Date endDate,
                                              int guestsAdult, int guestsChildren) {
        return roomRepository.findByCriteria(regionId, startDate, endDate, guestsAdult, guestsChildren, pageable);
    }

    public Page<Room> findAll (Pageable pageable) {
        return roomRepository.findAll(pageable);
    }
}
