package com.example.crudwithvaadin;

import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.customfield.CustomFieldVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.select.SelectVariant;

public class MonthField extends CustomField<Month> {

    private Select<String> month;
    private Select<Integer> year;

    public MonthField(String label) {
        this();
        setLabel(label);
    }

    public MonthField() {
        month = new Select<>();
        month.setItems("Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember");
        month.setWidth("10em");
    
        year = new Select<>();
        // year.setItems(2024);
        year.setWidth("8em");

        HorizontalLayout layout = new HorizontalLayout(month, year);
        // Removes default spacing
        layout.setSpacing(false);
        // Adds small amount of space between the components
        layout.getThemeList().add("spacing-s");

        add(layout);
    }

    public void addThemeVariant(CustomFieldVariant variant) {
        super.addThemeVariants(variant);
        month.addThemeVariants(SelectVariant.valueOf(variant.name()));
        year.addThemeVariants(SelectVariant.valueOf(variant.name()));
    }

    public void setYear(Integer year) {
        this.year.setItems(year);
    }

    @Override
    protected Month generateModelValue() {
        return new Month(month.getValue(), year.getValue());
    }

    @Override
    protected void setPresentationValue(Month mnt) {
        month.setValue(mnt.getMonth());
        year.setValue(mnt.getYear());
    }
}