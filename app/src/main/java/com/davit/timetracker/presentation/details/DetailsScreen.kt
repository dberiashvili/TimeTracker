package com.davit.timetracker.presentation.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.davit.timetracker.databinding.FragmentDetailsScreenBinding
import com.davit.timetracker.domain.models.Task
import com.davit.timetracker.presentation.utils.bag
import com.davit.timetracker.presentation.utils.untilDestroy
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

@AndroidEntryPoint
class DetailsScreen : Fragment() {
    private val viewModel: DetailsVM by viewModels()
    private lateinit var binding: FragmentDetailsScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsScreenBinding.inflate(layoutInflater)

        binding.addTrackerBtn.setOnClickListener {
            if (viewModel.checkInputs(binding.nameET, binding.descriptionET, binding.timeET)) {
                val task = Task(
                    task = binding.nameET.text.toString(),
                    description = binding.descriptionET.text.toString(),
                    time = binding.timeET.text.toString()
                )
                viewModel.saveTask(task).subscribe().untilDestroy()

                findNavController().navigate(
                    DetailsScreenDirections.actionDetailsScreenToHomeScreen()
                )
            }
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        bag.clear()
    }

}