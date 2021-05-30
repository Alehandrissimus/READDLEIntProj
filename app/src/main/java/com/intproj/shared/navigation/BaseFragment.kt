package com.intproj.shared.navigation

import androidx.fragment.app.Fragment
import com.intproj.NavigationActivity

abstract class BaseFragment : Fragment() {

    protected val navigator: Navigator by lazy { (requireActivity() as NavigationActivity).navigator }

}