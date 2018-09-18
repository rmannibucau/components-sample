package org.talend.components.source.pojo;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.talend.components.Data;
import org.talend.components.service.WithrecordService;
import org.talend.components.source.Configuration;
import org.talend.sdk.component.api.configuration.Option;
import org.talend.sdk.component.api.input.Producer;
import org.talend.sdk.component.api.meta.Documentation;
import org.talend.sdk.component.api.service.record.RecordBuilderFactory;

@Documentation("TODO fill the documentation for this source")
public class PojoInputSource implements Serializable {

    private final Configuration configuration;

    private final WithrecordService service;

    private final RecordBuilderFactory recordBuilderFactory;

    private Queue<Data> recordList;

    public PojoInputSource(@Option("configuration") final Configuration configuration,
            final WithrecordService service,
            final RecordBuilderFactory recordBuilderFactory) {
        this.configuration = configuration;
        this.service = service;
        this.recordBuilderFactory = recordBuilderFactory;
    }

    @PostConstruct
    public void init() {
        // this method will be executed once for the whole component execution,
        // this is where you can establish a connection for instance
        recordList = new LinkedList<>();
        for (int i = 0; i < configuration.getCount(); i++) {
            recordList.add(new Data(i,
                    //new Date(),
                    UUID.randomUUID().toString()));
        }

    }

    @Producer
    public Object next() {
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