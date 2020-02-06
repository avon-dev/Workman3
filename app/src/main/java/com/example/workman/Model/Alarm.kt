import android.net.Uri
import java.io.Serializable

data class Alarm(
    val id: Int,
    var hour: Int,
    var minute: Int,
    var on_off: Int,
    var days: BooleanArray,
    var melody: String?,
    var melody_name: String?
): Serializable