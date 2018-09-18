package org.talend.components.source.jsonobject;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import org.talend.components.service.WithrecordService;
import org.talend.components.source.Configuration;
import org.talend.sdk.component.api.configuration.Option;
import org.talend.sdk.component.api.input.Producer;
import org.talend.sdk.component.api.meta.Documentation;
import org.talend.sdk.component.api.record.Record;
import org.talend.sdk.component.api.service.record.RecordBuilderFactory;

@Documentation("TODO fill the documentation for this source")
public class JsonObjectInputSource implements Serializable {

    private final Configuration configuration;

    private final WithrecordService service;

    private final JsonBuilderFactory jsonObjectBuilder;

    private Queue<JsonObject> recordList;

    public JsonObjectInputSource(@Option("configuration") final Configuration configuration,
            final WithrecordService service,
            final JsonBuilderFactory jsonObjectBuilder) {
        this.configuration = configuration;
        this.service = service;
        this.jsonObjectBuilder = jsonObjectBuilder;
    }

    @PostConstruct
    public void init() {
        // this method will be executed once for the whole component execution,
        // this is where you can establish a connection for instance
        recordList = new LinkedList<>();
        for (int i = 0; i < configuration.getCount(); i++) {
            recordList.add(jsonObjectBuilder.createObjectBuilder().add("id", i)
                    //.add("datetime", new Date().toString())
                    .add("data", UUID.randomUUID().toString())
                    .build());
        }

    }

    @Producer
    public JsonObject next() {
        // this is the method allowing you to go through the dataset associated
        // to the component configuration
        //
        // return null means the dataset has no more data to go through
        // you can use the jsonBuilderFactory to create new JsonObjects.

        return recordList.poll();
    }

    @PreDestroy
    public void release() {
        // this is the symmetric method of the init() one,
        // release potential connections you created or data you cached
        recordList = null;
    }
}