package com.mediousbag.mlm.ui.mypremium

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mediousbag.mlm.R
import com.mediousbag.mlm.api.ApiService
import com.mediousbag.mlm.api.RetrofitClient
import com.mediousbag.mlm.databinding.FragmentMyPremiumBinding
import com.mediousbag.mlm.models.MyPremiumResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPremiumFragment : Fragment() {

    private var _binding: FragmentMyPremiumBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyPremiumBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Dummy token, replace with real auth logic
        val token = "dummy_token"
        loadMyPremiumData(token)

        return root
    }

    private fun loadMyPremiumData(token: String) {
        val call = RetrofitClient.instance.getMyPremium(token)
        call.enqueue(object : Callback<MyPremiumResponse> {
            override fun onResponse(call: Call<MyPremiumResponse>, response: Response<MyPremiumResponse>) {
                if (response.isSuccessful) {
                    val myPremium = response.body()
                    binding.premiumText.text = "Package: ${myPremium?.packageName ?: "N/A"}, Status: ${if (myPremium?.isPremium == true) "Active" else "Inactive"}"
                } else {
                    Toast.makeText(context, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<MyPremiumResponse>, t: Throwable) {
                Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}￼Enter
