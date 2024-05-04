package com.nhathuy.mviexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.nhathuy.mviexample.data.api.ApiHelperImpl
import com.nhathuy.mviexample.data.api.RetrofitBuilder
import com.nhathuy.mviexample.data.model.User
import com.nhathuy.mviexample.mvi.ViewModelFactory
import com.nhathuy.mviexample.ui.main.adapter.MainAdapter
import com.nhathuy.mviexample.ui.main.intent.MainIntent
import com.nhathuy.mviexample.ui.main.viewmodel.MainViewModel
import com.nhathuy.mviexample.ui.main.viewstate.MainState
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {


    private lateinit var mainViewModel: MainViewModel
    private var adapter= MainAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpUI()
        setupViewModel()
        observerViewModel()
        setupClicks()
    }

    private fun setupClicks() {
        btn_showUser.setOnClickListener {
            lifecycleScope.launch {
                mainViewModel.userIntent.send(MainIntent.FetchUser)
            }
        }
    }

    private fun observerViewModel() {
        lifecycleScope.launch {
            mainViewModel.state.collect{
                when(it){
                    is MainState.Idle -> {

                    }
                    is MainState.Loading ->{
                        btn_showUser.visibility= View.GONE
                        progressbar.visibility=View.VISIBLE
                    }
                    is MainState.Users ->{
                        progressbar.visibility=View.GONE
                        btn_showUser.visibility=View.GONE
                        showUserList(it.user)
                    }
                    is MainState.Error ->{
                        progressbar.visibility=View.GONE
                        btn_showUser.visibility=View.INVISIBLE
                        Toast.makeText(this@MainActivity,it.error,Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun showUserList(user: List<User>) {
        recylerView.visibility=View.VISIBLE
        user.let {
            listOfUser -> listOfUser.let {
                adapter.addData(it)
        }
        }
        adapter.notifyDataSetChanged()
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                ApiHelperImpl(
                    RetrofitBuilder.apiServer
                )
            )
        ).get(MainViewModel::class.java)
    }


    private fun setUpUI() {
        recylerView.layoutManager=LinearLayoutManager(this)
        recylerView.run {
            addItemDecoration(DividerItemDecoration(recylerView.context,(recylerView.layoutManager as LinearLayoutManager).orientation))
        }
        recylerView.adapter=adapter
    }
}