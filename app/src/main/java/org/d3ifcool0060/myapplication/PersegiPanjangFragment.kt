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
import kotlinx.android.synthetic.main.fragment_persegi_panjang.*
import org.d3ifcool0060.myapplication.databinding.FragmentPersegiPanjangBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class PersegiPanjangFragment : Fragment() {

    private var luaspp = 0
    private var kelpp = 0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentPersegiPanjangBinding>(inflater,
            R.layout.fragment_persegi_panjang, container, false)

        if (savedInstanceState != null){
            luaspp = savedInstanceState.getInt("luasnya")
            kelpp = savedInstanceState.getInt("kelnya")
        }

        binding.apply {
            bHitung1.setOnClickListener {
                var p = ed_panjang.text.toString()
                var l = ed_lebar.text.toString()

                if (p.isEmpty() && l.isEmpty()){
                    Toast.makeText(context,"Isi Field Kosong !", Toast.LENGTH_SHORT).show()
                }

                luaspp = p.toInt() * l.toInt()

                txt_luaspp.text = luaspp.toString()

                kelpp = 2 * (p.toInt() + l.toInt())

                txt_kelpp.text = kelpp.toString()
            }

            bReset1.setOnClickListener {
                txt_luaspp.text = "0"
                txt_kelpp.text = "0"
                ed_panjang.text.clear()
                ed_lebar.text.clear()
            }

            bShare1.setOnClickListener {
                var shareIntent = ShareCompat.IntentBuilder.from(requireActivity())
                    .setText(getString(R.string.share_data,luaspp,kelpp))
                    .setType("text/plain")
                    .intent
                try {
                    startActivity(shareIntent)
                }catch (e: ActivityNotFoundException){
                    Toast.makeText(context,"Kosong !", Toast.LENGTH_SHORT).show()
                }
            }
        }
        binding.luas1 = luaspp
        binding.kel1 = kelpp
        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("luasnya", luaspp)
        outState.putInt("kelnya", kelpp)
        super.onSaveInstanceState(outState)
    }

}
