package com.intproj.contacts.data.utils

import com.intproj.contacts.data.models.PeopleContactModel
import java.security.MessageDigest

class ContactsGenerator {

    companion object {
        const val minContacts = 50
        const val maxContacts = 150
    }

    operator fun invoke(): List<PeopleContactModel> {
        val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        val contactsNum = (minContacts..maxContacts).random()
        val contacts = mutableListOf<PeopleContactModel>()

        for (i in 0..contactsNum) {
            val name = (1..16)
                .map { kotlin.random.Random.nextInt(0, charPool.size) }
                .map(charPool::get)
                .joinToString("")

            val status = (0..10).random() < 7

            val md = MessageDigest.getInstance("MD5").digest(name.toByteArray())
            val avatar = "https://www.gravatar.com/avatar/${md.toHex()}?d=monsterid"

            val contact = PeopleContactModel(
                avatar = avatar,
                isOnline = status,
                fullName = name,
                mail = "${name}@gmail.com",
            )
            contacts.add(contact)
        }

        return contacts
    }

    private fun ByteArray.toHex(): String {
        return joinToString("") { "%02x".format(it) }
    }
}