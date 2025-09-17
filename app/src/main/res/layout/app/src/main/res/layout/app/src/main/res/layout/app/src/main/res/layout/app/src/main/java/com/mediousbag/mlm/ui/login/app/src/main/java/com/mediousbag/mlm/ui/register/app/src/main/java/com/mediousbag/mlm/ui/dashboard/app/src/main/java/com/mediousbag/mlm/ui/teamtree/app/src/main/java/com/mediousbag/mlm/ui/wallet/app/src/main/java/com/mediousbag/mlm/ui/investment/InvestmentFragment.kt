package com.mediousbag.mlm.ui.investment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mediousbag.mlm.R
import com.mediousbag.mlm.api.ApiService
import com.mediousbag.mlm.api.RetrofitClient
import com.mediousbag.mlm.databinding.FragmentInvestmentBinding
import com.mediousbag.mlm.models.InvestmentResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InvestmentFragment : Fragment() {

    private var _binding: FragmentInvestmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInvestmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Dummy token, replace with real auth logic
        val token = "dummy_token"
        loadInvestmentPlans(token)

        return root
    }

    private fun loadInvestmentPlans(token: String) {
        val call = RetrofitClient.instance.getInvestment(token)
        call.enqueue(object : Callback<InvestmentResponse> {
            override fun onResponse(call: Call<InvestmentResponse>, response: Response<InvestmentResponse>) {
                if (response.isSuccessful) {
                    val investment = response.body()
                    // Update UI with investment plans (e.g., ListView or RecyclerView)
                    // Placeholder text for now
                    binding.investmentText.text = "Plans: ${investment?.plans?.joinToString { it.name } ?: "N/A"}"
                } else {
                    Toast.makeText(context, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<InvestmentResponse>, t: Throwable) {
                Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}￼Enter
