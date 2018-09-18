package org.talend.components.processor;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.json.JsonObject;

import org.talend.components.Data;
import org.talend.sdk.component.api.component.Icon;
import org.talend.sdk.component.api.component.Version;
import org.talend.sdk.component.api.meta.Documentation;
import org.talend.sdk.component.api.processor.ElementListener;
import org.talend.sdk.component.api.processor.Input;
import org.talend.sdk.component.api.processor.Output;
import org.talend.sdk.component.api.processor.OutputEmitter;
import org.talend.sdk.component.api.processor.Processor;
import org.talend.sdk.component.api.record.Record;

@Version
@Icon(value = Icon.IconType.STAR)
@Processor(name = "MyProcessor")
@Documentation("")
public class MyProcessor implements Serializable {

    public MyProcessor() {
    }

    @PostConstruct
    public void init() {
    }

    @ElementListener
    public void onNext(
            @Input final Record record,
            @Input("jsonobjectInput") final JsonObject jo,
            @Input("pojoInput") final Data pojo,

            final @Output OutputEmitter<Record> recordOut,
            final @Output("jsonobjectOut") OutputEmitter<JsonObject> jsonObjectOut,
            final @Output("rejectOut") OutputEmitter<Data> pojoOut) {

        recordOut.emit(record);
        jsonObjectOut.emit(jo);
        pojoOut.emit(pojo);

    }

}
