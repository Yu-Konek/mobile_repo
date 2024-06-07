import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MyButton(text: String, modifier: Modifier = Modifier) {
    Button(modifier = modifier, onClick = { print("tapped") }, shape = RoundedCornerShape(8.dp)) {
        Text(text = (text))
    }
}