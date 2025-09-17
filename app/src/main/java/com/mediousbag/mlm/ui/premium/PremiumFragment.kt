package com.mediousbag.mlm.ui.premium

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mediousbag.mlm.R
import com.mediousbag.mlm.api.ApiService
import com.mediousbag.mlm.api.RetrofitClient
import com.mediousbag.mlm.databinding.FragmentPremiumBinding
import com.mediousbag.mlm.models.PremiumResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PremiumFragment : Fragment() {

    private var _binding: FragmentPremiumBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPremiumBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Dummy token, replace with real auth logic
        val token = "dummy_token"
        loadPremiumPackages(token)

        return root
    }

    private fun loadPremiumPackages(token: String) {
        val call = RetrofitClient.instance.getPremium(token)
        call.enqueue(object : Callback<PremiumResponse> {
            override fun onResponse(call: Call<PremiumResponse>, response: Response<PremiumResponse>) {
                if (response.isSuccessful) {
                    val premium = response.body()
                    // Update UI with premium packages (e.g., ListView or RecyclerView)
                    // Placeholder text for now
                    binding.premiumText.text = "Packages: ${premium?.packages?.joinToString { it.name } ?: "N/A"}"
                } else {
                    Toast.makeText(context, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<PremiumResponse>, t: Throwable) {
                Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}￼Enter
