package com.intproj.contacts.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.transition.Fade
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.intproj.R
import com.intproj.contacts.data.models.PeopleContactModel
import com.intproj.databinding.FragmentDetailsBinding
import com.intproj.shared.navigation.BaseFragment

class DetailsFragment : BaseFragment() {

    companion object {
        private const val KEY_MODEL = "KEY_MODEL"

        fun newInstance(itemModel: PeopleContactModel): DetailsFragment {
            val bundle = Bundle()
            bundle.putParcelable(KEY_MODEL, itemModel)

            val fragment = DetailsFragment()
            fragment.arguments = bundle
            fragment.enterTransition = Fade()
            fragment.exitTransition = Fade()
            return fragment
        }
    }

    private lateinit var binding: FragmentDetailsBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<PeopleContactModel>(KEY_MODEL)?.let { model ->
            binding.apply {
                Glide.with(view)
                    .load(model.avatar)
                    .circleCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(ivDetails)
                tvDetailsName.text = model.fullName
                tvDetailsStatus.text =
                    if (model.isOnline) getString(R.string.online) else getString(R.string.offline)
                tvDetailsMail.text = model.mail
            }
        }
    }

}