package org.vaadin.example;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.router.RouteData;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.material.Material;


@CssImport(value = "./styles/vaadin-button-styles.css", themeFor = "vaadin-button")
public class AbstractLayout extends HorizontalLayout implements RouterLayout {

    private final VerticalLayout menuLayout = new VerticalLayout();
    private final FlexLayout contentContainer = new FlexLayout();

    public AbstractLayout() {
        menuLayout.setSizeUndefined();
        Button refresh = new Button("refresh");

        refresh.addThemeName("general-primary");
        menuLayout.add(refresh);

        contentContainer.setSizeFull();

        add(menuLayout, contentContainer);
    }


    @Override
    public void showRouterLayoutContent(HasElement content) {
        contentContainer.add((Component) content);
    }

    @Override
    public void removeRouterLayoutContent(HasElement oldContent) {
        contentContainer.remove((Component) oldContent);
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);

        RouteConfiguration configuration = RouteConfiguration.forSessionScope();
        // add all currently available views
        configuration.getAvailableRoutes().forEach(this::addMenuItem);
    }

    private void addMenuItem(RouteData routeData) {
        menuLayout.add(new RouterLink(routeData.getUrl(), routeData.getNavigationTarget()));
    }
}
