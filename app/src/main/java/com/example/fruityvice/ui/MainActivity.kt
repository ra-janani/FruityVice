package com.example.fruityvice.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.fragment.app.DialogFragment
import com.example.fruityvice.R
import com.example.fruityvice.data.FruityViceItemModel
import com.example.fruityvice.data.NutritionsModel
import com.example.fruityvice.databinding.ActivityMainBinding
import com.example.fruityvice.databinding.FragmentDetailsBinding
import com.example.myrv.FruitAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),FruitAdapter.ItemListener {

    private lateinit var binding: ActivityMainBinding

    private val fruitsAdapter: FruitAdapter by lazy {
        FruitAdapter(this)
    }
    private val fruitsViewModel by viewModels<FruitsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fruitsViewModel.let {vm->
            vm.getFruitsData()
            vm.fruits.observe(this) {
                it?.let{
                    setupUI(it)

                }
            }
        }

    }
    private fun setupUI(fruits: ArrayList<FruityViceItemModel>) {
        binding.rvFruits.adapter = fruitsAdapter
        fruitsAdapter.submitList(fruits)
    }

    override fun itemListenerFruitDetails(currentItem: FruityViceItemModel) {

        showDetailsBottomSheet(currentItem)
    }

    private fun showDetailsBottomSheet(fruit: FruityViceItemModel) {

        val bottomSheetDialog =
            BottomSheetDialog(this, DialogFragment.STYLE_NO_FRAME)
        val bindingBottomSheet =
            FragmentDetailsBinding.inflate(LayoutInflater.from(this))
        bottomSheetDialog.apply {
            setContentView(bindingBottomSheet.root)
            setCanceledOnTouchOutside(true)
            behavior.apply {
                window?.setBackgroundDrawableResource(R.color.black_transparent)
                state = BottomSheetBehavior.STATE_EXPANDED
                skipCollapsed = true
            }
        }

        bindingBottomSheet.apply {


            tvFruitName.text= fruit.name

            val nutritions: NutritionsModel = fruit.nutritions!!
            tvCalories.text= nutritions.calories.toString()
            tvCarbohydrates.text=nutritions.carbohydrates.toString()
            tvFat.text=nutritions.fat.toString()
            tvProtein.text=nutritions.protein.toString()
            tvSugar.text=nutritions.sugar.toString()
        }


        bottomSheetDialog.show()
    }
}