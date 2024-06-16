package com.altindas.expandable_fab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.altindas.expandable_fab.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val rotateOpen: Animation by lazy { AnimationUtils.loadAnimation(this,R.anim.rotate_open_anim) }
    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(this,R.anim.rotate_close_anim) }
    private val fromBottom: Animation by lazy { AnimationUtils.loadAnimation(this,R.anim.from_bottom_anim) }
    private val toBottom: Animation by lazy { AnimationUtils.loadAnimation(this,R.anim.to_bottom_anim) }


    private lateinit var binding: ActivityMainBinding




    private var clicked =false
    private var number=0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        binding.addBtn.setOnClickListener {
            onAddButtonClicked()

        }
        binding.editBtn.setOnClickListener {
            Toast.makeText(this,"edit",Toast.LENGTH_SHORT).show()

        }
        binding.imageBtn.setOnClickListener {
            Toast.makeText(this,"img",Toast.LENGTH_SHORT).show()
        }

        binding.refreshLayout.setOnRefreshListener {
            number++
            binding.textView.setText(number.toString())
            binding.refreshLayout.isRefreshing=false
        }

    }
    private fun onAddButtonClicked(){
        setVisibility(clicked)
        setAnimation(clicked)
        clicked=!clicked
    }
    private fun setVisibility(clicked:Boolean){
        if(!clicked){
            binding.editBtn.visibility= View.VISIBLE
            binding.imageBtn.visibility=View.VISIBLE
        }else{
            binding.editBtn.visibility= View.INVISIBLE
            binding.imageBtn.visibility=View.INVISIBLE
        }

    }
    private fun setAnimation(clicked: Boolean){
        if(!clicked){
            binding.editBtn.startAnimation(fromBottom)
            binding.imageBtn.startAnimation(fromBottom)
            binding.addBtn.startAnimation(rotateOpen)
        }else{
            binding.editBtn.startAnimation(toBottom)
            binding.imageBtn.startAnimation(toBottom)
            binding.addBtn.startAnimation(rotateClose)
        }

    }


}