package com.example.todoappcat23.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todoappcat23.R
import com.example.todoappcat23.model.MyEvent
import com.example.todoappcat23.utils.DETAILS_EVENT

class DetailsEventFragment : Fragment() {

    private var detailsEvent: MyEvent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            detailsEvent = it.getParcelable(DETAILS_EVENT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details_event, container, false)
    }

    companion object {

        fun newInstance(event: MyEvent) =
            DetailsEventFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(DETAILS_EVENT, event)
                }
            }
    }
}