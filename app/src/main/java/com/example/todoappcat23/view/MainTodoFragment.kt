package com.example.todoappcat23.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoappcat23.R
import com.example.todoappcat23.adapter.EventAdapter
import com.example.todoappcat23.adapter.OnEventClickHandler
import com.example.todoappcat23.databinding.FragmentMainTodoBinding
import com.example.todoappcat23.model.MyEvent
import com.example.todoappcat23.utils.DETAILS_EVENT
import com.example.todoappcat23.utils.switchingToAnotherFragment
import com.google.android.material.snackbar.Snackbar

class MainTodoFragment : Fragment(), OnEventClickHandler {

    private val binding by lazy {
        FragmentMainTodoBinding.inflate(layoutInflater)
    }

    private val eventAdapter by lazy {
        EventAdapter(this) { event ->
            findNavController().navigate(
                R.id.action_MainTodoFragment_to_DetailsEventFragment,
                bundleOf(
                    Pair(DETAILS_EVENT, event)
                )
            )
        }
    }

    var counter = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding.eventRecycler.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = eventAdapter
        }

        binding.createEventBtn.setOnClickListener {
            // moving to create fragment using support fragemnt manager
//            parentFragmentManager
//                .switchingToAnotherFragment(
//                    CreateEventFragment.newInstance()
//                )

            // moving to create event fragment using navigation
            findNavController().navigate(
                R.id.action_MainTodoFragment_to_CreateEventFragment
            )

            eventAdapter.updateEvent(
                MyEvent(
                    "New name $counter",
                    "category $counter",
                    "$counter"
                )
            )

            counter++
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainTodoFragment()
    }

    override fun onEventClicked(event: MyEvent) {
        Snackbar.make(binding.root, "click from interface: ${event.name}", Snackbar.LENGTH_LONG).show()
    }
}