package org.talend.components.source;

import java.io.Serializable;

import org.talend.sdk.component.api.configuration.Option;
import org.talend.sdk.component.api.configuration.action.Proposable;
import org.talend.sdk.component.api.configuration.action.Updatable;
import org.talend.sdk.component.api.configuration.ui.layout.GridLayout;
import org.talend.sdk.component.api.meta.Documentation;
import org.talend.sdk.component.api.service.completion.DynamicValues;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@GridLayout({
        @GridLayout.Row("count"),
        @GridLayout.Row("driver"),
        @GridLayout.Row("update"),
})
@Documentation("TODO fill the documentation for this configuration")
public class Configuration implements Serializable {

    @Option
    @Documentation("record count")
    private Integer count = 50;

    @Option
    @Proposable(value = "drivers")
    @Documentation("")
    private String driver;

    @Option
    @Updatable(value = "updateURLForDriver", parameters = "driver")
    @Documentation("")
    private Model update;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @GridLayout({
            @GridLayout.Row("url")
    })
    public static class Model {

        @Option
        @Documentation("")
        private String url;
    }

}