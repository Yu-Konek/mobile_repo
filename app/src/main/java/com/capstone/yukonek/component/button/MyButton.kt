import android.service.autofill.OnClickAction
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.motion.widget.MotionScene.Transition.TransitionOnClick

@Composable
fun MyButton(text: String, modifier: Modifier = Modifier, onClick: () -> Unit = {}, enabled: Boolean = false) {
    Button(modifier = modifier, onClick = onClick, shape = RoundedCornerShape(8.dp), enabled = enabled) {
        Text(text = (text))
    }
}