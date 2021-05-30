package com.intproj.contacts.ui

import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.intproj.R
import com.intproj.contacts.data.utils.ContactsGenerator
import com.intproj.databinding.FragmentPeopleBinding
import com.intproj.shared.navigation.BaseFragment

class ContactsFragment : BaseFragment() {

    companion object {
        fun newInstance(): ContactsFragment {
            return ContactsFragment()
        }
    }

    private lateinit var binding: FragmentPeopleBinding
    private lateinit var peopleAdapter: PeopleRVAdapter
    private var rvLayoutManager: GridLayoutManager? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPeopleBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setupRV()
    }

    private fun setupRV() {
        rvLayoutManager = GridLayoutManager(context, 1)
        peopleAdapter = PeopleRVAdapter(navigator::openDetailsFragment, rvLayoutManager)
        binding.rvPeople.apply {
            layoutManager = rvLayoutManager
            adapter = peopleAdapter
        }
        peopleAdapter.submitList(ContactsGenerator().invoke())
    }

    private fun setupListeners() {
        binding.apply {
            btnFloatingGridList.setOnClickListener {
                changeButtonImage()
                changeLayoutManager()
            }
            btnSimCh.setOnClickListener {
                peopleAdapter.submitList(ContactsGenerator().invoke())
            }
        }
    }

    private fun changeLayoutManager() {
        if(rvLayoutManager?.spanCount == 1) {
            rvLayoutManager?.spanCount = 5
        } else {
            rvLayoutManager?.spanCount = 1
        }
        peopleAdapter.notifyItemRangeChanged(0, peopleAdapter.itemCount)
    }

    private fun changeButtonImage() {
        if(rvLayoutManager?.spanCount == 5) {
            val drawable =  ContextCompat.getDrawable(context!!, R.drawable.transition_drawable) as TransitionDrawable
            binding.btnFloatingGridList.setImageDrawable(drawable)
            drawable.startTransition(200)
        } else {
            val drawable =  ContextCompat.getDrawable(context!!, R.drawable.transition_drawable_rev) as TransitionDrawable
            binding.btnFloatingGridList.setImageDrawable(drawable)
            drawable.startTransition(200)
        }
    }

}