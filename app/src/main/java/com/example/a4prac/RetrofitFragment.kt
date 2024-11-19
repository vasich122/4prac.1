package com.example.a4prac

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.a4prac.databinding.FragmentRetrofitBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitFragment : Fragment() {
    private lateinit var binding: FragmentRetrofitBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRetrofitBinding.inflate(inflater, container, false)
        binding.toDtbBtn.setOnClickListener {
            findNavController().navigate(R.id.action_retrofitFragment_to_dataBaseFragment)
        }
        val retrofit=Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val productApi=retrofit.create(MainApi::class.java)
        val db = AppDataBase.getDatabase(requireContext())
        val postsDao = db.postsDao()
        binding.showDataBtn.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                val posts = productApi.getPostById(1)
                val NewPosts = posts
                postsDao.insertPosts(NewPosts)
                withContext(Dispatchers.Main){
                    binding.showData.text = posts.title
                }
            }
        }
        return binding.root
    }
}