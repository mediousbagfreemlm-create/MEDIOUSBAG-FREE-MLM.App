package com.mediousbag.mlm.ui.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mediousbag.mlm.R
import com.mediousbag.mlm.api.ApiService
import com.mediousbag.mlm.api.RetrofitClient
import com.mediousbag.mlm.databinding.FragmentShopBinding
import com.mediousbag.mlm.models.ShopResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShopFragment : Fragment() {

    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShopBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Dummy token, replace with real auth logic
        val token = "dummy_token"
        loadShopProducts(token)

        return root
    }

    private fun loadShopProducts(token: String) {
        val call = RetrofitClient.instance.getShop(token)
        call.enqueue(object : Callback<ShopResponse> {
            override fun onResponse(call: Call<ShopResponse>, response: Response<ShopResponse>) {
                if (response.isSuccessful) {
                    val shop = response.body()
                    // Update UI with products (e.g., ListView or RecyclerView)
                    // Placeholder text for now
                    binding.shopText.text = "Products: ${shop?.products?.joinToString { it.name } ?: "N/A"}"
                } else {
                    Toast.makeText(context, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ShopResponse>, t: Throwable) {
                Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}￼Enter
