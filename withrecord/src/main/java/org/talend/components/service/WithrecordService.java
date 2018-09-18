package org.talend.components.service;

import static java.util.Arrays.asList;

import org.talend.components.source.Configuration;
import org.talend.sdk.component.api.configuration.Option;
import org.talend.sdk.component.api.service.Service;
import org.talend.sdk.component.api.service.completion.DynamicValues;
import org.talend.sdk.component.api.service.completion.Values;
import org.talend.sdk.component.api.service.update.Update;

@Service
public class WithrecordService {

    // you can put logic here you can reuse in components

    @DynamicValues("drivers")
    private Values drivers() {
        return new Values(asList(new Values.Item("mysql", "mysql"),
                new Values.Item("oracle", "oracle")));
    }

    @Update("updateURLForDriver")
    private Configuration.Model updateURLForDriver(@Option String driver) {

        if ("mysql".equals(driver)) {
            return new Configuration.Model("jdbc:mysql");
        }

        return new Configuration.Model("jdbc");
    }

}