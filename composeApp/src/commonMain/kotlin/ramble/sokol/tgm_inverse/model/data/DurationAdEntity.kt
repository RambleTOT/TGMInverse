package ramble.sokol.tgm_inverse.model.data

import kotlinx.serialization.Serializable

@Serializable
data class DurationAdEntity(
    val start: String?,
    val end: String?,
)