package com.wasla.multiscreendemo;

import android.app.Presentation;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Display;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.ArrayList;

public class SecondaryDisplayVideo extends Presentation {
    /**
     * Creates a new presentation that is attached to the specified display
     * using the default theme.
     *
     * @param outerContext The context of the application that is showing the presentation.
     *                     The presentation will create its own context (see {@link #getContext()}) based
     *                     on this context and information about the associated display.
     * @param display      The display to which the presentation should be attached.
     */
    public SecondaryDisplayVideo(Context outerContext, Display display) {
        super(outerContext, display);
    }

    private VideoView videoView;
    private ArrayList<String> videosList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String photoDirectory = Environment.getExternalStorageDirectory().getAbsolutePath();

        videosList.add(photoDirectory + "/cityscape/videos/cfc_video_1.mp4");
        videosList.add(photoDirectory + "/cityscape/videos/cfc_video_1.mp4");
//        videosList.add(photoDirectory + "/cityscape/videos/cfc_video_2.mp4");
//        videosList.add(photoDirectory + "/cityscape/videos/cfc_video_3.mp4");
//        videosList.add(photoDirectory + "/cityscape/videos/cfc_video_4.mp4");


        setContentView(R.layout.secondary_display_video);
        videoView = findViewById(R.id.videoView);

        //Creating MediaController
        MediaController mediaController = new MediaController(getContext());
        mediaController.setAnchorView(videoView);

        //Setting MediaController and URI, then starting the videoView
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(
                Uri.parse(videosList.get(0))
        );
        videoView.requestFocus();
        videoView.start();
        final int[] index = {1};
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                videoView.setVideoURI(Uri.parse(videosList.get(index[0])));
                videoView.start();
                if (index[0] + 1 < videosList.size())
                    index[0]++;
                else index[0] = 0;
            }
        });

        videoView.setKeepScreenOn(true);
//        videoView.setOnPreparedListener { mp -> mp.isLooping = true }
        videoView.setMediaController(null);
    }
}