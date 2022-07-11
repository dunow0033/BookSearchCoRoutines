package com.express.android.lwbooksearch.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.express.android.lwbooksearch.api.ApiManager
import com.express.android.lwbooksearch.databinding.FragmentBooksearchBinding
import com.express.android.lwbooksearch.model.BookListModel
import com.express.android.lwbooksearch.repository.BookRepository
import com.express.android.lwbooksearch.ui.BookListAdapter
import com.express.android.lwbooksearch.ui.viewmodel.BookViewModelFactory
import com.express.android.lwbooksearch.ui.viewmodel.MainActivityViewModel

class BookSearchFragment : Fragment() {

    private var _binding: FragmentBooksearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var bookListAdapter: BookListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBooksearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            BookViewModelFactory(BookRepository(ApiManager()))
        ).get(MainActivityViewModel::class.java)

        initRecyclerView()

        viewModel.bookList.observe(viewLifecycleOwner, Observer {
            bookListAdapter.differ.submitList(it)
        })
    }

    fun initRecyclerView() {
        binding.recyclerView.apply {
            bookListAdapter = BookListAdapter()
            adapter = bookListAdapter
            layoutManager = LinearLayoutManager(requireContext())
            val decoration = DividerItemDecoration(requireContext(), StaggeredGridLayoutManager.VERTICAL)
            addItemDecoration(decoration)
        }
    }

//    fun loadAPIData(input: String) {
//        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
//        viewModel.getBookListObserver().observe(this, Observer {
//            if(it != null) {
//                bookListAdapter.bookListData
//            }
//        })
//    }

}