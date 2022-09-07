package main.WTLibraryApp.Reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@CrossOrigin(maxAge=3600)
public class ReservationController {
	
	@Autowired
	private ReservationService service;
	
	// list all entries from reservations table
	// returns list of Reservation objects
	@GetMapping(value = "/reservations")
	public String findAllReservations(Model model) {
		model.addAttribute("reservations", service.allReservations());
		return "/reservations/reservations";
	}
	
	@GetMapping(value = "/reservations/user/{id}")
	public String reservationsByUser(@PathVariable long id, Model model) {
		Reservation userRes = new Reservation();
		userRes.setUserId(id);
		model.addAttribute("reservation", service.reservationsByUserId(userRes));
		return "/reservations/user";
	}
	
	// Creates a new reservation in the reservations table
	@GetMapping("reservations/create")
	public String createReservation(Reservation reservation) {
		return "reservations/confirmReservation";
	}
	
	@PostMapping("reservations/create")
	public String createReservation(Reservation reservation, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "reservations/confirmReservation";
		}
		service.saveReservation(reservation);
		return "redirect:/reservations";
	}
	
	// Cancels a user's reservations by removing it from the reservations table
	@GetMapping("reservations/cancel/{reservationId}")
	public String cancelReservation(@PathVariable long reservationId) {
		Reservation reservation = service.reservationById(reservationId);
		service.deleteReservation(reservation);
		return "redirect:/reservations";
	}
		
	/*
	 * find book by key word
	 * inputs key word
	 */
//	@RequestMapping(method = RequestMethod.POST, value = "books/search")
//	public List<Book> search(@RequestBody Search bookSearch) {
//		String keyWord = bookSearch.getKeyWord();
//		return service.searchBook(keyWord, keyWord, keyWord);
//	}

}
