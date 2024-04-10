package org.application.steps;

import io.cucumber.java.Before;
import utilities.RestAssuredExtension;

public class TestInitialize {

    @Before
    public void testSetup() {
        RestAssuredExtension assuredExtension = new RestAssuredExtension();
    }
}
