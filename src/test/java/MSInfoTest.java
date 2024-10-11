import io.appium.java_client.windows.WindowsDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileInputStream;
import java.net.URI;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class MSInfoTest {
    private static final int TIMEOUT_IN_SECONDS = 5;
    private WindowsDriver<WebElement> infoSession = null;

    @BeforeEach
    public void setUp() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/config.properties"));
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("app", properties.getProperty("msinfo"));
            capabilities.setCapability("platformName", "Windows");
            capabilities.setCapability("deviceName", "WindowsPC");

            infoSession = new WindowsDriver<>(new URI("http://localhost:4723").toURL(), capabilities);
            infoSession.manage().timeouts().implicitlyWait(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @AfterEach
    public void tearDown() {
        infoSession.quit();
    }

    @Test
    public void someTest() {
        //WebElement other = infoSession.findElementByAccessibilityId("ListViewSubItem-0");
        //WebElement other2 = infoSession.findElementByCustom("ListViewSubItem-0");
        //WebElement other3 = infoSession.findElementById("ListViewSubItem-0");
        //WebElement other4 = infoSession.findElementByWindowsUIAutomation("ListViewSubItem-0");
        //WebElement other5 = infoSession.findElementByPartialLinkText("ListViewSubItem-0");
        //WebElement other6 = infoSession.findElementByXPath("//*[@Name='OS Name']");
        //WebElement other7 = infoSession.findElementByXPath("//*[@Previous='\"Version\" text']");
        //System.out.println(other6.getText());
        //System.out.println(other7.getText());
        WebElement uefi = infoSession.findElement(By.name("UEFI"));
        System.out.println(uefi.getTagName());
        System.out.println(uefi.getAttribute("id"));
        System.out.println(uefi.getAttribute("Id"));
        System.out.println(uefi.getAttribute("RuntimeId"));
        System.out.println(uefi.getAttribute("AutomationId"));
        System.out.println(uefi.getAttribute("Previous"));
        System.out.println(uefi.getAttribute("Ancestors"));
        System.out.println(uefi.getAttribute("FrameworkId"));
        System.out.println(uefi.getAttribute("ProviderDescription"));
        System.out.println(uefi.getAttribute("ControlType"));
        System.out.println(uefi.getAttribute("Table.ItemColumnHeaderItems"));
        //System.out.println(infoSession.findElementByAccessibilityId("ListViewSubItem-1").getText());
    }
}
