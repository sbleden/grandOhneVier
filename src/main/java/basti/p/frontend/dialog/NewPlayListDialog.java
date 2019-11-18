package basti.p.frontend.dialog;

import static java.util.stream.Collectors.toList;

import java.util.Set;
import java.util.function.Consumer;

import com.google.common.collect.Lists;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import basti.p.backend.playlist.UserService;
import basti.p.model.viewmodel.PlayListCreation;
import basti.p.model.viewmodel.UserViewModel;

public class NewPlayListDialog extends Dialog {

	private static final long serialVersionUID = 434492015615144921L;

	private UserService userService;
	private Consumer<PlayListCreation> callback;

	private CheckboxGroup<UserViewModel> usersCheckBoxes;

	public NewPlayListDialog(Consumer<PlayListCreation> callback, UserService userService) {
		super();
		this.callback = callback;
		this.userService = userService;

		init();
	}

	private void init() {

		setCloseOnEsc(false);
		setCloseOnOutsideClick(false);

		usersCheckBoxes = new CheckboxGroup<>();
		usersCheckBoxes.setItems(userService.getUsers());
		usersCheckBoxes.setItemLabelGenerator(e -> e.getName());

		Button confirmButton = new Button("Ok", event -> confirm());
		Button cancelButton = new Button("Abbrechen", event -> close());

		add(new VerticalLayout(usersCheckBoxes, new HorizontalLayout(confirmButton, cancelButton)));

	}

	private void confirm() {

		Set<UserViewModel> selectedItems = usersCheckBoxes.getSelectedItems();
		PlayListCreation playListCreation = new PlayListCreation(
				selectedItems.stream().map(UserViewModel::getPlayerId).collect(toList()));
		callback.accept(playListCreation);

		close();
	}

}
