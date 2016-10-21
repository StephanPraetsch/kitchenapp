package com.mercateo.kitchenapp.pages.subscription;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.inject.Inject;

import org.apache.wicket.extensions.yui.calendar.DateField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import lombok.NonNull;

import com.mercateo.kitchenapp.pages.PagesRegistry;

class SubscriptionForm extends Form<LocalDate> {

    @Inject
    private PagesRegistry pagesRegistry;

    private final DateField fromField;

    private final DateField toField;

    SubscriptionForm(@NonNull String id, @NonNull LocalDate from, @NonNull LocalDate to) {
        super(id);

        this.fromField = new DateField("filterFrom", Model.of(convert(from)));
        this.toField = new DateField("filterTo", Model.of(convert(to)));

        add(fromField);
        add(toField);

    }

    private Date convert(LocalDate ld) {
        return Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    @Override
    protected void onSubmit() {
        super.onSubmit();
        PageParameters pageParameters = new PageParameters();
        pageParameters.add("from", convert(fromField));
        pageParameters.add("to", convert(toField));
        setResponsePage(pagesRegistry.getSubscriptionPage(), pageParameters);
    }

    private String convert(DateField field) {
        Date date = field.getModel().getObject();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return SubscriptionPage.FORMATTER.format(localDate);
    }

}
