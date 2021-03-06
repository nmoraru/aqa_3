import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestCreditCard {

    @Test
    void shouldSubmitRequest() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Иванов Иван");
        form.$("[data-test-id=phone] input").setValue("+78005554433");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $(".Success_successBlock__2L3Cw").shouldHave(exactText("Ваша заявка успешно отправлена! " +
                "Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldUncheckedCheckbox() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Петров Петр");
        form.$("[data-test-id=phone] input").setValue("+78005554433");
        form.$(".button").click();
        $("[data-test-id=agreement].input_invalid").shouldHave(exactText("Я соглашаюсь с условиями " +
                "обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных" +
                " историй"));
    }

    @Test
    void shouldColorUncheckedCheckbox() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Петров Петр");
        form.$("[data-test-id=phone] input").setValue("+78005554433");
        form.$(".button").click();
        $("[data-test-id=agreement].input_invalid").shouldHave(cssValue("color", "rgba(255, 92, 92, 1)"));
    }

    @Test
    void shouldInputPatronymic() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Петров Петр Петрович");
        form.$("[data-test-id=phone] input").setValue("+78005554433");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $(".input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. " +
                "Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldEmptyName() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=phone] input").setValue("+78005554433");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $(".input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldEmptyTelNumber() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Петров Петр");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $(".input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldEmptyAll() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$(".button").click();
        $(".input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldEngName() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Ivanov Ivan");
        form.$("[data-test-id=phone] input").setValue("+78005554433");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $(".input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. " +
                "Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldNumericInName() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("123");
        form.$("[data-test-id=phone] input").setValue("+78005554433");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $(".input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. " +
                "Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldSpecialCharacterInName() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Ив@нов В@силий");
        form.$("[data-test-id=phone] input").setValue("+78005554433");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $(".input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. " +
                "Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldSpacesBeforeText() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("   Иванов Василий");
        form.$("[data-test-id=phone] input").setValue("+78005554433");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $(".input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы " +
                "только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldSpacesAfterText() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Иванов Василий   ");
        form.$("[data-test-id=phone] input").setValue("+78005554433");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $(".input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы " +
                "только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldMoreSpacesBetweenNameAndSurname() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Иванов   Василий");
        form.$("[data-test-id=phone] input").setValue("+78005554433");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $(".Success_successBlock__2L3Cw").shouldHave(exactText("Ваша заявка успешно отправлена! " +
                "Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldHyphenInStartName() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("-Иван Иванов");
        form.$("[data-test-id=phone] input").setValue("+78005554433");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $(".input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. " +
                "Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldHyphenInFinishName() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Иван Иванов-");
        form.$("[data-test-id=phone] input").setValue("+78005554433");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $(".input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. " +
                "Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldHyphenBetweenNameAndSurname() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Петров-Разумовский Василий");
        form.$("[data-test-id=phone] input").setValue("+78005554433");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $(".Success_successBlock__2L3Cw").shouldHave(exactText("Ваша заявка успешно отправлена! " +
                "Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldSpaceBetweenTelNumber() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Петров Василий");
        form.$("[data-test-id=phone] input").setValue("+78005 554433");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $(".input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр," +
                " например, +79012345678."));
    }

    @Test
    void shouldSpaceStartTelNumber() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Петров Василий");
        form.$("[data-test-id=phone] input").setValue(" +78005554433");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $(".input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр," +
                " например, +79012345678."));
    }

    @Test
    void shouldSpaceFinishTelNumber() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Петров Василий");
        form.$("[data-test-id=phone] input").setValue("+78005554433 ");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $(".input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр," +
                " например, +79012345678."));
    }

    @Test
    void shouldTelNumberMoreMax() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Петров Василий");
        form.$("[data-test-id=phone] input").setValue("+780055544332");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $(".input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр," +
                " например, +79012345678."));
    }

    @Test
    void shouldTelNumberLessMax() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Петров Василий");
        form.$("[data-test-id=phone] input").setValue("+7800555443");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $(".input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр," +
                " например, +79012345678."));
    }

}
