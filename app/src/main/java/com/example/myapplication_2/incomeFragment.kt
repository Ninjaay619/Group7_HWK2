package com.example.myapplication_2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class incomeFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_income, container, false)

        val etAmount = view.findViewById<EditText>(R.id.editTextNumber)
        val spSource = view.findViewById<Spinner>(R.id.spSource)
        val etNote = view.findViewById<EditText>(R.id.editTextText)
        val btnSave = view.findViewById<Button>(R.id.btnSave)
        val imgDuck = view.findViewById<ImageView>(R.id.imgDuck)

        val sourceList = listOf("薪水", "紅包", "其他")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, sourceList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spSource.adapter = adapter

        btnSave.setOnClickListener {
            val amount = etAmount.text.toString()
            val source = spSource.selectedItem.toString()
            val note = etNote.text.toString()

            if (amount.isBlank()) {
                Toast.makeText(requireContext(), "請輸入金額", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "已記帳：$amount 元（$source）", Toast.LENGTH_SHORT).show()

                // 鴨鴨跳起來動畫 🐤
                imgDuck.animate()
                    .translationYBy(-100f)
                    .setDuration(200)
                    .withEndAction {
                        imgDuck.animate()
                            .translationYBy(100f)
                            .setDuration(200)
                            .start()
                    }.start()

                // 清除欄位
                etAmount.text.clear()
                etNote.text.clear()
                spSource.setSelection(0)
            }
        }

        return view
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        /**
         * 建立 Fragment 實例的工廠方法，可帶參數。
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            incomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
