package ir.payment.configurations;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.TimeZone;

@Configuration
@EnableScheduling
public class AppConfiguration {

    private static final String DEFAULT_BUNDLE_PATH = "classpath:messages";
    private static final String DEFAULTS_PATH = "classpath:messages";

    @Bean
    public MessageSource messageSource() {
        var messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames(DEFAULT_BUNDLE_PATH, DEFAULTS_PATH);
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
        return messageSource;
    }

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setDefaultLocale(Locale.ENGLISH);
        // timezone to change datetime output
        localeResolver.setDefaultTimeZone(TimeZone.getTimeZone("UTC"));

        return localeResolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        // Defaults to "locale" if not set
        localeChangeInterceptor.setParamName("localeData");
        return localeChangeInterceptor;
    }

}
