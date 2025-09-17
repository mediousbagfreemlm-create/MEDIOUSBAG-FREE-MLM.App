package com.mediousbag.mlm.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mediousbag.mlm.R
import com.mediousbag.mlm.api.ApiService
import com.mediousbag.mlm.api.RetrofitClient
import com.mediousbag.mlm.databinding.FragmentRegisterBinding
import com.mediousbag.mlm.models.RegisterRequest
import com.mediousbag.mlm.models.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.registerButton.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val mobile = binding.mobileEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val sponsor = binding.sponsorEditText.text.toString()
            if (name.isNotEmpty() && mobile.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                registerUser(name, mobile, email, password, sponsor)
            } else {
                Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        return root
    }

    private fun registerUser(name: String, mobile: String, email: String, password: String, sponsor: String) {
        val request = RegisterRequest(name, mobile, email, password, sponsor)
        val call = RetrofitClient.instance.register(request)
        call.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                if (response.isSuccessful) {
                    val registerResponse = response.body()
                    if (registerResponse?.success == true) {
                        Toast.makeText(context, registerResponse.message, Toast.LENGTH_SHORT).show()
                        requireActivity().findNavController(R.id.nav_host_fragment_activity_main).navigate(R.id.navigation_login)
                    }
                } else {
                    Toast.makeText(context, "Registration Failed: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}￼Enter
