package org.vaadin.example;

import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route(value = "three", layout = AbstractModuleThreeLayout.class)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-button.css", themeFor = "vaadin-button")
public class ModuleThreeView extends VerticalLayout {

    public ModuleThreeView() {

        setId("three");
        // Use TextField for standard text input
        TextField textField = new TextField("Your name");
        textField.addThemeName("bordered");

        // Button click listeners can be defined as lambda expressions
        Button button = new Button("Say hello",
                e -> Notification.show("Hello " + textField.getValue()));
        button.addClassName("three");

        // Theme variants give you predefined extra styles for components.
        // Example: Primary button is more prominent look.
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        // You can specify keyboard shortcuts for buttons.
        // Example: Pressing enter in this view clicks the Button.
        button.addClickShortcut(Key.ENTER);

        // Use custom CSS classes to apply styling. This is defined in shared-styles.css.
        addClassName("centered-content");

        Button ping = new Button("ping");
        ping.addClassName("ping");
        add(textField, button, ping);

        setSizeFull();
    }
}
