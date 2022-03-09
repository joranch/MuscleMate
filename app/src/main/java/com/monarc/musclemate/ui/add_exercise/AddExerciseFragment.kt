package com.monarc.musclemate.ui.add_exercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.chip.Chip
import com.monarc.musclemate.data.enums.ApiExerciseCategory
import com.monarc.musclemate.databinding.FragmentAddExerciseBinding
import com.monarc.musclemate.domain.models.Muscle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddExerciseFragment : Fragment() {

    private val viewModel: AddExerciseViewModel by viewModels()
    private var _binding: FragmentAddExerciseBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddExerciseBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = viewModel

        addChips()
    }

    private fun addChips() {
//        val chipGroup = binding.chipGroup
//        viewModel.muscleList.forEach { muscle ->
//            chipGroup.addView(createChip(muscle.name))
//        }

        val chipGroupCategory = binding.chipGroupCategory
        viewModel.exerciseCategoryList.forEach { category ->
            chipGroupCategory.addView(createChip(category.name))
        }
    }

    private fun createChip(labelText: String) : View {
        val chip = Chip(requireContext())
        return chip.apply {
            id = ViewCompat.generateViewId()
            text = labelText
            isCheckable = true
            layoutParams = ViewGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}