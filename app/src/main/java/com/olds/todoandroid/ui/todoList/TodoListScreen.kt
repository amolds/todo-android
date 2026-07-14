package com.olds.todoandroid.ui.todoList

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun TodoListScreen(
    viewModel: TodoListViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Todo List",
            style = MaterialTheme.typography.headlineMedium,
        )
        if (viewModel.uiState.isLoading) {
            Text("Loading todos...")
        } else {
            Text("Your todo content will live here.")
        }
        viewModel.uiState.errorMessage?.let { message ->
            Text(text = message)
        }
        Button(onClick = viewModel::onLogoutClicked) {
            Text("Log out")
        }
    }
}