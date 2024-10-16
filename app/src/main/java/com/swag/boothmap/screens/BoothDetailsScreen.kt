import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.swag.boothmap.datacalsses.Booth
import com.swag.boothmap.ui.theme.green
import com.swag.boothmap.ui.theme.saffron
import com.swag.boothmap.ui.theme.white
import com.swag.boothmap.viewmodels.BoothViewmodel
import kotlinx.coroutines.delay



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoothDetailsScreen(
    navController: NavController,
    viewmodel: BoothViewmodel
) {
    val scrollState = rememberScrollState()
    val booth = viewmodel.selectedBooth
    val context = LocalContext.current

    booth?.let {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = booth.name,
                            color = white,
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = white)
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = saffron,
                        titleContentColor = white,
                        actionIconContentColor = white
                    )
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(scrollState)
                    .background(white)
            ) {
                ImageSlider(booth.images)
                BoothInfo(booth) {
                    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${booth.bloContact}"))
                    context.startActivity(intent)
                }
            }
        }
    }
}

@Composable
fun ImageSlider(images: List<Uri>) {
    if (images.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            Text("No images available", color = Color.DarkGray)
        }
        return
    }

    var currentImageIndex by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            currentImageIndex = (currentImageIndex + 1) % images.size
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(data = images[currentImageIndex])
                    .build()
            ),
            contentDescription = "Booth Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            images.forEachIndexed { index, _ ->
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(
                            if (index == currentImageIndex) white else Color.Gray.copy(alpha = 0.5f)
                        )
                )
            }
        }
    }
}

@Composable
fun BoothInfo(booth: Booth, onCallBLO: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        InfoItem("Booth ID", booth.id, saffron)
        InfoItem("District", booth.district, saffron)
        InfoItem("Taluka", booth.taluka, saffron)
        InfoItem("BLO Name", booth.bloName, saffron)

        Spacer(modifier =   Modifier.height(25.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text("BLO Contact", fontWeight = FontWeight.Bold, color = saffron)
                Text(booth.bloContact, color = Color.Black)
            }
            IconButton(
                onClick = onCallBLO,
                modifier = Modifier
                    .size(48.dp)
                    .background(green, CircleShape)
            ) {
                Icon(Icons.Default.Call, contentDescription = "Call BLO", tint = white)
            }
        }
    }
}

@Composable
fun InfoItem(label: String, value: String, backgroundColor: Color) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(label, fontWeight = FontWeight.Bold, color = Color.White)
            Text(value, color = Color.White)
        }
    }
}