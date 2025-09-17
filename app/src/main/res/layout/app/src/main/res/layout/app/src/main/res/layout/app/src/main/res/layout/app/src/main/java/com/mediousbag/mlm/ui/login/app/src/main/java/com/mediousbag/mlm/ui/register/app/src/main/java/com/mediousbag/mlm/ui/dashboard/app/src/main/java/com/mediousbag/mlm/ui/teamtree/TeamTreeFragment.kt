package com.mediousbag.mlm.ui.teamtree

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mediousbag.mlm.R
import com.mediousbag.mlm.api.ApiService
import com.mediousbag.mlm.api.RetrofitClient
import com.mediousbag.mlm.databinding.FragmentTeamTreeBinding
import com.mediousbag.mlm.models.TeamTreeResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamTreeFragment : Fragment() {

    private var _binding: FragmentTeamTreeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTeamTreeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Dummy token, replace with real auth logic
        val token = "dummy_token"
        loadTeamTreeData(token)

        return root
    }

    private fun loadTeamTreeData(token: String) {
        val call = RetrofitClient.instance.getTeamTree(token)
        call.enqueue(object : Callback<TeamTreeResponse> {
            override fun onResponse(call: Call<TeamTreeResponse>, response: Response<TeamTreeResponse>) {
                if (response.isSuccessful) {
                    val teamTree = response.body()
                    binding.uplineText.text = "Upline: ${teamTree?.upline?.joinToString() ?: "N/A"}"
                    binding.downlineText.text = "Downline: ${teamTree?.downline?.joinToString() ?: "N/A"}"
                } else {
                    Toast.makeText(context, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<TeamTreeResponse>, t: Throwable) {
                Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}￼Enter
