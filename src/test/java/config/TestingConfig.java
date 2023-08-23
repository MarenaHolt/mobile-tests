package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "file:/tmp/secret.properties",
        "classpath:testing.properties"
})
public interface TestingConfig extends Config{
    @Config.Key("app")
    String app();

    @Config.Key("project")
    String project();

    @Config.Key("build")
    String build();

    @Config.Key("name")
    String name();
}
