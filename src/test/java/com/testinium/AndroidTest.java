package com.testinium;

import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class AndroidTest extends BaseTest {

    @Test
    public  void SuccessAndroid() throws InterruptedException {
        waitBySecond(2);
        konumIzniAlertVarsaTikla("İzin Ver");
        konumIzniAlertVarsaTikla("İzin Ver");
        konumIzniAlertVarsaTikla("Uygulamayı Kullanırken İzin Ver");
        konumIzniAlertVarsaTikla("Allow While Using App");
        konumIzniAlertVarsaTikla("İzin Ver");
        konumIzniAlertVarsaTikla("Tamam");
        konumIzniAlertVarsaTikla("İzin Ver");
        waitBySecond(2);
        WebElement profileButton= driver.findElement(AppiumBy.id("android:id/`bottomBarItem_Profile"));
        profileButton.click();

    }

    @Test
    public  void FailAndroid() throws InterruptedException {
        waitBySecond(2);
        konumIzniAlertVarsaTikla("İzin Ver");
        konumIzniAlertVarsaTikla("İzin Ver");
        konumIzniAlertVarsaTikla("Uygulamayı Kullanırken İzin Ver");
        konumIzniAlertVarsaTikla("Allow While Using App");
        konumIzniAlertVarsaTikla("İzin Ver");
        konumIzniAlertVarsaTikla("Tamam");
        konumIzniAlertVarsaTikla("İzin Ver");
        waitBySecond(2);
        WebElement profileButton= driver.findElement(AppiumBy.id("android:id/`bottomBarItem_Prosadsadasdfile"));
        profileButton.click();

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
