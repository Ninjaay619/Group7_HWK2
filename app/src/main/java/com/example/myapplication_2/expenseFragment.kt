package com.example.myapplication_2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class expenseFragment : Fragment() {

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
        val view = inflater.inflate(R.layout.fragment_expense, container, false)

        // 元件綁定
        val etAmount = view.findViewById<EditText>(R.id.editTextNumber)
        val spSource = view.findViewById<Spinner>(R.id.spSource)
        val etNote = view.findViewById<EditText>(R.id.editTextText)
        val btnSave = view.findViewById<Button>(R.id.btnSave)
        val imgDuck = view.findViewById<ImageView>(R.id.imgDuck)

        // Spinner 資料（支出分類）
        val categoryList = listOf("餐飲", "交通", "娛樂", "購物", "其他")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, categoryList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spSource.adapter = adapter

        // 按鈕點擊事件
        btnSave.setOnClickListener {
            val amount = etAmount.text.toString()
            val category = spSource.selectedItem.toString()
            val note = etNote.text.toString()

            if (amount.isBlank()) {
                Toast.makeText(requireContext(), "請輸入金額", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "支出：$amount 元（$category）", Toast.LENGTH_SHORT).show()

                // 鴨鴨左右搖擺動畫 🐤↔️
                imgDuck.animate()
                    .translationXBy(-30f) // 向左
                    .setDuration(100)
                    .withEndAction {
                        imgDuck.animate()
                            .translationXBy(60f) // 再向右
                            .setDuration(100)
                            .withEndAction {
                                imgDuck.animate()
                                    .translationXBy(-30f) // 回到原位
                                    .setDuration(100)
                                    .start()
                            }
                            .start()
                    }
                    .start()


                // 清空欄位
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

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            expenseFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
