package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.AuthConfig;
import config.MobileConfig;
import config.TestingConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackDriver implements WebDriverProvider {
    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        AuthConfig config = ConfigFactory.create(AuthConfig.class, System.getProperties());
        TestingConfig testingConfig = ConfigFactory.create(TestingConfig.class, System.getProperties());
        MobileConfig mobileConfig = ConfigFactory.create(MobileConfig.class, System.getProperties());

        MutableCapabilities mutableCapabilities = new MutableCapabilities();
        mutableCapabilities.merge(capabilities);

        // Set your access credentials
        mutableCapabilities.setCapability("browserstack.user", config.username());
        mutableCapabilities.setCapability("browserstack.key", config.password());

        // Set URL of the application under test
        mutableCapabilities.setCapability("app", testingConfig.app());

        // Specify device and os_version for testing
        mutableCapabilities.setCapability("device", mobileConfig.getDeviceName());
        mutableCapabilities.setCapability("os_version", mobileConfig.getOsVersion());

        // Set other BrowserStack capabilities
        mutableCapabilities.setCapability("project", testingConfig.project());
        mutableCapabilities.setCapability("build", testingConfig.build());
        mutableCapabilities.setCapability("name", testingConfig.name());


        // Initialise the remote Webdriver using BrowserStack remote URL
        // and desired capabilities defined above
        try {
            return new RemoteWebDriver(
                    new URL("http://hub.browserstack.com/wd/hub"), mutableCapabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}