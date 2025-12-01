package com.testinium;

import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class AndroidTest extends BaseTest {

    @Test
    public void SuccessAndroid() {
        waitBySecond(2);
        tapAllLocationAlerts();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement profileButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            AppiumBy.id("com.gratis.android.dev:id/bottomBarItem_Profile")
                    )
            );

            profileButton.click();

        } catch (TimeoutException e) {
            // ERROR yerine kontrollü FAIL
            Assertions.fail("Profil butonu 10 saniye içinde görünür/clickable olmadı.", e);
        }
    }

    @Test
    public void FailAndroid() {
        waitBySecond(2);
        tapAllLocationAlerts();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // Bilerek saçma/yanlış locator
        var wrongLocator = AppiumBy.id("com.gratis.android.dev:id/bottomBarItem_Prosadsadasdfile");

        try {
            // Eğer bu satır element bulursa, bu beklenmeyen durum → FAIL
            wait.until(ExpectedConditions.elementToBeClickable(wrongLocator)).click();
            Assertions.fail("Yanlış locator ile bile element bulundu, bu beklenmiyordu!");

        } catch (TimeoutException e) {
            // Beklenen durum: element yok → negatif senaryo BAŞARILI (test PASS)
            System.out.println("Beklenen şekilde element bulunamadı, negatif Android senaryosu başarılı.");
        }
    }

    // Ortak izin akışını tek yerde topladım
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
