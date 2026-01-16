package com.example.dji_rc_pro.ui.components

import android.view.SurfaceView
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

/**
 * Placeholder for DJI Video Feed.
 * Typically uses a SurfaceView or TextureView to render the decoded stream.
 */
@Composable
fun VideoFeedView(
    modifier: Modifier = Modifier
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            FrameLayout(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                
                // The actual SurfaceView for video rendering
                val surfaceView = SurfaceView(context)
                addView(surfaceView, FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT
                ))
                
                // TODO: Initialize DJI Video Feeder here or in ViewModel
                // VideoFeeder.getInstance().primaryVideoFeed.addVideoDataListener(...)
                // DJICodecManager.init(...)
            }
        }
    )
}
