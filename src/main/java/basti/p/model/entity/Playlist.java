package basti.p.model.entity;

import java.util.Collections;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Playlist {

	@Id
	@GeneratedValue
	private Long id;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> players;

	private long time = System.currentTimeMillis();

	public Playlist() {
		// hibernate
	}

	public Playlist(List<String> players) {
		super();
		this.players = players;
	}

	public Long getId() {
		return id;
	}

	public long getTime() {
		return time;
	}

	public List<String> getPlayers() {
		return players == null ? Collections.emptyList() : players;
	}
}
