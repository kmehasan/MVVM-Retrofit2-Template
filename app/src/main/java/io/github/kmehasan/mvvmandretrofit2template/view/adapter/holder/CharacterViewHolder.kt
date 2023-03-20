package io.github.kmehasan.mvvmandretrofit2template.view.adapter.holder

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import coil.Coil
import coil.load
import io.github.kmehasan.mvvmandretrofit2template.R
import io.github.kmehasan.mvvmandretrofit2template.databinding.ItemRowBinding
import io.github.kmehasan.mvvmandretrofit2template.response.charecter_model.Result

class CharacterViewHolder(val view: ItemRowBinding): RecyclerView.ViewHolder(view.root) {
    fun bind(result:Result){
        view.name.text = result.name
        view.image.load(result.image)
        view.location.text = result.location.name

        view.root.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable("character",result)
            Navigation.findNavController(view.root).navigate(R.id.action_charectersFragment_to_characterDetailsFragment,bundle)
        }
    }
}