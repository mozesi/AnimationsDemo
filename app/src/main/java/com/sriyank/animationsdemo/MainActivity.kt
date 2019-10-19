package com.sriyank.animationsdemo

import android.animation.*
import android.animation.Animator.AnimatorListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewPropertyAnimator
import android.view.animation.BounceInterpolator
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.sriyank.animationsdemo.databinding.ActivityMainBinding

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AnimatorListener {

    private lateinit var bindingObj: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindingObj = DataBindingUtil.setContentView(this,R.layout.activity_main)
        bindingObj.rotateButton.setOnClickListener{rotateAnimation()}
        targetImage.setOnClickListener{vieAnimate()}


    }
    fun fromCode(){
        val rootSet  = AnimatorSet()
        val childSet = AnimatorSet()
        val flip :ObjectAnimator = ObjectAnimator.ofFloat(targetImage,"rotationX",0.0f,360.0f)
        flip.duration=500
        val scaleX :ObjectAnimator = ObjectAnimator.ofFloat(targetImage,"scaleX",0.0f,1.5f)
        scaleX.duration=500
        val scaleY :ObjectAnimator = ObjectAnimator.ofFloat(targetImage,"scaleY",0.0f,1.5f)
        scaleY.duration=500

        rootSet.playSequentially(flip,childSet)
        childSet.playSequentially(scaleX,scaleY)
        rootSet.start()
    }
    fun vieAnimate(){

        val vpa :ViewPropertyAnimator =targetImage.animate()
        vpa.apply {
            duration=500
            rotationX(360.0f)
            scaleX(1.5f)
            scaleY(1.5f)
            interpolator= BounceInterpolator()
            start()
        }
    }
    fun rotateAnimation() {
        val rotateAnimator = AnimatorInflater.loadAnimator(this, R.animator.rotate)
        rotateAnimator?.apply {
            setTarget(targetImage)
            addListener(this@MainActivity)
            start()
        }
    }

    fun scaleAnimation(view: View) {

        val scaleAnimator = AnimatorInflater.loadAnimator(this, R.animator.scale)
        scaleAnimator?.apply {
            setTarget(targetImage)
            addListener(this@MainActivity)
            start()
        }
    }

    fun translateAnimation(view: View) {

        val translateAnimator = ObjectAnimator.ofFloat(targetImage,"Alpha",1.0f,0.0f)
        translateAnimator.apply {
            duration=1500
            repeatMode= ValueAnimator.REVERSE
            repeatCount=1
            addListener(this@MainActivity)
            start()
        }
    }

    fun fadeAnimation(view: View) {

        val fadeAnimator = AnimatorInflater.loadAnimator(this, R.animator.alpha)
        fadeAnimator.apply {
            setTarget(targetImage)
            addListener(this@MainActivity)
            start()
        }
    }

    fun setType() {

        val fadeAnimator = AnimatorInflater.loadAnimator(this, R.animator.set)
        fadeAnimator.apply {
            setTarget(targetImage)
            addListener(this@MainActivity)
            start()
        }
    }
    override fun onAnimationRepeat(animation: Animator?) {
        Toast.makeText(this,"Animation repeated",Toast.LENGTH_LONG).show()
    }

    override fun onAnimationEnd(animation: Animator?) {
        Toast.makeText(this,"Animation ended",Toast.LENGTH_LONG).show()
    }

    override fun onAnimationCancel(animation: Animator?) {
        Toast.makeText(this,"Animation canceled",Toast.LENGTH_LONG).show()
    }

    override fun onAnimationStart(animation: Animator?) {
        Toast.makeText(this,"Animation started",Toast.LENGTH_LONG).show()
    }
}
