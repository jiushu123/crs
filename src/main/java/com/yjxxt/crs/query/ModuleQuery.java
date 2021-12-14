package com.yjxxt.crs.query;

import com.yjxxt.crs.base.BaseQuery;

public class ModuleQuery extends BaseQuery {
    private String moduleName;
    private String optValue;

    public ModuleQuery() {
    }

    public ModuleQuery(String moduleName, String optValue) {
        this.moduleName = moduleName;
        this.optValue = optValue;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getOptValue() {
        return optValue;
    }

    public void setOptValue(String optValue) {
        this.optValue = optValue;
    }

    @Override
    public String toString() {
        return "ModuleQuery{" +
                "moduleName='" + moduleName + '\'' +
                ", optValid='" + optValue + '\'' +
                '}';
    }
}
