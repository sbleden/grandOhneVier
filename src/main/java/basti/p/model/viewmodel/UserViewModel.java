package basti.p.model.viewmodel;

public class UserViewModel {

	private String name;
	private String playerId;

	public UserViewModel(String name, String playerId) {
		super();
		this.name = name;
		this.playerId = playerId;
	}

	public String getName() {
		return name;
	}

	public String getPlayerId() {
		return playerId;
	}
}
