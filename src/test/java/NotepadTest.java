import io.appium.java_client.windows.WindowsDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileInputStream;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotepadTest {

    private static final int TIMEOUT_IN_SECONDS = 3;
    private WindowsDriver<WebElement> notepadSession = null;

    public static String getDateTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("h:mm a M/d/yyyy"));
    }

    @BeforeEach
    public void setUp() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/config.properties"));
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("app", properties.getProperty("notepad"));
            capabilities.setCapability("platformName", "Windows");
            capabilities.setCapability("deviceName", "WindowsPC");

            notepadSession = new WindowsDriver<>(new URI("http://localhost:4723").toURL(), capabilities);
            notepadSession.manage().timeouts().implicitlyWait(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @AfterEach
    public void tearDown() {
        notepadSession.quit();
    }

    @Test
    public void checkDateForming() {
        notepadSession.findElement(By.name("Редагувати")).click();
        notepadSession.findElement(By.name("Дата й час")).click();
        WebElement textField = notepadSession.findElement(By.name("Текстовий редактор"));
        assertEquals(
                getDateTime(),
                textField.getText().trim().replace("\u200E", "")
        );
        textField.sendKeys(Keys.LEFT_CONTROL, "a");
        textField.sendKeys(Keys.DELETE);

    }
}
