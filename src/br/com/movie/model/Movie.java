package br.com.movie.model;

import java.util.Arrays;
import java.util.List;

public class Movie {
	private Long year;
	private String title;
	private List<String> studios;
	private List<String> producers;
	private boolean winner;

	private static final String _TXT_SEPARATOR = ",";
	private static final String _AND = " and ";
	private static final String _YES = "yes";

	public List<String> createListFromString(String toList)
	{
		toList = toList.replaceAll(_AND, _TXT_SEPARATOR);
		List<String> listedString = Arrays.asList(toList.split(_TXT_SEPARATOR));
		listedString.replaceAll(String::trim);
		return listedString;
	}
	
	public boolean defineWinnerStatus(String winnerStatus) {
		return _YES.contentEquals(winnerStatus);
	}

	public Long getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = Long.parseLong(year);
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getStudios() {
		return studios;
	}

	public void setStudios(List<String> studios) {
		this.studios = studios;
	}


	public List<String> getProducers() {
		return producers;
	}

	public void setProducers(List<String> producers) {
		this.producers = producers;
	}

	public boolean isWinner() {
		return winner;
	}

	public void setWinner(boolean winner) {
		this.winner = winner;
	}

	public void setWinner(String winner) {
		this.winner = _YES.equalsIgnoreCase(winner);
	}

}
