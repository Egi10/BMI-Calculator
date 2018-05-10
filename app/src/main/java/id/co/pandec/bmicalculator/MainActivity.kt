package id.co.pandec.bmicalculator

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat

class MainActivity : AppCompatActivity(){
    var JenisKelamin = arrayOf("" , "Laki-Laki" , "Perempuan")
    var spinner: Spinner? = null
    var jenisKelamin : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinner = findViewById(R.id.spJenisKelamin)

        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, JenisKelamin)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner?.setAdapter(aa)

        spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                jenisKelamin = JenisKelamin[position]
            }
        }

        btnKalkulasi.setOnClickListener {
            var beratBadan : Float
            var tinggi : Float
            var tinggiM : Float
            var hasil : Float

            if (etNamaLengkap.text.isEmpty() || etBeratBadan.text.isEmpty() || etTinggi.text.isEmpty()) {
                Toast.makeText(baseContext, "Tidak Boleh Ada Yang Kosong", Toast.LENGTH_SHORT).show()
            } else {
                val NamaLengkap = etNamaLengkap.text.toString()

                beratBadan = Integer.parseInt(etBeratBadan.text.toString()).toFloat()
                tinggi = Integer.parseInt(etTinggi.text.toString()).toFloat()

                tinggiM = tinggi / 100

                hasil = (beratBadan / (tinggiM * tinggiM))

                val decimalFormat = DecimalFormat("#.##")
                val hasilakhir = decimalFormat.format(hasil)

                val intent = Intent(baseContext, HasilActivity::class.java)
                intent.putExtra("NamaLengkap", NamaLengkap)
                intent.putExtra("JenisKelamin", jenisKelamin)
                intent.putExtra("Hasil", hasilakhir)
                startActivity(intent)
                finish()
            }
        }

        ivInfo.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setMessage(getString(R.string.bmi))
            alertDialog.setPositiveButton("Oke", DialogInterface.OnClickListener { dialog, which ->
                dialog.cancel()
            })
            val dialog = alertDialog.create()
            dialog.show()
        }

        ivExit.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("Peringatan")
            alertDialog.setMessage("Apakah Yakin Keluar Aplikasi ?")
            alertDialog.setPositiveButton("Iya", DialogInterface.OnClickListener { dialog, which ->
                val intent = Intent(Intent.ACTION_MAIN)
                intent.addCategory(Intent.CATEGORY_HOME)
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            })
            alertDialog.setNegativeButton("Tidak", DialogInterface.OnClickListener { dialog, which ->
                dialog.cancel()
            })
            val dialog = alertDialog.create()
            dialog.show()
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("Peringatan")
            alertDialog.setMessage("Apakah Yakin Keluar Aplikasi ?")
            alertDialog.setPositiveButton("Iya", DialogInterface.OnClickListener { dialog, which ->
                val intent = Intent(Intent.ACTION_MAIN)
                intent.addCategory(Intent.CATEGORY_HOME)
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            })
            alertDialog.setNegativeButton("Tidak", DialogInterface.OnClickListener { dialog, which ->
                dialog.cancel()
            })
            val dialog = alertDialog.create()
            dialog.show()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}
