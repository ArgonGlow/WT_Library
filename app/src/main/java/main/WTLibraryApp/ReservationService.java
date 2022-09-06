package main.WTLibraryApp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
	
	@Autowired
	private ReservationRepository repo;
	
	public List<Reservation> allReservations() {
		return repo.findAll();
	}
	
	public Reservation reservationById(long id) {
		Reservation reservation = repo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid reservation Id: " + id));
		return reservation;
	}
	
	public void saveReservation(Reservation newReservation) {
		repo.save(newReservation);
	}
	
	public void deleteReservation(Reservation reservation) {
		repo.delete(reservation);
	}

	public List<Reservation> reservationsByUserId(Reservation userReservation) {
		return repo.findAll(Example.of(userReservation));
	}
}
