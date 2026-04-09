package com.orangehrmlive.UI;

import net.serenitybdd.screenplay.targets.Target;

public class DashboardUI {

    public static final Target DASHBOARD = Target.the("Dashboard")
                    .locatedBy("//h6[text()='Dashboard']");
}
