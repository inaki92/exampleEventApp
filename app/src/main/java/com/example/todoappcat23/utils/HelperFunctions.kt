package com.example.todoappcat23.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.todoappcat23.R

const val DETAILS_EVENT = "DETAILS_EVENT"

fun movingToAnotherFragment(fragManager: FragmentManager, newFragment: Fragment) {
    fragManager.beginTransaction()
        .replace(R.id.container_frag, newFragment)
        .addToBackStack(null)
        .commit()
}

fun FragmentManager.switchingToAnotherFragment(newFragment: Fragment) {
    this.beginTransaction()
        .replace(R.id.container_frag, newFragment)
        .addToBackStack(null)
        .commit()
}