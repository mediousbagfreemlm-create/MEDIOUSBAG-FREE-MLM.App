package com.mediousbag.mlm.ui.wallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mediousbag.mlm.R
import com.mediousbag.mlm.api.ApiService
import com.mediousbag.mlm.api.RetrofitClient
import com.mediousbag.mlm.databinding.FragmentWalletBinding
import com.mediousbag.mlm.models.WalletResponse
import com.mediousbag.mlm.models.WithdrawalRequest
import com.mediousbag.mlm.models.WithdrawalResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WalletFragment : Fragment() {

    private var _binding: FragmentWalletBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWalletBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Dummy token, replace with real auth logic
        val token = "dummy_token"
        loadWalletData(token)

        binding.withdrawButton.setOnClickListener {
            val amount = binding.amountEditText.text.toString().toDoubleOrNull() ?: 0.0
            val bankDetails = binding.bankDetailsEditText.text.toString()
            if (amount > 0 && bankDetails.isNotEmpty()) {
                requestWithdrawal(token, amount, bankDetails)
            } else {
                Toast.makeText(context, "Please enter valid amount and bank details", Toast.LENGTH_SHORT).show()
            }
        }

        return root
    }

    private fun loadWalletData(token: String) {
        val call = RetrofitClient.instance.getWallet(token)
        call.enqueue(object : Callback<WalletResponse> {
            override fun onResponse(call: Call<WalletResponse>, response: Response<WalletResponse>) {
                if (response.isSuccessful) {
                    val wallet = response.body()
                    binding.balanceText.text = "Balance: ₹${wallet?.balance ?: 0.0}"
                } else {
                    Toast.makeText(context, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<WalletResponse>, t: Throwable) {
                Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun requestWithdrawal(token: String, amount: Double, bankDetails: String) {
        val request = WithdrawalRequest(amount, bankDetails)
        val call = RetrofitClient.instance.requestWithdrawal(token, request)
        call.enqueue(object : Callback<WithdrawalResponse> {
            override fun onResponse(call: Call<WithdrawalResponse>, response: Response<WithdrawalResponse>) {
                if (response.isSuccessful) {
                    val withdrawalResponse = response.body()
                    if (withdrawalResponse?.success == true) {
                        Toast.makeText(context, "Withdrawal Requested", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<WithdrawalResponse>, t: Throwable) {
                Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}￼Enter
