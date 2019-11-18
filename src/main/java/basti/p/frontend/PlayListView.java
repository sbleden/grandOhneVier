package basti.p.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import basti.p.backend.playlist.PayListService;
import basti.p.backend.playlist.UserService;
import basti.p.frontend.dialog.NewPlayListDialog;
import basti.p.model.viewmodel.PlayListCreation;
import basti.p.model.viewmodel.PlayListViewModel;

@Component
public class PlayListView extends VerticalLayout {

	private static final long serialVersionUID = -4639727400698945703L;

	private MessageBean bean;
	private PayListService playListRepo;
	private UserService userService;

	private Grid<PlayListViewModel> grid;

	public PlayListView(@Autowired MessageBean bean, @Autowired PayListService playListRepo,
			@Autowired UserService userService) {

		this.bean = bean;
		this.playListRepo = playListRepo;
		this.userService = userService;
		grid = new Grid<>(PlayListViewModel.class);
		add(grid);
		Button button = new Button("Click me", this::newPayList);
		add(button);
		updatePayLists();
	}

	private void newPayList(ClickEvent<Button> e) {
		new NewPlayListDialog(this::newPlayList, userService).open();
	}

	private void newPlayList(PlayListCreation playListViewModel) {
		playListRepo.addNewPayList(playListViewModel);
		bean.printMessage("Liste angelegt");
		updatePayLists();
	}

	private void updatePayLists() {
		grid.setItems(playListRepo.getAllPaylists());
	}

}
