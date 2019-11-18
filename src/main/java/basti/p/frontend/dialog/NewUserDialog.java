package basti.p.frontend.dialog;

import java.util.function.Consumer;

import com.google.common.base.Strings;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.model.Label;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

import basti.p.frontend.StyleUtil;
import basti.p.frontend.StyleUtil.FlexDirection;
import basti.p.model.viewmodel.UserViewModel;

public class NewUserDialog extends Dialog {

	private static final long serialVersionUID = 434492015615144921L;

	private Consumer<UserViewModel> callback;

	private TextField nameLabel;
	private TextField playerIdLabel;

	public NewUserDialog(Consumer<UserViewModel> callback) {
		super();
		this.callback = callback;

		init();
	}

	private void init() {

		setCloseOnEsc(false);
		setCloseOnOutsideClick(false);

		nameLabel = new TextField();
		nameLabel.setLabel("Name: ");

		playerIdLabel = new TextField();
		playerIdLabel.setLabel("Spielernummer: ");

		Button confirmButton = new Button("Ok", event -> {
			confirm();
		});
		Button cancelButton = new Button("Abbrechen", event -> {
			close();
		});
		add(new VerticalLayout(nameLabel, playerIdLabel, new HorizontalLayout(confirmButton, cancelButton)));

		nameLabel.focus();
		playerIdLabel.addKeyUpListener(Key.ENTER, e -> confirm());
	}

	private void confirm() {
		String name = nameLabel.getValue();
		String playerId = playerIdLabel.getValue();
		if (!Strings.isNullOrEmpty(name) && !Strings.isNullOrEmpty(playerId)) {
			callback.accept(new UserViewModel(name, playerId));
		}
		close();
	}

}
