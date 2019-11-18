package basti.p.frontend;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import basti.p.backend.playlist.IUserRepository;
import basti.p.backend.playlist.UserService;
import basti.p.frontend.dialog.NewUserDialog;
import basti.p.model.entity.Playlist;
import basti.p.model.entity.User;
import basti.p.model.viewmodel.UserViewModel;

@Component
public class UserView extends VerticalLayout {

	private static final long serialVersionUID = -7410334083350273808L;

	@Autowired
	private UserService userService;

	@Autowired
	private MessageBean messageBean;

	private Grid<UserViewModel> grid;

	@PostConstruct
	public void init() {
		add(new Button("Nutzer hinzufügen", this::openNewUserDialog));
		grid = new Grid<>(UserViewModel.class);
		add(grid);
		updateUser();
	}

	private void openNewUserDialog(ClickEvent<Button> e) {
		NewUserDialog dialog = new NewUserDialog(this::createNewUser);
		dialog.open();
	}

	private void createNewUser(UserViewModel user) {
		userService.addUser(user);
		messageBean.printMessage("Nutzer angelegt");
		updateUser();
	}

	private void updateUser() {
		grid.setItems(userService.getUsers());
	}
}
