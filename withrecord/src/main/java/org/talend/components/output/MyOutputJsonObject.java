package org.talend.components.output;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.json.JsonObject;

import org.talend.components.Data;
import org.talend.sdk.component.api.component.Icon;
import org.talend.sdk.component.api.component.Version;
import org.talend.sdk.component.api.meta.Documentation;
import org.talend.sdk.component.api.processor.ElementListener;
import org.talend.sdk.component.api.processor.Input;
import org.talend.sdk.component.api.processor.Processor;
import org.talend.sdk.component.api.record.Record;

@Version
@Icon(value = Icon.IconType.STAR)
@Processor(name = "MyOutputJsonObject")
@Documentation("")
public class MyOutputJsonObject implements Serializable {

    public MyOutputJsonObject() {
    }

    @PostConstruct
    public void init() {
    }

    @ElementListener
    public void onNext(@Input("jsonobjectInput") final JsonObject jo) {
        System.out.println("jsonobject - " + jo.toString());
    }

}
