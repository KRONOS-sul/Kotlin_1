package kg.geektech.kotlin_1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import kg.geektech.kotlin_1.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private var resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK && result.data != null) {
            val intent = result.data?.getStringExtra(Constants.KEY)
            binding.etEditTextTwo.setText(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.etEditTextTwo.setText(intent.getStringExtra(Constants.KEY)) //сетит текст из мейн активити
        btnListenerTwo()
    }

    private fun btnListenerTwo() {
        binding.btnBack.setOnClickListener {
            if (binding.etEditTextTwo.text.isEmpty()) {
                Toast.makeText(applicationContext, "Введите текст", Toast.LENGTH_SHORT).show()
            } else {
                val init = Intent(this, MainActivity::class.java)
                init.putExtra(Constants.KEY, binding.etEditTextTwo.text.toString())
                resultLauncher.launch(init)
            }
        }
    }
}