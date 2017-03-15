package com.example;

import com.vaadin.cdi.CDIUI;
import com.vaadin.data.provider.QuerySortOrder;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import javax.inject.Inject;
import java.util.LinkedHashMap;
import java.util.Map;

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
                (sortOrders, offset, limit) -> {
                    Map<String, Boolean> sortOrder = new LinkedHashMap<>();

                    for (QuerySortOrder order : sortOrders) {
                        sortOrder.put(order.getSorted(), order.getDirection().equals(SortDirection.ASCENDING));
                    }

                    return service.findAll(offset, limit, sortOrder).stream();
                },
                () -> service.count()
        );

        VerticalLayout layout = new VerticalLayout(grid);
        layout.setSizeFull();
        setContent(layout);
    }

}
