package com.jmj.mypatients.views.treatment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.jmj.mypatients.R
import com.jmj.mypatients.databinding.FragmentAddSessionBinding

class AddSessionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentAddSessionBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_session, container, false)
        return binding.root
    }


}
