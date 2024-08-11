package com.company.rentalapplication.tool.tooltype;

import com.company.rentalapplication.tool.Tool;
import com.company.rentalapplication.tool.ToolHolder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ToolHolderTest {

    @Test
    public void invalidCodeTest() {
        try {
            ToolHolder toolHolder = new ToolHolder();
            toolHolder.getTool("INVALID");
            fail("Did not get expected exception.");
        } catch (RuntimeException e) {
            assertEquals("Tool not found for code: INVALID", e.getMessage());
        }
    }

    @Test
    public void validCodeTest() {
        try {
            ToolHolder toolHolder = new ToolHolder();
            Tool tool = toolHolder.getTool("CHNS");
            assertEquals("CHNS", tool.getCode());
        } catch (RuntimeException e) {
            assertEquals("Tool not found for code: INVALID", e.getMessage());
        }
    }
}
