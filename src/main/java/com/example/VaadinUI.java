package com.example;

import com.vaadin.cdi.CDIUI;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import javax.inject.Inject;

/**
 * @author Alejandro Duarte
 */
@CDIUI("")
public class VaadinUI extends UI {

    @Inject
    private PersonService service;


    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Grid<Person> grid = new Grid<>(Person.class);
        grid.setSizeFull();

        grid.setDataProvider(
                (sortOrders, offset, limit) -> service.findAll(offset, limit).stream(),
                () -> service.count()
        );


        VerticalLayout layout = new VerticalLayout(grid);
        layout.setSizeFull();
        setContent(layout);
    }

}
