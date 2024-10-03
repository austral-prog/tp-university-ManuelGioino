package com.university;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TeacherTest {

    @Test
    void testRetrieveFullName() {

        Teacher teacher = new Teacher("Alice Johnson", "Mathematics", 123);

        String fullName = teacher.retrieveFullName();

        assertEquals("Alice Johnson", fullName, "The full name should match the initialized value.");
    }

    @Test
    void testGetSubject() {
        Teacher teacher = new Teacher("Alice Johnson", "Mathematics", 123);
        String subject = teacher.getSubject();
        assertEquals("Mathematics", subject, "The subject should match the initialized value.");
    }

    @Test
    void testGetIdentifier() {
        Teacher teacher = new Teacher("Alice Johnson", "Mathematics", 123);
        int identifier = teacher.getIdentifier();
        assertEquals(123, identifier, "The identifier should match the initialized value.");
    }
}
