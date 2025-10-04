package pe.edu.upc.easymovie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import pe.edu.upc.easymovie.features.movies.presentation.MoviesView
import pe.edu.upc.easymovie.ui.theme.EasyMovieTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EasyMovieTheme {
                Scaffold { innerPadding ->
                    MoviesView(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}