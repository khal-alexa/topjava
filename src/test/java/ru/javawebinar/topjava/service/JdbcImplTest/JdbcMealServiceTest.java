package ru.javawebinar.topjava.service.JdbcImplTest;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.MealServiceTest;

@ActiveProfiles(profiles = {Profiles.JDBC})
public class JdbcMealServiceTest extends MealServiceTest {
}
