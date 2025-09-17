package com.mediousbag.mlm.ui.withdrawals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mediousbag.mlm.R
import com.mediousbag.mlm.api.ApiService
import com.mediousbag.mlm.api.RetrofitClient
import com.mediousbag.mlm.databinding.FragmentWithdrawalsBinding
import com.mediousbag.mlm.models.WithdrawalsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WithdrawalsFragment : Fragment() {

    private var _binding: FragmentWithdrawalsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWithdrawalsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Dummy token, replace with real auth logic
        val token = "dummy_token"
        loadWithdrawals(token)

        return root
    }

    private fun loadWithdrawals(token: String) {
        val call = RetrofitClient.instance.getMyWithdrawals(token)
        call.enqueue(object : Callback<WithdrawalsResponse> {
            override fun onResponse(call: Call<WithdrawalsResponse>, response: Response<WithdrawalsResponse>) {
                if (response.isSuccessful) {
                    val withdrawals = response.body()
                    // Update UI with withdrawals (e.g., ListView or RecyclerView)
                    // Placeholder text for now
                    binding.withdrawalsText.text = "Withdrawals: ${withdrawals?.withdrawals?.joinToString { it.amount.toString() } ?: "N/A"}"
                } else {
                    Toast.makeText(context, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<WithdrawalsResponse>, t: Throwable) {
                Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}￼Enter
