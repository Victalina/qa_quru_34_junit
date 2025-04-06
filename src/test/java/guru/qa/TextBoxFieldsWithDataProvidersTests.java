package guru.qa;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import guru.qa.pages.TextBoxPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;


@DisplayName("Параметризированные тесты заполнения полей формы TextBox")
public class TextBoxFieldsWithDataProvidersTests extends TestBase {

  TextBoxPage textBoxPage = new TextBoxPage();

  @ValueSource(strings = {"Tom Black", "Nick J. Smith", "Charles Philip Arthur George"})
  @ParameterizedTest(name = "Заполнение поля Full Name валидным значением {0}")
  @Disabled
  void fillFullNameFieldWithValidValuesTest(String userName) {
    textBoxPage.openPage()
            .setUserName(userName)
            .submit()
            .checkNameResultValue(userName);
  }

  @CsvSource(value = {
          "Tom Black, test@test.ru",
          "Nick J. Smith, TEST@TEST.COM",
          "Charles Philip Arthur, test.test@test.com",
          "Charles P, test@test.test.com",
          "Charles Philip, test-test@test.com",
          "J. Philip, test@test-test.com",
          "Charles Philip, test_test@test.com",
          "Tom, test@test_test.com"
  })
  @ParameterizedTest(name = "Заполнение поля Full Name валидным значением {0} и поля Email валидным значением {1}")
  @Tag("SMOKE")
  void fillFullNameAndEmailFieldsWithValidValuesTest(String userName, String email) {
    textBoxPage.openPage()
            .setUserName(userName)
            .setEmail(email)
            .submit()
            .checkNameResultValue(userName)
            .checkEmailResultValue(email);
  }

  @CsvFileSource(resources = "/test_data/FullNameFieldWithValidValueAndEmailFieldWithInvalidValue.csv")
  @ParameterizedTest(name = "Заполнение поля Full Name валидным значением {0} и поля Email невалидным значением {1}")
  void fillFullNameFieldWithValidValueAndEmailFieldWithInvalidValueTest(String userName, String email) {
    textBoxPage.openPage()
            .setUserName(userName)
            .setEmail(email)
            .submit()
            .checkNameResultValueNotAppearing()
            .checkEmailResultValueNotAppearing();
  }
}
