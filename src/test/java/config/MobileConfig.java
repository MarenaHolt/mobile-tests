package config;

import org.aeonbits.owner.Config;


@Config.Sources({
        "classpath:${device}.properties",
         "classpath:google-pixel.properties"
})
public interface MobileConfig extends Config {

    @Key("device")
    String getDeviceName();

    @Key("platform.name")
    String getPlatformName();

    @Key("platform.version")
    String getPlatformVersion();

    @Key("os.version")
    String getOsVersion();

}
