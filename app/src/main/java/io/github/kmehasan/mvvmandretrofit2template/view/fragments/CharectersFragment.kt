package io.github.kmehasan.mvvmandretrofit2template.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import io.github.kmehasan.mvvmandretrofit2template.R
import io.github.kmehasan.mvvmandretrofit2template.databinding.FragmentCharectersBinding
import io.github.kmehasan.mvvmandretrofit2template.view.adapter.CharacterListAdapter
import io.github.kmehasan.mvvmandretrofit2template.view_model.MainScreenViewModel
import io.github.kmehasan.mvvmandretrofit2template.response.charecter_model.Result
import okhttp3.internal.notify

class CharectersFragment : Fragment() {
    lateinit var mainScreenViewModel: MainScreenViewModel
    lateinit var characterListAdapter:CharacterListAdapter
    lateinit var binding: FragmentCharectersBinding
    private val resultList = mutableListOf<Result>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mainScreenViewModel = ViewModelProvider(this).get()
        characterListAdapter = CharacterListAdapter(resultList)
        binding = FragmentCharectersBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.RecyclerView.apply {
            adapter = characterListAdapter
        }
        mainScreenViewModel.isLoading.observe(viewLifecycleOwner) {
            binding.progressbar.visibility = if (it) View.VISIBLE else View.GONE
        }
        if(resultList.isEmpty()) mainScreenViewModel.getCharacterList().observe(viewLifecycleOwner, Observer {
            resultList.addAll(it.results)
            characterListAdapter.notifyItemRangeInserted(0, it.results.size)
        })
    }

}