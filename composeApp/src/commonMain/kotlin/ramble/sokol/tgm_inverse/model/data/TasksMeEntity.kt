package ramble.sokol.tgm_inverse.model.data

import kotlinx.serialization.Serializable

@Serializable
data class TasksMeEntity(
    val task: TasksEntity,
    val checkedAt: String? = null,
)
