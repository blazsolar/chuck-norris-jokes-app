package com.github.blazsolar.chuck.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.blazsolar.chuck.App;
import com.github.blazsolar.chuck.BuildConfig;
import com.github.blazsolar.chuck.R;
import com.github.blazsolar.chuck.data.ApiEndpoint;
import com.github.blazsolar.chuck.data.api.ApiEndpoints;
import com.github.blazsolar.chuck.data.prefs.StringPreference;
import com.github.blazsolar.chuck.ui.main.MainActivity;
import com.github.blazsolar.chuck.util.Strings;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.inject.Inject;

import butterknife.InjectView;
import retrofit.MockRestAdapter;
import retrofit.RestAdapter;

import static butterknife.ButterKnife.findById;
import static butterknife.ButterKnife.inject;
import static retrofit.RestAdapter.LogLevel;

/**
 * Created by Blaz Solar on 21/10/14.
 */
public class DebugAppContainer implements AppContainer {

    private static final String TAG = "DebugAppContainer";

    private static final DateFormat DATE_DISPLAY_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm a");

    private final StringPreference networkEndpoint;
    private final RestAdapter restAdapter;
    private final MockRestAdapter mockRestAdapter;
    private final Application app;

    private Activity activity;
    private Context drawerContext;

    @InjectView(R.id.debug_drawer_layout) DrawerLayout drawerLayout;
    @InjectView(R.id.debug_content) ViewGroup content;

    @InjectView(R.id.debug_network_endpoint) Spinner endpointView;
    @InjectView(R.id.debug_network_endpoint_edit) View endpointEditView;
    @InjectView(R.id.debug_network_delay) Spinner networkDelayView;
    @InjectView(R.id.debug_network_variance) Spinner networkVarianceView;
    @InjectView(R.id.debug_network_error) Spinner networkErrorView;
    @InjectView(R.id.debug_network_logging) Spinner networkLoggingView;

    @InjectView(R.id.debug_build_name) TextView buildNameView;
    @InjectView(R.id.debug_build_code) TextView buildCodeView;
    @InjectView(R.id.debug_build_sha) TextView buildShaView;
    @InjectView(R.id.debug_build_date) TextView buildDateView;

    @InjectView(R.id.debug_device_make) TextView deviceMakeView;
    @InjectView(R.id.debug_device_model) TextView deviceModelView;
    @InjectView(R.id.debug_device_resolution) TextView deviceResolutionView;
    @InjectView(R.id.debug_device_density) TextView deviceDensityView;
    @InjectView(R.id.debug_device_release) TextView deviceReleaseView;
    @InjectView(R.id.debug_device_api) TextView deviceApiView;

    @Inject public DebugAppContainer(@ApiEndpoint StringPreference networkEndpoint,
                                     RestAdapter restAdapter,
                                     MockRestAdapter mockRestAdapter, App app) {
        this.networkEndpoint = networkEndpoint;
        this.restAdapter = restAdapter;
        this.mockRestAdapter = mockRestAdapter;
        this.app = app;
    }

    @Override
    public ViewGroup get(Activity activity) {
        this.activity = activity;
        drawerContext = new ContextThemeWrapper(activity, R.style.Base_Theme_AppCompat);

        activity.setContentView(R.layout.debug_activity_frame);

        // Manually find the debug drawer and inflate the drawer layout inside of it.
        ViewGroup drawer = findById(activity, R.id.debug_drawer);
        LayoutInflater.from(drawerContext).inflate(R.layout.debug_drawer_content, drawer);

        inject(this, activity);

        setupNetworkSection();
        setupBuildSection();
        setupDeviceSection();

        return content;
    }

    private void setupNetworkSection() {
        final ApiEndpoints currentEndpoint = ApiEndpoints.from(networkEndpoint.get());
        final EnumAdapter<ApiEndpoints> endpointAdapter =
                new EnumAdapter<>(drawerContext, ApiEndpoints.class);
        endpointView.setAdapter(endpointAdapter);
        endpointView.setSelection(currentEndpoint.ordinal());
        endpointView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                ApiEndpoints selected = endpointAdapter.getItem(position);
                if (selected != currentEndpoint) {
                    if (selected == ApiEndpoints.CUSTOM) {
                        Log.d(TAG, "Custom network endpoint selected. Prompting for URL.");
                        showCustomEndpointDialog(currentEndpoint.ordinal(), "http://");
                    } else {
                        setEndpointAndRelaunch(selected.url);
                    }
                } else {
                    Log.d(TAG, String.format("Ignoring re-selection of network endpoint %s", selected));
                }
            }

            @Override public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        final NetworkDelayAdapter delayAdapter = new NetworkDelayAdapter(drawerContext);
        networkDelayView.setAdapter(delayAdapter);
        networkDelayView.setSelection(
                NetworkDelayAdapter.getPositionForValue(mockRestAdapter.getDelay()));
        networkDelayView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                long selected = delayAdapter.getItem(position);
                if (selected != mockRestAdapter.getDelay()) {
                    Log.d(TAG, String.format("Setting network delay to %sms", selected));
                    mockRestAdapter.setDelay(selected);
                } else {
                    Log.d(TAG, String.format("Ignoring re-selection of network delay %sms", selected));
                }
            }

            @Override public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        final NetworkVarianceAdapter varianceAdapter = new NetworkVarianceAdapter(drawerContext);
        networkVarianceView.setAdapter(varianceAdapter);
        networkVarianceView.setSelection(
                NetworkVarianceAdapter.getPositionForValue(mockRestAdapter.getVariancePercentage()));
        networkVarianceView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                int selected = varianceAdapter.getItem(position);
                if (selected != mockRestAdapter.getVariancePercentage()) {
                    Log.d(TAG, String.format("Setting network variance to %s%%", selected));
                    mockRestAdapter.setVariancePercentage(selected);
                } else {
                    Log.d(TAG, String.format("Ignoring re-selection of network variance %s%%", selected));
                }
            }

            @Override public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        final NetworkErrorAdapter errorAdapter = new NetworkErrorAdapter(drawerContext);
        networkErrorView.setAdapter(errorAdapter);
        networkErrorView.setSelection(
                NetworkErrorAdapter.getPositionForValue(mockRestAdapter.getErrorPercentage()));
        networkErrorView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                int selected = errorAdapter.getItem(position);
                if (selected != mockRestAdapter.getErrorPercentage()) {
                    Log.d(TAG, String.format("Setting network error to %s%%", selected));
                    mockRestAdapter.setErrorPercentage(selected);
                } else {
                    Log.d(TAG, String.format("Ignoring re-selection of network error %s%%", selected));
                }
            }

            @Override public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        // Only show the endpoint editor when a custom endpoint is in use.
        endpointEditView.setVisibility(currentEndpoint == ApiEndpoints.CUSTOM ? View.VISIBLE : View.GONE);

        if (currentEndpoint == ApiEndpoints.MOCK_MODE) {
            // Disable network proxy if we are in mock mode.

            networkLoggingView.setEnabled(false);
        } else {
            // Disable network controls if we are not in mock mode.
            networkDelayView.setEnabled(false);
            networkVarianceView.setEnabled(false);
            networkErrorView.setEnabled(false);
        }

        // We use the JSON rest adapter as the source of truth for the log level.
        final EnumAdapter<LogLevel> loggingAdapter = new EnumAdapter<>(drawerContext, LogLevel.class);
        networkLoggingView.setAdapter(loggingAdapter);
        networkLoggingView.setSelection(restAdapter.getLogLevel().ordinal());
        networkLoggingView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                LogLevel selected = loggingAdapter.getItem(position);
                if (selected != restAdapter.getLogLevel()) {
                    Log.d(TAG, String.format("Setting logging level to %s", selected));
                    restAdapter.setLogLevel(selected);
                } else {
                    Log.d(TAG, String.format("Ignoring re-selection of logging level " + selected));
                }
            }

            @Override public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void setupBuildSection() {
        buildNameView.setText(BuildConfig.VERSION_NAME);
        buildCodeView.setText(String.valueOf(BuildConfig.VERSION_CODE));
        buildShaView.setText(BuildConfig.GIT_SHA);

        try {
            // Parse ISO8601-format time into local time.
            DateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
            inFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date buildTime = inFormat.parse(BuildConfig.BUILD_TIME);
            buildDateView.setText(DATE_DISPLAY_FORMAT.format(buildTime));
        } catch (ParseException e) {
            throw new RuntimeException("Unable to decode build time: " + BuildConfig.BUILD_TIME, e);
        }
    }

    private void setupDeviceSection() {
        DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
        String densityBucket = getDensityString(displayMetrics);
        deviceMakeView.setText(Strings.truncateAt(Build.MANUFACTURER, 20));
        deviceModelView.setText(Strings.truncateAt(Build.MODEL, 20));
        deviceResolutionView.setText(displayMetrics.heightPixels + "x" + displayMetrics.widthPixels);
        deviceDensityView.setText(displayMetrics.densityDpi + "dpi (" + densityBucket + ")");
        deviceReleaseView.setText(Build.VERSION.RELEASE);
        deviceApiView.setText(String.valueOf(Build.VERSION.SDK_INT));
    }

    private static String getDensityString(DisplayMetrics displayMetrics) {
        switch (displayMetrics.densityDpi) {
            case DisplayMetrics.DENSITY_LOW:
                return "ldpi";
            case DisplayMetrics.DENSITY_MEDIUM:
                return "mdpi";
            case DisplayMetrics.DENSITY_HIGH:
                return "hdpi";
            case DisplayMetrics.DENSITY_XHIGH:
                return "xhdpi";
            case DisplayMetrics.DENSITY_XXHIGH:
                return "xxhdpi";
            case DisplayMetrics.DENSITY_XXXHIGH:
                return "xxxhdpi";
            case DisplayMetrics.DENSITY_TV:
                return "tvdpi";
            default:
                return "unknown";
        }
    }

    private void showCustomEndpointDialog(final int originalSelection, String defaultUrl) {
        View view = LayoutInflater.from(app).inflate(R.layout.debug_drawer_network_endpoint, null);
        final EditText url = findById(view, R.id.debug_drawer_network_endpoint_url);
        url.setText(defaultUrl);
        url.setSelection(url.length());

        new AlertDialog.Builder(drawerContext) //
                .setTitle("Set Network Endpoint")
                .setView(view)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialog, int i) {
                        endpointView.setSelection(originalSelection);
                        dialog.cancel();
                    }
                })
                .setPositiveButton("Use", new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialog, int i) {
                        String theUrl = url.getText().toString();
                        if (!TextUtils.isEmpty(theUrl)) {
                            setEndpointAndRelaunch(theUrl);
                        } else {
                            endpointView.setSelection(originalSelection);
                        }
                    }
                })
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override public void onCancel(DialogInterface dialogInterface) {
                        endpointView.setSelection(originalSelection);
                    }
                })
                .show();
    }

    private void setEndpointAndRelaunch(String endpoint) {
        Log.d(TAG, String.format("Setting network endpoint to %s", endpoint));
        networkEndpoint.set(endpoint);

        Intent newApp = new Intent(app, MainActivity.class);
        newApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        app.startActivity(newApp);
        App.get(app).buildObjectGraphAndInject();
    }

}
