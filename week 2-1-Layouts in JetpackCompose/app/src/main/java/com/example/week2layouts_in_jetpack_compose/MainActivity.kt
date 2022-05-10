package com.example.week2layouts_in_jetpack_compose

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import coil.compose.rememberImagePainter
import com.example.week2layouts_in_jetpack_compose.ui.theme.LayoutsCodelabTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LayoutsCodelabTheme {
                ScrollingList()
            }
        }
    }
}

//@Composable
//fun LayoutsCodelab() {
//    Scaffold (
//        topBar = {
//            TopAppBar (
//                title = {
//                    Text(text = "LayoutsCodelab")
//                },
//                actions = {
//                    IconButton(onClick = { } ) {
//                        Icon(Icons.Filled.Favorite, contentDescription = null)
//                    }
//                }
//            )
//        }
//    ) { innerPadding ->
//        BodyContent(modifier = Modifier.padding(innerPadding))
//    }
//}
//
//@Composable
//fun BodyContent(modifier: Modifier = Modifier) {
//    Column(modifier = modifier.padding(8.dp)) {
//        Text(text = "Hi there!")
//        Text(text = "Thanks for going through the Layouts codelab")
//    }
//}

//@Preview
//@Composable
//fun LayoutsCodelabPreview() {
//    LayoutsCodelabTheme {
//        LayoutsCodelab()
//    }
//}

@Composable
fun ImageList() {
    val scrollState = rememberLazyListState()

    LazyColumn(state = scrollState) {
        items(100) {
            ImageListItem(it)
        }
    }
}

@Composable
fun ImageListItem(index: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = rememberImagePainter(
                data = "https://developer.android.com/images/brand/Android_Robot.png"
            ),
            contentDescription = "Android Logo",
            modifier = Modifier.size(50.dp)
        )

        Spacer(modifier = Modifier.width(50.dp))
        Text("Item #$index", style = MaterialTheme.typography.subtitle1)
    }
}

@Composable
fun ScrollingList() {
    val listSize = 100
    // We save the scrolling position with this state
    val scrollState = rememberLazyListState()
    // We save the coroutine where our animated scroll will be executed
    val coroutineScope = rememberCoroutineScope()

    Column {
        Row {
            Button(onClick = {
                coroutineScope.launch {
                    scrollState.animateScrollToItem(0)
                }
            },
                modifier = Modifier.weight(1f)) {
                Text("Scroll to the top")
            }

            Button(onClick = {
                coroutineScope.launch {
                    scrollState.animateScrollToItem(listSize-1)
                }
            }, modifier = Modifier.weight(1f)) {
                Text("Scroll to the end")
            }
        }

        LazyColumn(state = scrollState) {
            items(listSize) {
                ImageListItem(index = it)
            }
        }
    }
}

@Preview
@Composable
fun SimpleListPreview() {
    LayoutsCodelabTheme {
        ImageList()
    }
}

//
//@Composable
//fun PhotographerCard(modifier: Modifier = Modifier) {
//    Row(
//        modifier
//            .padding(16.dp)
//            .clip(RoundedCornerShape(4.dp))
//            .background(MaterialTheme.colors.surface)
//            .clickable(onClick = {  })
//            .padding(16.dp)
//
//    ) {
//        Surface(
//            modifier = Modifier
//                .size(50.dp)
//                .align(Alignment.CenterVertically),
//            shape = CircleShape,
//            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
//        ) {
//            //Image go
//        }
//        Column(
//            modifier = Modifier
//                .padding(start = 8.dp)
//                .align(Alignment.CenterVertically)
//
//        ) {
//            Text("Alfred Sisley", fontWeight = FontWeight.Bold)
//            // LocalContentAlpha is defining opacity level of its children
//            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
//                Text("3 minutes ago", style = MaterialTheme.typography.body2)
//            }
//        }
//    }
//
//}

//@Preview
//@Composable
//fun PhotographerCardPreview() {
//    LayoutsCodelabTheme {
//        PhotographerCard()
//    }
//}