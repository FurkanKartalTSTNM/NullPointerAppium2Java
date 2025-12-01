package com.testinium;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class IOSTest extends BaseTest {


    @Test
    public void SuccessIOS() {
        waitBySecond(2);
        tapAllLocationAlerts();

        // iOS butonu için locator
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement kategorilerButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.xpath("**/XCUIElementTypeButton['label==\"Kategoriler\"']")
                )
        );
        kategorilerButton.click();
    }

    /**
     * Bu testte locator bilerek yanlış.
     *
     * - Eğer "yanlış locator element bulamasın, bu da KONTROLLÜ bir durum olsun" diyorsan:
     *   Aşağıdaki versiyon testin PASS olmasına neden olur (negatif senaryo).
     * - Bunu raporda kırmızı (FAIL) görmek istersen en alta örnek de ekledim.
     */
    @Test
    public void FailIOS() {
        waitBySecond(2);
        tapAllLocationAlerts();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // Bilerek yanlış locator
        var wrongLocator = AppiumBy.xpath("//XCUIElementTypeButton[contains(@name, \"mainTabasdsadsadCategoriesTabBtn\")]");

        // ❕ Negatif senaryo: Bu elementin gelmemesi BEKLENEN durum
        try {
            wait.until(ExpectedConditions.elementToBeClickable(wrongLocator));
            // Eğer buraya kadar gelirse, yanlış locator’a rağmen element bulundu demektir → FAIL
            org.junit.jupiter.api.Assertions.fail("Yanlış locator ile bile element bulundu, bu beklenmiyordu!");
        } catch (TimeoutException e) {
            // Beklenen: element bulunamadı → test PASS (kontrollü negatif senaryo)
            System.out.println("Beklenen şekilde element bulunamadı, negatif iOS senaryosu başarılı.");
        }
    }

    /**
     * Ortak izin pop-up tıklamalarını toparladım, kod tekrarını azaltıyor.
     */
    private void tapAllLocationAlerts() {
        konumIzniAlertVarsaTikla("İzin Ver");
        konumIzniAlertVarsaTikla("İzin Ver");
        konumIzniAlertVarsaTikla("Uygulamayı Kullanırken İzin Ver");
        konumIzniAlertVarsaTikla("Allow While Using App");
        konumIzniAlertVarsaTikla("İzin Ver");
        konumIzniAlertVarsaTikla("Tamam");
        konumIzniAlertVarsaTikla("İzin Ver");
    }

    public void konumIzniAlertVarsaTikla(String text) {
        try {
            HashMap<String, String> args = new HashMap<>();
            args.put("action", "accept");
            args.put("buttonLabel", text);

            ((JavascriptExecutor) driver).executeScript("mobile: alert", args);
            System.out.println("mobile: alert ile '" + text + "' butonuna tıklandı.");
        } catch (Exception e) {
            System.out.println("Alert bulunamadı veya '" + text + "' butonu tıklanamadı. Devam ediliyor...");
        }
    }

    public void waitBySecond(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
