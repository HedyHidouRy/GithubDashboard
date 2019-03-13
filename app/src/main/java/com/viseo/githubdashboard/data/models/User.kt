package com.viseo.githubdashboard.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.viseo.githubdashboard.data.providers.local.USERS_TABLE
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = USERS_TABLE)
data class User(
    @PrimaryKey
    @SerializedName("id") val id: Int,
    @SerializedName("login") val login: String,
    @SerializedName("avatar_url") val avatarUrl: String
): Parcelable