package org.linkdev.notificationservice.mapper;

import java.util.function.Consumer;

public class TemplateRecordListBuilder implements Consumer {
    @Override
    public void accept(Object o) {

    }

    @Override
    public Consumer andThen(Consumer after) {
        return Consumer.super.andThen(after);
    }
}
