package com.example.androidadvanced

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidadvanced.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // View 动画
//        binding.customView.animation = AnimationUtils.loadAnimation(this, R.anim.translate)

        // 属性动画
//        ObjectAnimator.ofFloat(binding.customView, "alpha", 1f, 0.5f)
//            .setDuration(1000)
//            .start()
//        ObjectAnimator.ofFloat(binding.customView, "translationX", 0f, 300f)
//            .setDuration(1000)
//            .start()
        // 使用 Scroller 滑动视图
//        binding.customView.smoothScrollTo(-400, -500)

        // 属性动画组合
        binding.customView.let {
            val animator1 = ObjectAnimator.ofFloat(it,"translationX", 0.0f, 200.0f, 0f)
            val animator2 =ObjectAnimator.ofFloat (it,"scaleX", 1.0f, 2.0f)
            val animator3 = ObjectAnimator.ofFloat (it, "rotationX", 0.0f, 90.0f, 0.0F)
            val animator4 = ObjectAnimator.ofFloat(it, "alpha", 1f, 0.5f)
            AnimatorSet().apply {
                duration = 1000
                // animator1、animator2 同时进行，并且它们在 animator3 之后，在 animator4 之前进行
                // 动画顺序：animator3 -> animator1 & animator2 -> animator4
                play(animator1).with(animator2).after(animator3).before(animator4)
                // 使这些动画同时进行
//                playTogether(animator1, animator2, animator3, animator4)
                start()
            }
        }




    }
}