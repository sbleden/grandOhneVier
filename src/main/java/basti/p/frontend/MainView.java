package basti.p.frontend;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.Tabs.Orientation;
import com.vaadin.flow.component.tabs.Tabs.SelectedChangeEvent;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

import basti.p.frontend.StyleUtil.FlexDirection;

@Route
@PWA(name = "Grand ohne Vier", shortName = "GrandOhneVier")
public class MainView extends Composite<FlexLayout> {

	private static final long serialVersionUID = -5508626966189073694L;

	@Autowired
	private UserView userView;

	@Autowired
	private PlayListView playListView;

	private Tabs tabs;
	private FlexLayout content = new FlexLayout();
	private Map<Tab, Component> components = new LinkedHashMap<>();

	@PostConstruct
	public void init() {
		tabs = new Tabs();
		tabs.setOrientation(Orientation.HORIZONTAL);

		getContent().add(tabs);
		getContent().add(content);
		getContent().setWidth("100%");

		StyleUtil.setFlexDirection(getContent(), FlexDirection.COLUMN);
		StyleUtil.setFlexShrink(0, tabs);
		getContent().setFlexGrow(1, content);
		tabs.addSelectedChangeListener(this::onTabChanged);

		addTab("User", userView);
		addTab("Listen", playListView);
	}

	private Tab addTab(String caption, Component component) {
		Tab tab = new Tab(caption);
		tabs.add(tab);

		if (components.isEmpty()) {
			content.add(component);
		}
		components.put(tab, component);

		return tab;
	}

	private void onTabChanged(SelectedChangeEvent e) {
		Tab selectedTab = e.getSource().getSelectedTab();
		content.removeAll();

		Optional.ofNullable(components.get(selectedTab)).ifPresent(content::add);
	}
}
