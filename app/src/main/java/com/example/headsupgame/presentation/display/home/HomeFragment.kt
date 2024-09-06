package com.example.headsupgame.presentation.display.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.headsupgame.ParentAdapter
import com.example.headsupgame.R
import com.example.headsupgame.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ParentAdapter
    private val homeVm by lazy {
        ViewModelProvider(this)[HomeScreenViewModel()::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.parentRecycler
        homeVm.decksList.observe(viewLifecycleOwner) { decks ->
            adapter = ParentAdapter(decks, onClick = { selectedCategory ->
                val bundle = Bundle().apply {
                    putString("name",selectedCategory.title)
                }

                findNavController().navigate(R.id.action_homeFragment_to_gameFragment, bundle)
            })
            recyclerView.adapter = adapter
        }
    }


}



