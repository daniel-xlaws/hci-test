package com.test.hci.tuple;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Data
public class UserModuleList implements Serializable {
    @JsonProperty("modules")
    private List<UserModule> moduleList = new LinkedList<>();

    public void setModelModuleList(List<com.test.hci.model.UserModule> moduleList) {
        if (moduleList != null) {
            for (com.test.hci.model.UserModule userModule : moduleList) {
                this.moduleList.add(new UserModule(userModule.getModule().getName(), userModule.getModuleOrder()));
            }
        }
    }
}
