package br.edu.ifsp.scl.sc3037291.postviewer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import br.edu.ifsp.scl.sc3037291.postviewer.navigation.AppNavGraph
import br.edu.ifsp.scl.sc3037291.postviewer.ui.theme.PostViewerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PostViewerTheme {
                AppNavGraph()
            }
        }
    }
}