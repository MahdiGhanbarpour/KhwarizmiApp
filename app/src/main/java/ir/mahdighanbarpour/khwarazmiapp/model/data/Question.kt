package ir.mahdighanbarpour.khwarazmiapp.model.data


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Question(
    @SerializedName("attachments") val attachments: List<Attachment>,
    @SerializedName("options") val options: List<Option>,
    @SerializedName("question") val question: String
) : Parcelable

@Parcelize
data class Option(
    @SerializedName("isCorrect") val isCorrect: Boolean,
    @SerializedName("option") val option: String
) : Parcelable

@Parcelize
data class Attachment(
    @SerializedName("detail") val detail: String, @SerializedName("image") val image: String
) : Parcelable