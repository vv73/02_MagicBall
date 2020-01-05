package study.android.magicball

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random


class MainActivity : AppCompatActivity(), ShakeDetector.OnShakeListener {
    var alreadyAsked = false;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        magicBall.setOnClickListener {
            answer()
        }
        val shakerDetector = ShakeDetector(this)
        shakerDetector.setOnShakeListener(this)

    }
    fun answer(){
        if (alreadyAsked) {
            val toast: Toast = Toast.makeText(this, R.string.notNow, Toast.LENGTH_LONG)
            toast.show()
            finish()
            return
        }
        alreadyAsked = true; //отвечаем первый раз, больше не будем
        // получаем доступ из ресурсов к массиву предсказаний
        val messages = resources.getStringArray(R.array.messages)
        val randomIndex: Int = Random.nextInt(messages.size)
        val msg = messages[randomIndex]
        val fadeInAnim = AnimationUtils.loadAnimation(this, android.R.anim.fade_in)
        message.startAnimation(fadeInAnim)
        val shakeAnim = AnimationUtils.loadAnimation(this, R.anim.shake)
        screen.startAnimation(shakeAnim);
        message.text = msg
    }

    override fun onShake() {
        answer();
    }
}
