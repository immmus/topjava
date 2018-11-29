package ru.javawebinar.topjava.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.AbstractJpaUserServiceTest;

import static ru.javawebinar.topjava.Profiles.JPA;

@ActiveProfiles(JPA)
public class JpaUserServiceTest extends AbstractJpaUserServiceTest {
    @Override
    public void testValidation() throws Exception {
        super.testValidation();
    }
}