package com.intproj.contacts.ui

import com.intproj.contacts.data.models.PeopleContactModel
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class PeopleDiffsTest {

    @Test
    fun `PeopleDiffs works correctly`() {
        val diffUtils = PeopleDiffs()
        val contact1 = PeopleContactModel(
            avatar = "some url",
            fullName = "FullName",
            mail = "Mail",
            isOnline = false
        )

        val contact2 = PeopleContactModel(
            avatar = "another url",
            fullName = "another Name",
            mail = "another Mail",
            isOnline = true
        )

        val contact3 = PeopleContactModel(
            avatar = "another url",
            fullName = "another Name",
            mail = "Mail",
            isOnline = true
        )

        assertAll("Group",
            { assert(!diffUtils.areItemsTheSame(contact1, contact2)) },
            { assert(diffUtils.areItemsTheSame(contact1, contact1))  },
            { assert(!diffUtils.areContentsTheSame(contact1, contact2)) },
            { assert(diffUtils.areContentsTheSame(contact1, contact1))  },
            { assert(diffUtils.areItemsTheSame(contact1, contact3))  },
        )

    }

}