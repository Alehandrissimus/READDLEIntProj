package com.intproj.contacts.data.utils

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class ContactsGeneratorTest {

    @Test
    fun `ContactsGenerator works correctly`() {
        val list = ContactsGenerator().invoke()

        assertAll(
            "Group",
            { assert(list.size in ContactsGenerator.minContacts..ContactsGenerator.maxContacts) },
            { assert(list[0] != null) })
    }
}