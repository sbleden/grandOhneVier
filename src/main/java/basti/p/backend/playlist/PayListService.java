package basti.p.backend.playlist;

import static java.util.stream.Collectors.toList;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import basti.p.model.entity.Playlist;
import basti.p.model.viewmodel.PlayListCreation;
import basti.p.model.viewmodel.PlayListViewModel;

@Service
public class PayListService {

	@Autowired
	private IPlayListRepository repo;

	public Collection<PlayListViewModel> getAllPaylists() {

		return Lists.newArrayList(repo.findAll()).stream().map(this::toViewModel).collect(toList());
	}

	public void addNewPayList(PlayListCreation playListViewModel) {
		repo.save(new Playlist(playListViewModel.getPlayers()));
	}

	private PlayListViewModel toViewModel(Playlist playlist) {
		return new PlayListViewModel(Lists.newArrayList(playlist.getPlayers()),
				SimpleDateFormat.getDateTimeInstance().format(new Date(playlist.getTime())));
	}

}
