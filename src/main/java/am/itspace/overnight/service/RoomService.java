package am.itspace.overnight.service;
import am.itspace.overnight.entity.Room;
import am.itspace.overnight.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public List<Room> findRoomByRating() {
        return roomRepository.findTop6ByOrderByProduct_RatingDesc();
    }

    public Optional<Room> findById(int id) {
        return roomRepository.findById(id);
    }
}
