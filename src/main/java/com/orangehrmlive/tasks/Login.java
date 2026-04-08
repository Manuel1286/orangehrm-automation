package com.orangehrmlive.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

import net.serenitybdd.screenplay.waits.WaitUntil;

import static com.orangehrmlive.ui.LoginUI.*;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class Login implements Task {

    private final String username;
    private final String password;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public static Login conDatos(String username, String password) {
        return instrumented(Login.class, username, password);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(CAMPO_USUARIO, isVisible()).forNoMoreThan(10).seconds(),
                Enter.theValue(username).into(CAMPO_USUARIO),
                WaitUntil.the(CAMPO_PASSWORD, isVisible()).forNoMoreThan(10).seconds(),
                Enter.theValue(password).into(CAMPO_PASSWORD),
                WaitUntil.the(BOTON_INGRESAR, isClickable()).forNoMoreThan(10).seconds(),
                Click.on(BOTON_INGRESAR)
        );
    }




}