package com.olds.todoandroid.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Login",
            style = MaterialTheme.typography.headlineMedium,
        )
        viewModel.uiState.errorMessage?.let { message ->
            Text(text = message)
        }
        Button(
            onClick = viewModel::onLoginClicked,
            modifier = Modifier.padding(top = 16.dp),
            enabled = !viewModel.uiState.isLoading,
        ) {
            Text(if (viewModel.uiState.isLoading) "Loading..." else "Continue")
        }
    }
}