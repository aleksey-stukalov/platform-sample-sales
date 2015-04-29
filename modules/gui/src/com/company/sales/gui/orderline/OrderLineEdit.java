/*
 * Copyright (c) 2014 Haulmont
 */
package com.company.sales.gui.orderline;

import com.company.sales.entity.OrderLine;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.haulmont.cuba.gui.components.LookupPickerField;

import javax.inject.Named;
import java.util.Map;

public class OrderLineEdit extends AbstractEditor<OrderLine> {

    @Named("fieldGroup.product")
    private LookupPickerField lookupPickerField;

    @Override
    public void init(Map<String, Object> params) {
        lookupPickerField.addOpenAction();
        lookupPickerField.addLookupAction();
    }
}