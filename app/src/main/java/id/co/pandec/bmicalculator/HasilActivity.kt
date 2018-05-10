package id.co.pandec.bmicalculator

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import kotlinx.android.synthetic.main.activity_hasil.*

class HasilActivity : AppCompatActivity() {
    var Hasil : Float = 0.0f
    var JenisKelamin : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hasil)

        val inten = intent

        inten?.let {
            tvNamaLengkap.text = it.getStringExtra("NamaLengkap")
            tvJenisKelamin.text = it.getStringExtra("JenisKelamin")
            tvHasil.text = "Perhitungan BMI : " + it.getStringExtra("Hasil")
            tvhasil.text = it.getStringExtra("Hasil")
        }

        Hasil = tvhasil.text.toString().toFloat()
        JenisKelamin = tvJenisKelamin.text.toString()

        if (JenisKelamin.equals("Laki-Laki") && Hasil < 18.0) {
            ivImage.setImageResource(R.drawable.layer_1)
            tvDetail.text = "Kurus"
        } else if (JenisKelamin.equals("Laki-Laki") && Hasil < 25.0) {
            ivImage.setImageResource(R.drawable.layer_2)
            tvDetail.text = "Normal"
        } else if (JenisKelamin.equals("Laki-Laki") && Hasil < 27.0) {
            ivImage.setImageResource(R.drawable.layer_3)
            tvDetail.text = "Kegemukan"
        } else if (JenisKelamin.equals("Laki-Laki") && Hasil > 27.0) {
            ivImage.setImageResource(R.drawable.layer_4)
            tvDetail.text = "Obesitas"
        } else if (JenisKelamin.equals("Perempuan") && Hasil < 17.0) {
            ivImage.setImageResource(R.drawable.layer_1)
            tvDetail.text = "Kurus"
        } else if (JenisKelamin.equals("Perempuan") && Hasil < 23.0) {
            ivImage.setImageResource(R.drawable.layer_2)
            tvDetail.text = "Normal"
        } else if (JenisKelamin.equals("Perempuan") && Hasil < 27.0) {
            ivImage.setImageResource(R.drawable.layer_3)
            tvDetail.text = "Kegemukan"
        } else if (JenisKelamin.equals("Perempuan") && Hasil > 27.0) {
            ivImage.setImageResource(R.drawable.layer_4)
            tvDetail.text = "Obesitas"
        }

        ivBack.setOnClickListener {
            val intent = Intent(baseContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        ivShare.setOnClickListener {
            val intent = Intent()
            intent.setAction(Intent.ACTION_SEND)
            intent.setType("text/plain")
            intent.putExtra(Intent.EXTRA_TEXT, "Saya Menggunakan BMI Calculator Dan Berat Badan Saya Dinyatakan "+tvDetail.text.toString())
            startActivity(Intent.createChooser(intent, "Share link using"))
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            val intent = Intent(baseContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        return super.onKeyDown(keyCode, event)
    }
}
