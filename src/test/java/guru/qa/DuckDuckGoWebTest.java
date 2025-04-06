package guru.qa;

import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class DuckDuckGoWebTest {

  @BeforeEach
  void setUp(){
    open("https://duckduckgo.com/");
  }

  @ValueSource(strings = {
         "Selenide", "JUnit5"
  })
  @ParameterizedTest(name = "Для поискового запроса {0} должен быть не пустой список результатов")
  @Tag("BLOCKER")
  void searchResultsShouldNotBeEmptyTest(String searchQuery) {
    $("[name=q]").setValue(searchQuery).pressEnter();
    $$("[data-testid=mainline] li[data-layout='organic']").shouldBe(CollectionCondition.sizeGreaterThan(0));
  }

  @CsvSource(value = {
          "Selenide, https://selenide.org",
          "JUnit5, https://junit.org"
  })
  @ParameterizedTest(name = "Для поискового запроса {0} должна быть первая ссылка {1}")
  @Tag("BLOCKER")
  void searchResultsShouldContainExpectedUrlTest(String searchQuery, String expectedLink) {
    $("[name=q]").setValue(searchQuery).pressEnter();
    $("[data-testid=mainline] li[data-layout='organic']").shouldHave(text(expectedLink));
  }

  @CsvFileSource(resources = "/test_data/searchResultsShouldContainExpectedUrlCsvFileTest.csv")
  @ParameterizedTest(name = "Для поискового запроса {0} должна быть первая ссылка {1}")
  @Tag("BLOCKER")
  void searchResultsShouldContainExpectedUrlCsvFileTest(String searchQuery, String expectedLink) {
    $("[name=q]").setValue(searchQuery).pressEnter();
    $("[data-testid=mainline] li[data-layout='organic']").shouldHave(text(expectedLink));
  }

  @Test
  @Tag("BLOCKER")
  @DisplayName("Для поискового запроса 'selenide' должен быть не пустой список фото")
  void successfulSearchPhotoTest() {
    $("[name=q]").setValue("selenide").pressEnter();
    $("[data-testid=duckbar] li:nth-of-type(2)").click();
    $$(".tile--img__img ").shouldBe(CollectionCondition.sizeGreaterThan(0));
  }
}
