package com.mediousbag.mlm.ui.investments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mediousbag.mlm.R
import com.mediousbag.mlm.api.ApiService
import com.mediousbag.mlm.api.RetrofitClient
import com.mediousbag.mlm.databinding.FragmentInvestmentsBinding
import com.mediousbag.mlm.models.InvestmentsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InvestmentsFragment : Fragment() {

    private var _binding: FragmentInvestmentsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInvestmentsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Dummy token, replace with real auth logic
        val token = "dummy_token"
        loadInvestments(token)

        return root
    }

    private fun loadInvestments(token: String) {
        val call = RetrofitClient.instance.getMyInvestments(token)
        call.enqueue(object : Callback<InvestmentsResponse> {
            override fun onResponse(call: Call<InvestmentsResponse>, response: Response<InvestmentsResponse>) {
                if (response.isSuccessful) {
                    val investments = response.body()
                    // Update UI with investments (e.g., ListView or RecyclerView)
                    // Placeholder text for now
                    binding.investmentsText.text = "Investments: ${investments?.investments?.joinToString { it.plan } ?: "N/A"}"
                } else {
                    Toast.makeText(context, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<InvestmentsResponse>, t: Throwable) {
                Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}￼Enter
