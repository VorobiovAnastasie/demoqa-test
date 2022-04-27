package com.demoqa;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegistrationFormPage;

import static com.codeborne.selenide.Selenide.open;
import static java.lang.String.format;

public class HomeWorkTestData {
    Faker faker = new Faker();
    RegistrationFormPage registrationFormPage = new RegistrationFormPage();


    @BeforeAll
    static void setUp() {
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void fillForm() {

        open("/automation-practice-form");
        String firstName = faker.name().firstName(),
               lastName = faker.name().lastName(),
                email = faker.internet().emailAddress(),
                userNumber = "3807645322",
                day = "19",
                month = "September",
                year = "1997",
                subject = "English",
                hobbies = "Reading",
                uploadPicture = "Harry_Potter.jpeg",
                userAddress = faker.harryPotter().location(),
                selectState = "NCR",
                selectCity = "Delhi",
                genderWrapper = "Male";


        String expectedFullName = format("%s %s", firstName, lastName);
        String expectedDateOfBirth = format("%s %s,%s", day,month,year);
        String expectedStateAndCity = format("%s %s", selectState, selectCity);

        registrationFormPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(genderWrapper)
                .setUserNumber(userNumber)
                .setBirthDate(day, month, year)
                .subjectsInput(subject)
                .hobbiesInput(hobbies)
                .uploadPicture(uploadPicture)
                .currentAddress(userAddress)
                .selectState(selectState)
                .selectCity(selectCity)
                .submit()
                .checkResult("Student Name", expectedFullName)
                .checkResult("Student Email", email)
                .checkResult("Gender", genderWrapper)
                .checkResult("Mobile", userNumber)
                .checkResult("Date of Birth", expectedDateOfBirth)
                .checkResult("Subjects", subject)
                .checkResult("Hobbies", hobbies)
                .checkResult("Picture", uploadPicture)
                .checkResult("Address", userAddress)
                .checkResult("State and City",expectedStateAndCity);

    }

}
