package net.hwyz.iov.mp.app.ui.widget.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import net.hwyz.iov.mp.app.ui.widget.button.PrimaryButton
import net.hwyz.iov.mp.app.ui.widget.button.SecondlyButton
import net.hwyz.iov.mp.app.ui.widget.item.TextContentItem
import net.hwyz.iov.mp.app.ui.widget.title.MediumTitle

@Composable
fun SelectAlertDialog(
    title: String,
    content: String,
    primaryButtonText: String,
    secondButtonText: String,
    onPrimaryButtonClick: () -> Unit,
    onSecondButtonClick: () -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card {
            Column(Modifier.padding(20.dp)) {
                MediumTitle(title = title, modifier = Modifier.padding(bottom = 20.dp))
                TextContentItem(text = content, modifier = Modifier.padding(bottom = 20.dp))
                PrimaryButton(text = primaryButtonText, Modifier.padding(bottom = 10.dp)) {
                    onPrimaryButtonClick.invoke()
                    onDismiss.invoke()
                }
                SecondlyButton(text = secondButtonText) {
                    onSecondButtonClick.invoke()
                    onDismiss.invoke()
                }
            }
        }
    }
}