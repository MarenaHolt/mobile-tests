package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.BrowserstackConfig;
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
//        AuthConfig config = ConfigFactory.create(AuthConfig.class, System.getProperties());
//        TestingConfig testingConfig = ConfigFactory.create(TestingConfig.class, System.getProperties());
//        MobileConfig mobileConfig = ConfigFactory.create(MobileConfig.class, System.getProperties());

        BrowserstackConfig config = ConfigFactory.create(BrowserstackConfig.class, System.getProperties());

        MutableCapabilities mutableCapabilities = new MutableCapabilities();
        mutableCapabilities.merge(capabilities);

        // Set your access credentials
        mutableCapabilities.setCapability("browserstack.user", config.getUser());
        mutableCapabilities.setCapability("browserstack.key", config.getKey());

        // Set URL of the application under test
        mutableCapabilities.setCapability("app", config.getApp());

        // Specify device and os_version for testing
        mutableCapabilities.setCapability("device", config.getDevice());
        mutableCapabilities.setCapability("os_version", config.getOsVersion());

        // Set other BrowserStack capabilities
        mutableCapabilities.setCapability("project", config.getProject());
        mutableCapabilities.setCapability("build", config.getBuild());
        mutableCapabilities.setCapability("name", config.getName());


        // Initialise the remote Webdriver using BrowserStack remote URL
        // and desired capabilities defined above
        try {
            return new RemoteWebDriver(
                    new URL(config.getBaseUrl()), mutableCapabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}