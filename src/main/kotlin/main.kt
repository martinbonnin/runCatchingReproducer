import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue

fun main() = Window {
  val text = remember { mutableStateOf(TextFieldValue("")) }

  val actualText = runCatching {
    someFunctionThatMayThrow(text.value.text)
  }

  MaterialTheme {
    Column {
      TextField(
        value = text.value,
        onValueChange = {
          text.value = it
        }
      )

      if (actualText.isSuccess) {
        Text(text = actualText.getOrThrow())
      } else {
        Text(text = actualText.exceptionOrNull()?.message ?: "Oh no!")
      }
    }
  }
}

fun someFunctionThatMayThrow(text: String): String {
  if (text == "throw") {
    throw Exception("Oh no!")
  }
  return "Hello $text"
}
