package guru.qa;


import org.junit.jupiter.api.*;


@DisplayName("Тесты на email рассылку")
public class EmailTest {

  @Test
  @Tag("SMOKE")
  @DisplayName("Email должен быть отправлен новому юзеру")
  void emailShouldBeSentForNewUserTest(){
    System.out.println("Hello World");

  }

  @Test
  @Tags({
          @Tag("SMOKE"),
          @Tag("WEB")
  })
  @DisplayName("Email должен быть отправлен забаненому юзеру")
  void emailShouldBeSentForBannedUserTest(){
    System.out.println("Hello World");

  }

  @Disabled("JDK-2544555")
  @Test
  @DisplayName("Email должен быть отправлен в случае изменения метода PaymentMethod")
  void emailShouldBeSentAfterChangePaymentMethod(){
    throw new AssertionError("Падаем!");

  }



}
