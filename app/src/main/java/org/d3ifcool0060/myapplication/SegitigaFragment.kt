package org.d3ifcool0060.myapplication


import android.content.ActivityNotFoundException
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import kotlinx.android.synthetic.main.fragment_segitiga.*
import org.d3ifcool0060.myapplication.databinding.FragmentSegitigaBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SegitigaSikuFragment : Fragment() {

    private var luassgt = 0.0
    private var kelsegt = 0.0
    private var simir = 0.0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentSegitigaBinding>(inflater,
            R.layout.fragment_segitiga, container, false)

        if (savedInstanceState != null){
            luassgt = savedInstanceState.getDouble("luas")
            kelsegt = savedInstanceState.getDouble("kel")

        }
        binding.apply {
            bHitung2.setOnClickListener {
                val a = ed_alas.text.toString()
                val t = ed_tinggi.text.toString()

                if (a.isEmpty() && t.isEmpty()){
                    Toast.makeText(context,"Isi Field Kosong !", Toast.LENGTH_SHORT).show()
                }
                luassgt = (a.toDouble() * t.toDouble())/2.0
                txt_luassgt.text = luassgt.toString()

                simir = Math.sqrt(Math.pow(a.toDouble(),2.0) + Math.pow(t.toDouble(),2.0))

                kelsegt = simir+a.toDouble()+t.toDouble()

                txt_kelsgt.text = kelsegt.toString()
            }

            bReset2.setOnClickListener {
                txt_luassgt.text = "0.0"
                txt_kelsgt.text = "0.0"

                ed_alas.text.clear()
                ed_tinggi.text.clear()
            }
            bShare2.setOnClickListener {
                var shareIntent = ShareCompat.IntentBuilder.from(requireActivity())
                    .setText(getString(R.string.share_data,luassgt.toInt(),kelsegt.toInt()))
                    .setType("text/plain")
                    .intent
                try {
                    startActivity(shareIntent)
                }catch (e: ActivityNotFoundException){
                    Toast.makeText(context,"Kosong !", Toast.LENGTH_SHORT).show()
                }
            }

        }

        binding.luas2 = luassgt
        binding.kel2 = kelsegt
        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putDouble("luas", luassgt)
        outState.putDouble("kel", kelsegt)
        super.onSaveInstanceState(outState)
    }

}
