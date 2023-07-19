package com.example.showimagefromapi

import android.media.Image
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.showimagefromapi.model.ImagePost
import com.example.showimagefromapi.repository.Repository
import com.example.showimagefromapi.ui.theme.ShowImageFromAPITheme
import retrofit2.Response

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = Repository()
        viewModel = MainViewModel(repository)
        setContent {
            ShowImageFromAPITheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ShowApp(viewModel)
                }
            }
        }
    }
}

@Composable
fun ImageCard(image:String) {
    Box(
        modifier = Modifier
            .wrapContentSize(align = Alignment.TopStart)
            .padding(10.dp)
    ) {
        AsyncImage(
            model = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(120.dp)
        )
    }
}

@Composable
fun ShowApp(viewModel: MainViewModel){
    val response by viewModel.response.collectAsState()
    ShowImageInGrid(response = response)
    LaunchedEffect(key1 = response.isSuccessful){
        viewModel.getImagePost()
        Log.d("aaa" , "This execute once time")
    }
    //
    Log.d("aaa" , response.body().toString())
}
@Composable
fun ShowImageInGrid (response : Response<List<ImagePost>>){
    LazyVerticalGrid(columns = GridCells.Fixed(3)) {
        items(response.body()!!) {imagePost ->
            ImageCard(imagePost.img_src)
        }
    }
}