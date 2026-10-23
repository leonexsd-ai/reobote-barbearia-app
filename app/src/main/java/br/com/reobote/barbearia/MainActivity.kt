package br.com.reobote.barbearia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.reobote.barbearia.presentation.navigation.ReoboteNavGraph
import br.com.reobote.barbearia.presentation.theme.ReoboteTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReoboteTheme {
                ReoboteNavGraph(
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}
//teste de comunicação com o git
@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    ReoboteTheme {
        ReoboteNavGraph()
    }
}
