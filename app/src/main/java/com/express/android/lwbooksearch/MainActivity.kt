package com.express.android.lwbooksearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.express.android.lwbooksearch.databinding.ActivityMainBinding
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.express.android.lwbooksearch.api.ApiManager
import com.express.android.lwbooksearch.ui.BookListAdapter
import com.express.android.lwbooksearch.model.BookListModel
import com.express.android.lwbooksearch.repository.BookRepository
import com.express.android.lwbooksearch.ui.viewmodel.BookViewModelFactory
import com.express.android.lwbooksearch.ui.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var viewModel:  MainActivityViewModel
    lateinit var bookListAdapter: BookListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        initSearchBox()
    }

    fun initSearchBox() {
        binding.inputBookName.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                loadAPIData(s.toString())
            }
        })
    }



    fun loadAPIData(input: String) {
        viewModel = ViewModelProvider(
            this,
            BookViewModelFactory(BookRepository(ApiManager()))
        ).get(MainActivityViewModel::class.java)

        viewModel.bookList.observe(this, Observer {
            if(it != null) {
                bookListAdapter.bookListData = it.items
                bookListAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "Error in fetching data", Toast.LENGTH_SHORT).show()
            }
        })
    }
}