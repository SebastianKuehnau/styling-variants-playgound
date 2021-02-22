package org.vaadin.example;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.vaddon.CustomMediaQuery;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Route(value = "grid", layout = AbstractLayout.class)
public class GridView extends VerticalLayout {

    private final Set<Person> personSet = Arrays.asList(
            new Person("Donald", "Duck", "donald@gmail.com", LocalDate.of(1990, 10, 23)),
            new Person("Homer", "Simpson", "homer@springfield.com", LocalDate.of(1988, 4, 1))
    ).stream().collect(Collectors.toSet()) ;
    private final Grid.Column<Person> firstNameColumn;
    private final Grid.Column<Person> lastNameColumn;
    private final Grid.Column<Person> emailColumn;
    private final Grid.Column<Person> birthdayColumn;
    private final Grid.Column<Person> personSummaryColumn;
    private final Grid<Person> personGrid;

    public GridView() {
        personGrid = new Grid<>();

        firstNameColumn = personGrid.addColumn(Person::getFirstName).setHeader("Firstname");
        lastNameColumn = personGrid.addColumn(Person::getLastName).setHeader("Last Name");
        emailColumn = personGrid.addColumn(Person::getEmail).setHeader("Email");
        birthdayColumn = personGrid.addColumn(Person::getBirthday).setHeader("Birthday");
        personSummaryColumn = personGrid.addComponentColumn(this::createPersonComponent);

        personGrid.setItems(personSet);

        add(personGrid);

        CustomMediaQuery customMediaQuery1200 = new CustomMediaQuery(this::toggleColumnVisibility);
        customMediaQuery1200.setQuery("(min-width: 1200px)");
        add(customMediaQuery1200);
    }

    private void toggleColumnVisibility(Boolean visible) {
        firstNameColumn.setVisible(visible);
        lastNameColumn.setVisible(visible);
        emailColumn.setVisible(visible);
        birthdayColumn.setVisible(visible);

        personSummaryColumn.setVisible(!visible);
    }

    private VerticalLayout createPersonComponent(Person person) {
        VerticalLayout rootLayout = new VerticalLayout();
        rootLayout.add(new Span(person.getLastName() + " " + person.getFirstName()));
        rootLayout.add(new Span(person.getEmail()));
        rootLayout.add(new Span(String.valueOf(person.getBirthday())));

        return rootLayout;
    }

    public class Person {

        private final String firstName ;
        private final String lastName ;
        private final String email ;
        private final LocalDate birthday ;

        public Person(String firstName, String lastName, String email, LocalDate birthday) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.birthday = birthday;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getEmail() {
            return email;
        }

        public LocalDate getBirthday() {
            return birthday;
        }
    }
}
