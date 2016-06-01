package com.gtranks.application.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Proxy;

@PersistenceContext
@Entity
@Table(name = "race")
@Proxy(lazy = false)
public class Race {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_race")
	@NotNull
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "id_championship")
	@NotNull
	private Championship championship;
	
	@ManyToOne
	@JoinColumn(name = "id_track")
	@NotNull
	private Track track;
	
	// x == 0 - false | x > 0 - true
	@Column(name = "in_oldboy_rank")
	@Min(0)
	private int inOldboyRank;
	
	@Column(name = "rw")
	@NotNull
	@DecimalMin(value = "0", inclusive = true) @DecimalMax(value = "10", inclusive = true)
	private double rw;
	
	@Column(name = "date")
	@NotNull
	private java.sql.Date date;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Championship getChampionship() {
		return championship;
	}

	public void setChampionship(Championship championship) {
		this.championship = championship;
	}

	public Track getTrack() {
		return track;
	}

	public void setTrack(Track track) {
		this.track = track;
	}

	public int getInOldboyRank() {
		return inOldboyRank;
	}

	public void setInOldboyRank(int inOldboyRank) {
		this.inOldboyRank = inOldboyRank;
	}

	public double getRw() {
		return rw;
	}

	public void setRw(double rw) {
		this.rw = rw;
	}

	public java.sql.Date getDate() {
		return date;
	}

	public void setDate(java.sql.Date date) {
		this.date = date;
	}

	protected Race() {
	}

	public Race( Championship championship, Track track, int inOldboyRank, LocalDate date) {
		this.championship = championship;
		this.track = track;
		this.inOldboyRank = inOldboyRank;
		this.date = java.sql.Date.valueOf(date);
	}

	public Race( Championship championship, Track track, int inOldboyRank, double rw, LocalDate date) {
		
		this.championship = championship;
		this.track = track;
		this.inOldboyRank = inOldboyRank;
		this.rw = rw;
		this.date = java.sql.Date.valueOf(date);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((championship == null) ? 0 : championship.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + inOldboyRank;
		long temp;
		temp = Double.doubleToLongBits(rw);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((track == null) ? 0 : track.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Race other = (Race) obj;
		if (championship == null) {
			if (other.championship != null)
				return false;
		} else if (!championship.equals(other.championship))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id != other.id)
			return false;
		if (inOldboyRank != other.inOldboyRank)
			return false;
		if (Double.doubleToLongBits(rw) != Double.doubleToLongBits(other.rw))
			return false;
		if (track == null) {
			if (other.track != null)
				return false;
		} else if (!track.equals(other.track))
			return false;
		return true;
	}
	
}
