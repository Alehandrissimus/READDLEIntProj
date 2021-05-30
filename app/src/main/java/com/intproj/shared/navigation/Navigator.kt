package com.intproj.shared.navigation

import android.view.View
import android.widget.ImageView
import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import com.intproj.R
import com.intproj.contacts.data.models.PeopleContactModel
import com.intproj.contacts.ui.ContactsFragment
import com.intproj.contacts.ui.DetailsFragment

class Navigator(
    private val fragmentManager: FragmentManager,
    @IdRes private val containerId: Int,
) {

    fun openPeopleFragment() {
        fragmentManager.beginTransaction()
            .replace(containerId, ContactsFragment.newInstance())
            .commit()
    }

    fun openDetailsFragment(itemModel: PeopleContactModel) {
        fragmentManager.beginTransaction()
            .add(containerId, DetailsFragment.newInstance(itemModel))
            .addToBackStack(null)
            .commit()
    }

}