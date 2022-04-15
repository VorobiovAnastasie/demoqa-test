package com.demoqa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.selector.ByText;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class HomeWork {

    @BeforeAll
    static void setUp() {
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void fillFormTest() {
        open("/automation-practice-form");

        $("[id=firstName]").setValue("Harry");
        $("[id=lastName]").setValue("Potter");
        $("[id=userEmail]").setValue("harry@potter.com");
        $(new ByText("Male")).click();
        $("[id=userNumber]").setValue("3807645322");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("September");
        $(".react-datepicker__year-select").selectOption("1997");
        $("[aria-label$='September 19th, 1997']").click();
        $("#subjectsInput").setValue("English").pressEnter();
        $( new ByText("Reading")).click();
        $("#uploadPicture").uploadFromClasspath("Harry_Potter.jpeg");
        $("#currentAddress").setValue("Nyutona street, building 5, apt 95");
        $("#stateCity-wrapper").$("input").setValue("NCR").sendKeys(Keys.TAB);
        $("#city").$("input").setValue("Delhi").sendKeys(Keys.TAB);
        $("#submit").click();
        $(".modal-title").shouldHave(Condition.text("Thanks for submitting the form"));
        $(".table-responsive").$(new ByText("Student Name")).parent().shouldHave(Condition.text("Harry Potter"));
        $(".table-responsive").$(new ByText("Student Email")).parent().shouldHave(Condition.text("harry@potter.com"));
        $(".table-responsive").$(new ByText("Gender")).parent().shouldHave(Condition.text("Male"));
        $(".table-responsive").$(new ByText("Mobile")).parent().shouldHave(Condition.text("3807645322"));
        $(".table-responsive").$(new ByText("Date of Birth")).parent().shouldHave(Condition.text("19 September, 1997"));
        $(".table-responsive").$(new ByText("Subjects")).parent().shouldHave(Condition.text("English"));
        $(".table-responsive").$(new ByText("Hobbies")).parent().shouldHave(Condition.text("Reading"));
        $(".table-responsive").$(new ByText("Picture")).parent().shouldHave(Condition.text("Harry_Potter.jpeg"));
        $(".table-responsive").$(new ByText("Address")).parent().shouldHave(Condition.text("Nyutona street, building 5, apt 95"));
        $(".table-responsive").$(new ByText("State and City")).parent().shouldHave(Condition.text("NCR Delhi"));
    }



}