package com.company.rentalapplication.tool;

import com.company.rentalapplication.tool.tooltype.Chainsaw;
import com.company.rentalapplication.tool.tooltype.Jackhammer;
import com.company.rentalapplication.tool.tooltype.Ladder;

import java.util.HashMap;

public class ToolHolder {

    private HashMap<String, Tool> toolMap = new HashMap<>();

    public ToolHolder() {
        loadTools();
    }

    private void loadTools() {
        Tool tempTool;
        tempTool = new Tool("CHNS", new Chainsaw(), "Stihl");
        toolMap.put(tempTool.getCode(), tempTool);
        tempTool = new Tool("LADW", new Ladder(), "Werner");
        toolMap.put(tempTool.getCode(), tempTool);
        tempTool = new Tool("JAKD", new Jackhammer(), "DeWalt");
        toolMap.put(tempTool.getCode(), tempTool);
        tempTool = new Tool("JAKR", new Jackhammer(), "Ridgid");
        toolMap.put(tempTool.getCode(), tempTool);
    }

    public Tool getTool(String toolCode) {
        Tool result = toolMap.get(toolCode);
        if (result == null) {
            throw new RuntimeException("Tool not found for code: " + toolCode);
        }
        return result;
    }

}
