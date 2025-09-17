package com.mediousbag.mlm.ui.reports

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mediousbag.mlm.R
import com.mediousbag.mlm.api.ApiService
import com.mediousbag.mlm.api.RetrofitClient
import com.mediousbag.mlm.databinding.FragmentReportsBinding
import com.mediousbag.mlm.models.ReportsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReportsFragment : Fragment() {

    private var _binding: FragmentReportsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReportsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Dummy token, replace with real auth logic
        val token = "dummy_token"
        loadReports(token)

        return root
    }

    private fun loadReports(token: String) {
        val call = RetrofitClient.instance.getReports(token)
        call.enqueue(object : Callback<ReportsResponse> {
            override fun onResponse(call: Call<ReportsResponse>, response: Response<ReportsResponse>) {
                if (response.isSuccessful) {
                    val reports = response.body()
                    // Update UI with commissions (e.g., ListView or RecyclerView)
                    // Placeholder text for now
                    binding.reportsText.text = "Commissions: ${reports?.commissions?.joinToString { it.amount.toString() } ?: "N/A"}"
                } else {
                    Toast.makeText(context, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ReportsResponse>, t: Throwable) {
                Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}￼Enter
