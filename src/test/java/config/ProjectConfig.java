package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config/${env}.properties"
})
public interface ProjectConfig extends Config {
    @Key("baseUrl")
    @DefaultValue("https://ticktick.com")
    String getBaseUrl();

    @Key("baseUri")
    @DefaultValue("https://ticktick.com")
    String getBaseUri();

    @Key("browser")
    @DefaultValue("CHROME")
    String getBrowser();

    @Key("browserVersion")
    @DefaultValue("100.0")
    String getBrowserVersion();

    @Key("browserSize")
    String getBrowserSize();

    @Key("remote")
    String getRemoteURL();
}

