package basti.p.model.viewmodel;

import java.util.List;

public class PlayListViewModel {

	private List<String> players;
	private String date;

	public PlayListViewModel(List<String> players, String date) {
		super();
		this.players = players;
		this.date = date;
	}

	public List<String> getPlayers() {
		return players;
	}

	public String getDate() {
		return date;
	}

}
