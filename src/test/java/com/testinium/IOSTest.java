package com.testinium;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class IOSTest extends BaseTest {


    @Test
    public  void SuccessIOS() throws InterruptedException {
        waitBySecond(2);
        konumIzniAlertVarsaTikla("İzin Ver");
        konumIzniAlertVarsaTikla("İzin Ver");
        konumIzniAlertVarsaTikla("Uygulamayı Kullanırken İzin Ver");
        konumIzniAlertVarsaTikla("Allow While Using App");
        konumIzniAlertVarsaTikla("İzin Ver");
        konumIzniAlertVarsaTikla("Tamam");
        konumIzniAlertVarsaTikla("İzin Ver");
        waitBySecond(2);
        WebElement kategorilerButton= driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[contains(@name, \"mainTabCategoriesTabBtn\")]"));
        kategorilerButton.click();

    }

    @Test
    public  void FailIOS() throws InterruptedException {
        waitBySecond(2);
        konumIzniAlertVarsaTikla("İzin Ver");
        konumIzniAlertVarsaTikla("İzin Ver");
        konumIzniAlertVarsaTikla("Uygulamayı Kullanırken İzin Ver");
        konumIzniAlertVarsaTikla("Allow While Using App");
        konumIzniAlertVarsaTikla("İzin Ver");
        konumIzniAlertVarsaTikla("Tamam");
        konumIzniAlertVarsaTikla("İzin Ver");
        waitBySecond(2);
        WebElement kategorilerButton= driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[contains(@name, \"mainTabasdsadsadCategoriesTabBtn\")]"));
        kategorilerButton.click();

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
            e.printStackTrace();
        }
    }


}
