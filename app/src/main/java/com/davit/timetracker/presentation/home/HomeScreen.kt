package com.davit.timetracker.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.davit.timetracker.R
import com.davit.timetracker.databinding.FragmentHomeScreenBinding
import com.davit.timetracker.presentation.utils.bag
import com.davit.timetracker.presentation.utils.share
import com.davit.timetracker.presentation.utils.showDialog
import com.davit.timetracker.presentation.utils.untilDestroy
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeScreen : Fragment() {
    private val viewModel: HomeVM by viewModels()
    lateinit var binding: FragmentHomeScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeScreenBinding.inflate(layoutInflater)
        val taskRVA = TaskRVA()
        binding.timeRV.apply {
            adapter = taskRVA
            layoutManager = LinearLayoutManager(context)
        }

        viewModel.getTasks().subscribe {
            taskRVA.submitList(it)
        }.untilDestroy()

        binding.addTrackerBtn.setOnClickListener {
            val action = HomeScreenDirections.actionHomeScreenToDetailsScreen()
            findNavController().navigate(action)
        }
        taskRVA.shareTask = {
            context?.share(it)
        }
        taskRVA.showTimer = { task ->
            var time = 0
            val alertDialog = showDialog(R.string.app_name, task.time)
                viewModel.startCountDown(task.time.toInt()).subscribe {
                    alertDialog.setMessage(it.toString())
                    time = it
                }.untilDestroy()


            alertDialog.setOnDismissListener {
                viewModel.updateTask(task.copy(time = time.toString())).subscribe().untilDestroy()
            }


        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        bag.clear()

    }

}