package ru.javawebinar.topjava.service.JpaImplTest;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.MealServiceTest;

@ActiveProfiles(profiles = {Profiles.JPA})
public class JpaMealServiceTest extends MealServiceTest {
}
