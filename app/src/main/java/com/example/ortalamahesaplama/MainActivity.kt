package com.example.ortalamahesaplama

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.yeni_ders_layout.view.*

class MainActivity : AppCompatActivity() {
    private val Dersler = arrayOf("Matematik","Türkçe","Edebiyat","Algoritma","Tarih")
    private var tumDersler:ArrayList<Dersler> = ArrayList(5)
    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var adapter=ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,Dersler)
        etDersAd.setAdapter(adapter)
        btnDersEkle.setOnClickListener {
            if (!etDersAd.text.isNullOrBlank()) {
                var inflater = LayoutInflater.from(this)
                //var inflater2 = layoutInflater
                //var inflater3=getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                //inflater3.inflate()

                var yeniDersView = inflater.inflate(R.layout.yeni_ders_layout, null)
                //statik alandan kullanıcının girdiği değerleri alalım

                yeniDersView.etYeniDersAd.setAdapter(adapter)

                btnOrtalamaHesaplama.visibility


                var dersAdi = etDersAd.text.toString()
                var dersKredi = spnDersKredi.selectedItem.toString()
                var dersHarf = spnDersNotu.selectedItem.toString()

                yeniDersView.etYeniDersAd.setText(dersAdi)
                yeniDersView.spnYeniDersKredi.setSelection(getSpinerIndex(spnDersKredi, dersKredi))
                yeniDersView.spnYeniDersNotu.setSelection(getSpinerIndex(spnDersNotu, dersHarf))

                yeniDersView.btnDersSil.setOnClickListener {
                    rootLayout.removeView(yeniDersView)

                    if (rootLayout.childCount == 0) {
                        btnOrtalamaHesaplama.visibility = View.INVISIBLE
                    } else btnOrtalamaHesaplama.visibility = View.VISIBLE
                }

                rootLayout.addView(yeniDersView)
                sifirla()
                btnOrtalamaHesaplama.visibility = View.VISIBLE
            }else{
                //Toast.makeText(this,"DERS ADI GİRİNİZ",Toast.LENGTH_LONG).show()
                FancyToast.makeText(this,"DERS ADI GİRİNİZ !",FancyToast.LENGTH_LONG, FancyToast.WARNING,false).show()
            }
        }
    }

    private fun sifirla() {
        etDersAd.setText("")
        spnDersKredi.setSelection(0)
        spnDersNotu.setSelection(0)
    }

    fun getSpinerIndex(spinner: Spinner, aranacakDeger:String):Int{
        var index=0
        for(i in 0..spinner.count){
            if (spinner.getItemAtPosition(i).toString()==aranacakDeger){
                index = i
                break
            }
        }
        return index
    }

    @SuppressLint("ShowToast")
    fun ortalamaHesaplama(view: View){
        var toplamNot=0.0
        var toplamKredi=0.0
        for (i in 0 until rootLayout.childCount){
            var tekSatir = rootLayout.getChildAt(i)
            var geciciDers=Dersler(
                tekSatir.etYeniDersAd.text.toString(),
                ((tekSatir.spnYeniDersKredi.selectedItemPosition)+1).toString(),
                tekSatir.spnYeniDersNotu.selectedItem.toString()
            )

            tumDersler.add(geciciDers)
        }

        for (oankiDers in tumDersler){
            //Log.e("DENEME",oankiDers.dersHarfNot)
            toplamNot+=harfiNoteCevir(oankiDers.dersHarfNot)*(oankiDers.dersKredi.toDouble())
            //Log.e("deneme",toplamNot.toString())
            toplamKredi+=oankiDers.dersKredi.toDouble()

        }
        //Toast.makeText(this,"ORTALAMA: "+(toplamNot/toplamKredi),Toast.LENGTH_SHORT).show()
        FancyToast.makeText(this,"ORTALAMA: "+(toplamNot/toplamKredi),FancyToast.LENGTH_LONG, FancyToast.SUCCESS,false).show()
        tumDersler.clear()
    }

    fun harfiNoteCevir(harfDegeri:String):Double{
        var deger = 0.0
        when(harfDegeri){
            "A" -> deger=5.0
            "B+" -> deger=4.5
            "B" -> deger=4.0
            "B-" -> deger=3.5
            "C+" -> deger=3.0
            "C" -> deger=2.5
            "C-" -> deger=2.0
            "D+" -> deger=1.5
            "D" -> deger=1.0
            "D-" -> deger=0.5
        }
        return deger
    }

}
