package ru.mts;

import org.awaitility.Awaitility;
import org.awaitility.Duration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import ru.mts.config.AnimalFactoryTestConfig;
import ru.mts.config.ScheduledTaskConfig;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(
        classes = {AnimalFactoryTestConfig.class, ScheduledTaskConfig.class}
)
@ActiveProfiles("test")
@DisplayName("Тестирование класса ScheduledTask")
@EnableScheduling
@TestPropertySource(locations = "classpath:application-test.yml")
public class ScheduledTaskTest {

    @SpyBean
    private ScheduledTask scheduledTask;

    @Test
    @DisplayName("Проверка вызова метода SchedulerTask согласно интервалу")
    public void testScheduledTask() {
        Awaitility.await().atMost(Duration.FIVE_SECONDS)
                .untilAsserted(() -> verify(scheduledTask, times(2)).task());
    }

    @Autowired
    public void setScheduledTask(ScheduledTask scheduledTask) {
        this.scheduledTask = scheduledTask;
    }
}
