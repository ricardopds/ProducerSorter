package br.com.movie.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.movie.model.Movie;

public class MovieController {
	private static final String _CSV_SEPARATOR = ";";
	private static final int _YEAR_COLLUM = 0;
	private static final int _TITLE_COLLUM = 1;
	private static final int _STUDIO_COLLUM = 2;
	private static final int _PRODUCER_COLLUM = 3;
	private static final int _WINNER_COLLUM = 4;
	private static final String _CSV_FILE = "/movielist.csv";
	private static final String _YEAR = "year";
	private static final String _TITLE = "title";
	private static final String _STUDIOS = "studios";
	private static final String _PRODUCERS = "producers";
	private static final String _WINNER = "winner";
	
	
	private static MovieController _INSTANCE;
	private List<Movie> moviesList = new ArrayList<Movie>();
	

	public static synchronized MovieController getInstance() {
		if (_INSTANCE == null)
			_INSTANCE = new MovieController();

		return _INSTANCE;
	}

	private MovieController() {
		BufferedReader csvReader = null;
		try {
			String currentLine;
			csvReader = new BufferedReader(new FileReader(this.getClass().getResource(_CSV_FILE).getFile()));
			int counter = 0;
			while ((currentLine = csvReader.readLine()) != null) {
				if (counter == 0) {
					this.testCsvHeader(currentLine);
				} else {
					String[] currentLineArray = currentLine.split(_CSV_SEPARATOR);
					Movie currentMovie = new Movie();
					currentMovie.setYear(currentLineArray[_YEAR_COLLUM]);
					currentMovie.setTitle(currentLineArray[_TITLE_COLLUM]);
					currentMovie.setStudios(currentMovie.createListFromString(currentLineArray[_STUDIO_COLLUM]));
					currentMovie.setProducers(currentMovie.createListFromString(currentLineArray[_PRODUCER_COLLUM]));
					try {
						currentMovie.setWinner(currentMovie.defineWinnerStatus(currentLineArray[_WINNER_COLLUM]));
					} catch (Exception e) {
						currentMovie.setWinner(false);
					}
					moviesList.add(currentMovie);
				}
				counter++;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (csvReader != null) {
				try {
					csvReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		Collections.sort(moviesList, new Comparator<Movie>() {
			public int compare(Movie o1, Movie o2) {
				return o1.getYear().compareTo(o2.getYear());
			}
		});
		moviesList.stream().forEach(movie -> System.out.println(movie.getYear()));

	}

	public List<Movie> getMoviesList() {
		return moviesList;
	}

	public void setMoviesList(List<Movie> moviesList) {
		this.moviesList = moviesList;
	}

	private void testCsvHeader(String csvHeader) throws Exception {
		String[] csvHeaderArray = csvHeader.split(_CSV_SEPARATOR);
		try {
			if (!_YEAR.equals(csvHeaderArray[_YEAR_COLLUM]))
				throw new Exception();
			if (!_TITLE.equals(csvHeaderArray[_TITLE_COLLUM]))
				throw new Exception();
			if (!_STUDIOS.equals(csvHeaderArray[_STUDIO_COLLUM]))
				throw new Exception();
			if (!_PRODUCERS.equals(csvHeaderArray[_PRODUCER_COLLUM]))
				throw new Exception();
			if (!_WINNER.equals(csvHeaderArray[_WINNER_COLLUM]))
				throw new Exception();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
