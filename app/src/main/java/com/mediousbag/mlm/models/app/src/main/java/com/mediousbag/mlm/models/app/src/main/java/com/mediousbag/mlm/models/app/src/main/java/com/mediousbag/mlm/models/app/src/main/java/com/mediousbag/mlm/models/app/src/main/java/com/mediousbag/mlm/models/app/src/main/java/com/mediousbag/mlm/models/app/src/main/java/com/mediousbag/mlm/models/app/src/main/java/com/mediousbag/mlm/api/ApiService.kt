package com.mediousbag.mlm.api

import com.mediousbag.mlm.models.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST("register")
    fun register(@Body request: RegisterRequest): Call<RegisterResponse>

    @GET("dashboard")
    fun getDashboard(@Header("Authorization") token: String): Call<DashboardResponse>

    @GET("team-tree")
    fun getTeamTree(@Header("Authorization") token: String): Call<TeamTreeResponse>

    @GET("wallet")
    fun getWallet(@Header("Authorization") token: String): Call<WalletResponse>

    @POST("withdraw")
    fun requestWithdrawal(@Header("Authorization") token: String, @Body request: WithdrawalRequest): Call<WithdrawalResponse>

    @GET("investment")
    fun getInvestment(@Header("Authorization") token: String): Call<InvestmentResponse>

    @GET("premium")
    fun getPremium(@Header("Authorization") token: String): Call<PremiumResponse>

    @GET("shop")
    fun getShop(@Header("Authorization") token: String): Call<ShopResponse>

    @GET("my-orders")
    fun getMyOrders(@Header("Authorization") token: String): Call<OrdersResponse>

    @GET("my-investments")
    fun getMyInvestments(@Header("Authorization") token: String): Call<InvestmentsResponse>

    @GET("my-withdrawals")
    fun getMyWithdrawals(@Header("Authorization") token: String): Call<WithdrawalsResponse>

    @GET("my-account")
    fun getMyAccount(@Header("Authorization") token: String): Call<AccountResponse>

    @GET("my-premium")
    fun getMyPremium(@Header("Authorization") token: String): Call<MyPremiumResponse>

    @GET("reports")
    fun getReports(@Header("Authorization") token: String): Call<ReportsResponse>
}￼Enter
