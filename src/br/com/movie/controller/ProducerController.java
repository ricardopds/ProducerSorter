package br.com.movie.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.movie.model.AlreadyExistsProducerException;
import br.com.movie.model.Movie;
import br.com.movie.model.Producer;

public class ProducerController {
	List<Producer> producers = new ArrayList<Producer>();

	public ProducerController() {
		List<Movie> movies = MovieController.getInstance().getMoviesList();
		for (Movie movie : movies) {
			for (String producerName : movie.getProducers()) {
				if (containsProducer(producerName)) {
					Producer currentProducer = this.createProducer(movie, producerName);
					producers.add(currentProducer);
				} else {
					Producer currentProducer = producers.stream()
							.filter(producer -> producerName.equals(producer.getProducer()))
							.findFirst()
							.get();
					currentProducer = updateProducer(currentProducer, movie);
				}
			}

		}
	}

	public List<Producer> getProducers() {
		return producers;
	}

	public List<Producer> maxIntervalOfAWinner() {

		Long maxInterval = this.producers.stream()
				.filter(producer -> producer.isMultipleWins())
				.max((producer1, producer2) -> Long.compare(producer1.getInterval(), producer2.getInterval()))
				.get()
				.getInterval();
		List<Producer> maxIntervalProducers = this.producers.stream()
				.filter(producer -> producer.getInterval() == maxInterval)
				.collect(Collectors.toList());
		return maxIntervalProducers;
	}

	public List<Producer> minIntervalOfAWinner() {
		Long minInterval = this.producers.stream()
				.filter(producer -> producer.isMultipleWins())
				.min((producer1, producer2) -> Long.compare(producer1.getInterval(), producer2.getInterval()))
				.get()
				.getInterval();
		List<Producer> minIntervalProducers = this.producers.stream()
				.filter(producer -> producer.getInterval() == minInterval)
				.collect(Collectors.toList());
		return minIntervalProducers;
	}

	public List<Producer> findProducer(String producerName) {
		List<Producer> foundProducer = this.producers.stream()
				.filter(producer -> producer.getProducer().equals(producerName))
				.collect(Collectors.toList());

		return foundProducer;
	}

	public void addProducer(Producer toAddproducer) {
		if (this.producers.stream()
				.filter(producer -> producer.getProducer().equals(toAddproducer.getProducer()))
				.collect(Collectors.toList()).size() == 0L) {
			producers.add(toAddproducer);
		} else {
			throw new AlreadyExistsProducerException();
		}
	}

	public void updateProducer(Producer toUpdateProducer) {
		List<Producer> foundProducers = this.producers.stream()
				.filter(producer -> producer.getProducer().equals(toUpdateProducer.getProducer()))
				.collect(Collectors.toList());
		for (Producer producer : foundProducers) {
			producer.setFollowingWin(toUpdateProducer.getFollowingWin());
			producer.setPreviousWin(toUpdateProducer.getPreviousWin());
			producer.setInterval(toUpdateProducer.getInterval());
			producer.setProducer(toUpdateProducer.getProducer());
			producer.setMultipleWins(toUpdateProducer.isMultipleWins());
		}
	}

	public void removeProducer(String toDeleteProducer) {
		List<Producer> toDeleteProducers = this.producers.stream()
				.filter(producer -> producer.getProducer().equals(toDeleteProducer))
				.collect(Collectors.toList());
		this.producers.removeAll(toDeleteProducers);
	}

	private boolean containsProducer(String producerName) {
		return producers.stream()
				.filter(producer -> producer.getProducer().equals(producerName))
				.count() == 0L;
	}
	
	private boolean isAlreadyAWinner(Producer currentProducer) {
		return currentProducer.getFollowingWin() == 0L && currentProducer.getPreviousWin() == 0L;
	}
	
	private Producer createProducer(Movie movie, String producerName) {
		Producer currentProducer = new Producer();
		if (movie.isWinner()) {
			currentProducer.setFollowingWin(movie.getYear());
			currentProducer.setPreviousWin(movie.getYear());
			currentProducer.setInterval();
		} else {
			currentProducer.setFollowingWin(0L);
			currentProducer.setPreviousWin(0L);
			currentProducer.setInterval();
		}
		currentProducer.setProducer(producerName);
		return currentProducer;
	}
	
	private Producer updateProducer(Producer currentProducer, Movie movie) {
		if (movie.isWinner()) {
			if (this.isAlreadyAWinner(currentProducer)) {
				currentProducer.setFollowingWin(movie.getYear());
				currentProducer.setPreviousWin(movie.getYear());
			} else {
				currentProducer.setMultipleWins(true);
				if (currentProducer.getFollowingWin() < movie.getYear()) {
					currentProducer.setFollowingWin(movie.getYear());
				} else if (currentProducer.getPreviousWin() > movie.getYear()) {
					currentProducer.setPreviousWin(movie.getYear());
				}
			}
		}
		currentProducer.setInterval();
		return currentProducer;
	}
	

}
