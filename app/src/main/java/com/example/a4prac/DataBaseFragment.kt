package com.example.a4prac

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.a4prac.databinding.FragmentDataBaseBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DataBaseFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DatabaseAdapter
    private lateinit var db: AppDataBase
    private lateinit var postsDao: PostsDAO
    private lateinit var binding: FragmentDataBaseBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        db = AppDataBase.getDatabase(requireContext())
        postsDao = db.postsDao()
        // Inflate the layout for this fragment
        binding = FragmentDataBaseBinding.inflate(inflater, container, false)
        postsDao.getAllPosts().observe(viewLifecycleOwner) { allPosts ->
            if (allPosts.isNotEmpty()) {
                val firstPost = allPosts[0] // Получаем первый пост
                binding.bodyTextView.text = firstPost.body
            } else {
                binding.bodyTextView.text = "Нет доступных записей"
            }
        }
        return binding.root
    }
}