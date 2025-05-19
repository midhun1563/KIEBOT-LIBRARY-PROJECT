package in.kiebotlibrary.com.dto;

import java.time.LocalDate;

public class LoanSummaryDTO {

	private String memberId;

	private LocalDate dueDate;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

}
