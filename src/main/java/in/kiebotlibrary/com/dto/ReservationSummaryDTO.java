package in.kiebotlibrary.com.dto;

import java.time.LocalDateTime;

public class ReservationSummaryDTO {

	private String memberId;

	private LocalDateTime reservationDate;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public LocalDateTime getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(LocalDateTime reservationDate) {
		this.reservationDate = reservationDate;
	}

}
