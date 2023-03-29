package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TitleTest extends BaseTest {

    @Test
    @DisplayName("Testing title")
    void shouldValidateCorrectTitle() {
        String actualTitle = driver.getTitle();
        logger.info("Actual title is " + actualTitle);
        assertThat(actualTitle).isEqualTo(System.getProperty("title"));
    }
}
