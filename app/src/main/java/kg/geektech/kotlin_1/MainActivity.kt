package kg.geektech.kotlin_1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import kg.geektech.kotlin_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK && result.data != null) {
            val intent = result.data?.getStringExtra(Constants.KEY)
            binding.etEditText.setText(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.etEditText.setText(intent.getStringExtra(Constants.KEY)) //сетит текст из второго активити
        btnListenerOne()
    }

    private fun btnListenerOne() {
        binding.btnSend.setOnClickListener {
            if (binding.etEditText.text.isEmpty()) {
                Toast.makeText(applicationContext, "Введите текст", Toast.LENGTH_SHORT).show()
            } else {
                val init = Intent(this, SecondActivity::class.java)
                init.putExtra(Constants.KEY, binding.etEditText.text.toString())
                resultLauncher.launch(init)
            }
        }
    }
}
