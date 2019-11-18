package basti.p.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue
	private Long id;

	private String playerId;

	private String name;

	public User() {
		// hibernat only
	}

	public User(String playerId, String name) {
		super();
		this.playerId = playerId;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPlayerId() {
		return playerId;
	}
}
