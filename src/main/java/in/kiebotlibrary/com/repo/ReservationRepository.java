package in.kiebotlibrary.com.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.kiebotlibrary.com.entity.Book;
import in.kiebotlibrary.com.entity.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByBookAndMember_MemberIdAndStatusIn(Book book, String memberId, List<Reservation.Status> statuses);
    
}