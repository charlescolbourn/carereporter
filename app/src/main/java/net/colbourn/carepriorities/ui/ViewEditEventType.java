package net.colbourn.carepriorities.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.maltaisn.icondialog.IconDialog;

import net.colbourn.carepriorities.R;

// TODO   https://stackoverflow.com/questions/50676100/program-type-already-present-android-support-v4-os-resultreceivermyresultrecei

public class ViewEditEventType extends AppCompatActivity {

    private static final String ICON_DIALOG_TAG = "icon-dialog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_type_edit);


//        IconDialog dialog = (IconDialog) getSupportFragmentManager().findFragmentByTag(ICON_DIALOG_TAG);
//        IconDialog iconDialog = dialog != null ? dialog
//                : IconDialog.newInstance(new IconDialogSettings.Builder().build());
//
//        Button btn = findViewById(R.id.btn_open_dialog);
//        btn.setOnClickListener(v -> {
//            // Open icon dialog
//            iconDialog.show(getSupportFragmentManager(), ICON_DIALOG_TAG);
//        });
    }


//    @Nullable
//    @Override
//    public IconPack getIconDialogIconPack() {
//        return ((App) getApplication()).getIconPack();
//    }
//
//    @Override
//    public void onIconDialogIconsSelected(@NotNull IconDialog dialog, @NotNull List<Icon> icons) {
//        // Show a toast with the list of selected icon IDs.
//        StringBuilder sb = new StringBuilder();
//        for (Icon icon : icons) {
//            sb.append(icon.getId());
//            sb.append(", ");
//        }
//        sb.delete(sb.length() - 2, sb.length());
//        Toast.makeText(this, "Icons selected: " + sb, Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onIconDialogCancelled() {}
//
//
//    @Nullable
//    private IconPack iconPack;
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//
//        // Load the icon pack on application start.
//        loadIconPack();
//    }
//
//    @Nullable
//    public IconPack getIconPack() {
//        return iconPack != null ? iconPack : loadIconPack();
//    }
//
//    private IconPack loadIconPack() {
//        // Create an icon pack loader with application context.
//        IconPackLoader loader = new IconPackLoader(this);
//
//        // Create an icon pack and load all drawables.
//        iconPack = IconPackDefault.createDefaultIconPack(loader);
//        iconPack.loadDrawables(loader.getDrawableLoader());
//
//        return iconPack;
//    }

}
