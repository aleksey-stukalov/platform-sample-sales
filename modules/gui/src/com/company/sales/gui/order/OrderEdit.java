/*
 * Copyright (c) 2014 Haulmont
 */
package com.company.sales.gui.order;

import com.company.sales.entity.OrderLine;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.company.sales.entity.Order;
import com.haulmont.cuba.gui.components.actions.CreateAction;
import com.haulmont.cuba.gui.components.actions.EditAction;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.CollectionDatasourceListener;
import com.haulmont.cuba.gui.data.impl.CollectionDsListenerAdapter;

import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class OrderEdit extends AbstractEditor<Order> {
    @Named("linesTable.create")
    private CreateAction linesTableCreate;
    @Named("linesTable.edit")
    private EditAction linesTableEdit;

    @Inject
    private CollectionDatasource<OrderLine, UUID> linesDs;

    @Override
    public void init(Map<String, Object> params) {

        linesTableCreate.setOpenType(WindowManager.OpenType.DIALOG);
        linesTableEdit.setOpenType(WindowManager.OpenType.DIALOG);

        linesDs.addListener(new CollectionDsListenerAdapter<OrderLine>() {
            @Override
            public void collectionChanged(CollectionDatasource ds, Operation operation, List<OrderLine> items) {
                calculateAmount();
            }

            @Override
            public void valueChanged(OrderLine source, String property, Object prevValue, Object value) {
                super.valueChanged(source, property, prevValue, value);
            }
        });
    }

    private void calculateAmount() {
        BigDecimal amount = BigDecimal.ZERO;
        for (OrderLine line : linesDs.getItems()) {
            amount = amount.add(line.getProduct().getPrice().multiply(line.getQuantity()));
        }
        getItem().setAmount(amount);
    }
}