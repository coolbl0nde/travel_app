
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.example.travelmap.R
import com.mapbox.geojson.Point
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import com.mapbox.maps.plugin.annotation.generated.createPointAnnotationManager

@Composable
fun MapScreen(
    coordinates: List<Point>,
    @DrawableRes markerResId: Int = R.drawable.marker_icon
) {
    val context = LocalContext.current

    val mapView = remember {
        MapView(context)
    }

    AndroidView(
        factory = { mapView }
    )

    LaunchedEffect(Unit) {
        mapView.mapboxMap.loadStyleUri(
            Style.MAPBOX_STREETS
        ) { style ->
            addAnnotationsToMap(mapView, context, markerResId, coordinates)
        }
    }

    LaunchedEffect(coordinates) {
        mapView.mapboxMap.getStyle { style ->
            updateAnnotations(mapView, context, markerResId, coordinates)
        }
    }

    /*DisposableEffect(Unit) {
        onDispose {
            mapView.onStop()
            mapView.onDestroy()
        }
    }*/
}


private fun addAnnotationsToMap(
    mapView: MapView,
    context: Context,
    @DrawableRes markerResId: Int,
    coordinates: List<Point>
) {
    val annotationApi = mapView.annotations
    val pointAnnotationManager = annotationApi.createPointAnnotationManager()


    val markerBitmap = bitmapFromDrawableRes(context, markerResId) ?: return

    coordinates.forEach { point ->

        val pointAnnotationOptions = PointAnnotationOptions()
            .withPoint(point)
            .withIconImage(markerBitmap)

        pointAnnotationManager.create(pointAnnotationOptions)
    }
}


private fun bitmapFromDrawableRes(
    context: Context,
    @DrawableRes resourceId: Int
): Bitmap? {
    val drawable = AppCompatResources.getDrawable(context, resourceId)
    return convertDrawableToBitmap(drawable)
}


private fun convertDrawableToBitmap(sourceDrawable: Drawable?): Bitmap? {

    if (sourceDrawable == null) return null

    return if (sourceDrawable is BitmapDrawable) {
        sourceDrawable.bitmap
    } else {
        val constantState = sourceDrawable.constantState ?: return null
        val drawable = constantState.newDrawable().mutate()
        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)

        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        bitmap
    }
}

private fun updateAnnotations(
    mapView: MapView,
    context: Context,
    @DrawableRes markerResId: Int,
    coordinates: List<Point>
) {
    val annotationApi = mapView.annotations

    annotationApi.cleanup()

    val pointAnnotationManager = annotationApi.createPointAnnotationManager()
    val markerBitmap = bitmapFromDrawableRes(context, markerResId) ?: return

    coordinates.forEach { point ->
        val pointAnnotationOptions = PointAnnotationOptions()
            .withPoint(point)
            .withIconImage(markerBitmap)
        pointAnnotationManager.create(pointAnnotationOptions)
    }
}
