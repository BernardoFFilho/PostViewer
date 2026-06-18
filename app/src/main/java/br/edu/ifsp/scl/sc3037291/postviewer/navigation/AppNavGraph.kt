package br.edu.ifsp.scl.sc3037291.postviewer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.edu.ifsp.scl.sc3037291.postviewer.ui.postdetail.PostDetailScreen
import br.edu.ifsp.scl.sc3037291.postviewer.ui.postlist.PostListScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "post_list") {
        // tela inicial do app;
        composable("post_list") {
            PostListScreen(
                onPostClick = { postId ->
                    navController.navigate("post_detail/$postId")
                }
            )
        }
        // tela de detalhes
        composable(
            route = "post_detail/{postId}",
            arguments = listOf(navArgument("postId") { type = NavType.IntType })
        ) { backStackEntry ->
            val postId = backStackEntry.arguments?.getInt("postId") ?: 0
            PostDetailScreen(postId = postId)
        }
    }
}
