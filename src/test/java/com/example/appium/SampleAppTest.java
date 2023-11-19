package com.example.appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static java.lang.System.getenv;

public class SampleAppTest {
    private AppiumDriverLocalService server;
    private AppiumDriver driver;

    @BeforeClass
    private void setUp() throws MalformedURLException {

        String platform = getenv("APPIUM_DRIVER");
        platform = platform == null ? "ANDROID" : platform.toUpperCase();
        String path = System.getProperty("user.dir");
        server = AppiumDriverLocalService.buildDefaultService();
        server.start();


        DesiredCapabilities capabilities = new DesiredCapabilities();
        if (platform.equals("ANDROID")) {
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 3a");
            capabilities.setCapability(MobileCapabilityType.APP, path + "/ApiDemos-debug.apk");
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UIAutomator2");
            capabilities.setCapability("avd","Pixel_3a_API_33_arm64-v8a");
            capabilities.setCapability("appActivity", ".view.TextFields");
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), capabilities);
        } else {
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "17.0");
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCuiTest");
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 15 Pro Max");
            capabilities.setCapability(MobileCapabilityType.APP, path + "/TestApp.app.zip");
            capabilities.setCapability( MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
            driver = new IOSDriver(new URL("http://127.0.0.1:4723/"), capabilities);
        }
    }

    @Test
    public void textFieldTest() {
        PageView view = new PageView(driver);
        view.setTextField("test");
        Assert.assertEquals(view.getTextField(), "test", "Text field was not set.");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
        server.stop();
    }
}
