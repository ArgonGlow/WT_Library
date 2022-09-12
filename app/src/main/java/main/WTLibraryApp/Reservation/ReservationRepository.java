package main.WTLibraryApp.Reservation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import main.WTLibraryApp.User.User;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	
	List<Reservation> findByBookIdAndUserId(long bookId, long userId);
}
