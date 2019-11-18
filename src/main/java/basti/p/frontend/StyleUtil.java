package basti.p.frontend;

import com.vaadin.flow.component.HasStyle;

public class StyleUtil {
	public static enum FlexDirection {
		ROW, COLUMN
	}

	public static void setFlexShrink(double shrink, HasStyle component) {
		component.getStyle().set("flex-shrink", Double.toString(shrink));
	}

	public static void setFlexDirection(HasStyle component, FlexDirection direction) {
		if (direction == FlexDirection.ROW) {
			component.getStyle().set("flex-direction", "row");
		} else if (direction == FlexDirection.COLUMN) {
			component.getStyle().set("flex-direction", "column");
		}
	}
}
