package com.altindas.expandable_fab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val rotateOpen: Animation by lazy { AnimationUtils.loadAnimation(this,R.anim.rotate_open_anim) }
    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(this,R.anim.rotate_close_anim) }
    private val fromBottom: Animation by lazy { AnimationUtils.loadAnimation(this,R.anim.from_bottom_anim) }
    private val toBottom: Animation by lazy { AnimationUtils.loadAnimation(this,R.anim.to_bottom_anim) }

    lateinit var add_btn:FloatingActionButton
    lateinit var edit_btn:FloatingActionButton
    lateinit var image_btn:FloatingActionButton

    lateinit var refreshLayout:SwipeRefreshLayout
    lateinit var textView: TextView


    private var clicked =false
    private var number=0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        add_btn=findViewById<FloatingActionButton>(R.id.add_btn)
        edit_btn=findViewById<FloatingActionButton>(R.id.edit_btn)
        image_btn=findViewById<FloatingActionButton>(R.id.image_btn)

        textView=findViewById<TextView>(R.id.textView)
        refreshLayout=findViewById<SwipeRefreshLayout>(R.id.refreshLayout)


        add_btn.setOnClickListener {
            onAddButtonClicked()

        }
        edit_btn.setOnClickListener {
            Toast.makeText(this,"edit",Toast.LENGTH_SHORT).show()

        }
        image_btn.setOnClickListener {
            Toast.makeText(this,"img",Toast.LENGTH_SHORT).show()
        }

        refreshLayout.setOnRefreshListener {
            number++
            textView.setText(number.toString())
            refreshLayout.isRefreshing=false
        }

    }
    private fun onAddButtonClicked(){
        setVisibility(clicked)
        setAnimation(clicked)
        clicked=!clicked
    }
    private fun setVisibility(clicked:Boolean){
        if(!clicked){
            edit_btn.visibility= View.VISIBLE
            image_btn.visibility=View.VISIBLE
        }else{
            edit_btn.visibility= View.INVISIBLE
            image_btn.visibility=View.INVISIBLE
        }

    }
    private fun setAnimation(clicked: Boolean){
        if(!clicked){
            edit_btn.startAnimation(fromBottom)
            image_btn.startAnimation(fromBottom)
            add_btn.startAnimation(rotateOpen)
        }else{
            edit_btn.startAnimation(toBottom)
            image_btn.startAnimation(toBottom)
            add_btn.startAnimation(rotateClose)
        }

    }


}