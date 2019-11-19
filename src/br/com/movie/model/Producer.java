package br.com.movie.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Producer {
	private String producer;
	private Long interval;
	private Long previousWin;
	private Long followingWin;
	transient private boolean multipleWins;

	public boolean isMultipleWins() {
		return multipleWins;
	}

	public void setMultipleWins(boolean multipleWins) {
		this.multipleWins = multipleWins;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public Long getInterval() {
		return interval;
	}

	public void setInterval(Long interval) {
		this.interval = interval;
	}

	public void setInterval() {
		this.interval = this.followingWin - this.previousWin;
	}

	public Long getPreviousWin() {
		return previousWin;
	}

	public void setPreviousWin(Long previousWin) {
		this.previousWin = previousWin;
	}

	public Long getFollowingWin() {
		return followingWin;
	}

	public void setFollowingWin(Long followingWin) {
		this.followingWin = followingWin;
	}

}
