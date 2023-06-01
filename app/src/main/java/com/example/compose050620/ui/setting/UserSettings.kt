package com.example.compose050620.ui.setting

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.RadioButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun UserSettings() {
    val items = listOf(
        SettingItem.Toggle("Dark Mode", true),
        SettingItem.Radio("Language", listOf("English", "Spanish", "French"), 0),
        SettingItem.Checkbox("Notifications", true),
        SettingItem.TextInput("Name", "John Smith"),
        SettingItem.Clickable("Sign Out")
    )

    LazyColumn {
        items(items.size) { index ->
            when (val item = items[index]) {
                is SettingItem.Toggle -> ToggleSetting(item)
                is SettingItem.Radio -> RadioSetting(item)
                is SettingItem.Checkbox -> CheckboxSetting(item)
                is SettingItem.TextInput -> TextInputSetting(item)
                is SettingItem.Clickable -> ClickableSetting(item)
            }
            Divider()
        }
    }
}

@Composable
fun ToggleSetting(item: SettingItem.Toggle) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = item.title)
        Switch(checked = item.value, onCheckedChange = {})
    }
}

@Composable
fun RadioSetting(item: SettingItem.Radio) {
    var selected by remember { mutableStateOf(item.selectedIndex) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = item.title)

        item.options.forEachIndexed { index, option ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
                    .clickable {
                        selected = index
                    }
            ) {
                RadioButton(
                    selected = selected == index,
                    onClick = { selected = index }
                )
                Text(text = option, modifier = Modifier.padding(start = 8.dp))
            }
        }
    }
}

@Composable
fun CheckboxSetting(item: SettingItem.Checkbox) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = item.title)
        Checkbox(checked = item.value, onCheckedChange = {})
    }
}

@Composable
fun TextInputSetting(item: SettingItem.TextInput) {
    var value by remember { mutableStateOf(item.value) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = item.title)
        OutlinedTextField(
            value = value,
            onValueChange = { value = it },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
    }
}

@Composable
fun ClickableSetting(item: SettingItem.Clickable) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                Toast
                    .makeText(context, "Clicked ${item.title}", Toast.LENGTH_SHORT)
                    .show()
            }
    ) {
        Text(text = item.title)
    }
}

sealed class SettingItem() {
    data class Toggle(val title: String, val value: Boolean) : SettingItem()
    data class Radio(val title: String, val options: List<String>, val selectedIndex: Int) : SettingItem()
    data class Checkbox(val title: String, val value: Boolean) : SettingItem()
    data class TextInput(val title: String, val value: String) : SettingItem()
    data class Clickable(val title: String) : SettingItem()
}
