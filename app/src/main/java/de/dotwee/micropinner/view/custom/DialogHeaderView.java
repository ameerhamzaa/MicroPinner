package de.dotwee.micropinner.view.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

import de.dotwee.micropinner.BuildConfig;
import de.dotwee.micropinner.R;

/**
 * Created by lukas on 25.07.2016.
 */
public class DialogHeaderView extends AbstractDialogView
        implements Switch.OnCheckedChangeListener, View.OnClickListener, View.OnLongClickListener {

    private static final String TAG = DialogHeaderView.class.getSimpleName();
    private Switch switchAdvanced;

    public DialogHeaderView(Context context) {
        super(context);
    }

    public DialogHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DialogHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init() {
        super.init();

        inflate(getContext(), R.layout.dialog_main_head, this);

        LinearLayout linearLayoutHeader = findViewById(R.id.linearLayoutHeader);
        linearLayoutHeader.setOnLongClickListener(this);
        linearLayoutHeader.setOnClickListener(this);

        switchAdvanced = findViewById(R.id.switchAdvanced);
        switchAdvanced.setOnCheckedChangeListener(this);
        switchAdvanced.setOnLongClickListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        checkIfPresenterNull();
        switch (compoundButton.getId()) {

            case R.id.switchAdvanced:
                mainPresenter.onViewExpand(isChecked);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.linearLayoutHeader:
                switchAdvanced.performClick();
                break;

            default:
                if (BuildConfig.DEBUG) {
                    Log.w(TAG, "Registered click on unknown view");
                }
                break;
        }
    }

    /**
     * Called when a view has been clicked and held.
     *
     * @param view The view that was clicked and held.
     * @return true if the callback consumed the long click, false otherwise.
     */
    @Override
    public boolean onLongClick(View view) {
        checkIfPresenterNull();

        switch (view.getId()) {

            case R.id.switchAdvanced:
                mainPresenter.onSwitchHold();
                return true;

            case R.id.linearLayoutHeader:
                mainPresenter.onSwitchHold();
                return true;

            default:
                if (BuildConfig.DEBUG) {
                    Log.w(TAG, "Registered long-click on unknown view");
                }
                return false;
        }
    }
}
