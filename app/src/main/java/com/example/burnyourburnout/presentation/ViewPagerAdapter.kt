package com.example.burnyourburnout.presentation

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.burnyourburnout.presentation.community.post.CommunityPostFragment
import com.example.burnyourburnout.presentation.lobby.LobbyFragment
import com.example.burnyourburnout.presentation.private_place.PrivatePlaceFragment
import com.example.burnyourburnout.presentation.try_something_new.TrySomethingNewFragment

class ViewPagerAdapter(private val mainActivity: MainActivity): FragmentStateAdapter(mainActivity) {

    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> {
                return LobbyFragment()
            }
            1 -> {
                return CommunityPostFragment()
            }
            2 -> {
                return TrySomethingNewFragment()
            }
            3 -> {
                return PrivatePlaceFragment()
            }
            else -> {
                return PrivatePlaceFragment()
            }
        }
    }
}