package com.example.retrofit

import android.os.Bundle
import android.util.Log

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.retrofit.ui.theme.RetrofitTheme
import retrofit2.http.POST

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RetrofitTheme {
                val viewModel: MainViewModel = viewModel()
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) {
                    PostList(viewModel = viewModel)
                }
            }
        }

    }
}


@Composable
internal fun PostItem(post: Post) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "User ID: ${post.userId}", style = MaterialTheme.typography.headlineLarge)
        Text(text = "Post ID: ${post.id}", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Title: ${post.title}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Body: ${post.body}", style = MaterialTheme.typography.bodyMedium)
        Divider(modifier = Modifier.padding(vertical = 8.dp))
    }
}


@Composable
internal fun PostList(viewModel: MainViewModel = viewModel(), modifier: Modifier = Modifier) {
    val posts by viewModel.posts.observeAsState(emptyList())

    Log.d("PostList", "Observed posts: ${posts.size}")

    LazyColumn(modifier = modifier) {
        items(posts) { item: Post ->
            PostItem(post = item)
        }
    }
}







