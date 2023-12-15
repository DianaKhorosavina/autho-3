package ru.netology.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class NegativeOrderTests {

    @BeforeEach
    public void openAll() {
        open("http://localhost:9999");
    }


        @Test
        void englishNameTest() {
            SelenideElement form = $(By.className("form"));
            form.$("[data-test-id=name] input").setValue("Diana Khoroshavina");
            form.$("[data-test-id=phone] input").setValue("+79011479956");
            form.$("[data-test-id=agreement]").click();
            form.$(By.className("button")).click();
            $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
        }

        @Test
        void emptyNameFieldTest() {
            SelenideElement form = $(By.className("form"));
            form.$("[data-test-id=name] input").setValue("");
            form.$("[data-test-id=phone] input").setValue("+79011479956");
            form.$("[data-test-id=agreement]").click();
            form.$(By.className("button")).click();
            $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
        }

        @Test
        void numbersInNameFieldTest () {
            SelenideElement form = $(By.className("form"));
            form.$("[data-test-id=name] input").setValue("125894");
            form.$("[data-test-id=phone] input").setValue("+79011479956");
            form.$("[data-test-id=agreement]").click();
            form.$(By.className("button")).click();
            $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
        }

        @Test
        void usingAnApostropheInNameFieldTest () {
            SelenideElement form = $(By.className("form"));
            form.$("[data-test-id=name] input").setValue("Жанна д’Арк");
            form.$("[data-test-id=phone] input").setValue("+79011479956");
            form.$("[data-test-id=agreement]").click();
            form.$(By.className("button")).click();
            $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
        }

        @Test
        void emptyTelephoneNumberFieldTest () {
            SelenideElement form = $(By.className("form"));
            form.$("[data-test-id=name] input").setValue("Петров Александр");
            form.$("[data-test-id=phone] input").setValue("");
            form.$("[data-test-id=agreement]").click();
            form.$(By.className("button")).click();
            $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
        }


        @Test
        void useLettersInTelephoneNumberFieldTest () {
            SelenideElement form = $(By.className("form"));
            form.$("[data-test-id=name] input").setValue("Петров Александр");
            form.$("[data-test-id=phone] input").setValue("Привет");
            form.$("[data-test-id=agreement]").click();
            form.$(By.className("button")).click();
            $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
        }

        @Test
        void useMoreThen11SymbolsInTelephoneNumberFieldTest () {
            SelenideElement form = $(By.className("form"));
            form.$("[data-test-id=name] input").setValue("Петров Александр");
            form.$("[data-test-id=phone] input").setValue("+79198832480045");
            form.$("[data-test-id=agreement]").click();
            form.$(By.className("button")).click();
            $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
        }

        @Test
        void sendFormWithoutCheckboxTest () {
            SelenideElement form = $(By.className("form"));
            form.$("[data-test-id=name] input").setValue("Петров Александр");
            form.$("[data-test-id=phone] input").setValue("+79198832480");
            form.$(By.className("button")).click();
            $("[data-test-id=phone].input_has-value .input__sub").shouldHave(exactText("На указанный номер моб. тел. будет отправлен смс-код для подтверждения заявки на карту. Проверьте, что номер ваш и введен корректно."));
        }
    }

