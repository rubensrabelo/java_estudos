package com.appsdeveloperblog.tutorials.junit.io;

import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class UserEntityIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    UserEntity user;

    @BeforeEach
    void setup() {
        user = new UserEntity();
        user.setUserId(UUID.randomUUID().toString());
        user.setFirstName("Sergey");
        user.setLastName("Kargopolov");
        user.setEmail("test@test.com");
        user.setEncryptedPassword("12345678");
    }

    @Test
    void testUserEntity_whenValidUserDetailsProvided_shouldReturnStoredUserDetails() {
        UserEntity storedUser = entityManager.persistAndFlush(user);

        Assertions.assertTrue(storedUser.getId() > 0);
        Assertions.assertEquals(user.getUserId(), storedUser.getUserId());
        Assertions.assertEquals(user.getFirstName(), storedUser.getFirstName());
        Assertions.assertEquals(user.getLastName(), storedUser.getLastName());
        Assertions.assertEquals(user.getEmail(), storedUser.getEmail());
        Assertions.assertEquals(user.getEncryptedPassword(), storedUser.getEncryptedPassword());
    }

    @Test
    void testUserEntity_whenFirstNameIsTooLong_shouldThrowException() {
        user.setFirstName("123456789012345678901234567890123456789012345678901234567890");

        assertThrows(PersistenceException.class, ()->{
            entityManager.persistAndFlush(user);
        }, "Was expecting a PersistenceException to be thrown.");
    }

    @Test
    void testUserEntity_whenExistingUserIdProvided_shouldThrowException() {
        UserEntity newEntity = new UserEntity();
        newEntity.setUserId("1");
        newEntity.setEmail("test2@test.com");
        newEntity.setFirstName("test");
        newEntity.setLastName("test");
        newEntity.setEncryptedPassword("test");
        entityManager.persistAndFlush(newEntity);

        user.setUserId("1");

        assertThrows(PersistenceException.class, ()-> {
            entityManager.persistAndFlush(user);
        }, "Expected PersistenceException to be thrown here");
    }
}
