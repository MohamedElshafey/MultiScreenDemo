package com.wasla.multiscreendemo;

import android.app.Presentation;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.widget.TextView;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import org.jetbrains.annotations.NotNull;

public class SecondaryDisplay extends Presentation implements Observer {
    /**
     * Creates a new presentation that is attached to the specified display
     * using the default theme.
     *
     * @param outerContext The context of the application that is showing the presentation.
     *                     The presentation will create its own context (see {@link #getContext()}) based
     *                     on this context and information about the associated display.
     * @param display      The display to which the presentation should be attached.
     */
    public SecondaryDisplay(Context outerContext, Display display) {
        super(outerContext, display);
    }

    TextView textView;
    SubsamplingScaleImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondary_display);
        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);

        imageView.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CENTER_CROP);
//        imageView.setScaleAndCenter(SubsamplingScaleImageView.SCALE_TYPE_CENTER_CROP, null);
        imageView.setImage(ImageSource.resource(R.drawable.image4k2));
//        imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.image4k2));

        Observable.Companion.addObserver(this);
    }

    @Override
    public void update(@NotNull String string) {
        textView.setText(string);
    }
}