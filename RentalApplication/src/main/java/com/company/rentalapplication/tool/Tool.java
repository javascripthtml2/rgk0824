package com.company.rentalapplication.tool;

import com.company.rentalapplication.tool.tooltype.IToolType;

public class Tool {
    private String code;
    private IToolType toolType;
    private String brand;

    public Tool(String code, IToolType toolType, String brand) {
        this.code = code;
        this.toolType = toolType;
        this.brand = brand;
    }


    public String getCode() {
        return code;
    }

    public IToolType getToolType() {
        return toolType;
    }

    public String getBrand() {
        return brand;
    }
}
